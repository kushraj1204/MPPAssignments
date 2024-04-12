package librarysystem;

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

public class LibraryMemberWindow extends JFrame implements LibWindow {

	ControllerInterface ci = new SystemController();
	public final static LibraryMemberWindow INSTANCE = new LibraryMemberWindow();
	JPanel mainPanel;
	JMenuBar menuBar;
	JMenu admin;
	JMenuItem libraryMembers, books, copy;
	String pathToImage;
	private boolean isInitialized = false;
	JTable table;
	JScrollPane tablePane;
	CustomTableModel model;
	// JPanels
	JPanel upper, middle, comboPanel, lower;

	// Columns
	private final String ID = "id";
	private final String FIRSTNAME = "first name";
	private final String LASTNAME = "last name";
	private final String TELEPHONE = "telephone";
	private final String ADDRESS = "address";

	// Title
	private final String MAIN_LABEL = "Maintain Members";

	// Buttons
	private final String ADD_BUTN = "Add";
	private final String EDIT_BUTN = "Edit";
	private final String DELETE_BUTN = "Delete";
	private final String SEARCH_BUTN = "Search";
	private final String BACK_TO_MAIN = "Back to Main";

	// table config
	private final String[] DEFAULT_COLUMN_HEADERS = { ID, FIRSTNAME, LASTNAME, TELEPHONE, ADDRESS };
	private final int TABLE_WIDTH = GuiControl.SCREEN_WIDTH;
	private final int DEFAULT_TABLE_HEIGHT = Math.round(0.75f * GuiControl.SCREEN_HEIGHT);

	// these numbers specify relative widths of the columns -- they must add up to 1
	private final float[] COL_WIDTH_PROPORTIONS = { 0.2f, 0.2f, 0.2f, 0.2f, 0.2f };

	private static LibWindow[] allWindows = { LibraryMemberWindow.INSTANCE, LoginWindow.INSTANCE, AllMemberIdsWindow.INSTANCE,
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

		// edit button
		JButton editButton = new JButton(EDIT_BUTN);
		editButton.addActionListener(new EditButtonListener());

		// delete button
		JButton deleteButton = new JButton(DELETE_BUTN);
		deleteButton.addActionListener(new DeleteButtonListener());

		// search button
		JButton searchButton = new JButton(SEARCH_BUTN);
		// searchButton.addActionListener(new SearchButtonListener());
		searchButton.setEnabled(false);

		// exit button
		JButton backToMainButton = new JButton(BACK_TO_MAIN);
		backToMainButton.addActionListener(new BackToMainButtonListener());

		// create lower panel
		JButton[] buttons = { addButton, editButton, deleteButton, searchButton, backToMainButton };
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
		GuiControl.createCustomColumns(table, TABLE_WIDTH, COL_WIDTH_PROPORTIONS, DEFAULT_COLUMN_HEADERS);

		middle.add(GuiControl.createStandardTablePanePanel(table, tablePane), BorderLayout.CENTER);

	}

	private void createTableAndTablePane() {
		table = new JTable(model);

		updateModel();
		tablePane = new JScrollPane();
		tablePane.setPreferredSize(new Dimension(TABLE_WIDTH, DEFAULT_TABLE_HEIGHT));
		tablePane.getViewport().add(table);

	}

	public void updateModel(List<String[]> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i)[0]);
		}
		model = new CustomTableModel();
		model.setTableValues(list);
	}

	/**
	 * If default data is being used, this method obtains it and then passes it to
	 * updateModel(List). If real data is being used, the public updateModel(List)
	 * should be called by the controller class.
	 */
	private void updateModel() {
		ControllerInterface ci = new SystemController();
		List<String[]> theData = ci.allMemberIdsTable();
		updateModel(theData);
		updateTable();
	}

	private void updateTable() {
		table.setModel(model);
		table.updateUI();
		repaint();

	}

	class AddButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {

			// no field values need to be passed into AddEditProduct when adding a new
			// product
			// so we create an empty Properties instance
			Properties emptyLibraryMember = new Properties();

			AddEditLibraryMember addProd = new AddEditLibraryMember(GuiControl.ADD_NEW, emptyLibraryMember);
			// setVisible(false);
			// addProd.setParentWindow(UserSystem.this);
			addProd.setVisible(true);

		}

	}

	class EditButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {

			int selectedRow = table.getSelectedRow();
			if (selectedRow >= 0) {
				String[] fldNames = { "id LibraryMember" };

				Properties LibraryMember = new Properties();

				// index for id User
				LibraryMember.setProperty("id LibraryMember", (String) model.getValueAt(selectedRow, 0));

				AddEditLibraryMember editProd = new AddEditLibraryMember(GuiControl.EDIT, LibraryMember);
				editProd.setVisible(true);

			} else {
				JOptionPane.showMessageDialog(LibraryMemberWindow.this, "Need to select a valid row!", "Error",
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
			int selectedRow = table.getSelectedRow();
			if (selectedRow >= 0) {
				// Students: code goes here.
				ci.deleteLibraryMember(model.getValueAt(selectedRow, 0).toString());
				updateModel();

			} else {
				JOptionPane.showMessageDialog(LibraryMemberWindow.this, "Need to select a valid row!", "Error",
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
		(new LibraryMemberWindow()).setVisible(true);
	}

}
