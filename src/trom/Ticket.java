package trom;
import java.util.*;

/*
 * Class: Ticket
 * Ticket contains a vector of Orders and calculates subtotal and grandTotal.
 * @param Vector<Item> list
 */

public class Ticket {
	
	private Vector<Order> list = new Vector<Order>();
	private float subTotal;
	private float grandTotal;
	
	public void calculateSubTotal() {
		for(int i = 0;i < list.size();i++) {
			this.subTotal += list.get(i).getPrice();
		}
	}
	
	public Ticket(Vector<Order> input) {
		this.list = input;
		this.subTotal = 0.00f;
		calculateSubTotal();
	}
	
	public float getSubTotal() {
		return subTotal;
	}
}
