package eu.atos.sla.parser;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

import org.apache.log4j.Logger;

public class ValidationHandler implements ValidationEventHandler {
	private static Logger logger = Logger.getLogger(ValidationHandler.class);

	@Override
	public boolean handleEvent(ValidationEvent validationEvent) {
		if (validationEvent.getLinkedException()==null){
			logger.warn("detected " +validationEvent.getMessage() +"  it will be ignored");			
			return true;
		}
		return false;
	}

}
