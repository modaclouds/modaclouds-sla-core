package eu.atos.sla.evaluation.guarantee;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.ICompensation;
import eu.atos.sla.datamodel.ICompensationDefinition.IPenaltyDefinition;
import eu.atos.sla.datamodel.IGuaranteeTerm;
import eu.atos.sla.datamodel.IPolicy;
import eu.atos.sla.datamodel.IViolation;
import eu.atos.sla.datamodel.bean.PenaltyDefinition;
import eu.atos.sla.enforcement.TestAgreementFactory;

public class SimpleBusinessValuesEvaluatorTest {

	IAgreement contract;
	
	@Before
	public void setUp() throws Exception {
		
		String kpiName = "LATENCY";
		String constraint = kpiName + " LT 100";

		contract = TestAgreementFactory.newAgreement(
			Arrays.asList(
				TestAgreementFactory.newGuaranteeTerm(
					kpiName, 
					constraint,
					Arrays.<IPenaltyDefinition>asList(
						new PenaltyDefinition(1, "euro", "10"),
						new PenaltyDefinition(2, "euro", "20")
					)
				)
			)
		);
	}

	@Test
	public void testEvaluate() {
		
		SimpleBusinessValuesEvaluator bvEval = new SimpleBusinessValuesEvaluator();
		IGuaranteeTerm term = contract.getGuaranteeTerms().get(0);
		IPolicy policy = term.getPolicies().get(0);
		
		List<? extends ICompensation> compensations;
		
		compensations = bvEval.evaluate(
			contract, 
			term, 
			Arrays.<IViolation>asList(
				TestAgreementFactory.newViolation(contract, term, policy)
			)
		);
		
		assertEquals(1, compensations.size());

		compensations = bvEval.evaluate(
			contract, 
			term, 
			Arrays.<IViolation>asList(
				TestAgreementFactory.newViolation(contract, term, policy),
				TestAgreementFactory.newViolation(contract, term, policy)
			)
		);
		assertEquals(2, compensations.size());
		
	}

}
