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

public class CheckOutBookWindow extends JFrame implements LibWindow {

	ControllerInterface ci = new SystemController();
	public final static CheckOutBookWindow INSTANCE = new CheckOutBookWindow();
	JPanel mainPanelcow;


	private boolean isInitialized = false;
	JTable tablecow;
	JScrollPane tablePanecow;
	CustomTableModel modelcow;
	// JPanels
	JPanel uppercow, middlecow, comboPanelcow, lowercow;

	// Columns
	private final String ID = "Id";
	private final String LIBRARYMEMBER = "Library member";
	private final String BOOK = "Book(s)";
	private final String DUEDATE = "Due Date";
	private final String RETURNDATE = "Return Date";

	// Title
	private final String MAIN_LABEL = "Manage CheckOut";

	// Buttons
	private final String ADD_BUTN = "Add";
	private final String EDIT_BUTN = "Edit";
	private final String DELETE_BUTN = "Delete";
	private final String SEARCH_BUTN = "Search";
	private final String BACK_TO_MAIN = "Back to Main";

	// table config
	private final String[] DEFAULT_COLUMN_HEADERS = { ID, LIBRARYMEMBER, BOOK, DUEDATE, RETURNDATE };
	private final int TABLE_WIDTH = GuiControl.SCREEN_WIDTH;
	private final int DEFAULT_TABLE_HEIGHT = Math.round(0.75f * GuiControl.SCREEN_HEIGHT);

	// these numbers specify relative widths of the columns -- they must add up to 1
	private final float[] COL_WIDTH_PROPORTIONS = { 0.2f, 0.2f, 0.2f, 0.2f, 0.2f };

	public void init() {
		initializeWindow();
		formatContentPane();
		setSize(700, 700);
		isInitialized = true;
	}

	public void refresh() {
		updateModel();
	}

	private void formatContentPane() {
		mainPanelcow = new JPanel();
		mainPanelcow.setLayout(new BorderLayout());
		mainPanelcow.setBackground(GuiControl.FILLER_COLOR);
		mainPanelcow.setBorder(new WindowBorder(GuiControl.WINDOW_BORDER));
		defineUpperPanel();
		defineMiddlePanel();
		defineLowerPanel();
		mainPanelcow.add(uppercow, BorderLayout.NORTH);
		mainPanelcow.add(middlecow, BorderLayout.CENTER);
		mainPanelcow.add(lowercow, BorderLayout.SOUTH);
		getContentPane().add(mainPanelcow);
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
		// editButton.addActionListener(new EditButtonListener());
		editButton.setEnabled(false);

		// delete button
		JButton deleteButton = new JButton(DELETE_BUTN);
		// deleteButton.addActionListener(new DeleteButtonListener());
		deleteButton.setEnabled(false);
		// search button
		JButton searchButton = new JButton(SEARCH_BUTN);
		// searchButton.addActionListener(new SearchButtonListener());
		searchButton.setEnabled(false);

		// exit button
		JButton backToMainButton = new JButton(BACK_TO_MAIN);
		backToMainButton.addActionListener(new BackToMainButtonListener());

		// create lower panel
		JButton[] buttons = { addButton, editButton, deleteButton, searchButton, backToMainButton };
		lowercow = GuiControl.createStandardButtonPanel(buttons);
	}

	// label
	public void defineUpperPanel() {
		uppercow = new JPanel();
		uppercow.setBackground(GuiControl.FILLER_COLOR);
		uppercow.setLayout(new FlowLayout(FlowLayout.CENTER));

		JLabel mainLabel = new JLabel(MAIN_LABEL);
		Font f = GuiControl.makeVeryLargeFont(mainLabel.getFont());
		f = GuiControl.makeBoldFont(f);
		mainLabel.setFont(f);
		uppercow.add(mainLabel);
	}

	// middle -- table and combo box
	public void defineMiddlePanel() {

		middlecow = new JPanel();
		middlecow.setLayout(new BorderLayout());

		// table
		createTableAndTablePane();
		GuiControl.createCustomColumns(tablecow, TABLE_WIDTH, COL_WIDTH_PROPORTIONS, DEFAULT_COLUMN_HEADERS);

		middlecow.add(GuiControl.createStandardTablePanePanel(tablecow, tablePanecow), BorderLayout.CENTER);

	}

	private void createTableAndTablePane() {
		tablecow = new JTable(modelcow);
		updateModel();
		tablePanecow = new JScrollPane();
		tablePanecow.setPreferredSize(new Dimension(TABLE_WIDTH, DEFAULT_TABLE_HEIGHT));
		tablePanecow.getViewport().add(tablecow);

	}

	public void updateModel(List<String[]> list) {
		modelcow = new CustomTableModel();
		modelcow.setTableValues(list);
	}

	/**
	 * If default data is being used, this method obtains it and then passes it to
	 * updateModel(List). If real data is being used, the public updateModel(List)
	 * should be called by the controller class.
	 */
	private void updateModel() {
		ControllerInterface ci = new SystemController();
		List<String[]> theData = ci.allCheckOutTable();
		updateModel(theData);
		updateTable();
	}

	private void updateTable() {
		tablecow.setModel(modelcow);
		tablecow.updateUI();
		repaint();

	}

	class AddButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {


			Properties emptyCheckOutBook = new Properties();
			AddCheckOutBook addCheckOutBook = new AddCheckOutBook(GuiControl.ADD_NEW, emptyCheckOutBook);
			addCheckOutBook.setVisible(true);

		}

	}

	class BackToMainButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {

			LibrarySystemWindow.hideAllWindows();
			if(!MajorWindow.INSTANCE.isInitialized())
			MajorWindow.INSTANCE.init();
			Util.centerFrameOnDesktop(MajorWindow.INSTANCE);
			MajorWindow.INSTANCE.setVisible(true);

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
		(new CheckOutBookWindow()).setVisible(true);
	}

}
