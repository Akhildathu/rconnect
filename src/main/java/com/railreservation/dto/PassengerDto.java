package com.railreservation.dto;
public class PassengerDto {
    private int passengerNo;
    private String passengerName;
    private int age;
    private String gender;


	public PassengerDto(int passengerNo, String passengerName, int age, String gender) {
		this.passengerNo = passengerNo;
		this.passengerName = passengerName;
		this.age = age;
		this.gender = gender;	
	}

    public int getPassengerNo() {
		return passengerNo;
	}
	public void setPassengerNo(int passengerNo) {
		this.passengerNo = passengerNo;
	}
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
}