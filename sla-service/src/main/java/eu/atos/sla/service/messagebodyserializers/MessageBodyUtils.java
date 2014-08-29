package eu.atos.sla.service.messagebodyserializers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class MessageBodyUtils {
	private static Logger logger = Logger.getLogger(MessageBodyUtils.class);
	// convert InputStream to String
	static protected String getStringFromInputStream(InputStream is) {
 
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
 
		String line;
		try {
 
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
 
		} catch (IOException e) {
			logger.fatal(e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.fatal(e);
				}
			}
		}
 
		return sb.toString();
 
	}
 
}
