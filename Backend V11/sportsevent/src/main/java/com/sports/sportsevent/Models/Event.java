package com.sports.sportsevent.Models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long eventId;
	private Date eventDate;;
	private String eventName;
	private int noOfSlots;
	private String sportsName;
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Event(long eventId, Date eventDate, String eventName, int noOfSlots, String sportsName) {
		super();
		this.eventId = eventId;
		this.eventDate = eventDate;
		this.eventName = eventName;
		this.noOfSlots = noOfSlots;
		this.sportsName = sportsName;
	}
	public long getEventId() {
		return eventId;
	}
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public int getNoOfSlots() {
		return noOfSlots;
	}
	public void setNoOfSlots(int noOfSlots) {
		this.noOfSlots = noOfSlots;
	}
	public String getSportsName() {
		return sportsName;
	}
	public void setSportsName(String sportsName) {
		this.sportsName = sportsName;
	}
}
