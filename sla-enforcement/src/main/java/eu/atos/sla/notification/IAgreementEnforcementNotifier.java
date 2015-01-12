package eu.atos.sla.notification;

import java.util.Map;

import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.IGuaranteeTerm;
import eu.atos.sla.evaluation.guarantee.IGuaranteeTermEvaluator.GuaranteeTermEvaluationResult;

public interface IAgreementEnforcementNotifier {
	
	void onFinishEvaluation(IAgreement agreement, Map<IGuaranteeTerm, GuaranteeTermEvaluationResult>  guaranteeTermEvaluationMap);
}
