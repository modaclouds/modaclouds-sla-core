package eu.atos.sla.enforcement.checking;

import java.util.Date;
import java.util.List;

import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.IBreach;
import eu.atos.sla.datamodel.IGuaranteeTerm;
import eu.atos.sla.datamodel.IPolicy;
import eu.atos.sla.datamodel.IViolation;

/**
 * @deprecated
 */
public interface IAgreementChecker {

	/**
	 * A list of this represents the graph of generation of violations and breaches.
	 * 
	 * Stores the term and policy (if any) that generated the violation/breaches 
	 */
	public static class ResultItem {
		private IGuaranteeTerm term;
		private IPolicy policy;
		private List<IViolation> violations;
		private List<IBreach> breaches;
		
		public ResultItem(IGuaranteeTerm term, IPolicy policy, List<IViolation> violations,
				List<IBreach> breaches) {
			this.term = term;
			this.policy = policy;
			this.violations = violations;
			this.breaches = breaches;
		}
		
		public IGuaranteeTerm getTerm() {
			return term;
		}
		public IPolicy getPolicy() {
			return policy;
		}
		public List<IViolation> getViolations() {
			return violations;
		}
		public List<IBreach> getBreaches() {
			return breaches;
		}
	}

	List<IAgreementChecker.ResultItem> calculateViolations(IAgreement contract, Date lastExecuted);
}
