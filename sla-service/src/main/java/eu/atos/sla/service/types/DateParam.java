package eu.atos.sla.service.types;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/*
 * http://programmers.stackexchange.com/questions/138391/should-i-use-the-date-type-in-jax-rs-pathparam
 * 
 * Be careful with the time zone. If the date is not interpreted with timezone (i.e. blablaZ+0000),
 * the date is parsed with local timezone, wich may not be the expected result. The timezone spec of SimpleDateFormat
 * is not iso8601 compliant, so a replacing could be madre, or not using SimpleDateFormat at all, but 
 * javax.xml.bind.DatatypeConverter.parseDateTime("2010-01-01T12:00:00Z")
 * 
 */

public class DateParam {
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); 
	private final Calendar calendar;

	public static DateParam valueOf(String dateStr) {
	
		return new DateParam(dateStr);
	}

	public static Date getDate(DateParam instance) {

		return instance == null? null : instance.getDate();
	}
	
	public DateParam(String dateStr) throws WebApplicationException {
		if ("".equals(dateStr)) {
			this.calendar = null;
			return;
		}
	
		
		
		try {
			/*
			 * SimpleDateFormat is not thread-safe. 
			 */
			synchronized (dateFormat) {
				dateFormat.parse(dateStr);
				calendar = dateFormat.getCalendar();
			}
		} catch (ParseException e) {
			throw new WebApplicationException(Response
					.status(Status.BAD_REQUEST)
					.entity("Couldn't parse date string: " + e.getMessage())
					.build());
		}
	}

	public Date getDate() {
		return calendar == null? null : calendar.getTime();
	}
	
	public Calendar getCalendar() {
		return calendar;
	}
}
