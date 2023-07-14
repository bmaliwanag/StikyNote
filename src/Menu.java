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
	public Vector<Vector<String>> getStringMenu(){
		Vector<Vector<String>> list = new Vector<Vector<String>>(); 
		for(int row = 0; row < menu.size(); row++) {
			Vector<String> entry = new Vector<String>();
			entry.add(menu.get(row).getName()); entry.add(String.format("%.2f", menu.get(row).getPrice()));
			list.add(entry);
		}
		return list;
	}
	public void update(Vector<Item> input) {
		//System.out.println("Menu Updated!");
		this.menu = input;
		data.save(menu);
	}
}
