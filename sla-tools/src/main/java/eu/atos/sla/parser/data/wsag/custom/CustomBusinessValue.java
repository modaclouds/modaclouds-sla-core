package eu.atos.sla.parser.data.wsag.custom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.Duration;

/**
 * According to wsag spec, this element allows the definition of domain specific customized business values.
 * 
 * The valid format of this default implementation is:
 * <pre>
 * <code>
 * &lt;wsag:CustomBusinessValue count="xs:integer" duration="xs:duration">
 *   &lt;sla:Penalty>...&lt;/sla:Penalty>*
 *   
 * &lt;/wsag:CustomBusinessValue>
 * </code></pre>
 * Count and duration attributes are optional. If not specified, this CustomBusinessValue applies at every
 * violation. Otherwise, it applies if <code>count</code> violations occur in a <code>duration</code> interval
 * of time.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="CustomBusinessValue")
public class CustomBusinessValue {
	private static final Date START_INSTANT = new Date(0);

	@XmlAttribute(name="count")
	private Integer count = 1;
	
	@XmlAttribute(name="duration")
	private Duration duration;

	@XmlElement(name="Penalty")
	private List<Penalty> penalties;

	public Integer getCount() {
		return count;
	}
	
	public Date getDuration() {
		return (duration == null)?
				START_INSTANT:
				new Date(duration.getTimeInMillis(START_INSTANT));
	}
	
	public List<Penalty> getPenalties() {
		if (penalties == null) {
			penalties = new ArrayList<Penalty>();
		}
		return penalties;
	}
	
	@Override
	public String toString() {
		return String.format(
				"CustomBusinessValue [count=%s, duration=%s, penalties=%s]",
				count, duration, penalties);
	}
}
