package warehouseStock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StockHistory {

	// StockHistory keeps track of opening, closing, total purchases/sales in a day
	// Item object keeps track of it's own stock during opening and closing by making a copy of the object and storing it in the instance of StockHistory
	private Map<String, Item> opening = new HashMap<String,Item>();
	private Map<String, Item> closing = new HashMap<String,Item>();
	private Map<String, Transaction> purchases = new HashMap<String,Transaction>();
	private Map<String, Transaction> sales = new HashMap<String,Transaction>();
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

	// Stock History is created each day at opening and initializes the opening stock
	public StockHistory(Map<String, Item> opening) {
		this.opening = opening;
	}
	
	public StockHistory() {
		
	}
	
	// Use the transaction list to fill the total purchases and sales done in the day
	public void fillTransactions() {
		// Initialize the values in the maps "purchases and sales" with Items present in the opening Map
		for(String k : opening.keySet()) {
			Transaction t = new Transaction(k);
			purchases.put(k, t);
			// Initialize new object, so they are not referencing the same
			t = new Transaction(k);
			sales.put(k, t);
		}
		// Iterate through transactions in a day and update the total purchases/sales
		for(Transaction t : transactions) {
			if(t.getAmount()<0) {
				Transaction n = sales.get(t.getCode());
				n.update(t);
				sales.put(t.getCode(), n);
			}
			else {
				Transaction n = purchases.get(t.getCode());
				n.update(t);
				purchases.put(t.getCode(), n);
			}
		}
	}
	
	public void print() {
		System.out.println("Opening Stock");
		for(Item i : opening.values()) {
			i.print();
		}
		System.out.println("Closing Stock");
		for(Item i : closing.values()) {
			i.print();
		}
		System.out.println("Purchases / Stock in");
		for(Transaction t : purchases.values()) {
			t.print();
		}
		System.out.println("Sales / Stock out");
		for(Transaction t : sales.values()){
			t.print();
		}
	}
	
	public void addTransaction(Transaction t) {
		transactions.add(t);
	}

	public void setOpening(Map<String, Item> opening) {
		this.opening = opening;
	}

	public void setClosing(HashMap<String, Item> closing) {
		this.closing = closing;
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public Map<String, Transaction> getPurchases() {
		return purchases;
	}

	public Map<String, Transaction> getSales() {
		return sales;
	}
	
}
