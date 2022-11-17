package jiaChen_Deliverable2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Util {
	
	// Function to get current date as int, used when creating stockHistory and transactions
	public static int getDate() {
		Calendar cal = Calendar.getInstance();
		StringBuilder date = new StringBuilder();
		date.append(cal.get(Calendar.YEAR));
		int month = cal.get(Calendar.MONTH);
		if(month < 10)
			date.append(0);
		date.append(month);
		int day = cal.get(Calendar.DATE);
		if(day<10)
			date.append(0);
		date.append(day);
		return Integer.valueOf(date.toString());

	}
	
	// Function to convert date to int based on a Calendar object
	public static int getDate(Calendar cal) {
		StringBuilder date = new StringBuilder();
		date.append(cal.get(Calendar.YEAR));
		int month = cal.get(Calendar.MONTH);
		if(month < 10)
			date.append(0);
		date.append(month);
		int day = cal.get(Calendar.DATE);
		if(day<10)
			date.append(0);
		date.append(day);
		return Integer.valueOf(date.toString());

	}
	
	// Function to get a date based on input
	public static int getDate(int year, int month, int day) {
		StringBuilder date = new StringBuilder();
		date.append(year);
		if(month < 10)
			date.append(0);
		date.append(month);
		if(day<10)
			date.append(0);
		date.append(day);
		return Integer.valueOf(date.toString());

	}
	
	// Function to get a list of dates in a range
	public static List<Integer> getDateList(GregorianCalendar startDate, GregorianCalendar endDate) {
		
		List<Integer> dates = new ArrayList<Integer>();
		while(startDate.getTime().before(endDate.getTime())){
			int year = startDate.get(Calendar.YEAR);
			int month = startDate.get(Calendar.MONTH);
			int day = startDate.get(Calendar.DATE);
			int result = Util.getDate(year,month,day);
			System.out.println(result);
			dates.add(result);
			startDate.add(Calendar.DATE, 1);
		}
		
		return dates;
	};

}
