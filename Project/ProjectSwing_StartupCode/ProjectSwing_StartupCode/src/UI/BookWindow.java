package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import business.ControllerInterface;
import business.SystemController;
import dataaccess.Auth;
import librarysystem.GuiControl;

public class BookWindow extends JFrame implements LibWindow {

	ControllerInterface ci = new SystemController();
	public final static BookWindow INSTANCE = new BookWindow();
	JPanel mainPanel;
	JMenuBar menuBar;
	JMenu admin;
	JMenuItem libraryMembers, books, copy;
	String pathToImage;
	private boolean isInitialized = false;
	JTable tablebooks;
	JScrollPane tablePanebooks;
	CustomTableModel modelbooks;
	// JPanels
	JPanel upper, middle, comboPanel, lower;

	// Columns
	private final String ISBN = "ISBN";
	private final String TITLE = "Title";
	private final String AUTHORS = "Authors";
	private final String MAXCHECKOUT = "Max Checkout";
	private final String NCOPIES = "No Of Copies";

	// Title
	private final String MAIN_LABEL = "Manage Books";

	// Buttons
	private final String ADD_BUTN = "Add";
	private final String ADD_COPY = "Add Copy";
	private final String EDIT_BUTN = "Edit";
	private final String DELETE_BUTN = "Delete";
	private final String SEARCH_BUTN = "Search";
	private final String BACK_TO_MAIN = "Back to Main";

	// table config
	private final String[] DEFAULT_COLUMN_HEADERS = { ISBN, TITLE, AUTHORS, MAXCHECKOUT, NCOPIES };
	private final int TABLE_WIDTH = GuiControl.SCREEN_WIDTH;
	private final int DEFAULT_TABLE_HEIGHT = Math.round(0.75f * GuiControl.SCREEN_HEIGHT);

	// these numbers specify relative widths of the columns -- they must add up to 1
	private final float[] COL_WIDTH_PROPORTIONS = { 0.2f, 0.2f, 0.2f, 0.2f, 0.2f };

	private static LibWindow[] allWindows = { BookWindow.INSTANCE, LoginWindow.INSTANCE, AllMemberIdsWindow.INSTANCE,
			AllBookIdsWindow.INSTANCE, MajorWindow.INSTANCE };

	public static void hideAllWindows() {
		for (LibWindow frame : allWindows) {
			frame.setVisible(false);
		}
	}

	public void init() {
		initializeWindow();
		formatContentPane();
		// setPathToImage();
		// insertSplashImage();
		// defineMainPanel();
		// createMenus();
		// pack();
		setSize(700, 700);
		isInitialized = true;
	}

	public void refresh() {
		updateModel();
	}

	private void formatContentPane() {
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
		getContentPane().add(mainPanel);
	}

	private void initializeWindow() {

		setSize(GuiControl.SCREEN_WIDTH, GuiControl.SCREEN_HEIGHT);
		GuiControl.centerFrameOnDesktop(this);

	}

	// buttons
	public void defineLowerPanel() {
		// add button
		JButton addButton = new JButton(ADD_BUTN);
		addButton.addActionListener(new AddButtonListener());

		// add copies
		JButton addcopiesButton = new JButton(ADD_COPY);
		addcopiesButton.addActionListener(new AddCopyListener());

		// edit button
		JButton editButton = new JButton(EDIT_BUTN);
		editButton.setEnabled(false);
		// editButton.addActionListener(new EditButtonListener());

		// delete button
		JButton deleteButton = new JButton(DELETE_BUTN);
		deleteButton.setEnabled(false);
		// deleteButton.addActionListener(new DeleteButtonListener());

		// search button
		JButton searchButton = new JButton(SEARCH_BUTN);
		// searchButton.addActionListener(new SearchButtonListener());
		searchButton.setEnabled(false);

		// exit button
		JButton backToMainButton = new JButton(BACK_TO_MAIN);
		backToMainButton.addActionListener(new BackToMainButtonListener());

		// create lower panel
		JButton[] buttons = { addButton, addcopiesButton, editButton, deleteButton, searchButton, backToMainButton };
		lower = GuiControl.createStandardButtonPanel(buttons);
	}

	// label
	public void defineUpperPanel() {
		upper = new JPanel();
		upper.setBackground(GuiControl.FILLER_COLOR);
		upper.setLayout(new FlowLayout(FlowLayout.CENTER));

		JLabel mainLabel = new JLabel(MAIN_LABEL);
		Font f = GuiControl.makeVeryLargeFont(mainLabel.getFont());
		f = GuiControl.makeBoldFont(f);
		mainLabel.setFont(f);
		upper.add(mainLabel);
	}

	// middle -- table and combo box
	public void defineMiddlePanel() {

		middle = new JPanel();
		middle.setLayout(new BorderLayout());

		// table
		createTableAndTablePane();
		GuiControl.createCustomColumns(tablebooks, TABLE_WIDTH, COL_WIDTH_PROPORTIONS, DEFAULT_COLUMN_HEADERS);

		middle.add(GuiControl.createStandardTablePanePanel(tablebooks, tablePanebooks), BorderLayout.CENTER);

	}

	private void createTableAndTablePane() {
		tablebooks = new JTable(modelbooks);

		updateModel();
		tablePanebooks = new JScrollPane();
		tablePanebooks.setPreferredSize(new Dimension(TABLE_WIDTH, DEFAULT_TABLE_HEIGHT));
		tablePanebooks.getViewport().add(tablebooks);

	}

	public void updateModel(List<String[]> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i)[0]);
		}
		modelbooks = new CustomTableModel();
		modelbooks.setTableValues(list);
	}

	/**
	 * If default data is being used, this method obtains it and then passes it to
	 * updateModel(List). If real data is being used, the public updateModel(List)
	 * should be called by the controller class.
	 */
	private void updateModel() {
		ControllerInterface ci = new SystemController();
		List<String[]> theData = ci.allBooksIdsTable();
		updateModel(theData);
		updateTable();
	}

	private void updateTable() {
		tablebooks.setModel(modelbooks);
		tablebooks.updateUI();
		repaint();

	}

	class AddCopyListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {
			int selectedRow = tablebooks.getSelectedRow();
			System.out.println("Selected Row" + selectedRow);

			if (selectedRow >= 0) {
				String[] fldNames = { "isbn" };

				Properties UserInfo = new Properties();

				// index for isbn
				UserInfo.setProperty("isbn", (String) modelbooks.getValueAt(selectedRow, 0));



				AddCopiesBook editProd = new AddCopiesBook(GuiControl.EDIT, UserInfo);
				editProd.setVisible(true);

			} else {
				JOptionPane.showMessageDialog(BookWindow.this, "Need to select a valid row!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
//			dispose();
//			CheckOutBookWindow.INSTANCE.refresh();
//			Util.centerFrameOnDesktop(CheckOutBookWindow.INSTANCE);

		}
	}

	class AddButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {

			// no field values need to be passed into AddEditProduct when adding a new
			// product
			// so we create an empty Properties instance
			Properties emptyBook = new Properties();

			AddEditBook addProd = new AddEditBook(GuiControl.ADD_NEW, emptyBook);
			// setVisible(false);
			// addProd.setParentWindow(UserSystem.this);
			addProd.setVisible(true);

		}

	}

	class EditButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {

			int selectedRow = tablebooks.getSelectedRow();
			if (selectedRow >= 0) {
				String[] fldNames = { "isbn" };

				Properties BookMember = new Properties();

				// index for id User
				BookMember.setProperty("isbn", (String) modelbooks.getValueAt(selectedRow, 0));

				// AddEditLibraryMember editProd = new AddEditLibraryMember(GuiControl.EDIT,
				// LibraryMember);
				// editProd.setVisible(true);

			} else {
				JOptionPane.showMessageDialog(BookWindow.this, "Need to select a valid row!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	class BackToMainButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {

			LibrarySystemWindow.hideAllWindows();
			MajorWindow.INSTANCE.init();
			Util.centerFrameOnDesktop(MajorWindow.INSTANCE);
			MajorWindow.INSTANCE.setVisible(true);

		}

	}

	class DeleteButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {

			ControllerInterface ci = new SystemController();
			int selectedRow = tablebooks.getSelectedRow();
			if (selectedRow >= 0) {
				// Students: code goes here.
				ci.deleteBook(modelbooks.getValueAt(selectedRow, 0).toString());
				updateModel();

			} else {
				JOptionPane.showMessageDialog(BookWindow.this, "Need to select a valid row!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		}

	}

	@Override
	public boolean isInitialized() {
		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		isInitialized = val;

	}

	public static void main(String[] args) {
		(new BookWindow()).setVisible(true);
	}

}
