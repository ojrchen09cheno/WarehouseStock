package jiaChen_Deliverable3;

public class Transaction {
	
	// each transaction list includes item id, amount purchased/sold, total price
	private String code;
	private int amount = 0;
	private double total = 0;
	
	public Transaction(String code, int amount, double total) {
		this.code = code;
		this.amount = amount;
		this.total = total;
	} 
	
	public Transaction(String code) {
		this.code = code;
	}
	
	public void print() {
		System.out.println("Item code: " + code + ". Amount: " + amount + ". Total: " + total + ".");
	}

	// Used to update map of total purchases/sales in StockHistory and purchases/sales in a date range
	public Transaction update(Transaction t) {
		amount += t.getAmount();
		total += t.getTotal();
		return t;
	}
	
	public String getCode() {
		return code;
	}

	public int getAmount() {
		return amount;
	}

	public double getTotal() {
		return total;
	}

}
