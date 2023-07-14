
/*
 * Class: Order
 * Not to be confused with Item. Order class is basically a Item with
 * a String attribute for notes. 
 * @param Item item
 * @param String Note
 */

public class Order extends Item{ //make this an extension of an item in the future
	
	private Item item;
	private String note;
	
	public Order(Item input, String note) {
		super(input.getName(),input.getPrice());
		this.note = note;
	}
	
	public Order(String name, float price, String note) {
		super(name,price);
		this.note = note;
	}
	
	public Order() {
		super("",0.00f);
		this.note = "";
	}
	
	public Item getItem() {
		return item;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public void displayOrder() {
		System.out.printf(
				"Item: %s%n" +
				"Price: %.2f%n" +
				"Notes: %s%n"
				);
	}
}
