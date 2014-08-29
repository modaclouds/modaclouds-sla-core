package eu.atos.sla.service.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import eu.atos.sla.parser.data.ErrorResponse;

public class ExceptionHandlerUtils {
	private static Logger logger = Logger.getLogger(ExceptionHandlerUtils.class);

	
	static protected Response buildResponse(Status status, Exception e) {
		int code = status.getStatusCode();
		
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setCode(code);
		errorResponse.setMessage(e.getMessage());
		logger.info("Sending error "+code+" - "+e.getMessage()+ " - "+status.getReasonPhrase());
		return  Response
				.status(status)
				.entity(errorResponse)
				.build();
	}
	
}
