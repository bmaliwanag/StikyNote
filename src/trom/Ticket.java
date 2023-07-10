package trom;
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
		for(int i = 0;i < list.size();i++) {
			this.subTotal += list.get(i).getPrice();
		}
		this.taxFee = this.subTotal * ConfigFile.taxRate;
		this.grandTotal = this.subTotal + this.taxFee;
	}
	
	public Ticket(Vector<Order> input, String name, String number) {
		this.list = input;
		this.name = name;
		this.number = number;
		calculateTotals();
	}
	
	public float getSubTotal() { return subTotal;}
	public float gettaxFee() { return taxFee;}
	public float getGrandTotal() {return grandTotal;}
	public boolean getPaidStatus() {return paid;}
	public boolean getCompletedStatus() {return completed;}
	public String getName() {return name;}
	public String getNumber() {return number;}
	
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
}
