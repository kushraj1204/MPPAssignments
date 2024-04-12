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
import business.Response;
import business.SystemController;
import dataaccess.User;
import librarysystem.GuiControl;

public class AddCopiesBook extends JFrame {

	private static final long serialVersionUID = 1L;
	/** final value of label will be set in the constructor */
	private String mainLabela = " Add Book Copies";
	private final String SAVE_BUTN = "Save";
	private final String BACK_BUTN = "Close";

	private JTextField isbn;
	private JNumberTextField Ncopies;

	/** value is "Add New" or "Edit" */
	private String addOrEdit = GuiControl.ADD_NEW;

	/** map of initial field values */
	private Properties fieldValues;

	// JPanels
	JPanel mainPanelcob;
	JPanel uppercob, middlecob, lowercob;

	public AddCopiesBook(String addOrEdit, Properties fieldValues) {
		this.addOrEdit = addOrEdit;
		this.fieldValues = fieldValues;
		initializeWindow();
		defineMainPanel();
		getContentPane().add(mainPanelcob);

	}

	private void initializeWindow() {

		setSize(Math.round(.8f * GuiControl.SCREEN_WIDTH), Math.round(.8f * GuiControl.SCREEN_HEIGHT));
		GuiControl.centerFrameOnDesktop(this);

	}

	private void defineMainPanel() {
		mainPanelcob = new JPanel();
		mainPanelcob.setLayout(new BorderLayout());
		mainPanelcob.setBackground(GuiControl.FILLER_COLOR);
		mainPanelcob.setBorder(new WindowBorder(GuiControl.WINDOW_BORDER));
		defineUpperPanel();
		defineMiddlePanel();
		defineLowerPanel();
		mainPanelcob.add(uppercob, BorderLayout.NORTH);
		mainPanelcob.add(middlecob, BorderLayout.CENTER);
		mainPanelcob.add(lowercob, BorderLayout.SOUTH);

	}

	// label
	public void defineUpperPanel() {
		uppercob = new JPanel();
		uppercob.setBackground(GuiControl.FILLER_COLOR);
		uppercob.setLayout(new FlowLayout(FlowLayout.CENTER));

		JLabel mainLabel = new JLabel(finalMainLabelName());
		Font f = GuiControl.makeVeryLargeFont(mainLabel.getFont());
		f = GuiControl.makeBoldFont(f);
		mainLabel.setFont(f);
		uppercob.add(mainLabel);
	}

	private String finalMainLabelName() {
		return  mainLabela;
	}

	// table
	public void defineMiddlePanel() {
		middlecob = new JPanel();
		middlecob.setBackground(GuiControl.FILLER_COLOR);
		middlecob.setLayout(new FlowLayout(FlowLayout.CENTER));
		JPanel gridPanel = new JPanel();
		gridPanel.setBackground(GuiControl.SCREEN_BACKGROUND);
		middlecob.add(gridPanel);
		GridLayout gl = new GridLayout(8, 4);
		gl.setHgap(8);
		gl.setVgap(8);
		gridPanel.setLayout(gl);
		gridPanel.setBorder(new WindowBorder(GuiControl.WINDOW_BORDER));

		String labelName = "ISBN";
		makeLabel(gridPanel, labelName);
		isbn = new JTextField(15);
		gridPanel.add(isbn);
		isbn.setEditable(false);
		isbn.setText(fieldValues.getProperty(labelName));

		labelName = "No Of Copies";
		makeLabel(gridPanel, labelName);
		Ncopies = new JNumberTextField();
		gridPanel.add(Ncopies);

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
		lowercob = GuiControl.createStandardButtonPanel(buttons);
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

			if (Ncopies.getText().length() > 0) {

				ControllerInterface ci = new SystemController();

				Response resp = ci.addBookCopies(isbn.getText(), Integer.parseInt(Ncopies.getText()));

				if (resp.isStatus()) {
					JOptionPane.showMessageDialog(AddCopiesBook.this, resp.getMessage(), "Info",
							JOptionPane.INFORMATION_MESSAGE);
					BookWindow.INSTANCE.refresh();
					dispose();
					Util.centerFrameOnDesktop(BookWindow.INSTANCE);

				} else {
					JOptionPane.showMessageDialog(AddCopiesBook.this, resp.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			} else {
				JOptionPane.showMessageDialog(AddCopiesBook.this, "Invalid field on form!", "Error",
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
