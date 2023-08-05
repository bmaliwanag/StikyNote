import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/*
 * tPanel class:
 * This class provides ticket objects to put
 * into the main GUI system. Outputs customer
 * name and phone number and amount due.
 */
public class tPanel{
	
	private String[] status = {"Unpaid","Pending","Complete"};
	private JTextField nameDisplay;
	private JTextField phoneDisplay;
	private JTextField priceDisplay;
	private JPanel ticket;
	private JPanel defaultPanel;
	private JComboBox<String> statusBox;
	
	tPanel(int x, int y, JPanel contentPane, Ticket[] pad, int index, Menu menu){
		JLayeredPane ticketPanel = new JLayeredPane();
		ticketPanel.setBounds(x, y, 222, 258);
		contentPane.add(ticketPanel);
		
		ticket = new JPanel();
		ticket.setVisible(false);
		ticketPanel.setLayer(ticket, 1);
		ticket.setBackground(new Color(255, 255, 191));
		ticket.setBounds(0, 0, 222, 258);
		ticketPanel.add(ticket);
		ticket.setLayout(null);
		
		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameLabel.setIconTextGap(2);
		nameLabel.setBounds(10, 10, 81, 19);
		ticket.add(nameLabel);
		
		nameDisplay = new JTextField();
		nameDisplay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameDisplay.setEditable(false);
		nameDisplay.setBounds(10, 30, 168, 19);
		ticket.add(nameDisplay);
		nameDisplay.setColumns(10);
		
		JLabel phoneLabel = new JLabel("Phone #:");
		phoneLabel.setIconTextGap(2);
		phoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		phoneLabel.setBounds(10, 50, 81, 19);
		ticket.add(phoneLabel);
		
		phoneDisplay = new JTextField();
		phoneDisplay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		phoneDisplay.setEditable(false);
		phoneDisplay.setColumns(10);
		phoneDisplay.setBounds(10, 70, 168, 19);
		ticket.add(phoneDisplay);
		
		JLabel priceLabel = new JLabel("Amount Due:");
		priceLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		priceLabel.setIconTextGap(2);
		priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		priceLabel.setBounds(108, 90, 104, 19);
		ticket.add(priceLabel);
		
		priceDisplay = new JTextField();
		priceDisplay.setBackground(new Color(0, 0, 0));
		priceDisplay.setForeground(new Color(0, 255, 0));
		priceDisplay.setHorizontalAlignment(SwingConstants.TRAILING);
		priceDisplay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		priceDisplay.setEditable(false);
		priceDisplay.setColumns(10);
		priceDisplay.setBounds(108, 111, 104, 34);
		ticket.add(priceDisplay);
		
		//cancels the order
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == cancelButton) {
					if(JOptionPane.showConfirmDialog(contentPane,"Are you sure you want to cancel this order?","Order Cancellation",JOptionPane.YES_NO_OPTION) == 0) {
						nameDisplay.setText("");phoneDisplay.setText("");priceDisplay.setText("");
						ticket.setVisible(false);
						ticketPanel.getComponentsInLayer(0)[0].setVisible(true);
						pad[index].clearTicket();
					}
				}
			}
		});
		cancelButton.setBackground(new Color(255, 128, 128));
		cancelButton.setForeground(new Color(0, 0, 0));
		cancelButton.setBounds(10, 155, 202, 31);
		ticket.add(cancelButton);
		
		//edit button becomes enabled when the order is incomplete.
		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == editButton) {
					try {
						TicketBuilder dialog = new TicketBuilder(pad, index,menu,true);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
						nameDisplay.setText(pad[index].getName());
						phoneDisplay.setText(pad[index].getNumber());
						priceDisplay.setText(String.format("$%.2f",pad[index].getGrandTotal()));
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		editButton.setBackground(new Color(255, 255, 0));
		editButton.setBounds(10, 186, 101, 31);
		ticket.add(editButton);
		
		//View button allows to view all details of ticket in Ticket Editor
		//Sets TicketEditor to read only.
		JButton viewButton = new JButton("View");
		viewButton.setEnabled(false);
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == viewButton) {
					try {
						TicketBuilder dialog = new TicketBuilder(pad, index,menu,false);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
						nameDisplay.setText(pad[index].getName());
						phoneDisplay.setText(pad[index].getNumber());
						priceDisplay.setText(String.format("$%.2f",pad[index].getGrandTotal()));
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		viewButton.setBackground(new Color(102, 179, 255));
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		viewButton.setBounds(111, 186, 101, 31);
		ticket.add(viewButton);
		
		//Same as delete but adds cost value to total gross value
		JButton completeButton = new JButton("Cash In");
		completeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == completeButton) {
					if(JOptionPane.showConfirmDialog(contentPane,"Has the customer recieved their food?","Order Completion",JOptionPane.YES_NO_OPTION) == 0) {
						MainGUI.grossRevenue += pad[index].getGrandTotal();
						MainGUI.revenueDisplay.setText(String.format("$%.2f",MainGUI.grossRevenue));
						pad[index].clearTicket();
						nameDisplay.setText("");phoneDisplay.setText("");priceDisplay.setText("");
						ticket.setVisible(false);
						ticketPanel.getComponentsInLayer(0)[0].setVisible(true);
					}
				}
			}
		});
		completeButton.setEnabled(false);
		completeButton.setBackground(new Color(0, 255, 128));
		completeButton.setBounds(10, 217, 202, 31);
		ticket.add(completeButton);
		
		//dropdown for choosing adequate status
		statusBox = new JComboBox<String>(status);
		statusBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getSource() == statusBox) {
					switch(statusBox.getSelectedIndex()) {
						case 0:
							cancelButton.setEnabled(true);
							editButton.setEnabled(true);
							viewButton.setEnabled(false);
							completeButton.setEnabled(false);
							break;
						case 1:
							cancelButton.setEnabled(false);
							editButton.setEnabled(false);
							viewButton.setEnabled(true);
							completeButton.setEnabled(false);
							break;
						case 2:
							cancelButton.setEnabled(false);
							editButton.setEnabled(false);
							viewButton.setEnabled(true);
							completeButton.setEnabled(true);
							break;
					}
				}
			}
		});
		statusBox.setBounds(10, 111, 88, 21);
		ticket.add(statusBox);
		
		defaultPanel = new JPanel();
		ticketPanel.setLayer(defaultPanel, 0);
		defaultPanel.setBounds(0, 0, 222, 258);
		ticketPanel.add(defaultPanel);
		defaultPanel.setLayout(null);
		
		//creates a new ticket.
		JButton createButton = new JButton("New Ticket");
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == createButton) {
					try {
						TicketBuilder dialog = new TicketBuilder(pad, index,menu,true);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
						nameDisplay.setText(pad[index].getName());
						phoneDisplay.setText(pad[index].getNumber());
						priceDisplay.setText(String.format("$%.2f",pad[index].getGrandTotal()));
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		createButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		createButton.setBounds(60, 103, 100, 50);
		defaultPanel.add(createButton);
	}
	
	public void activateTicket() {
		defaultPanel.setVisible(false);
		ticket.setVisible(true);
		statusBox.setSelectedIndex(0);
	}
}
