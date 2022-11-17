package jiaChen_Deliverable2;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calendar cal = new GregorianCalendar(2022,10,14);
		Calendar end = new GregorianCalendar(2023,0,3);
		
		// Simulate starting at date cal - 2022 / 10 / 14 format is year / month / date
		// Calendar class starts month at 0 to 11
		Stock s = new Stock(cal);
		Item i = new Item("bo", "Book", 5, 100); 
		s.addItem(i);
		i = new Item("l", "Laptop", 1000,5); 
		s.addItem(i);
		i = new Item("ba", "Backpack", 50, 50); 
		s.addItem(i);
		s.checkStock();
		
		s.stockItem("bo", 4); 
		s.sellItem("l", 50); 
		s.sellItem("l", 1); 
		s.stockItem("bo", 2); 
		System.out.println();
		s.checkStock();
		
		// Set opening stock as it is not created by default because we never run newDay() before
		s.getToday().setOpening(s.getCurrentStock());
		s.closing();
		s.newDay();
		
		s.checkStock();
		
		// Check stock of a previous date
		s.checkStock(20221014);
		s.closing();
		s.newDay();
		
		// Check stocck of previous date that had no transactions
		s.checkStock(20221015);
		
		s.stockItem("ba", 100);
		s.sellItem("ba", 50);
		
		// Test for change of year
		while(s.getCurrentDate() != 20221131) {
			s.closing();
			s.newDay();
		}
		
		s.stockItem("l", 100);
		s.sellItem("l", 2);
		s.sellItem("bo", 10);
		
		s.closing();
		s.newDay();
		
		s.stockItem("ba", 10);
		s.sellItem("ba", 25);
		s.closing();
		s.newDay();
		s.checkStock(20230001);
		
		cal = new GregorianCalendar(2022,10,14);
		// Check total amount sold/bought in a range of time
		s.checkStock(cal, end);
		
		// Books end total 96 / Purchased 6 / Sold 10
		// Laptop end total 102 / Purchased 100 / Sold 3
		// Backpacks end total 85 / Purchased 110 / Sold 75
		
	}

}
