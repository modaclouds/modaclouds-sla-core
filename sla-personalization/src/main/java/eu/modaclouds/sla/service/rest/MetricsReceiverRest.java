package eu.modaclouds.sla.service.rest;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import eu.atos.sla.dao.IAgreementDAO;
import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.IGuaranteeTerm;
import eu.atos.sla.enforcement.IEnforcementService;
import eu.atos.sla.monitoring.IMonitoringMetric;

@Path("/metrics")
@Component("modacloudsMetricsReceiverRest")
@Scope("request")
@Transactional
public class MetricsReceiverRest extends eu.atos.sla.service.rest.AbstractSLARest {
	private static Logger logger = LoggerFactory.getLogger(MetricsReceiverRest.class);

	@Autowired
	private IEnforcementService enforcementService;
	
	@Autowired
	private IAgreementDAO agreementDao;
	
	@Autowired
	private Tower4CloudsTranslator translator;
	
	@GET
	public Response getRoot() {
	
		return buildResponse(HttpStatus.NOT_FOUND, "Valid method is POST /{agreementId}");
	}
	
	@POST
	@Path("/{agreementId}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response receiveModacloudsMetrics(
			@PathParam("agreementId") String agreementId, 
			final String metrics) {

		logger.debug("receiveMetrics(agreementId=" + agreementId + ", data=" + metrics.toString());
		IAgreement agreement = agreementDao.getByAgreementId(agreementId);
		logger.debug("agreement=" + agreement.getAgreementId());
		
		Map<IGuaranteeTerm, List<IMonitoringMetric>> metricsMap = translator.translate(agreement, metrics);
		enforcementService.doEnforcement(agreement, metricsMap);
		return buildResponse(HttpStatus.ACCEPTED, "Metrics received");
	}
	
}
