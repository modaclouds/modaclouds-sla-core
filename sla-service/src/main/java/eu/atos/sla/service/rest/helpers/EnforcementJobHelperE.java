package eu.atos.sla.service.rest.helpers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.atos.sla.dao.IAgreementDAO;
import eu.atos.sla.dao.IEnforcementJobDAO;
import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.IEnforcementJob;
import eu.atos.sla.enforcement.IEnforcementService;
import eu.atos.sla.parser.data.EnforcementJob;
import eu.atos.sla.service.rest.helpers.exception.DBExistsHelperException;
import eu.atos.sla.service.rest.helpers.exception.DBMissingHelperException;
import eu.atos.sla.service.rest.helpers.exception.InternalHelperException;
import eu.atos.sla.util.IModelConverter;
import eu.atos.sla.util.ModelConversionException;

/**
 * 
 * @author Elena Garrido
 */

@Service
@Transactional
public class EnforcementJobHelperE{
	private static Logger logger = Logger.getLogger(EnforcementJobHelperE.class);

	
	@Autowired
	private IAgreementDAO agreementDAO;

	@Autowired
	private IEnforcementJobDAO enforcementJobDAO;
	
	@Autowired
	private IEnforcementService enforcementService;
	
	@Autowired
	IModelConverter modelConverter;

	public EnforcementJobHelperE() {
	}



	private boolean doesEnforcementExistInRepository(String agreementId) {
		return enforcementJobDAO.getByAgreementId(agreementId) != null;
	}


	public List<EnforcementJob> getEnforcements() {
		logger.debug("StartOf getEnforcements");
		List<IEnforcementJob> storedEnforcementJobs  = this.enforcementJobDAO.getAll();
		List<EnforcementJob> enforcementJobs = new ArrayList<EnforcementJob>(); 
		for (IEnforcementJob storedEnforcementJob:storedEnforcementJobs) enforcementJobs.add(modelConverter.getEnforcementJobXML(storedEnforcementJob));
		logger.debug("EndOf getEnforcements");
		return enforcementJobs;
	}

	public EnforcementJob getEnforcementJobByUUID(String agreementUUID) {
		logger.debug("StartOf getEnforcementJobByUUID uuid:"+agreementUUID);
		EnforcementJob enforcementJob = null;
		IEnforcementJob storedEnforcementJob = this.enforcementJobDAO.getByAgreementId(agreementUUID);
		if (storedEnforcementJob!=null)
			enforcementJob  = modelConverter.getEnforcementJobXML(storedEnforcementJob);
		logger.debug("EndOf getEnforcementJobByUUID");
		return enforcementJob;
	}


	public boolean startEnforcementJob(String agreementUUID){
		logger.debug("startEnforcementJob agreementId:"+agreementUUID);
		return enforcementService.startEnforcement(agreementUUID);
	}

	public boolean stopEnforcementJob(String agreementUuid){
		logger.debug("stopEnforcementJob agreementUuid:"+agreementUuid);
		return enforcementService.stopEnforcement(agreementUuid);
	}

	public String createEnforcementJob(String collectionUri, EnforcementJob enforcementJobXML)
			throws DBExistsHelperException, InternalHelperException, DBMissingHelperException {
		logger.debug("StartOf createEnforcementJob");
		IEnforcementJob enforcementJob = null;
		IEnforcementJob stored = null;
		String location = null;
	
		try {
			if (enforcementJobXML != null) {
				if (!doesEnforcementExistInRepository(enforcementJobXML.getAgreementId())) {
					// the enforcement doesn't eist
					enforcementJob = modelConverter.getEnforcementJobFromEnforcementJobXML(enforcementJobXML);
					IAgreement agreement = agreementDAO.getByAgreementId(enforcementJobXML.getAgreementId());
					if (agreement == null)
						throw new DBMissingHelperException("Agreement with id:"
								+ enforcementJobXML.getAgreementId()
								+ " doesn't exists in the SLA Repository Database. No enforcement job could be started");
					stored = enforcementService.createEnforcementJob(enforcementJob);
				} else {
					throw new DBExistsHelperException("Enforcement with id:"
							+ enforcementJobXML.getAgreementId()
							+ " already exists in the SLA Repository Database");
				}
			}
		
			if (stored != null) {
				location = buildResourceLocation(collectionUri, stored.getAgreement().getAgreementId());
				logger.debug("EndOf createEnforcementJob");
				return location;
			} else {
				logger.debug("EndOf createEnforcementJob");
				throw new InternalHelperException("Error when creating enforcementJob the SLA Repository Database");
			}
		} catch (ModelConversionException e) {
			logger.fatal("createEnforcementJob error:",e);
			throw new InternalHelperException(e.getMessage());
		}

	}
	private static final String PATH_SEP = "/";

	private String buildResourceLocation(String collectionUri, String resourceId) {
		String result;
		if (collectionUri.endsWith(PATH_SEP)) {
			result = collectionUri + resourceId;
		}
		else {
			result = collectionUri + PATH_SEP + resourceId;
		}
		return result;
	}

}
