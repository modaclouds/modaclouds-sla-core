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
package eu.modaclouds.sla.notification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.jersey.core.util.UnmodifiableMultivaluedMap;

import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.ICompensation;
import eu.atos.sla.datamodel.IGuaranteeTerm;
import eu.atos.sla.datamodel.bean.Penalty;
import eu.atos.sla.evaluation.guarantee.GuaranteeTermEvaluator.GuaranteeTermEvaluationResult;
import eu.atos.sla.notification.IAgreementEnforcementNotifier;

public class RestNotifier implements IAgreementEnforcementNotifier {
	private static final Logger logger = LoggerFactory.getLogger(RestNotifier.class);

	public static final MultivaluedMap<String, String> EMPTY_MAP = 
			new UnmodifiableMultivaluedMap<String, String>(new MultivaluedMapImpl());
	
	private final String url;
	private final MultivaluedMap<String, String> headersMap = new MultivaluedMapImpl();
	private final Client client;
	private final WebResource resource;
	
	
	public RestNotifier(String url, String contentType) {
		this(url, contentType, "", "");
	}
	
	public RestNotifier(String url, String contentType, String authUser, String authPassword) {
		this.url = url;
		headersMap.add("Content-type", contentType);

		if (url == null || "".equals(url)) {
			this.client = null;
			this.resource = null;
		}
		else {
			this.client = getClient(authUser, authPassword);
			this.resource = client.resource(UriBuilder.fromUri(url).build());
		}
	}

	private Client getClient(String user, String password) {
		ClientConfig config;
		Client client;

		config = new DefaultClientConfig();
		client = Client.create(config);

		if (!"".equals(user)) {
			ClientFilter filter = new HTTPBasicAuthFilter(user, password);
			client.addFilter(filter);
			
		}
		return client;
	}
	
	@Override
	public void onFinishEvaluation(
			IAgreement agreement,
			Map<IGuaranteeTerm, GuaranteeTermEvaluationResult> guaranteeTermEvaluationMap) {

		if (this.resource == null) {
			return;
		}
		
		List<Object> data = new ArrayList<Object>();
		for (GuaranteeTermEvaluationResult o : guaranteeTermEvaluationMap.values()) {
			for (ICompensation compensation : o.getCompensations()) {
				Object item = serialize(compensation);
				data.add(item);
				send(item);
			}
		}
//		send(data);
	}

	private void send(Object data) {
		ClientResponse response = method(HttpMethod.POST, "", data, EMPTY_MAP, headersMap);
		
		int status = response.getStatus();
		logger.debug("Compensations notified. Status={}", status);
		if (!isOk(status)) {
			throw new RestNotifierException(status, response.getEntity(String.class));
		}
	}
	
	/**
	 * Quick and easy serialization from a model compensation to a jaxb compensation
	 * @param compensation model compensation to serialize from
	 * @return corresponding jaxb object
	 */
	private Object serialize(ICompensation compensation) {
		Object result;
		if (compensation instanceof Penalty) {
			result = new eu.atos.sla.parser.data.Penalty((Penalty)compensation);
		}
		else {
			throw new UnsupportedOperationException("Not implemented");
		}
		return result;
	}
	
	/**
	 * Executes a method.
	 * 
	 * @param method method name (one of HttpMethod.*)
	 * @param relativeUrl the final url is baseUrl/relativeUrl?queryparam1=queryvalue1&...
	 * @param data in the request body: a string, a jaxb annotated pojo, etc.
	 * @param queryParams MultivaluedMap of query parameters
	 * @param headers MultivaluedMap of headers
	 * @return A {@link RequestResponse} with the response (getStatus() to check
	 *         status; getEntity() to get body)
	 */
	private ClientResponse method(String method, String relativeUrl,
			Object data, MultivaluedMap<String, String> queryParams,
			MultivaluedMap<String, String> headers) {

		WebResource finalResource = resource.path(relativeUrl).queryParams(queryParams);

		WebResource.Builder builder = finalResource.getRequestBuilder();

		builder = applyHeaders(builder, headers);

		ClientResponse response = builder.method(method, ClientResponse.class, data);

		return response;
	}
	
	private WebResource.Builder applyHeaders(WebResource.Builder builder, MultivaluedMap<String, String> headers) {

		for (String key : headers.keySet()) {
			for (String value : headers.get(key)) {
				builder = builder.header(key, value);
			}
		}
		return builder;
	}
	
	public String getUrl() {
		return url;
	}

	public boolean isOk(int statusCode) {
		return statusCode >= 200 && statusCode < 300;
	}

	public static class RestNotifierException extends RuntimeException {
		private static final long serialVersionUID = 1L;
		
		private String body;
		private int status;
		public RestNotifierException(int status, String body) {
			super(body);
			this.body = body;
			this.status = status;
		}
		
		public String getBody() {
			return body;
		}
		
		public int getStatus() {
			return status;
		}
	}
}
