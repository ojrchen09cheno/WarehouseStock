package jiaChen_Deliverable2;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class Stock {

	private Map<String, Item> stock = new HashMap<String,Item>();
	private Map<Integer, StockHistory> stockHistory = new HashMap<Integer,StockHistory>();
	private Calendar cal;
	private int currentDate;
	private StockHistory today;


	public Stock() {
		cal = GregorianCalendar.getInstance();
		currentDate = Util.getDate();
		stockHistory.put(currentDate, new StockHistory());
		today = stockHistory.get(currentDate);
	}
	
	public Stock(Calendar cal) {
		this.cal = cal;
		currentDate = Util.getDate(cal);
		stockHistory.put(currentDate, new StockHistory());
		today = stockHistory.get(currentDate);
	}

	public void addItem(Item item) {
		stock.put(item.getCode(), item);

	}

	public void stockItem(String code, int amount) {
		stock.get(code).add(amount);
		Transaction t = new Transaction(code, amount, -amount*stock.get(code).getPrice());
		today.addTransaction(t);
		System.out.println("Stocked " + amount + " " + stock.get(code).getName());

	}

	public void sellItem(String code, int amount) {
		if(amount > 0)
			amount *= -1;
		if(stock.get(code).getAmount() + amount > 0) {
			stock.get(code).add(amount);
			Transaction t = new Transaction(code, amount, -amount*stock.get(code).getPrice());
			today.addTransaction(t);
			System.out.println("Sold " + amount*-1 + " " + stock.get(code).getName());
		}
		else
			System.out.println("Not enough stock to sell " + -amount + " " + stock.get(code).getName());

	}

	// Function used to "open store on new day"
	// Ideally just get current day, but for testing purposes we will add +1 day
	public void newDay() {
		cal.add(Calendar.DATE, 1);
		currentDate = Util.getDate(cal);
		// Create new StockHistory for new day
		if(!stockHistory.containsKey(currentDate))
		{
			stockHistory.put(currentDate, new StockHistory(getCurrentStock()));
			today = stockHistory.get(currentDate);
		}
		System.out.println("Opening Store - " + currentDate);
		System.out.println();
	}

	// Function to close day. Updates the closing stock and total sales/purchases
	public void closing() {
		today.setClosing(getCurrentStock());
		today.fillTransactions();
		System.out.println("Closing store");
		System.out.println();
	}

	// Function to get the current stock of items. Used to save the stock information during opening and closing
	public HashMap<String, Item> getCurrentStock() {
		HashMap<String,Item> currentStock = new HashMap<String,Item>();
		for(HashMap.Entry<String, Item> i : stock.entrySet()) {
			String code = i.getKey();
			Item t = new Item(i.getValue());
			currentStock.put(code, t);
		}
		return currentStock;
	}

	// Print stock during a specific day
	public void checkStock(int date) {
		if(date == currentDate)
			checkStock();
		else {
		System.out.println("Stock of " + date);
		StockHistory checkStock = stockHistory.get(date);
		checkStock.print();
		System.out.println();
		}
	}

	// Print stock of current date
	public void checkStock() {
		System.out.println("Stock of " + currentDate);
		for(Item i : stock.values()) {
			i.print();
		}
		System.out.println();
	}
	
	// Print total purchases/sales of a date range
	public void checkStock(Calendar start, Calendar end) {
		int startDate = Util.getDate(start);
		Map<String, Transaction> purchases = new HashMap<String,Transaction>();
		Map<String, Transaction> sales = new HashMap<String,Transaction>();
		for(Map.Entry<String, Item> i : stock.entrySet()) {
			purchases.put(i.getKey(), new Transaction(i.getKey()));
			sales.put(i.getKey(), new Transaction(i.getKey()));
		}
		while(start.before(end)) {
			StockHistory s = stockHistory.get(Util.getDate(start));
			for(Map.Entry<String, Transaction> t : s.getPurchases().entrySet()) {
				purchases.get(t.getKey()).update(t.getValue());
			}
			for(Map.Entry<String, Transaction> t : s.getSales().entrySet()) {
				sales.get(t.getKey()).update(t.getValue());
			}
			start.add(Calendar.DATE, 1);
		}
		System.out.println("Total purchases from " + startDate + " - " + Util.getDate(end));
		for(Transaction t : purchases.values()) {
			t.print();
		}
		System.out.println("Total sales from " + startDate + " - " + Util.getDate(end));
		for(Transaction t : sales.values()) {
			t.print();
		}
		
	}

	public int getCurrentDate() {
		return currentDate;
	}

	public StockHistory getToday() {
		return today;
	}


}
