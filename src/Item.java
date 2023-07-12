

/*
 * Class: Item
 * Item is an object that contains the name of a dish and a price.
 * @param String name
 * @param float price
 */

public class Item {
	private String name;
	private float price;
	
	public Item(String name, float price) {
		//Method: Item constructor takes a string and a float and initializes the data to the object.
		//Note that if float is negative, constructor initializes to price. 
		this.name = name;
		
		if(price >= 0) {
			this.price = price;
		} else {
			this.price = 0.00f;
		}
	}
	
	public String getName() {return name;}
	public float getPrice() {return price;}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(float price) {
		if(price >= 0) {
			this.price = price;
		} else {
			this.price = 0.00f;
		}
	}
	
	public void printItemInfo() {
		System.out.printf("Item: %s Price: %.2f%n", name, price);
	}
}
