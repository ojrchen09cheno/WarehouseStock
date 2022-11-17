package jiaChen_Deliverable2;

public class Item {

	private String code;
	private String name;
	private double price;
	private int amount;
	
	public Item(String code, String name, double price, int amount) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.amount = amount;
	}
	
	public Item(Item i) {
		this.code = i.code;
		this.name = i.name;
		this.price = i.price;
		this.amount = i.amount;
	}
	
	public void print() {
		System.out.println("Name: " + name + ". Price: " + price + ". Amount in stock: " + amount + ". Total value: " + price*amount + ".");
	}
	
	public void add(int i ) {
		amount += i;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getAmount() {
		return amount;
	}
	
}
