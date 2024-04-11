package librarysystem;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.model.Address;
import business.ControllerInterface;
import business.model.LibraryMember;
import business.SystemController;

public class AddEditLibraryMember extends JFrame {

	private static final long serialVersionUID = 1L;
	/** final value of label will be set in the constructor */
	private String mainLabel = " Library Member";
	private final String SAVE_BUTN = "Save";
	private final String BACK_BUTN = "Close";

	private JTextField idMemberField;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField telephone;
	private JTextField street;
	private JTextField city;
	private JTextField state;
	private JTextField zip;

	/** value is "Add New" or "Edit" */
	private String addOrEdit = GuiControl.ADD_NEW;

	/** map of initial field values */
	private Properties fieldValues;

	// JPanels
	JPanel mainPanel;
	JPanel upper, middle, lower;

	public AddEditLibraryMember(String addOrEdit, Properties fieldValues) {
		this.addOrEdit = addOrEdit;
		this.fieldValues = fieldValues;
		initializeWindow();
		defineMainPanel();
		getContentPane().add(mainPanel);

	}

	private void initializeWindow() {

		setSize(Math.round(.8f * GuiControl.SCREEN_WIDTH), Math.round(.8f * GuiControl.SCREEN_HEIGHT));
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
		GridLayout gl = new GridLayout(8, 4);
		gl.setHgap(8);
		gl.setVgap(8);
		gridPanel.setLayout(gl);
		gridPanel.setBorder(new WindowBorder(GuiControl.WINDOW_BORDER));

		String labelName = "id LibraryMember";
		makeLabel(gridPanel, labelName);
		idMemberField = new JTextField(15);
		idMemberField.setText(fieldValues.getProperty(labelName));
		gridPanel.add(idMemberField);
		idMemberField.setEditable(true);

		labelName = "First Name";
		makeLabel(gridPanel, labelName);
		firstName = new JTextField(15);
		gridPanel.add(firstName);

		// catalog group is different from the other fields
		// because it plays a different role in MaintainCatalog
		// so it is set differently
		labelName = "Last Name";
		makeLabel(gridPanel, labelName);
		lastName = new JTextField(15);
		gridPanel.add(lastName);

		labelName = "Telephone";
		makeLabel(gridPanel, labelName);
		telephone = new JTextField(15);
		gridPanel.add(telephone);

		labelName = "Street";
		makeLabel(gridPanel, labelName);
		street = new JTextField(15);
		gridPanel.add(street);

		labelName = "city";
		makeLabel(gridPanel, labelName);
		city = new JTextField(15);
		gridPanel.add(city);

		labelName = "state";
		makeLabel(gridPanel, labelName);
		state = new JTextField(15);
		gridPanel.add(state);

		labelName = "zip";
		makeLabel(gridPanel, labelName);
		zip = new JTextField(15);
		gridPanel.add(zip);

		if (fieldValues.getProperty("id LibraryMember") != null) {
			idMemberField.setEditable(false);
			ControllerInterface ci = new SystemController();
			LibraryMember lm = ci.getLibraryMemberbyId(fieldValues.getProperty("id LibraryMember"));
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

			if (idMemberField.getText().length() > 0 && firstName.getText().length() > 0
					&& lastName.getText().length() > 0 && telephone.getText().length() > 0
					&& street.getText().length() > 0 && city.getText().length() > 0 && state.getText().length() > 0
					&& zip.getText().length() > 0) {

				Address a = new Address(street.getText(), city.getText(), state.getText(), zip.getText());

				LibraryMember lm = new LibraryMember(idMemberField.getText(), firstName.getText(), lastName.getText(),
						telephone.getText(), a);

				ControllerInterface ci = new SystemController();
				ci.saveLibraryMember(lm);
				dispose();
				LibraryMemberWindow.INSTANCE.refresh();
				Util.centerFrameOnDesktop(LibraryMemberWindow.INSTANCE);
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
