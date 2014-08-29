package eu.atos.sla.datamodel;

import java.util.Date;

import eu.atos.sla.datamodel.ICompensationDefinition.IPenaltyDefinition;

public interface ICompensation {

	public interface IPenalty extends ICompensation {
		
		IPenaltyDefinition getDefinition();
	}
	
	public interface IReward extends ICompensation {
		
	}

	/*
	 * Internal generated ID
	 */
	Long getId();

	/**
	 * Internal generated UUID. The interested external parties are going to
	 * identify this violation by the UUID.
	 */
	String getUuid();

	/**
	 * AgreementId where this compensation has been enforced.
	 */
	String getAgreementId();

	/**
	 * Date and time when the compensation was raised.
	 */
	Date getDatetime();

	/*
	 * TODO: finish
	 */
//	/**
//	 * If not null, violation that generated this penalty; otherwise, this is a reward.
//	 */
//	IViolation getViolation();
	
//	/**
//	 * Compensation info
//	 */
//	ICompensationDefinition getDefinition();
}
