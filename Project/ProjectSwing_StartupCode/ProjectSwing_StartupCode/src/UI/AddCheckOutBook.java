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
import dataaccess.Auth;
import dataaccess.User;
import librarysystem.GuiControl;

public class AddCheckOutBook extends JFrame {

	private static final long serialVersionUID = 1L;
	/** final value of label will be set in the constructor */
	private String mainLabela = " CheckOut Book";
	private final String SAVE_BUTN = "Save";
	private final String BACK_BUTN = "Close";

	private JTextField idlibraryMember;
	private JTextField isbnBook;

	/** value is "Add New" or "Edit" */
	private String addOrEdit = GuiControl.ADD_NEW;

	/** map of initial field values */
	private Properties fieldValues;

	// JPanels
	JPanel mainPanelcob;
	JPanel uppercob, middlecob, lowercob;

	public AddCheckOutBook(String addOrEdit, Properties fieldValues) {
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
		return addOrEdit + " " + mainLabela;
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

		String labelName = "Library Member Id";
		makeLabel(gridPanel, labelName);
		idlibraryMember = new JTextField(15);
		gridPanel.add(idlibraryMember);

		labelName = "Book ISBN";
		makeLabel(gridPanel, labelName);
		isbnBook = new JTextField(15);
		gridPanel.add(isbnBook);

	}

	// buttons
	public void defineLowerPanel() {
		// proceed button
		JButton saveButton = new JButton(SAVE_BUTN);
		saveButton.addActionListener(new SaveListener());

		// back to check out book
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

			if (idlibraryMember.getText().length() > 0 || isbnBook.getText().length() > 0) {

				ControllerInterface ci = new SystemController();

				Response resp = ci.checkoutBook(idlibraryMember.getText(), isbnBook.getText());
				if (resp.isStatus()) {
					CheckOutBookWindow.INSTANCE.refresh();

					JOptionPane.showMessageDialog(AddCheckOutBook.this, resp.getMessage(), "Info",
							JOptionPane.INFORMATION_MESSAGE);

					dispose();
					Util.centerFrameOnDesktop(CheckOutBookWindow.INSTANCE);

				} else {
					JOptionPane.showMessageDialog(AddCheckOutBook.this, resp.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(AddCheckOutBook.this, "Invalid field on form!", "Error",
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
