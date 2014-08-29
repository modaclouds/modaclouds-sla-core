package eu.atos.sla.enforcement.checking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import eu.atos.sla.dao.IBreachDAO;
import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.IBreach;
import eu.atos.sla.datamodel.IGuaranteeTerm;
import eu.atos.sla.datamodel.IPolicy;
import eu.atos.sla.datamodel.IViolation;
import eu.atos.sla.datamodel.bean.Breach;
import eu.atos.sla.datamodel.bean.Violation;
import eu.atos.sla.evaluation.IMetricsValidator;
import eu.atos.sla.monitoring.IMetricsRetriever;
import eu.atos.sla.monitoring.IMonitoringMetric;

/**
 * @deprecated
 */
public class PolicyChecker implements IPolicyChecker {

	@Autowired
	private IBreachDAO breachRepository;
	private IMetricsRetriever metricsRetriever;
	private IMetricsValidator metricsValidator;

	private boolean isWithoutPolicy(IPolicy policy) {
		
		boolean withoutPolicy = (policy.getCount() == 1 && policy.getTimeInterval() == null);
		return withoutPolicy;
	}
	
	/**
	 * Build the servicescope to be passed as parameter to metricsRetriever. It is a combination
	 * of (term.servicename, term.servicescope)
	 */
	private String getServiceScope(IGuaranteeTerm term) {
		StringBuffer result = new StringBuffer();
		
		String name = term.getServiceName();
		String scope = term.getServiceScope();
		if (name != null && name.length() > 0) {
			result.append(name);
			result.append("/");
		}
		if (scope != null && scope.length() > 0) {
			result.append(scope);
		}
		return result.toString();
	}
	
	@Override
	public IAgreementChecker.ResultItem calculateViolations(IAgreement contract, 
			IGuaranteeTerm term, String kpiName, IPolicy policy, String constraint, 
			Date lastExecution, Date now) {

		List<IBreach> newBreaches = new ArrayList<IBreach>();
		List<IViolation> newViolations = new ArrayList<IViolation>();
		
		if (policy.getCount() != 1 && policy.getTimeInterval() == null) {
			throw new IllegalArgumentException("Invalid policy[count=" + policy.getCount() + 
					" interval=null]");
		}
		boolean withoutPolicy = isWithoutPolicy(policy);

		Date metricsBegin;
		int nBreaches;
		
		if (withoutPolicy) {
			nBreaches = 0;
			metricsBegin = lastExecution;
		}
		else {
			long policyInterval = policy.getTimeInterval().getTime();
			Date breachesBegin = new Date(now.getTime() - policyInterval);
			List<? extends IBreach> breaches = breachRepository.getByTimeRange(
					contract, kpiName, breachesBegin, now);
			nBreaches = breaches.size();
			if (nBreaches > 0) {
				System.out.println("nBreaches > 0");
			}
			metricsBegin = (nBreaches == 0)? 
					breachesBegin : new Date(now.getTime() - policyInterval);
		}

		String variable = metricsValidator.getConstraintVariable(constraint);
		String serviceScope = getServiceScope(term);
		List<IMonitoringMetric> metrics = metricsRetriever.getMetrics(
				contract.getAgreementId(), serviceScope, variable, metricsBegin, now, 1000);
		
		for (IMonitoringMetric metric : metricsValidator.getBreaches(contract, kpiName, metrics, constraint)) {
			
			if (metric == null) {
				continue;
			}

			if (withoutPolicy) {
				/*
				 * every breach is a violation
				 */
				newViolations.add(newViolation(
						contract, term, policy, kpiName, metric.getMetricValue(), 
						null, metric.getDate()));
			}
			else {
				newBreaches.add(newBreach(contract, policy, metric, kpiName));
				nBreaches++;

				if (nBreaches >= policy.getCount()) {
					/*
					 * Take this metric as violation value. As we are using anyType as metricvalue,
					 * we can't calculate a mean, for example. Maybe, this could be a provider's 
					 * task.
					 */
					newViolations.add(newViolation(
							contract, term, policy, kpiName, metric.getMetricValue(), 
							null, now));

					/*
					 * We only store one violation per task execution.
					 */
					break;
				}
			}
		}
		return new IAgreementChecker.ResultItem(
				term, withoutPolicy? null : policy, newViolations, newBreaches);
	}
	
	private Breach newBreach(final IAgreement contract, final IPolicy policy, 
			final IMonitoringMetric metric, final String kpiName) {
		
		Breach breach = new Breach();
		breach.setDatetime(metric.getDate());
		breach.setKpiName(kpiName);
		breach.setValue(metric.getMetricValue());
		breach.setAgreementUuid(contract.getAgreementId());
		
		return breach;
	}
	
	private Violation newViolation(final IAgreement contract, final IGuaranteeTerm term, 
			final IPolicy policy, final String kpiName, final String actualValue, 
			final String expectedValue, final Date timestamp) {

		Violation result = new Violation();
		result.setUuid(UUID.randomUUID().toString());
		result.setContractUuid(contract.getAgreementId());
		result.setKpiName(kpiName);
		result.setDatetime(timestamp);
		result.setExpectedValue(expectedValue);
		result.setActualValue(actualValue);
		result.setServiceName(term.getServiceName());
		result.setServiceScope(term.getServiceScope());
		result.setContractUuid(contract.getAgreementId());
		
		return result;
	}

	public IBreachDAO getBreachRepository() {
		return breachRepository;
	}

	public void setBreachRepository(IBreachDAO breachRepository) {
		this.breachRepository = breachRepository;
	}

	public IMetricsRetriever getMetricsRetriever() {
		return metricsRetriever;
	}

	public void setMetricsRetriever(IMetricsRetriever metricsRetriever) {
		this.metricsRetriever = metricsRetriever;
	}

	public IMetricsValidator getMetricsValidator() {
		return metricsValidator;
	}

	public void setMetricsValidator(IMetricsValidator metricsValidator) {
		this.metricsValidator = metricsValidator;
	}
}
