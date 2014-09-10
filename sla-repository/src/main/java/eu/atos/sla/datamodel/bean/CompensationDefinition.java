package eu.atos.sla.datamodel.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import eu.atos.sla.datamodel.ICompensationDefinition;

//@Entity
//@Table(name="compensation")
//@Access(AccessType.FIELD)
@MappedSuperclass
public abstract class CompensationDefinition implements Serializable, ICompensationDefinition {
	private static final long serialVersionUID = 1L;
	
	protected static int DEFAULT_COUNT = 0;
	protected static Date DEFAULT_INTERVAL = new Date(0);
	protected static String DEFAULT_VALUE_EXPRESSION = "";
	protected static String DEFAULT_VALUE_UNIT = "";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name="kind", nullable=false)
	@Enumerated(EnumType.STRING)
	private CompensationKind kind;

	@Column(name = "time_interval", nullable=false)
	private Date timeInterval;
	
	@Column(name="number", nullable=false)
	private int count;
	
	@Column(name="value_unit", nullable=false)
	private String valueUnit;
	
	@Column(name="value_expression", nullable=false)
	private String valueExpression;
	
	public CompensationDefinition() {
		this.kind = ICompensationDefinition.CompensationKind.UNKNOWN;
		this.timeInterval = DEFAULT_INTERVAL;
		this.count = DEFAULT_COUNT;
	}
	
	protected CompensationDefinition(CompensationKind kind, Date timeInterval,
			String valueUnit, String valueExpression) {
		
		checkNotNull(kind, "kind");
		checkNotNull(timeInterval, "timeInterval");
		checkNotNull(valueUnit, "valueUnit");
		checkNotNull(valueExpression, "valueExpression");

		this.kind = kind;
		this.timeInterval = timeInterval;
		this.valueUnit = valueUnit;
		this.valueExpression = valueExpression;

		this.count = DEFAULT_COUNT;
	}

	protected CompensationDefinition(CompensationKind kind, 
			int count, String valueUnit, String valueExpression) {	
		
		checkNotNull(kind, "kind");
		checkNotNull(valueUnit, "valueUnit");
		checkNotNull(valueExpression, "valueExpression");

		this.kind = kind;
		this.count = count;
		this.valueUnit = valueUnit;
		this.valueExpression = valueExpression;
		
		this.timeInterval = DEFAULT_INTERVAL;
	}
	
	private void checkNotNull(Object o, String property) {
		if (o == null) {
			throw new NullPointerException(property + " cannot be null");
		}
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public CompensationKind getKind() {
		return kind;
	}

	@Override
	public Date getTimeInterval() {
		return timeInterval;
	}

	@Override
	public Integer getCount() {
		return count;
	}

	@Override
	public String getValueUnit() {
		return valueUnit;
	}

	@Override
	public String getValueExpression() {
		return valueExpression;
	}
	
	@Override
	public String toString() {
		return String.format(
				"<CompensationDefinition(kind=%s,timeInterval=%d ms,count=%d,valueUnit=%s,valueExpression=%s)>",
				kind.toString(), 
				timeInterval.getTime(), 
				count, 
				valueUnit, 
				valueExpression);
	}

//	public boolean equals(Compensation other) {
//		
//		return kind.equals(other.getKind()) && 
//				timeInterval.equals(other.getTimeInterval()) &&
//				getCount().equals(other.getCount()) &&
//				valueUnit.equals(other.getValueUnit()) &&
//				valueExpression.equals(other.getValueExpression());
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + ((kind == null) ? 0 : kind.hashCode());
		result = prime * result
				+ ((timeInterval == null) ? 0 : timeInterval.hashCode());
		result = prime * result
				+ ((valueExpression == null) ? 0 : valueExpression.hashCode());
		result = prime * result
				+ ((valueUnit == null) ? 0 : valueUnit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CompensationDefinition)) {
			return false;
		}
		CompensationDefinition other = (CompensationDefinition) obj;
		if (count != other.count) {
			return false;
		}
		if (kind != other.kind) {
			return false;
		}
		if (timeInterval == null) {
			if (other.timeInterval != null) {
				return false;
			}
		/*
		 * Direct Date compare gives a lot of problems with timezones
		 */
		} else if (timeInterval.getTime() != other.timeInterval.getTime()) {
			return false;
		}
		if (valueExpression == null) {
			if (other.valueExpression != null) {
				return false;
			}
		} else if (!valueExpression.equals(other.valueExpression)) {
			return false;
		}
		if (valueUnit == null) {
			if (other.valueUnit != null) {
				return false;
			}
		} else if (!valueUnit.equals(other.valueUnit)) {
			return false;
		}
		return true;
	}
	
	public static final ICompensationDefinition EMPTY_COMPENSATION_DEFINITION = new ICompensationDefinition() {
		
		@Override
		public String getValueUnit() {
			return DEFAULT_VALUE_UNIT;
		}
		
		@Override
		public String getValueExpression() {
			return DEFAULT_VALUE_EXPRESSION;
		}
		
		@Override
		public Date getTimeInterval() {
			return DEFAULT_INTERVAL;
		}
		
		@Override
		public CompensationKind getKind() {
			return CompensationKind.UNKNOWN;
		}
		
		@Override
		public Long getId() {
			return null;
		}
		
		@Override
		public Integer getCount() {
			return DEFAULT_COUNT;
		}
	};
	

}