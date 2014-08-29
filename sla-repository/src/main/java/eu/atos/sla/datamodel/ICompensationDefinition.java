package eu.atos.sla.datamodel;

import java.util.Date;

/**
 * This element expresses the reward or penalty to be assessed for meeting (or not) and objetive.
 *
 */
public interface ICompensationDefinition {

	public static enum CompensationKind {
		REWARD, 
		PENALTY, 
		UNKNOWN
	}
	
	public interface IPenaltyDefinition extends ICompensationDefinition {
		
	}
	
	public interface IRewardDefinition extends ICompensationDefinition {
		
	}
	

	/*
	 * Internally generated id
	 */
	Long getId();

	/**
	 * Type of compensation: reward or penalty.
	 */
	CompensationKind getKind();
	
	/**
	 * When present, defines the assessment interval as a duration.
	 * 
	 * One of timeInterval or count MUST be specified. 
	 */
	Date getTimeInterval();
	
	/**
	 * When present, defines the assessment interval as a service specific count, such as
	 * number of invocations. 
	 * 
	 * One of timeInterval or count MUST be specified. 
	 */
	Integer getCount();
	
	/**
	 * Optional element that defines the unit for assessing penalty, such as USD.
	 */
	String getValueUnit();

	/**
	 * This element defines the assessment amount, which can be an integer, a float or an 
	 * arbitrary domain-specific expression.
	 */
	String getValueExpression();

}
