import static org.junit.Assert.*;
import java.util.Vector;

import org.junit.Test;

public class FirstTest {

	@Test
	public void test() {
		
		//Initialize Empty Item Vector
		Vector<Item> menuStub = new Vector<Item>();
		
		//Initial Empty Order Vector
		Vector<Order> orderBucket = new Vector<Order>();
		
		//Item Vector Data
		menuStub.add(new Item("Cookie",2.75f));
		menuStub.add(new Item("Ice Cream", 3.75f));
		menuStub.add(new Item("Milk", 1.75f));
			
		//Order Vector Data using Item Vector
		orderBucket.add(new Order(menuStub.get(0),"Chocolate Chunk"));
		orderBucket.add(new Order(menuStub.get(1),"DreamyWeaver"));
		orderBucket.add(new Order(menuStub.get(2),"Regular Lactose Free"));
			
		//Initialize Ticket using Order Vector with defined strings
		Ticket output = new Ticket(orderBucket, "Brian Larry Maliwanag", "3614443967");
		
		//Print out Ticket totals
		System.out.println(output.getSubTotal());
		System.out.println(output.getTaxFee());
		System.out.println(output.getGrandTotal());
		
		return;
	}
}
