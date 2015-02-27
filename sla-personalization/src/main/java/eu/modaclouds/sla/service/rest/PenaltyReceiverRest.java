package eu.modaclouds.sla.service.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Path("/accounting")
@Component
@Scope("request")
@Transactional
public class PenaltyReceiverRest extends AbstractSLARest {
	private static Logger logger = LoggerFactory.getLogger(PenaltyReceiverRest.class);

	@GET
	public Response getRoot() {
	
		return buildResponse(HttpStatus.NOT_FOUND, "Valid method is POST /");
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public Response postPenalty(String penalty) {
		
		logger.debug(penalty);
		return buildResponse(HttpStatus.OK, "Penalty received");
	}

}
