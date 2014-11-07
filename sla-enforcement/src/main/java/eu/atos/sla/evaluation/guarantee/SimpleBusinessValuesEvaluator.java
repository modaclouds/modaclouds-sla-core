package eu.atos.sla.evaluation.guarantee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.IBusinessValueList;
import eu.atos.sla.datamodel.ICompensation;
import eu.atos.sla.datamodel.ICompensation.IPenalty;
import eu.atos.sla.datamodel.ICompensationDefinition.IPenaltyDefinition;
import eu.atos.sla.datamodel.IGuaranteeTerm;
import eu.atos.sla.datamodel.IViolation;
import eu.atos.sla.datamodel.bean.Penalty;

/**
 * BusinessValuesEvaluator that raises a penalty if the number of violations found match the 
 * count in the penalty definition.
 * 
 * @author rsosa
 */
public class SimpleBusinessValuesEvaluator implements IBusinessValuesEvaluator {
	private static Logger logger = LoggerFactory.getLogger(SimpleBusinessValuesEvaluator.class);
	
	@Override
	public List<? extends ICompensation> evaluate(
			IAgreement agreement,
			IGuaranteeTerm term, List<IViolation> violations) {
		
		logger.debug("Evaluating business for {} new violations", violations.size());
		List<ICompensation> result = new ArrayList<ICompensation>();
		IBusinessValueList businessValues = term.getBusinessValueList();
		if (businessValues == null) {
			/*
			 * sanity check
			 */
			return Collections.emptyList();
		}
		for (IPenaltyDefinition penaltyDef : businessValues.getPenalties()) {
			
			if (violations.size() >= penaltyDef.getCount()) {
				
				IPenalty penalty = new Penalty(
						agreement.getAgreementId(), 
						getLastViolation(violations).getDatetime(),
						penaltyDef);
				result.add(penalty);
				logger.debug("Raised {}", penalty);
			}
		}
		return result;
	}
	
	private IViolation getLastViolation(List<IViolation> violations) {
		
		return violations.get(violations.size() - 1);
	}
	
}