import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;


public class MenuEditor extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private JTextField nameInput;
	private JTextField priceInput;
	
	public MenuEditor() {

		Vector<Vector<String>> data = new Vector<Vector<String>>();
		Vector<Item> list = MainGUI.menu.getMenu();
		
		//Converting Menu list to String
		for(int rowID = 0; rowID < list.size(); rowID++) {//load Menu items
			Vector<String> row = new Vector<>();
			data.add(row);
			data.get(rowID).add(list.get(rowID).getName());data.get(rowID).add(String.format("%.2f",list.get(rowID).getPrice()));
		}
		
		setTitle("Menu Editor");
		setAlwaysOnTop(true);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setBounds(100, 100, 797, 650);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JButton removeButton = new JButton("Remove");
		JButton insertButton = new JButton("Insert");
		
		{
			Vector<String> titles = new Vector<>(); titles.add("Item"); titles.add("Price"); 
			
			model = new DefaultTableModel(data,titles);
			table = new JTable(data,titles);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.getModel().addTableModelListener(new TableModelListener() {

			      public void tableChanged(TableModelEvent e) {
			          try {
			        	  Float.parseFloat(data.get(table.getSelectedRow()).get(e.getColumn()));
			          } catch (NumberFormatException nfe) {
			        	  JOptionPane.showMessageDialog(contentPanel, "Please enter a valid price");
			        	  data.get(table.getSelectedRow()).set(e.getColumn(),"");
			          }
			      }
			    });
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					removeButton.setEnabled(true);
					insertButton.setEnabled(true);
				}
			});
			table.setBounds(1, 1, 450, 0);
			contentPanel.add(table);
		}
		contentPanel.setLayout(null);
		
		
		removeButton.setEnabled(false);
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
		            // pop item off menu
					list.remove(table.getSelectedRow());
		            model.removeRow(table.getSelectedRow());
		            table.addNotify();
		            removeButton.setEnabled(false);
		            insertButton.setEnabled(false);
				}
			}
		});
		removeButton.setBounds(181, 409, 452, 21);
		contentPanel.add(removeButton);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(181, 10, 452, 402);
		contentPanel.add(scrollPane);
		
		nameInput = new JTextField();
		nameInput.setBounds(181, 457, 226, 21);
		contentPanel.add(nameInput);
		nameInput.setColumns(10);
		
		priceInput = new JTextField();
		priceInput.setColumns(10);
		priceInput.setBounds(417, 457, 132, 21);
		contentPanel.add(priceInput);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//push item to menu
					list.add(new Item(nameInput.getText(),Float.parseFloat(priceInput.getText())));
					Vector<String> row = new Vector<>();
					row.add(nameInput.getText());row.add(priceInput.getText());
					nameInput.setText("");priceInput.setText("");
					data.add(row);
					table.addNotify();
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(contentPanel, "Please enter a valid price");
					priceInput.setText("");
				}
			}
		});
		addButton.setBounds(559, 457, 74, 21);
		contentPanel.add(addButton);
		
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					//insert new item into menu at index
					list.insertElementAt(new Item(nameInput.getText(),Float.parseFloat(priceInput.getText())),table.getSelectedRow());
					Vector<String> row = new Vector<>();
					row.add(nameInput.getText());row.add(priceInput.getText());
					nameInput.setText("");priceInput.setText("");
					data.insertElementAt(row,table.getSelectedRow());
					table.addNotify();
					removeButton.setEnabled(false);
		            insertButton.setEnabled(false);
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(contentPanel, "Please enter a valid price");
					priceInput.setText("");
				}
			}
		});
		insertButton.setEnabled(false);
		insertButton.setBounds(559, 488, 74, 21);
		contentPanel.add(insertButton);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MainGUI.menu.update(list);
					}
				});
				okButton.setActionCommand("Save");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
