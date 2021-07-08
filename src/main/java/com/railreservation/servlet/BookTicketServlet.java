package com.railreservation.servlet;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import com.railreservation.dto.*;
import java.text.*;
import com.railreservation.dao.*;

public class BookTicketServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String sDate = null;
		TicketDto ticket = null;
		TicketDao ticketDao = null;
		SimpleDateFormat sdf = null;
		String[] passengerNames = null;
		PassengerDto passengerDto = null;
		List<PassengerDto> passengers = null;

		try {

			ticket = new TicketDto();
			ticket.setSource(request.getParameter("source"));
			ticket.setDestination(request.getParameter("destination"));
			sDate = request.getParameter("journeyDate");
			sdf = new SimpleDateFormat("dd/MM/yyyy");
			ticket.setJourneyDate(sdf.parse(sDate));
			ticket.setAmount(Double.parseDouble(request.getParameter("amount")));
			ticket.setContactNo(request.getParameter("contactNo"));

			passengers = new ArrayList<PassengerDto>();

			passengerNames = request.getParameterValues("passengerName");
			for (int i = 0; i < passengerNames.length; i++) {
				if (passengerNames[i] != null && passengerNames[i].trim().length() > 0) {
					passengerDto = new PassengerDto(i, passengerNames[i],
							Integer.parseInt(request.getParameterValues("age")[i]),
							request.getParameterValues("gender")[i]);
					passengers.add(passengerDto);
				}
			}
			ticket.setPassengers(passengers);
			ticketDao = new TicketDao();
			int ticketNo = ticketDao.saveTicket(ticket);

			PrintWriter out = response.getWriter();
			out.println("<HTML><HEAD><TITLE>Ticket Details</TITLE></HEAD><BODY>");
			out.println("<p style='border: 1px;border-style: solid; color: red;'>Your ticket no : " + ticketNo
					+ " use this for travel</p>");
			out.println("</BODY></HTML>");
			out.close();
		} catch (Exception e) {
			throw new ServletException(e);
		}

	}
}
