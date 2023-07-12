import java.awt.EventQueue;

/*
 * Main class: Initializes Menu object and creates a main window.
 */

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		Menu initialize = new Menu();	
		Ticket[] roster = new Ticket[ConfigFile.maxTicketAmount];
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}