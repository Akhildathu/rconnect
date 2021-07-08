create schema if not exists railreservationdb;
use railreservationdb;
create table IF NOT EXISTS ticket(ticket_no integer primary key auto_increment, primary_contact_no varchar(15), source varchar(50), destination varchar(50), journey_dt date, amount float);
create table IF NOT EXISTS passengers(passenger_no integer primary key auto_increment, passenger_nm varchar(100), age integer, gender varchar(10));

