package eu.atos.sla.evaluation.guarantee;

import java.util.Date;
import java.util.List;

import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.ICompensation;
import eu.atos.sla.datamodel.IGuaranteeTerm;
import eu.atos.sla.datamodel.IViolation;
import eu.atos.sla.monitoring.IMonitoringMetric;

/**
 * A GuaranteeTermEvaluator performs the evaluation of a guarantee term, consisting in:
 * <ul>
 * <li>A service level evaluation, assessing which metrics are violations.
 * <li>A business evaluation, assessing what penalties are derived from the raised violations.
 * </ul>
 *
 * @see IServiceLevelEvaluator
 * @see IBusinessValuesEvaluator
 * 
 * @author rsosa
 *
 */
public interface IGuaranteeTermEvaluator {
	
	/**
	 * Evaluate violations and penalties for a given guarantee term and a list of metrics.
	 * 
	 * @param agreement that contains the term to evaluate
	 * @param term guarantee term to evaluate
	 * @param metrics list of metrics to evaluated if fulfill the service level of the term.
	 * @param now the evaluation period ends at <code>now</code>.
	 * @return
	 */
	GuaranteeTermEvaluationResult evaluate(
			IAgreement agreement, IGuaranteeTerm term, List<IMonitoringMetric> metrics, Date now);

	/**
	 * Result of the guarantee term evaluation
	 */
	public interface GuaranteeTermEvaluationResult {
		
		List<IViolation> getViolations();
		List<? extends ICompensation> getCompensations();
	}
}

