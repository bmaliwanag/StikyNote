import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

/*
 * Menu Editor:
 * Allows editing the Menu database.
 */
public class MenuEditor extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private JTextField nameInput;
	private JTextField priceInput;
	private final Vector<Item> oldList;
	private final Vector<Vector<String>> oldData;
	
	public MenuEditor(Menu menu) {
		
		oldList = menu.getMenu();
		oldData = menu.getStringMenu();
		Vector<Vector<String>> data = oldData;
		Vector<Item> list = oldList;
		
		//performs one last save should the dialog box return "Yes"
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int i = JOptionPane.showConfirmDialog(contentPanel,"Save Changes?","Order Completion",JOptionPane.YES_NO_OPTION);
				if(i == 0) {
					//System.out.println("Bing!");
					menu.update(list);
				}
			}
		});
		
		setTitle("Menu Editor");
		setAlwaysOnTop(false);
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
				//input validation using parse
			      public void tableChanged(TableModelEvent e) {
			          try {
			        	  Float.parseFloat(data.get(table.getSelectedRow()).get(1));
			        	  
			          } catch (NumberFormatException nfe) {
			        	  JOptionPane.showMessageDialog(contentPanel, "Please enter a valid price");
			        	  data.get(table.getSelectedRow()).set(1,"");
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
		
		//appends item to end of the list.
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//push item to menu
					if(nameInput.getText().equals("") && priceInput.getText().equals("") ){
						JOptionPane.showMessageDialog(getContentPane(), "Please enter a valid name and price!");
					} else {
						list.add(new Item(nameInput.getText(),Float.parseFloat(priceInput.getText())));
						Vector<String> row = new Vector<>();
						row.add(nameInput.getText());row.add(priceInput.getText());
						nameInput.setText("");priceInput.setText("");
						data.add(row);
						table.addNotify();
					}
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(contentPanel, "Please enter a valid price");
					priceInput.setText("");
				}
			}
		});
		addButton.setBounds(559, 457, 74, 21);
		contentPanel.add(addButton);
		
		//inserts item into selected cell
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(nameInput.getText().equals("") && priceInput.getText().equals("") ){
						JOptionPane.showMessageDialog(getContentPane(), "Please enter a valid name and price!");
					} else {
					//insert new item into menu at index
						list.insertElementAt(new Item(nameInput.getText(),Float.parseFloat(priceInput.getText())),table.getSelectedRow());
						Vector<String> row = new Vector<>();
						row.add(nameInput.getText());row.add(priceInput.getText());
						nameInput.setText("");priceInput.setText("");
						data.insertElementAt(row,table.getSelectedRow());
						table.addNotify();
						removeButton.setEnabled(false);
			            insertButton.setEnabled(false);
					}
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
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						menu.update(list);
					}
				});
				buttonPane.add(okButton);
				//getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
