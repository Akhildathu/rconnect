<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Tickets</title>
		<style>
			body {
                font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
                font-size: 16px;
            }
            h2 {
                margin-top: 0%;
                background-color:ivory ;
                color: maroon;
                text-transform: uppercase;
                letter-spacing: 2px;
                text-align: center;
                border-bottom: navy 1px solid;
            }
            table, th, td {
                border: 1px solid black;
                border-collapse: collapse;            
                padding: 5px;
            }

            th {
                background-color: bisque;
                color: brown;
                text-transform: uppercase;                
            }
            tr:nth-child(even) {
                background-color: thistle;
            }
            tr:nth-child(odd) {
                background-color: aliceblue;
            }
            tr:hover {
                background-color: darkgrey;
                color: red;
            }

            table {
                width: 75%;
                margin: auto;                
            }
            a {
            	display: block;
            	text-decoration: none;
            	margin-top 20px;
            	text-align: center;
            	text-transform: capitalize;
            	color: maroon;
            	
            }
		</style>
	</head>
	
	<body>
		<h2 style = "font-family:sans-serif;">Booked Tickets</h2>
		<table>
			<tr>
				<th>TicketNo</th>
				<th>Source</th>
				<th>Destination</th>
				<th>Journey Date</th>
				<th>Amount</th>
				<th>Contact No</th>
			</tr>
			<c:forEach items="${tickets}" var="ticket">
				<tr>
					<td>${ticket.ticketNo}</td>
					<td>${ticket.source}</td>
					<td>${ticket.destination}</td>
					<td>${ticket.journeyDate}</td>
					<td>${ticket.amount}</td>
					<td>${ticket.contactNo}</td>
				</tr>
			</c:forEach>
		</table>
		<a href="${pageContext.request.contextPath}/book-ticket.html">Book Again</a>
	</body>
</html>