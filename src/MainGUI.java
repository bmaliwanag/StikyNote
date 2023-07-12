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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class MainGUI extends JFrame {

	private JPanel contentPane;
	private String[] status = {"Unpaid","Pending","Complete"};
	private JTextField nameDisplay;
	private JTextField phoneDisplay;
	private JTextField priceDisplay;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		
		tPanel[] thing = new tPanel[10];
		thing[0] = new tPanel(10,40,contentPane);
		thing[1] = new tPanel(242,40,contentPane);
		thing[2] = new tPanel(474,40,contentPane);
		thing[3] = new tPanel(706,40,contentPane);
		thing[4] = new tPanel(938,40,contentPane);
		thing[5] = new tPanel(10,308,contentPane);
		thing[6] = new tPanel(242,308,contentPane);
		thing[7] = new tPanel(474,308,contentPane);
		thing[8] = new tPanel(706,308,contentPane);
		thing[9] = new tPanel(938,308,contentPane);
		
	}
}