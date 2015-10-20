/**
 * Copyright 2015 Atos
 * Contact: Atos <roman.sosa@atos.net>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package eu.modaclouds.sla.service.rest;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import eu.atos.sla.parser.data.Penalty;
import eu.modaclouds.sla.notification.MailSender;

@Path("/accounting")
@Component
@Scope("request")
@Transactional
public class PenaltyReceiverRest extends AbstractSLARest {
	private static Logger logger = LoggerFactory.getLogger(PenaltyReceiverRest.class);

	@Resource
	MailSender sender;
	
	@Value("${MODACLOUDS_MAIL_TO}")
	private String recipient;
	
	@GET
	public Response getRoot() {
	
		return buildResponse(HttpStatus.NOT_FOUND, "Valid method is POST /");
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public Response postPenalty(Penalty penalty) {
		
		logger.debug(penalty.toString());

		if ("MAIL".equalsIgnoreCase(penalty.getDefinition().getType())) {
			sendMail(penalty);
		}

		return buildResponse(HttpStatus.OK, "Penalty received");
	}

	private void sendMail(Penalty penalty) {
		
		logger.debug("sendMail({})", penalty.toString());
		eu.atos.sla.parser.data.wsag.custom.Penalty def = penalty.getDefinition();
		
		String to = this.recipient;
		String subject = def.getExpression();
		String body = String.format("Agreement: %s\nDate:%s", 
				penalty.getAgreementId(), penalty.getDatetime().toString());
		sender.send(to, subject, body);
	}
}
