package movieFinder.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movieFinder.model.google.calendar.GoogleCalendar;
import movieFinder.model.google.calendar.insert.End;
import movieFinder.model.google.calendar.insert.GoogleCalendarInsert;
import movieFinder.model.google.calendar.insert.Start;
import movieFinder.model.resource.GoogleCalendarResource;
import movieFinder.model.youtubevideo.Item;

@SuppressWarnings("serial")
public class CalendarController extends HttpServlet{
	private static final Logger log = Logger.getLogger(CalendarController.class.getName());
	
	 public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		 String accessToken = (String) req.getSession().getAttribute("GoogleCalendar-token");
		 String calendarId = req.getParameter("email");
		 String description = req.getParameter("description");
		 String summary = req.getParameter("summary");
		 String fecha = req.getParameter("fecha");
		 String movieID = (String) req.getParameter("movieID");
		 String sessionID = req.getParameter("sessionID");
		 req.setAttribute("title", req.getAttribute("title"));
		 req.setAttribute("estreno",req.getParameter("estreno"));
		 if (accessToken != null && !"".equals(accessToken)) {
			 	GoogleCalendarResource gr = new GoogleCalendarResource(accessToken);
			 	GoogleCalendarInsert event = new GoogleCalendarInsert();
			 	event.setDescription(description);
			 	event.setSummary(summary);
			 	Start start = new Start();
			 	start.setDate(fecha);
			 	event.setStart(start);
			 	End end = new End();
			 	end.setDate(fecha);
			 	event.setEnd(end);
				gr.insertEvent(event,calendarId);
				GoogleCalendar calendar = gr.getCalendar(calendarId);
				String message = "El evento ha sido creado con éxito";
				if (calendar != null) {
					req.setAttribute("message", message);
					req.setAttribute("movieID", movieID);
					req.setAttribute("sessionID", sessionID);
					req.setAttribute("email", calendarId);
					req.getRequestDispatcher("/resultadoEspecificoController").forward(req, resp);
				} else {
					log.info(
							"The calendar returned are null... probably your token has experied. Redirecting to OAuth servlet.");
					req.getRequestDispatcher("/AuthController/GoogleCalendar").forward(req, resp);
				}
		 }else {
			log.info("Redirecting to OAuth servlet.");
			req.getRequestDispatcher("/AuthController/GoogleCalendar").forward(req, resp);
		 }
	 }


	 public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    doGet(req, resp);
	 }

	//TODO
}
