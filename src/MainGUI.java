import java.awt.EventQueue;
import javax.swing.JOptionPane;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ItemEvent;

public class MainGUI extends JFrame {

	public static JPanel contentPane;
	public JTextField revenueDisplay;
	public float grossRevenue = 0.00f;
	private Menu menu = new Menu();
	private static Ticket[] ticketPad = new Ticket[ConfigFile.maxTicketAmount];
	public static tPanel[] thing;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		//menu = new Menu();	
		
		for(int x = 0; x < ConfigFile.maxTicketAmount; x++) {
			ticketPad[x] = new Ticket();
		}
		
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

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				menu.update(menu.getMenu());
			}
		});
		
		setTitle("StikyNote v1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1184, 710);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(220, 220, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 1256, 30);
		contentPane.add(toolBar);
		
		JButton menuEditor = new JButton("Menu Editor");
		menuEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == menuEditor) {
					try {
						MenuEditor dialog = new MenuEditor(menu);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception exe) {
						exe.printStackTrace();
					}
				}
			}
		});
		toolBar.add(menuEditor);
		
		thing = new tPanel[ConfigFile.maxTicketAmount];
		thing[0] = new tPanel(10,40,contentPane,ticketPad,0,menu);
		thing[1] = new tPanel(242,40,contentPane,ticketPad,1,menu);
		thing[2] = new tPanel(474,40,contentPane,ticketPad,2,menu);
		thing[3] = new tPanel(706,40,contentPane,ticketPad,3,menu);
		thing[4] = new tPanel(938,40,contentPane,ticketPad,4,menu);
		thing[5] = new tPanel(10,308,contentPane,ticketPad,5,menu);
		thing[6] = new tPanel(242,308,contentPane,ticketPad,6,menu);
		thing[7] = new tPanel(474,308,contentPane,ticketPad,7,menu);
		thing[8] = new tPanel(706,308,contentPane,ticketPad,8,menu);
		thing[9] = new tPanel(938,308,contentPane,ticketPad,9,menu);
		
		revenueDisplay = new JTextField();
		revenueDisplay.setFont(new Font("Tahoma", Font.PLAIN, 35));
		revenueDisplay.setBackground(new Color(0, 0, 0));
		revenueDisplay.setText(String.format("$%.2f", grossRevenue));
		revenueDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		revenueDisplay.setForeground(new Color(0, 255, 0));
		revenueDisplay.setBounds(886, 589, 274, 74);
		contentPane.add(revenueDisplay);
		revenueDisplay.setColumns(10);
	}
	public Menu getMenu() {
		return menu;
	}
}