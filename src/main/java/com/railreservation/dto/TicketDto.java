package com.railreservation.dto;
import java.util.*;

public class TicketDto {
    private int ticketNo;
	private String source;
	private String destination;
	private Date journeyDate;
	private double amount;
	private String contactNo;
    private List<PassengerDto> passengers;

    public int getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(int ticketNo) {
		this.ticketNo = ticketNo;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Date getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(Date journeyDate) {
		this.journeyDate = journeyDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getContactNo() {
		return this.contactNo;
	}
    
    public List<PassengerDto> getPassengers() {
        return this.passengers;
    }
    public void setPassengers(List<PassengerDto> passengers) {
        this.passengers = passengers;
    }
    
	
}