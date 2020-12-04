
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class testBean {
	private Date data;
	private String question = "";
	private int bet;
	private Vector<Event> eventListOFDate = new Vector<Event>();


	private Event selectedEvent;
    private String text="";
	
	public testBean() {
			}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public void onDateSelect(SelectEvent event) {
		//Getting user selected date
		Date date= (Date)event.getObject();
		//Clearing the list of the events for tht day and reseting selected event
		this.eventListOFDate.clear();
		selectedEvent=null;
		//Getting events for the selected day; 
		BLFacade bl =  FacadeBean.getBusinessLogic();
		this.eventListOFDate = bl.getEvents(date);
	}
	public Event getSelectedEvent() {
		return selectedEvent;
	}
	public void setSelectedEvent(Event selectedEvent) {
		System.out.println("in");
		this.selectedEvent = selectedEvent;
	}
    public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
    public List<Event> getEventListOFDate() {
		return eventListOFDate;
	}
	public void setEventListOFDate(Vector<Event> eventListOFDate) {
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
			System.out.println("Invalid bet amount");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Errorea: Apustu kopurua okerra da."));
			return null;
		}else if(this.question.equals("")){
			System.out.println("Invalid Question");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Errorea: Sorturiko galdera okerra da."));
			return null;
		}else if(this.selectedEvent == null){
			System.out.println("Invalid even selection");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Errorea: Galdera igorri baino lehen eventu bat aukeratu."));
			return null;
		}else{
			//call to the bl to create teh question for the selected event
			BLFacade bl =  FacadeBean.getBusinessLogic();
			System.out.println(this.selectedEvent.getClass());
			try {
				bl.createQuestion(this.selectedEvent, this.question, this.bet);
				//reset parameters to deafault value
				this.question = "";
				this.bet = 0;
				return "ok";
			} catch (EventFinished e) {
				System.out.println(e.getMessage());
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