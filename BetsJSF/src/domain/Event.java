package domain;

import java.util.Date;

public class Event {
	private int id;
	private String eventDescription;
	private Date eventDate;
	public Event(int id, String eventDescription, Date eventDate) {
		super();
		this.id = id;
		this.eventDescription = eventDescription;
		this.eventDate = eventDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	
}
