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
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import business.Address;
import business.ControllerInterface;
import business.LibraryMember;
import business.Response;
import business.SystemController;
import dataaccess.Auth;
import dataaccess.User;
import librarysystem.GuiControl;

public class AddEditLibraryMember extends JFrame {

	private static final long serialVersionUID = 1L;
	/** final value of label will be set in the constructor */
	private String mainLabel = "Library Member";
	private final String SAVE_BUTN = "Save";
	private final String BACK_BUTN = "Close";

	private JTextField idMemberField;
	private JTextField firstName;
	private JTextField lastName;
	private JNumberTextField telephone;
	private JTextField street;
	private JTextField city;
	private JTextField state;
	private JNumberTextField zip;

	/** value is "Add New" or "Edit" */
	private String addOrEdit = GuiControl.ADD_NEW;

	/** map of initial field values */
	private Properties fieldValues;

	// JPanels
	JPanel mainPanellm;
	JPanel upperlm, middlelm, lowerlm;

	public AddEditLibraryMember(String addOrEdit, Properties fieldValues) {
		this.addOrEdit = addOrEdit;
		this.fieldValues = fieldValues;
		initializeWindow();
		defineMainPanel();
		getContentPane().add(mainPanellm);

	}

	private void initializeWindow() {

		setSize(Math.round(.8f * GuiControl.SCREEN_WIDTH), Math.round(.8f * GuiControl.SCREEN_HEIGHT));
		GuiControl.centerFrameOnDesktop(this);

	}

	private void defineMainPanel() {
		mainPanellm = new JPanel();
		mainPanellm.setLayout(new BorderLayout());
		mainPanellm.setBackground(GuiControl.FILLER_COLOR);
		mainPanellm.setBorder(new WindowBorder(GuiControl.WINDOW_BORDER));
		defineUpperPanel();
		defineMiddlePanel();
		defineLowerPanel();
		mainPanellm.add(upperlm, BorderLayout.NORTH);
		mainPanellm.add(middlelm, BorderLayout.CENTER);
		mainPanellm.add(lowerlm, BorderLayout.SOUTH);

	}

	// label
	public void defineUpperPanel() {
		upperlm = new JPanel();
		upperlm.setBackground(GuiControl.FILLER_COLOR);
		upperlm.setLayout(new FlowLayout(FlowLayout.CENTER));

		JLabel mainLabel = new JLabel(finalMainLabelName());
		Font f = GuiControl.makeVeryLargeFont(mainLabel.getFont());
		f = GuiControl.makeBoldFont(f);
		mainLabel.setFont(f);
		upperlm.add(mainLabel);
	}

	private String finalMainLabelName() {
		return addOrEdit + " " + mainLabel;
	}

	// table
	public void defineMiddlePanel() {
		middlelm = new JPanel();
		middlelm.setBackground(GuiControl.FILLER_COLOR);
		middlelm.setLayout(new FlowLayout(FlowLayout.CENTER));
		JPanel gridPanel = new JPanel();
		gridPanel.setBackground(GuiControl.SCREEN_BACKGROUND);
		middlelm.add(gridPanel);
		GridLayout gl = new GridLayout(8, 4);
		gl.setHgap(8);
		gl.setVgap(8);
		gridPanel.setLayout(gl);
		gridPanel.setBorder(new WindowBorder(GuiControl.WINDOW_BORDER));

		String labelName = "LibraryMember Id";
		makeLabel(gridPanel, labelName);
		idMemberField = new JTextField(15);
		if (fieldValues.getProperty(labelName) != null)
			idMemberField.setText(fieldValues.getProperty(labelName));
		else
			idMemberField.setText(UUID.randomUUID().toString());

		gridPanel.add(idMemberField);
		idMemberField.setEditable(false);

		labelName = "First Name";
		makeLabel(gridPanel, labelName);
		firstName = new JTextField(20);
		gridPanel.add(firstName);

		// catalog group is different from the other fields
		// because it plays a different role in MaintainCatalog
		// so it is set differently
		labelName = "Last Name";
		makeLabel(gridPanel, labelName);
		lastName = new JTextField(20);
		gridPanel.add(lastName);

		labelName = "Telephone";
		makeLabel(gridPanel, labelName);
		telephone = new JNumberTextField();
		gridPanel.add(telephone);

		labelName = "Street";
		makeLabel(gridPanel, labelName);
		street = new JTextField(20);
		gridPanel.add(street);

		labelName = "City";
		makeLabel(gridPanel, labelName);
		city = new JTextField(20);
		gridPanel.add(city);

		labelName = "State";
		makeLabel(gridPanel, labelName);
		state = new JTextField(15);
		gridPanel.add(state);

		labelName = "Zip";
		makeLabel(gridPanel, labelName);
		zip = new JNumberTextField();
		gridPanel.add(zip);

		if (fieldValues.getProperty("LibraryMember Id") != null) {
			idMemberField.setEditable(false);
			ControllerInterface ci = new SystemController();
			LibraryMember lm = ci.getLibraryMemberbyId(fieldValues.getProperty("LibraryMember Id"));
			firstName.setText(lm.getFirstName());
			lastName.setText(lm.getLastName());
			telephone.setText(lm.getTelephone());
			street.setText(lm.getAddress().getStreet());
			city.setText(lm.getAddress().getCity());
			state.setText(lm.getAddress().getState());
			zip.setText(lm.getAddress().getZip());
		}
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
		lowerlm = GuiControl.createStandardButtonPanel(buttons);
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

			if (idMemberField.getText().length() > 0 && firstName.getText().length() > 0
					&& lastName.getText().length() > 0 && telephone.getText().length() > 0
					&& street.getText().length() > 0 && city.getText().length() > 0 && state.getText().length() > 0
					&& zip.getText().length() > 0) {

				Address a = new Address(street.getText(), city.getText(), state.getText(), zip.getText());

				LibraryMember lm = new LibraryMember(idMemberField.getText(), firstName.getText(), lastName.getText(),
						telephone.getText(), a);

				ControllerInterface ci = new SystemController();
				
				Response resp=ci.saveLibraryMember(lm);
				if(resp.isStatus()) {
					JOptionPane.showMessageDialog(AddEditLibraryMember.this, resp.getMessage(), "Info",
							JOptionPane.INFORMATION_MESSAGE);
					LibraryMemberWindow.INSTANCE.refresh();
					dispose();
					Util.centerFrameOnDesktop(BookWindow.INSTANCE);
				}
				else {
					JOptionPane.showMessageDialog(AddEditLibraryMember.this, resp.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				
			} else {
				JOptionPane.showMessageDialog(AddEditLibraryMember.this, "Invalid field on form!", "Error",
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
