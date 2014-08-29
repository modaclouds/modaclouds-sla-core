package eu.atos.sla.enforcement.checking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.IGuaranteeTerm;
import eu.atos.sla.datamodel.IPolicy;
import eu.atos.sla.datamodel.bean.Policy;

/**
 * @deprecated
 */
public class AgreementChecker implements IAgreementChecker {

	private IPolicyChecker policyChecker;

	public AgreementChecker() {
	}
	
	@Override
	public List<IAgreementChecker.ResultItem> calculateViolations(
			IAgreement contract, Date lastExecuted) {
		
		List<IAgreementChecker.ResultItem> result = new ArrayList<IAgreementChecker.ResultItem>();
		
		if (contract == null || contract.getGuaranteeTerms() == null) {
			return result;
		}
		for (IGuaranteeTerm term : contract.getGuaranteeTerms()) {
			List<? extends IPolicy> policies = getPoliciesOrDefault(term);
			for (IPolicy policy : policies) {
				IAgreementChecker.ResultItem checkingResult = policyChecker
						.calculateViolations(contract, term, term.getKpiName(), 
								policy, term.getServiceLevel(), lastExecuted, new Date());
				
				if (checkingResult.getBreaches().size() > 0 || 
						checkingResult.getViolations().size() > 0) {
					result.add(checkingResult);
				}
			}
		}
		return result;
	}
	
	private List<? extends IPolicy> getPoliciesOrDefault(final IGuaranteeTerm term) {
		
		if (term.getPolicies() != null && term.getPolicies().size() > 0) {
			return term.getPolicies();
		}
		IPolicy policy = new Policy(1, null);
		
		return Collections.singletonList(policy);
	}

	public IPolicyChecker getPolicyChecker() {
		return policyChecker;
	}

	public void setPolicyChecker(IPolicyChecker policyChecker) {
		this.policyChecker = policyChecker;
	}

}
