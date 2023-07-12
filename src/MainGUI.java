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

public class MainGUI extends JFrame {

	private JPanel contentPane;
	private JTextField nameDisplay;
	private JTextField phoneDisplay;
	private JTextField priceDisplay;
	private String[] status = {"Unpaid","Pending","Complete"};
	private JPanel ticketPanel;

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
		
		JButton debugButton = new JButton("DebugTest");
		toolBar.add(debugButton);
		
		ticketPanel = new JPanel();
		ticketPanel.setVisible(false);
		ticketPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ticketPanel.setBackground(new Color(255, 255, 128));
		ticketPanel.setBounds(10, 40, 222, 258);
		contentPane.add(ticketPanel);
		ticketPanel.setLayout(null);
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameLabel.setBounds(10, 10, 85, 27);
		ticketPanel.add(nameLabel);
		
		nameDisplay = new JTextField();
		nameDisplay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameDisplay.setEditable(false);
		nameDisplay.setBackground(new Color(255, 255, 255));
		nameDisplay.setBounds(10, 33, 169, 28);
		ticketPanel.add(nameDisplay);
		nameDisplay.setColumns(10);
		
		JLabel phoneLabel = new JLabel("Phone #:");
		phoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		phoneLabel.setBounds(10, 71, 85, 27);
		ticketPanel.add(phoneLabel);
		
		phoneDisplay = new JTextField();
		phoneDisplay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		phoneDisplay.setEditable(false);
		phoneDisplay.setColumns(10);
		phoneDisplay.setBackground(Color.WHITE);
		phoneDisplay.setBounds(10, 94, 169, 28);
		ticketPanel.add(phoneDisplay);
		
		JLabel priceLabel = new JLabel("Grand Total:");
		priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		priceLabel.setBounds(127, 124, 85, 27);
		ticketPanel.add(priceLabel);
		
		priceDisplay = new JTextField();
		priceDisplay.setForeground(new Color(0, 255, 0));
		priceDisplay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		priceDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		priceDisplay.setEditable(false);
		priceDisplay.setBackground(new Color(0, 0, 0));
		priceDisplay.setBounds(109, 147, 103, 28);
		ticketPanel.add(priceDisplay);
		priceDisplay.setColumns(10);
		
		JLabel statusLabel = new JLabel("Order Status:");
		statusLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		statusLabel.setBounds(10, 124, 107, 27);
		ticketPanel.add(statusLabel);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(contentPane,"Are you sure you want to cancel this ticket?","Confirm Deletion",JOptionPane.YES_NO_OPTION);
				if(response == 0) {
					nameDisplay.setText(""); phoneDisplay.setText("");ticketPanel.setVisible(false);
				}
			}
		});
		cancelButton.setBackground(new Color(255, 128, 128));
		cancelButton.setBounds(10, 179, 202, 21);
		ticketPanel.add(cancelButton);
		
		JButton editButton = new JButton("Edit");
		editButton.setBackground(new Color(255, 255, 0));
		editButton.setBounds(10, 199, 101, 21);
		ticketPanel.add(editButton);
		
		JButton viewButton = new JButton("View");
		viewButton.setEnabled(false);
		viewButton.setBackground(new Color(115, 185, 255));
		viewButton.setBounds(111, 199, 101, 21);
		ticketPanel.add(viewButton);
		
		JButton finishButton = new JButton("Finish");
		finishButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ticketPanel.setVisible(false);
			}
		});
		finishButton.setEnabled(false);
		finishButton.setBackground(new Color(0, 255, 0));
		finishButton.setBounds(10, 227, 202, 21);
		ticketPanel.add(finishButton);
		

		JComboBox<String> statusBox = new JComboBox<String>(status);
		statusBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == statusBox) {
					switch(statusBox.getSelectedIndex()) {
					case 0:
						cancelButton.setEnabled(true);
						editButton.setEnabled(true);
						viewButton.setEnabled(false);
						finishButton.setEnabled(false);
						break;
					case 1:
						cancelButton.setEnabled(false);
						editButton.setEnabled(false);
						viewButton.setEnabled(true);
						finishButton.setEnabled(false);
						break;
					case 2:
						cancelButton.setEnabled(false);
						editButton.setEnabled(false);
						viewButton.setEnabled(true);
						finishButton.setEnabled(true);
						break;
					}
				}
			}
		});
		statusBox.setBounds(10, 147, 96, 28);
		ticketPanel.add(statusBox);
		
		debugButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == debugButton) {
					ticketPanel.setVisible(true);
				}
			}
		});
	}
}