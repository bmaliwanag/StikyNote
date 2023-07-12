import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

/*
 * Class: FileLoader
 * Reads data from a text file and
 * creates a vector of Items from that text file.
 * Once finished, the class writes back to the text file
 * with the same Item vector.
 * @param Vector<Item> list
 */

public class FileLoader {
	
	private Vector<Item> list = new Vector<Item>();
	private File menu; 
	private Scanner reader;
	
	public FileLoader(){ //Constructor
		
		String buffer = "",name = ""; float price = 0f;
		
	
		try {
			this.menu = new File("menu.txt");
			
			if (menu.createNewFile()) {
				System.out.println("New Menu file created!");
			}
		}	catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
		}
			
		try {
			this.reader = new Scanner(menu);
	
			while(reader.hasNextLine()) {
				buffer = reader.nextLine();
				StringTokenizer st = new StringTokenizer(buffer,"/");
				name = st.nextToken(); price = Float.parseFloat(st.nextToken());
				
				list.add(new Item(name,price));
			}
			
			System.out.print("Menu loaded!");
			reader.close();
		} catch (FileNotFoundException e){
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public Vector<Item> load() { //loads memory from inside the object to outside
		return list;
	}
	
	public void save(Vector<Item> input) {
		this.list = input;
		
		try {
			FileWriter writer = new FileWriter("menu.txt");
			
			for(int i = 0; i < list.size(); i++) {
				writer.write(list.get(i).getName() + "/" + list.get(i).getPrice() + System.getProperty("line.separator"));
			}
			
			writer.close();
			
			System.out.print("Menu Saved!");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	
}