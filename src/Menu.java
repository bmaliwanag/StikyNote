import java.util.*;

/*
 * Menu Class: This class houses all Items loaded from the FileLoader
 * class. 
 * 
 * public static Vector<Item> menu: a global 
 */

public class Menu {
	
	private Vector<Item> menu = new Vector<Item>(); //pass by reference solution
	public FileLoader data = new FileLoader();
	
	public Menu() {
		menu = data.load();
	}
	
	public Vector<Item> getMenu(){return menu;}; //pass by value solution
	public void update(Vector<Item> input) {
		this.menu = input;
		data.save(menu);
	}
}
