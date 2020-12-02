import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


import org.primefaces.event.SelectEvent;

import domain.Event;

public class testBean {
	private Date data;
	private List<Event> eventList = new ArrayList<Event>();
	private List<Event> eventListOFDate = new ArrayList<Event>();
    public List<Event> getEventListOFDate() {
		return eventListOFDate;
	}
	public void setEventListOFDate(List<Event> eventListOFDate) {
		this.eventListOFDate = eventListOFDate;
	}
	private Event selectedEvent;
    private int index = 0;
    private String text="";
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public testBean() {
		Date date;
		try {
			date = sdf.parse("2020-12-11");
			this.index +=1;
			Event event1= new Event(this.index, "Description " + this.index, date);
			this.index +=1;
			Event event2= new Event(this.index, "Description " + this.index, date);
			this.index +=1;
			Event event3= new Event(this.index, "Description " + this.index, date);
			this.index +=1;
			Event event4= new Event(this.index, "Description " + this.index, date);
			this.index +=1;
			Event event5= new Event(this.index, "Description " + this.index, date);
			this.index +=1;
			Event event6= new Event(this.index, "Description " + this.index, date);
			this.index +=1;
			Event event7= new Event(this.index , "Description " + this.index, date);
			this.index +=1;
			Event event8= new Event(this.index , "Description " + this.index, date);
			this.index +=1;
			Event event9= new Event(this.index, "Description " + this.index, date);
			this.index +=1;
			eventList.add(event1);
			eventList.add(event2);
			eventList.add(event3);
			eventList.add(event4);
			eventList.add(event5);
			eventList.add(event6);
			eventList.add(event7);
			eventList.add(event8);
			eventList.add(event9);
			this.text ="hello";

		} catch (ParseException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error parsin date"));
		}
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public void onDateSelect(SelectEvent event) {
		Date date= (Date)event.getObject();
		eventListOFDate.clear();
		for (Event event2 : eventList) {
			if (event2.getEventDate().equals(date)) {
				eventListOFDate.add(event2);
			}
		}
		FacesContext.getCurrentInstance().addMessage(null,
		new FacesMessage("Data aukeratua: "+event.getObject()));
	}
	public List<Event> getEventList() {
		return eventList;
	}
	public Event getSelectedEvent() {
		return selectedEvent;
	}
	public void setSelectedEvent(Event selectedEvent) {
		this.selectedEvent = selectedEvent;
	}
    public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}