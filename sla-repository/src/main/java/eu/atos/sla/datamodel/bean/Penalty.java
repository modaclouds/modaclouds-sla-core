package eu.atos.sla.datamodel.bean;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import eu.atos.sla.datamodel.ICompensation.IPenalty;
import eu.atos.sla.datamodel.ICompensationDefinition.IPenaltyDefinition;

@Entity
@Table(name="penalty")
@Access(AccessType.FIELD)
public class Penalty extends Compensation implements IPenalty {

	private static final IPenaltyDefinition DEFAULT_PENALTY = new PenaltyDefinition();
	
	@ManyToOne(targetEntity = PenaltyDefinition.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "definition_id", referencedColumnName = "id", nullable = false)
	private IPenaltyDefinition definition;
	
	public Penalty() {
		super();
		this.definition = DEFAULT_PENALTY;
	}

	public Penalty(String agreementId, Date datetime, IPenaltyDefinition definition) {
		super(agreementId, datetime, definition);
		this.definition = definition;
	}
	
	@Override
	public IPenaltyDefinition getDefinition() {
		return definition;
	}
	
	@Override
	public String toString() {
		return String.format(
				"Penalty [uuid=%s, agreementId=%s, datetime=%s, definition=%s]", 
				getUuid(), getAgreementId(), getDatetime(), definition);
	}

}
