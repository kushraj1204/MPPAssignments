package UI;

package librarysystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTable;
import java.awt.Font;

public class Book {

	private JFrame frame;
	private JTextField txtISBN;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Book window = new Book();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Book() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 683, 528);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Test Button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(30, 11, 598, 318);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ISBN");
		lblNewLabel.setBounds(10, 29, 70, 14);
		panel.add(lblNewLabel);
		
		txtISBN = new JTextField();
		txtISBN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				JOptionPane.showMessageDialog(null, "Textbox is pressed");
			}
		});
		txtISBN.setBounds(90, 25, 241, 23);
		panel.add(txtISBN);
		txtISBN.setColumns(10);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(90, 54, 241, 23);
		panel.add(textField);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(10, 58, 70, 14);
		panel.add(lblFirstName);
		
		table = new JTable();
		table.setBounds(88, 441, 1, 1);
		frame.getContentPane().add(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 128));
		panel_1.setBounds(30, 361, 598, 61);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
	
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(99, 11, 83, 35);
		panel_1.add(btnSave);
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSave.setBorderPainted(false); 
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				
				JOptionPane.showMessageDialog(null, "Your alert message here.");
			}
		});
		btnSave.setBackground(new Color(0, 0, 255));
		btnSave.setToolTipText("Calculate Area");
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setToolTipText("Calculate Area");
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnUpdate.setBorderPainted(false);
		btnUpdate.setBackground(new Color(0, 250, 154));
		btnUpdate.setBounds(192, 11, 83, 35);
		panel_1.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setToolTipText("Calculate Area");
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnDelete.setBorderPainted(false);
		btnDelete.setBackground(new Color(250, 128, 114));
		btnDelete.setBounds(285, 11, 83, 35);
		panel_1.add(btnDelete);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setToolTipText("Calculate Area");
		btnReset.setForeground(Color.WHITE);
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnReset.setBorderPainted(false);
		btnReset.setBackground(new Color(192, 192, 192));
		btnReset.setBounds(378, 11, 83, 35);
		panel_1.add(btnReset);
	}
}

