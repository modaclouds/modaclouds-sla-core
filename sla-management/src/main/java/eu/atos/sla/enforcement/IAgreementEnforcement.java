package eu.atos.sla.enforcement;

import java.util.Date;
import java.util.List;
import java.util.Map;

import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.IGuaranteeTerm;
import eu.atos.sla.datamodel.IViolation;
import eu.atos.sla.monitoring.IMonitoringMetric;

/**
 * A class that implements IAgreementEnforcement must execute the enforcement of a given agreement 
 * (checking the metrics fulfill the service levels, raising the appropriate violations, raising the
 * appropriate business violations, and finally, saving to repository the results.
 * 
 * @author rsosa
 *
 */
public interface IAgreementEnforcement {

	/**
	 * Enforce an agreement that was last enforced at <code>since</code>. The enforcement process must retrieve
	 * the metrics since that date and validate them, raising violations if applicable.
	 * 
	 * @param agreement Agreement to enforce.
	 * @param since Last time the agreement was enforced.
	 */
	void enforce(IAgreement agreement, Date since);
	
	/**
	 * Enforce an agreement given the new metrics that has occurred since the last enforcement.
	 * @param agreement Agreement to enforce.
	 * @param metricsMap new metrics to evaluate.
	 */
	void enforce(IAgreement agreement, Map<IGuaranteeTerm, List<IMonitoringMetric>> metricsMap);
	
	/**
	 * Enforce an agreement when violations are provided by an external smart monitoring.
	 * 
	 */
	void enforceBusiness(IAgreement agreement, Map<IGuaranteeTerm, List<IViolation>> violationsMap);
}
