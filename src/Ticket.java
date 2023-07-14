import java.util.*;

/*
 * Class: Ticket
 * Ticket contains a vector of Orders and calculates subtotal and grandTotal. Identified
 * by name and/or number.
 * @param Vector<Item> list
 * @param String name
 * @param String number
 */

public class Ticket {
	
	private Vector<Order> list = new Vector<Order>();
	private float subTotal = 0.00f;
	private float taxFee;
	private float grandTotal;
	private String name;
	private String number;
	private boolean paid = false;
	private boolean completed = false;
	
	public void calculateTotals() {
		subTotal = 0.00f;
		for(int i = 0;i < list.size();i++) {
			this.subTotal += list.get(i).getPrice();
		}
		this.taxFee = this.subTotal * (ConfigFile.taxRate/100);
		this.grandTotal = this.subTotal + this.taxFee;
	}
	
	public Vector<Vector<String>> getStringOrders(){
		Vector<Vector<String>> paper = new Vector<Vector<String>>();
		for(int i = 0; i < list.size(); i++) {
			Vector<String> entry = new Vector<String>();
			entry.add(list.get(i).getName()); entry.add(list.get(i).getNote()); entry.add(String.format("%.2f", list.get(i).getPrice()));
			paper.add(entry);
		}
		return paper;
	}
	
	/*public Ticket(Vector<Order> input, String name, String number) {
		this.list = input;
		this.name = name;
		this.number = number;
		calculateTotals();
	}
	*/
	
	public Ticket() {
		this.list = new Vector<Order>();
		this.name = "";
		this.number = "";
	}
	//getters
	public float getSubTotal() { return subTotal;}
	public float getTaxFee() { return taxFee;}
	public float getGrandTotal() {return grandTotal;}
	public boolean getPaidStatus() {return paid;}
	public boolean getCompletedStatus() {return completed;}
	public String getName() {return name;}
	public String getNumber() {return number;}
	
	//setters
	public void updateTicket(Vector<Order> input) {
		this.list = input;
		calculateTotals();
	}
	
	public void setPaidStatus(boolean input) {
		this.paid = input;
	}
	
	public void setCompletedStatus(boolean input) {
		this.completed = input;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public void clearTicket() {
		list.clear();
		this.name = "";
		this.number = "";
	}
}
