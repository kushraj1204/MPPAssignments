package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import business.ControllerInterface;
import business.SystemController;
import dataaccess.Auth;
import dataaccess.User;
import librarysystem.GuiControl;

public class AddEditUserSystem extends JFrame {

	private static final long serialVersionUID = 1L;
	/** final value of label will be set in the constructor */
	private String mainLabel = " User";
	private final String SAVE_BUTN = "Save";
	private final String BACK_BUTN = "Close";

	private JTextField idUserNameField;
	private JComboBox AuthGroupField;
	private JTextField NameField;
	private JPasswordField PasswordField;

	/** group is "Books", "Clothes" etc */
	private String authGroup;

	/** value is "Add New" or "Edit" */
	private String addOrEdit = GuiControl.ADD_NEW;

	/** map of initial field values */
	private Properties fieldValues;

	// JPanels
	JPanel mainPanel;
	JPanel upper, middle, lower;

	public AddEditUserSystem(String addOrEdit, Properties fieldValues) {
		this.addOrEdit = addOrEdit;
		this.fieldValues = fieldValues;
		initializeWindow();
		defineMainPanel();
		getContentPane().add(mainPanel);

	}

	private void initializeWindow() {

		setSize(Math.round(.7f * GuiControl.SCREEN_WIDTH), Math.round(.7f * GuiControl.SCREEN_HEIGHT));
		GuiControl.centerFrameOnDesktop(this);

	}

	private void defineMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(GuiControl.FILLER_COLOR);
		mainPanel.setBorder(new WindowBorder(GuiControl.WINDOW_BORDER));
		defineUpperPanel();
		defineMiddlePanel();
		defineLowerPanel();
		mainPanel.add(upper, BorderLayout.NORTH);
		mainPanel.add(middle, BorderLayout.CENTER);
		mainPanel.add(lower, BorderLayout.SOUTH);

	}

	// label
	public void defineUpperPanel() {
		upper = new JPanel();
		upper.setBackground(GuiControl.FILLER_COLOR);
		upper.setLayout(new FlowLayout(FlowLayout.CENTER));

		JLabel mainLabel = new JLabel(finalMainLabelName());
		Font f = GuiControl.makeVeryLargeFont(mainLabel.getFont());
		f = GuiControl.makeBoldFont(f);
		mainLabel.setFont(f);
		upper.add(mainLabel);
	}

	private String finalMainLabelName() {
		return addOrEdit + " " + mainLabel;
	}

	// table
	public void defineMiddlePanel() {
		middle = new JPanel();
		middle.setBackground(GuiControl.FILLER_COLOR);
		middle.setLayout(new FlowLayout(FlowLayout.CENTER));
		JPanel gridPanel = new JPanel();
		gridPanel.setBackground(GuiControl.SCREEN_BACKGROUND);
		middle.add(gridPanel);
		GridLayout gl = new GridLayout(5, 2);
		gl.setHgap(8);
		gl.setVgap(8);
		gridPanel.setLayout(gl);
		gridPanel.setBorder(new WindowBorder(GuiControl.WINDOW_BORDER));

		String labelName = "id User";
		makeLabel(gridPanel, labelName);
		idUserNameField = new JTextField(10);
		idUserNameField.setText(fieldValues.getProperty(labelName));
		gridPanel.add(idUserNameField);
		idUserNameField.setEditable(true);

		PasswordField = new JPasswordField(10);
		if (fieldValues.getProperty("Auth") != null) {
			ControllerInterface ci = new SystemController();
			System.out.println(ci.getPassword(fieldValues.getProperty(labelName)));
			PasswordField.setText(ci.getPassword(fieldValues.getProperty(labelName)));
			idUserNameField.setEditable(false);
		}
		labelName = "Password";
		makeLabel(gridPanel, labelName);
		gridPanel.add(PasswordField);

		// catalog group is different from the other fields
		// because it plays a different role in MaintainCatalog
		// so it is set differently
		labelName = "Auth";
		makeLabel(gridPanel, labelName);
		System.out.println(fieldValues.getProperty("Auth"));
		AuthGroupField = new JComboBox();
		AuthGroupField.addItem(Auth.ADMIN);
		AuthGroupField.addItem(Auth.BOTH);
		AuthGroupField.addItem(Auth.LIBRARIAN);

		if (fieldValues.getProperty("Auth") != null) {
			switch (fieldValues.getProperty("Auth")) {
			case "":
				AuthGroupField.setSelectedIndex(0);
				break;
			case "ADMIN":
				AuthGroupField.setSelectedIndex(0);
				break;
			case "BOTH":
				AuthGroupField.setSelectedIndex(1);
				break;
			case "LIBRARIAN":
				AuthGroupField.setSelectedIndex(2);
				break;
			// code block
			}
		}
		gridPanel.add(AuthGroupField);

	}

	// buttons
	public void defineLowerPanel() {
		// proceed button
		JButton saveButton = new JButton(SAVE_BUTN);
		saveButton.addActionListener(new SaveListener());

		// back to cart button
		JButton backButton = new JButton(BACK_BUTN);
		backButton.addActionListener(new BackListener());

		// create lower panel
		JButton[] buttons = { saveButton, backButton };
		lower = GuiControl.createStandardButtonPanel(buttons);
	}

	private void makeLabel(JPanel p, String s) {
		JLabel l = new JLabel(s);
		p.add(leftPaddedPanel(l));
	}

	private JPanel leftPaddedPanel(JLabel label) {
		JPanel paddedPanel = new JPanel();
		paddedPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		paddedPanel.add(GuiControl.createHBrick(1));
		paddedPanel.add(label);
		paddedPanel.setBackground(GuiControl.SCREEN_BACKGROUND);
		return paddedPanel;
	}

	class SaveListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {

			if (idUserNameField.getText().length() > 0 && PasswordField.getPassword().length > 0) {
				ControllerInterface ci = new SystemController();
				ci.saveUser(idUserNameField.getText(),new String(PasswordField.getPassword()),
						(Auth) AuthGroupField.getSelectedItem());
				dispose();
				UserSystem.INSTANCE.refresh();
				Util.centerFrameOnDesktop(UserSystem.INSTANCE);
			} else {
				JOptionPane.showMessageDialog(AddEditUserSystem.this, "Invalid field on form!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	class BackListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {

			dispose();

		}
	}

}
