

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import bussinessLogic.BLFacadeImplementation;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class Betsbean {
	private Date data;
	private String question = "";
	private int bet;
	private List<Event> eventListOFDate = new ArrayList<Event>();
	private Event selectedEvent;
	private BLFacadeImplementation bl;
	public Betsbean() {
		this.data = null;
		this.question ="";
		this.eventListOFDate.clear();
		this.bet = 0;
		this.selectedEvent = null;
		this.bl =  FacadeBean.getBusinessLogic();
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	/*
	 * Each time a new date is selected
	 * we will retrieve from the database
	 * a list of events which belong to 
	 * that date
	 */
	public void onDateSelect(SelectEvent event) {
		Date date= (Date)event.getObject();
		this.eventListOFDate.clear();
		selectedEvent=null; 
		this.eventListOFDate = bl.getEvents(date);
	}
	public Event getSelectedEvent() {
		return selectedEvent;
	}
	public void setSelectedEvent(Event selectedEvent) {
		this.selectedEvent = selectedEvent;
	}
	public List<Event> getEventListOFDate() {
		return eventListOFDate;
	}
	public void setEventListOFDate(List<Event> eventListOFDate) {
		this.eventListOFDate = eventListOFDate;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getBet() {
		return bet;
	}
	public void setBet(int bet) {
		this.bet = bet;
	}
	public String submitQuestion() {
		//chechk if submited data is valid
		if(this.bet < 0){
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Errorea: Apustu kopurua okerra da."));
			return null;
		}else if(this.question.equals("")){
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Errorea: Sorturiko galdera okerra da."));
			return null;
		}else if(this.selectedEvent == null){
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Errorea: Galdera igorri baino lehen eventu bat aukeratu."));
			return null;
		}else{
			//call to the bl to create teh question for the selected event
			try {
				bl.createQuestion(this.selectedEvent, this.question, this.bet);
				//reset parameters to deafault value
				this.question = "";
				this.bet = 0;
				return "ok";
			} catch (EventFinished e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Errorea: Aukeratutako eventoa bukatu da"));
				return null;
			} catch (QuestionAlreadyExist e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Errorea: Galdera dagoeneko sortua dago" ));
				return null;
			}
		}
	}
}