package testMain;

import java.util.Calendar;

import bussinessLogic.BLFacadeImplementation;
import configuration.UtilDate;

public class Main {

	public static void main(String[] args) {
		BLFacadeImplementation bl = new BLFacadeImplementation();
		Calendar today = Calendar.getInstance();
		int month=today.get(Calendar.MONTH);
		month+=1;
		int year=today.get(Calendar.YEAR);
		if (month==12) { month=0; year+=1;}  
		bl.getEvents(UtilDate.newDate(year,month,17));
	}

}
