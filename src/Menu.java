import java.util.*;

/*
 * Menu Class: This class houses all Items loaded from the FileLoader
 * class. 
 * 
 * public static Vector<Item> menu: a global 
 */

public class Menu {
	
	public Vector<Item> menu = new Vector<Item>(); 
	FileLoader data = new FileLoader();
	
	public Menu() {
		menu = data.load();
	}
	
	protected void finalize() {
		data.save(menu);
	}
}
