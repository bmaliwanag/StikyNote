import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;


public class MenuEditor extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	
	public MenuEditor() {
		setAlwaysOnTop(true);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setBounds(100, 100, 1184, 710);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			String[][] data = new String[Menu.menu.size()][2];
			String[] titles = {"Item","Price"};
			for(int i = 0; i < Menu.menu.size();i++) {
				data[i][0] = Menu.menu.get(i).getName();
				data[i][1] = String.format("$%.2f",Menu.menu.get(i).getPrice());
			}
			table = new JTable(data,titles);
			table.setBounds(0, 46, 450, 384);
			contentPanel.add(table);
		}
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 46, 500, 384);
		contentPanel.add(scrollPane);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
