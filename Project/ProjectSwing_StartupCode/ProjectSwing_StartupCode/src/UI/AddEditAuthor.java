package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

import business.Address;
import business.Author;
import business.Book;
import business.ControllerInterface;
import business.LibraryMember;
import business.SystemController;
import dataaccess.Auth;
import dataaccess.User;
import librarysystem.GuiControl;

public class AddEditAuthor extends JFrame {

	private static final long serialVersionUID = 1L;
	/** final value of label will be set in the constructor */
	private String mainLabela = " Author";
	private final String SAVE_BUTN = "Save";
	private final String BACK_BUTN = "Close";

	private JTextField firstName;
	private JTextField lastName;
	private JTextField telephone;
	private JTextField bio;

	/** value is "Add New" or "Edit" */
	private String addOrEdit = GuiControl.ADD_NEW;

	/** map of initial field values */
	private Properties fieldValues;

	// JPanels
	JPanel mainPanela;
	JPanel uppera, middlea, lowera;

	public AddEditAuthor(String addOrEdit, Properties fieldValues) {
		this.addOrEdit = addOrEdit;
		this.fieldValues = fieldValues;
		initializeWindow();
		defineMainPanel();
		getContentPane().add(mainPanela);

	}

	private void initializeWindow() {

		setSize(Math.round(.8f * GuiControl.SCREEN_WIDTH), Math.round(.8f * GuiControl.SCREEN_HEIGHT));
		GuiControl.centerFrameOnDesktop(this);

	}

	private void defineMainPanel() {
		mainPanela = new JPanel();
		mainPanela.setLayout(new BorderLayout());
		mainPanela.setBackground(GuiControl.FILLER_COLOR);
		mainPanela.setBorder(new WindowBorder(GuiControl.WINDOW_BORDER));
		defineUpperPanel();
		defineMiddlePanel();
		defineLowerPanel();
		mainPanela.add(uppera, BorderLayout.NORTH);
		mainPanela.add(middlea, BorderLayout.CENTER);
		mainPanela.add(lowera, BorderLayout.SOUTH);

	}

	// label
	public void defineUpperPanel() {
		uppera = new JPanel();
		uppera.setBackground(GuiControl.FILLER_COLOR);
		uppera.setLayout(new FlowLayout(FlowLayout.CENTER));

		JLabel mainLabel = new JLabel(finalMainLabelName());
		Font f = GuiControl.makeVeryLargeFont(mainLabel.getFont());
		f = GuiControl.makeBoldFont(f);
		mainLabel.setFont(f);
		uppera.add(mainLabel);
	}

	private String finalMainLabelName() {
		return addOrEdit + " " + mainLabela;
	}

	// table
	public void defineMiddlePanel() {
		middlea = new JPanel();
		middlea.setBackground(GuiControl.FILLER_COLOR);
		middlea.setLayout(new FlowLayout(FlowLayout.CENTER));
		JPanel gridPanel = new JPanel();
		gridPanel.setBackground(GuiControl.SCREEN_BACKGROUND);
		middlea.add(gridPanel);
		GridLayout gl = new GridLayout(8, 4);
		gl.setHgap(8);
		gl.setVgap(8);
		gridPanel.setLayout(gl);
		gridPanel.setBorder(new WindowBorder(GuiControl.WINDOW_BORDER));

		String labelName = "First Name";
		makeLabel(gridPanel, labelName);
		firstName = new JTextField(15);
		firstName.setText(fieldValues.getProperty(labelName));
		gridPanel.add(firstName);
		firstName.setEditable(true);

		labelName = "Last Name";
		makeLabel(gridPanel, labelName);
		lastName = new JTextField(15);
		gridPanel.add(lastName);

		labelName = "Telephone";
		makeLabel(gridPanel, labelName);
		telephone = new JTextField(15);
		gridPanel.add(telephone);

		// catalog group is different from the other fields
		// because it plays a different role in MaintainCatalog
		// so it is set differently
		labelName = "bio";
		makeLabel(gridPanel, labelName);
		bio = new JTextField(15);
		gridPanel.add(bio);

		if (fieldValues.getProperty("isbn") != null) {
			// isbn.setEditable(false);
			// ControllerInterface ci = new SystemController();
			// Book lm = ci.getBookbyisbn(fieldValues.getProperty("isbn"));
			// isbn.setText(lm.getIsbn());
			// author.setText(lm.getAuthors().get(0).getFirstName());
			// maxcheckout.setText("" + lm.getMaxCheckoutLength());
			// numberofcopies.setText("" + lm.getNumCopies());

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
		lowera = GuiControl.createStandardButtonPanel(buttons);
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

			if (firstName.getText().length() > 0 && lastName.getText().length() > 0 && telephone.getText().length() > 0
					&& bio.getText().length() > 0) {

				// Address a = new Address(street.getText(), city.getText(), state.getText(),
				// zip.getText());
				Address a = new Address("A", "b", "c", "d");
				// LibraryMember lm = new LibraryMember(idMemberField.getText(),
				// firstName.getText(), lastName.getText(),
				// telephone.getText(), a);
				Author b = new Author(firstName.getText(), lastName.getText(), telephone.getText(), a, bio.getText());
				ArrayList<Author> authors = new ArrayList<Author>();
				authors.add(b);

				ControllerInterface ci = new SystemController();
				ci.saveAuthor(b);
				dispose();
				AuthorWindow.INSTANCE.refresh();
				Util.centerFrameOnDesktop(BookWindow.INSTANCE);
			} else {
				JOptionPane.showMessageDialog(AddEditAuthor.this, "Invalid field on form!", "Error",
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