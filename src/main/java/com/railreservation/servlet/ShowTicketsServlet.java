package com.railreservation.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.railreservation.dao.TicketDao;
import com.railreservation.dto.TicketDto;

@SuppressWarnings("serial")
public class ShowTicketsServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TicketDao ticketDao = new TicketDao();
		try {
			List<TicketDto> tickets = ticketDao.getTickets();
			
			req.setAttribute("tickets", tickets);
			req.getRequestDispatcher("/WEB-INF/jsp/show-tickets.jsp").forward(req, resp);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			throw new ServletException(e);
		}
	}

}
