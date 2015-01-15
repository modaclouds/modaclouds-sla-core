package eu.atos.sla.parser.data.wsag;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "BusinessValueList")
public class BusinessValueList {
	@XmlElement(name = "CustomBusinessValue")
	protected List<Object> customBusinessValue;

	@XmlElement(name = "Importance")
	protected Integer importance;

	/**
	 * Gets the value of the importance property.
	 */
	public Integer getImportance() {
		return importance;
	}

	/**
	 * Sets the value of the importance property.
	 */
	public void setImportance(Integer value) {
		this.importance = value;
	}
	
	/**
	 * Gets the value of the customBusinessValue property.
	 */
	public List<Object> getCustomBusinessValue() {
		if (customBusinessValue == null) {
			customBusinessValue = new ArrayList<Object>();
		}
		return this.customBusinessValue;
	}

}
