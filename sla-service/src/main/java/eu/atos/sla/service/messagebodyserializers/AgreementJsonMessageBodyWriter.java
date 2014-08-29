package eu.atos.sla.service.messagebodyserializers;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.annotation.Resource;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.parser.IParser;
import eu.atos.sla.parser.NullParser;
import eu.atos.sla.parser.ParserException;
import eu.atos.sla.parser.data.wsag.Agreement;


/**
 * 
 * @author Elena Garrido
 */

@Component
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class AgreementJsonMessageBodyWriter implements MessageBodyWriter<IAgreement> {
	private static Logger logger = Logger.getLogger(AgreementJsonMessageBodyWriter.class);
	Throwable catchedException;
	
	@Resource(name="agreementJsonParser")
	IParser<Agreement> jsonParser;
	
	private void initParser() {
		if (jsonParser instanceof NullParser) jsonParser=null;		
	}
	
	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		initParser();
		boolean isUsed =  (genericType == IAgreement.class) && (mediaType.toString().equals(MediaType.APPLICATION_JSON)) && jsonParser!=null ;
		if (isUsed)
			logger.debug("isWriteable:"+isUsed+ " -->type:"+type+ " genericType:"+genericType+ " mediaType:"+mediaType+ " with parser:"+jsonParser);
		return isUsed;
	}
	
	byte[] serializedData = null;
	@Override
	public long getSize(IAgreement agreement, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		if (agreement.getText()!=null){
			try {
				String jsonData = jsonParser.getSerializedData(agreement.getText());
				logger.info("jsonData "+jsonData);
				serializedData = jsonData.getBytes();
				return serializedData.length;
			} catch (ParserException e) {
				logger.fatal(e.getMessage());
				catchedException = e;
			} catch (Throwable e) {
				logger.fatal(e.getMessage());
				catchedException = e;
			}
		}else {
	    	logger.fatal("Error marshalling data agreement text is null");
    		throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return 0;
	}

	@Override
	public void writeTo(IAgreement agreement, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> multivaluedMap, OutputStream entityStream)
			throws IOException, WebApplicationException {
		logger.info("writeTo " );
		if (catchedException!=null) {
			throw new WebApplicationException(catchedException, Response.Status.INTERNAL_SERVER_ERROR);
		}else
			entityStream.write(serializedData);
		
	}
}
