package com.railreservation.dao;

import java.sql.*;
import java.io.*;
import java.util.*;
import com.railreservation.dto.*;

public class TicketDao {
	public int saveTicket(TicketDto ticketDto) throws SQLException, ClassNotFoundException, IOException {
		Connection con = null;
		PreparedStatement ticketStatement = null;
		PreparedStatement passengerStatement = null;
		boolean flag = false;
		ResultSet rs = null;
		int ticketNo = 0;
		try {
			con = getConnection();
			ticketStatement = con.prepareStatement(
					"insert into ticket(source, destination, journey_dt, amount, primary_contact_no) values(?,?,?,?,?)",
					new String[] { "ticket_no" });
			ticketStatement.setString(1, ticketDto.getSource());
			ticketStatement.setString(2, ticketDto.getDestination());
			ticketStatement.setDate(3, new java.sql.Date(ticketDto.getJourneyDate().getTime()));
			ticketStatement.setDouble(4, ticketDto.getAmount());
			ticketStatement.setString(5, ticketDto.getContactNo());
			ticketStatement.executeUpdate();
			rs = ticketStatement.getGeneratedKeys();
			if (rs.next()) {
				ticketNo = rs.getInt(1);
			}

			passengerStatement = con
					.prepareStatement("insert into passengers(passenger_no, passenger_nm, age, gender) values(?,?,?,?)");

			for (PassengerDto dto : ticketDto.getPassengers()) {
				passengerStatement.setInt(1, dto.getPassengerNo());
				passengerStatement.setString(2, dto.getPassengerName());
				passengerStatement.setInt(3, dto.getAge());
				passengerStatement.setString(4, dto.getGender());
				passengerStatement.executeUpdate();
			}
			flag = true;

		} finally {
			if (con != null) {
				if (flag) {
					con.commit();
				} else {
					con.rollback();
				}
				con.close();
			}
			if (ticketStatement != null) {
				ticketStatement.close();
			}
			if (passengerStatement != null) {
				passengerStatement.close();
			}

		}
		return ticketNo;

	}

	private Connection getConnection() throws SQLException, ClassNotFoundException, IOException {
		Connection con = null;
		Properties props = new Properties();
		props.load(this.getClass().getClassLoader().getResourceAsStream("com/rconnect/db.properties"));

		Class.forName(props.getProperty("db.driverClassName"));
		con = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.username"),
				props.getProperty("db.password"));
		con.setAutoCommit(false);
		return con;
	}
	
	public List<TicketDto> getTickets() throws SQLException, ClassNotFoundException, IOException{
		List<TicketDto> tickets = new ArrayList<>();
		Connection con = null;
		PreparedStatement ticketStatement = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			ticketStatement = con.prepareStatement("select * from ticket");
			rs = ticketStatement.executeQuery();
			while(rs.next()) {
				TicketDto dto = new TicketDto();
				dto.setTicketNo(rs.getInt("ticket_no"));
				dto.setContactNo(rs.getNString("primary_contact_no"));
				dto.setSource(rs.getString("source"));
				dto.setDestination(rs.getString("destination"));
				dto.setJourneyDate(rs.getDate("journey_dt"));
				dto.setAmount(rs.getDouble("amount"));
				tickets.add(dto);
			}
		} finally {
			if (con != null) {
				con.close();
			}
			if (ticketStatement != null) {
				ticketStatement.close();
			}
		}
		return tickets;
	}
}