import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;

import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import javax.swing.JTextArea;

import java.awt.Font;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/*
 * TicketBuilder System:
 * Provides user with a blank Order list on left. Orders can be
 * created by the Item list on the right side or by writing a
 * custom order in the bottom right fields.
 */
public class TicketBuilder extends JDialog {
	private DefaultTableModel menuModel;
	private DefaultTableModel ticketModel;
	private JTable ticketTable;
	private JTable menuTable;
	private JTextField subTotalDisplay;
	private JTextField taxAmountDisplay;
	private JTextField grandTotalField;
	private JTextField priceField;
	private JTextField itemField;
	
	@SuppressWarnings("serial")
	public TicketBuilder(Ticket[] pad, int index,Menu menu,boolean editable) {
		setTitle("Ticket Builder");
		
		//initialization corner
		Vector<Vector<String>> data = menu.getStringMenu();
		Vector<Vector<String>> orderGrid = pad[index].getStringOrders();
		Vector<Order> orders = pad[index].getOrders();
		JTextArea noteField = new JTextArea();
		JButton deleteEntry = new JButton("Remove Order");deleteEntry.setEnabled(editable);
		
		priceField = new JTextField();
		itemField = new JTextField();
		JButton addOrder = new JButton("Add Order");
		addOrder.setEnabled(editable);
		
		setAlwaysOnTop(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setBounds(100, 100, 1250, 700);
		getContentPane().setLayout(null);
		
		Vector<String> ticketTitles = new Vector<String>();
		ticketTitles.add("Item");ticketTitles.add("Note");ticketTitles.add("Price");
		ticketModel = new DefaultTableModel(orderGrid,ticketTitles){
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
				if(!editable) {
					return false;
				}else {
					return true;
				}
		    }
		};
		ticketTable = new JTable(ticketModel);
		ticketTable.getModel().addTableModelListener(new TableModelListener() {

		      public void tableChanged(TableModelEvent e) {
		    	  System.out.println(e);
		          try {
		        	  Float.parseFloat(orderGrid.get(ticketTable.getSelectedRow()).get(2));
		        		  pad[index].updateTicket(orders);
			        	  subTotalDisplay.setText(String.format("$%.2f", pad[index].getSubTotal()));
			        	  taxAmountDisplay.setText(String.format("$%.2f", pad[index].getTaxFee()));
				          grandTotalField.setText(String.format("$%.2f", pad[index].getGrandTotal()));
		          } catch (NumberFormatException nfe) {
		        	  JOptionPane.showMessageDialog(getContentPane(), "Please enter a valid price");
		        	  orderGrid.get(ticketTable.getSelectedRow()).set(2,"");
		          }
		      }
		    });
		ticketTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(editable)
					deleteEntry.setEnabled(true);
			}
		});
		ticketTable.setBounds(75, 178, 423, 296);
		getContentPane().add(ticketTable);
		
		Vector<String> menuTitles = new Vector<String>();
		menuTitles.add("Item"); menuTitles.add("Price");
		menuModel = new DefaultTableModel(data,menuTitles) {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		menuTable = new JTable(menuModel);
		menuTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				itemField.setText(data.get(menuTable.getSelectedRow()).get(0));
				priceField.setText(data.get(menuTable.getSelectedRow()).get(1));
			}
		});
		menuTable.setBounds(683, 118, 543, 296);
		getContentPane().add(menuTable);
		
		//scroller for the ticket
		JScrollPane ticketScroll = new JScrollPane(ticketTable);
		ticketScroll.setBounds(25, 25, 500, 390);
		getContentPane().add(ticketScroll);
		
		//scroller for the menu database
		JScrollPane menuScroll = new JScrollPane(menuTable);
		menuScroll.setBounds(700, 25, 500, 476);
		getContentPane().add(menuScroll);
		
		itemField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		itemField.setEnabled(editable);
		itemField.setBounds(700, 511, 189, 22);
		getContentPane().add(itemField);
		
		priceField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		priceField.setEnabled(editable);
		priceField.setBounds(899, 511, 143, 22);
		getContentPane().add(priceField);
		
		addOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == addOrder) {
					if(itemField.getText().equals("") && priceField.getText().equals("") ){
						JOptionPane.showMessageDialog(getContentPane(), "Please enter a valid item name and price!");
					} else {
						
						try {
							//push item to menu
							if(itemField.getText().equals("") && priceField.getText().equals("")){
								JOptionPane.showMessageDialog(getContentPane(), "Please enter a valid name and price!");
							} else {
								orders.add(new Order(new Item(itemField.getText(),Float.parseFloat(priceField.getText())),noteField.getText()));
								Vector<String> input = new Vector<String>();
								input.add(itemField.getText());input.add(noteField.getText());input.add(priceField.getText());
								
								orderGrid.add(input);
								ticketTable.addNotify();
								pad[index].updateTicket(orders);
					        	subTotalDisplay.setText(String.format("$%.2f", pad[index].getSubTotal()));
					        	taxAmountDisplay.setText(String.format("$%.2f", pad[index].getTaxFee()));
					        	grandTotalField.setText(String.format("$%.2f", pad[index].getGrandTotal()));
							}
						} catch (NumberFormatException nfe) {
							JOptionPane.showMessageDialog(getContentPane(), "Please enter a valid price");
							priceField.setText("");
						}
					}
				}
			}
		});
		addOrder.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addOrder.setBounds(1052, 511, 148, 141);
		getContentPane().add(addOrder);
	
		//note box for Order creation
		noteField.setLineWrap(true);
		noteField.setEnabled(editable);
		noteField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		noteField.setBounds(700, 543, 342, 109);
		getContentPane().add(noteField);
		
		subTotalDisplay = new JTextField();
		subTotalDisplay.setFont(new Font("Tahoma", Font.PLAIN, 23));
		subTotalDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		subTotalDisplay.setForeground(new Color(0, 255, 128));
		subTotalDisplay.setBackground(new Color(0, 0, 0));
		subTotalDisplay.setEditable(false);
		subTotalDisplay.setBounds(336, 473, 189, 39);
		getContentPane().add(subTotalDisplay);
		subTotalDisplay.setColumns(10);
		
		JLabel sTLabel = new JLabel("Subtotal:");
		sTLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		sTLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sTLabel.setBounds(391, 446, 134, 29);
		getContentPane().add(sTLabel);
		
		taxAmountDisplay = new JTextField();
		taxAmountDisplay.setEditable(false);
		taxAmountDisplay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		taxAmountDisplay.setForeground(new Color(0, 255, 128));
		taxAmountDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		taxAmountDisplay.setBackground(new Color(0, 0, 0));
		taxAmountDisplay.setBounds(336, 534, 189, 39);
		getContentPane().add(taxAmountDisplay);
		taxAmountDisplay.setColumns(10);
		
		JLabel tLabel = new JLabel("Tax Amount: ");
		tLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		tLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tLabel.setBounds(428, 511, 97, 19);
		getContentPane().add(tLabel);
		
		grandTotalField = new JTextField();
		grandTotalField.setEditable(false);
		grandTotalField.setHorizontalAlignment(SwingConstants.RIGHT);
		grandTotalField.setForeground(new Color(0, 255, 128));
		grandTotalField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		grandTotalField.setBackground(new Color(0, 0, 0));
		grandTotalField.setEditable(false);
		grandTotalField.setBounds(336, 598, 189, 54);
		getContentPane().add(grandTotalField);
		grandTotalField.setColumns(10);
		
		subTotalDisplay.setText(String.format("$%.2f", pad[index].getSubTotal()));
    	taxAmountDisplay.setText(String.format("$%.2f", pad[index].getTaxFee()));
    	grandTotalField.setText(String.format("$%.2f", pad[index].getGrandTotal()));
		
		JLabel gTLabel = new JLabel("Grand Total:");
		gTLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		gTLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		gTLabel.setBounds(391, 572, 134, 29);
		getContentPane().add(gTLabel);
		
		JTextArea nameField = new JTextArea();
		nameField.setEnabled(editable);
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nameField.setText(pad[index].getName());
		nameField.setBounds(25, 481, 189, 22);
		getContentPane().add(nameField);
		
		JLabel nFieldLbl = new JLabel("Customer Name:");
		nFieldLbl.setHorizontalAlignment(SwingConstants.LEFT);
		nFieldLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nFieldLbl.setBounds(25, 458, 134, 29);
		getContentPane().add(nFieldLbl);
		
		JTextArea numberField = new JTextArea();
		numberField.setEnabled(editable);
		numberField.setText(pad[index].getNumber());
		numberField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		numberField.setBounds(25, 529, 189, 22);
		getContentPane().add(numberField);
		
		JLabel numFieldLbl = new JLabel("Customer Phone Number:");
		numFieldLbl.setHorizontalAlignment(SwingConstants.LEFT);
		numFieldLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		numFieldLbl.setBounds(25, 506, 189, 29);
		getContentPane().add(numFieldLbl);
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.setEnabled(editable);
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nameField.getText().equals("") && numberField.getText().equals("") ){
					JOptionPane.showMessageDialog(getContentPane(), "Please enter customer's name and number!");
				} else {
					pad[index].setName(nameField.getText());
					pad[index].setNumber(numberField.getText());
					MainGUI.thing[index].activateTicket();
					dispose();
				}
			}
		});
		confirmButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		confirmButton.setBounds(25, 580, 97, 54);
		getContentPane().add(confirmButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cancelButton.setBounds(132, 580, 97, 54);
		getContentPane().add(cancelButton);
		
		deleteEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ticketTable.getSelectedRow() != -1) {
					orders.remove(ticketTable.getSelectedRow());
					orderGrid.remove(ticketTable.getSelectedRow());
		            ticketTable.addNotify();
		            deleteEntry.setEnabled(false);
		            pad[index].updateTicket(orders);
		        	subTotalDisplay.setText(String.format("$%.2f", pad[index].getSubTotal()));
		        	taxAmountDisplay.setText(String.format("$%.2f", pad[index].getTaxFee()));
		        	grandTotalField.setText(String.format("$%.2f", pad[index].getGrandTotal()));
				}
			}
		});
		deleteEntry.setFont(new Font("Tahoma", Font.PLAIN, 15));
		deleteEntry.setBounds(25, 419, 189, 29);
		getContentPane().add(deleteEntry);
	}
}
