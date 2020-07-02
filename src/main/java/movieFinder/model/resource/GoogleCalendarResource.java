package movieFinder.model.resource;

import java.util.logging.Logger;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import movieFinder.model.google.calendar.GoogleCalendar;
import movieFinder.model.google.calendar.insert.GoogleCalendarInsert;

public class GoogleCalendarResource {
	private static final Logger log = Logger.getLogger(GoogleCalendarResource.class.getName());

    private final String uri = "https://www.googleapis.com/calendar/v3/calendars/";
    private String access_token;

    public GoogleCalendarResource(String access_token) {
    	this.access_token=access_token;
    }
    
    public GoogleCalendar getCalendar(String calendarId) {
        ClientResource cr = null;
        GoogleCalendar calendario = null;
        try {
            cr = new ClientResource(uri + calendarId + "?access_token=" + access_token);
            calendario = cr.get(GoogleCalendar.class);

        } catch (ResourceException re) {
            log.warning("Error when retrieving all files: " + cr.getResponse().getStatus());
        }

        return calendario;
        
    }
    
    public String insertEvent(GoogleCalendarInsert event, String calendarId) {

        ClientResource cr = null;
        String newId = null;
        try {
            cr = new ClientResource(uri + calendarId + "/events?access_token=" + access_token);
            GoogleCalendarInsert newEvent = cr.post(event, GoogleCalendarInsert.class);
            newId = newEvent.getId();
        } catch (ResourceException re) {
            log.warning("Error when inserting event: " + cr.getResponse().getStatus());
        }
        return newId;
    }
    
    public boolean deleteEvent(String eventId, String calendarId) {

        ClientResource cr = null;
        boolean result = true;
        try {
            cr = new ClientResource(uri + calendarId + "/events/" + eventId + "?access_token=" + access_token);
            cr.delete();
        } catch (ResourceException re) {
            log.warning("Error when deleting event: " + cr.getResponse().getStatus());
            result = false;
        }
        return result;

    }

}
