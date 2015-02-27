package eu.modaclouds.sla.service.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import eu.atos.sla.dao.IAgreementDAO;
import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.service.rest.AbstractSLARest;
import eu.modaclouds.sla.mediator.ViolationSubscriber;

@Path("/modaclouds")
@Component
@Scope("request")
@Transactional
public class ModacloudsRest extends AbstractSLARest {
	private static Logger logger = LoggerFactory.getLogger(ModacloudsRest.class);
	
	@Autowired
	private IAgreementDAO agreementDAO;

	@Value("${MODACLOUDS_METRICS_URL}")
	private String metricsUrl;
	
	@Value("${MODACLOUDS_SLA_URL}")
	private String slaUrl;
	
	@PUT
	@Path("{id}/start")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response startMasterAgreement(@Context UriInfo uriInfo, @PathParam("id") String agreementId) {
		
		logger.debug("Starting {}", agreementId);
	
		IAgreement agreement = agreementDAO.getByAgreementId(agreementId);
		if (agreement == null) {
			return buildResponse(HttpStatus.NOT_FOUND, "Agreement " + agreementId + " not found");
		}
		String slaUrl = getSlaUrl(this.slaUrl, uriInfo);
		String metricsUrl = getMetricsBaseUrl("", this.metricsUrl);
		ViolationSubscriber subscriber = getSubscriber(agreement, slaUrl, metricsUrl);
	
		subscriber.subscribeObserver();
		return buildResponse(HttpStatus.ACCEPTED, "Agreement started");
	}

	private ViolationSubscriber getSubscriber(IAgreement agreement, String slaUrl, String metricsBaseUrl) {
		return new ViolationSubscriber(metricsBaseUrl, slaUrl, agreement);
	}
	
	private String getSlaUrl(String envSlaUrl, UriInfo uriInfo) {
		String baseUrl = uriInfo.getBaseUri().toString();
		
		String result = ("".equals(envSlaUrl))? baseUrl : envSlaUrl;
		logger.debug("getSlaUrl(env={}, supplied={}) = {}", envSlaUrl, baseUrl, result);
		
		return result;
	}
	
	private String getMetricsBaseUrl(String suppliedBaseUrl, String envBaseUrl) {
		
		String result = ("".equals(suppliedBaseUrl))? envBaseUrl : suppliedBaseUrl;
		logger.debug("getMetricsBaseUrl(env={}, supplied={}) = {}", envBaseUrl, suppliedBaseUrl, result);
		
		return result;
	}
}
