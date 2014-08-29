package eu.atos.sla.enforcement.checking;

import java.util.Date;

import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.IGuaranteeTerm;
import eu.atos.sla.datamodel.IPolicy;

/**
 * @deprecated
 * @author rsosa
 *
 */
public interface IPolicyChecker {

	/**
	 * Check if a violation has been generated for a (ServiceLevelObjective, Policy) 
	 */
	IAgreementChecker.ResultItem calculateViolations(IAgreement contract, IGuaranteeTerm term,
			String kpiName, IPolicy policy, String constraint, Date lastExecution, Date now);
	
	
}
