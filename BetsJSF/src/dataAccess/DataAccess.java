package dataAccess;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import configuration.UtilDate;
import domain.Event;
import domain.Question;
import hibernateUtil.HibernateUtil;

public class DataAccess {

	public void initializeDB() {
		// Creating hibernate session
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		//Creating the data that will be inserted on the database
		Calendar today = Calendar.getInstance();
		int month=today.get(Calendar.MONTH);
		month+=1;
		int year=today.get(Calendar.YEAR);
		if (month==12) { month=0; year+=1;}  
		Event ev1=new Event("Atlético-Athletic", UtilDate.newDate(year,month,17));
		Event ev2=new Event("Eibar-Barcelona", UtilDate.newDate(year,month,17));
		Event ev3=new Event("Getafe-Celta", UtilDate.newDate(year,month,17));
		Event ev4=new Event("Alavés-Deportivo", UtilDate.newDate(year,month,17));
		Event ev5=new Event("Español-Villareal", UtilDate.newDate(year,month,17));
		Event ev6=new Event("Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
		Event ev7=new Event("Malaga-Valencia", UtilDate.newDate(year,month,17));
		Event ev8=new Event("Girona-Leganés", UtilDate.newDate(year,month,17));
		Event ev9=new Event("Real Sociedad-Levante", UtilDate.newDate(year,month,17));
		Event ev10=new Event("Betis-Real Madrid", UtilDate.newDate(year,month,17));

		Event ev11=new Event("Atletico-Athletic", UtilDate.newDate(year,month,1));
		Event ev12=new Event("Eibar-Barcelona", UtilDate.newDate(year,month,1));
		Event ev13=new Event("Getafe-Celta", UtilDate.newDate(year,month,1));
		Event ev14=new Event("Alavés-Deportivo", UtilDate.newDate(year,month,1));
		Event ev15=new Event("Español-Villareal", UtilDate.newDate(year,month,1));
		Event ev16=new Event("Las Palmas-Sevilla", UtilDate.newDate(year,month,1));


		Event ev17=new Event("Málaga-Valencia", UtilDate.newDate(year,month,28));
		Event ev18=new Event("Girona-Leganés", UtilDate.newDate(year,month,28));
		Event ev19=new Event("Real Sociedad-Levante", UtilDate.newDate(year,month,28));
		Event ev20=new Event("Betis-Real Madrid", UtilDate.newDate(year,month,28));

		Question q1;
		Question q2;
		Question q3;
		Question q4;
		Question q5;
		Question q6;

		q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
		q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
		q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
		q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
		q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
		q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);

		//Inserting the data into the database

		session.save(q1);
		session.save(q2);
		session.save(q3);
		session.save(q4);
		session.save(q5);
		session.save(q6);
		
		session.save(ev1);
		session.save(ev2);
		session.save(ev3);
		session.save(ev4);
		session.save(ev5);
		session.save(ev6);
		session.save(ev7);
		session.save(ev8);
		session.save(ev9);
		session.save(ev10);
		session.save(ev11);
		session.save(ev12);
		session.save(ev13);
		session.save(ev14);
		session.save(ev15);
		session.save(ev16);
		session.save(ev17);
		session.save(ev18);
		session.save(ev19);
		session.save(ev20);	

		session.getTransaction().commit();
		System.out.println("session initialized");
	}
	
	public Question createQuestion(Event e, String q,float minimumBet) {
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Question question = e.addQuestion(q, minimumBet);
		question.setEvent(e);
		session.save(question);
		session.getTransaction().commit();
		return question;
	}
	
	public List<Event> getEvents(Date date){
		//initializing variables
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String selectedDate = sf.format(date);
		List<Event> eventsOfSelectedDate = new ArrayList<Event>();
		//getting the events and selecting those who take place on selected date
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List result = session.createQuery("from Event").list();
		for (int i = 0; i < result.size(); i++) {
			Event ev = (Event) result.get(i);
			String eventDate = sf.format(ev.getEventDate());
			int z = i+1;
			System.out.println("Event " +z);
			System.out.println("Event date " + eventDate + " Selected date " + selectedDate);
			if (eventDate.compareTo(selectedDate) == 0) {
				eventsOfSelectedDate.add(ev);
			}
		}
		return eventsOfSelectedDate;
	}
	
	public List<Date> getEventsMonth(Date date){
		return new ArrayList<Date>();
	}
}
