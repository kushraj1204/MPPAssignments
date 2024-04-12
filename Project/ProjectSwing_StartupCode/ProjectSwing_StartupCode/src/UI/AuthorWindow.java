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

public class AuthorWindow extends JFrame implements LibWindow {

	ControllerInterface ci = new SystemController();
	public final static AuthorWindow INSTANCE = new AuthorWindow();
	JPanel mainPanelauthor;

	private boolean isInitialized = false;
	JTable tableauthor;
	JScrollPane tablePaneauthors;
	CustomTableModel modelauthors;
	// JPanels
	JPanel upperauthors, middleauthors, comboPanelauthors, lowerauthors;

	// Columns
	private final String IDAUTHOR = "id author";
	private final String FIRSTNAME = "first name";
	private final String LASTNAME = "last name";
	private final String BIO = "bio";
	private final String TELEPHONE = "telephone";

	// Title
	private final String MAIN_LABEL = "Maintain Authors";

	// Buttons
	private final String ADD_BUTN = "Add";
	private final String EDIT_BUTN = "Edit";
	private final String DELETE_BUTN = "Delete";
	private final String SEARCH_BUTN = "Search";
	private final String BACK_TO_MAIN = "Back to Main";

	// table config
	private final String[] DEFAULT_COLUMN_HEADERS = { IDAUTHOR, FIRSTNAME, LASTNAME, BIO, TELEPHONE };
	private final int TABLE_WIDTH = GuiControl.SCREEN_WIDTH;
	private final int DEFAULT_TABLE_HEIGHT = Math.round(0.75f * GuiControl.SCREEN_HEIGHT);

	// these numbers specify relative widths of the columns -- they must add up to 1
	private final float[] COL_WIDTH_PROPORTIONS = { 0.2f, 0.2f, 0.2f, 0.2f, 0.2f };

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
		mainPanelauthor = new JPanel();
		mainPanelauthor.setLayout(new BorderLayout());
		mainPanelauthor.setBackground(GuiControl.FILLER_COLOR);
		mainPanelauthor.setBorder(new WindowBorder(GuiControl.WINDOW_BORDER));
		defineUpperPanel();
		defineMiddlePanel();
		defineLowerPanel();
		mainPanelauthor.add(upperauthors, BorderLayout.NORTH);
		mainPanelauthor.add(middleauthors, BorderLayout.CENTER);
		mainPanelauthor.add(lowerauthors, BorderLayout.SOUTH);
		getContentPane().add(mainPanelauthor);
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
		lowerauthors = GuiControl.createStandardButtonPanel(buttons);
	}

	// label
	public void defineUpperPanel() {
		upperauthors = new JPanel();
		upperauthors.setBackground(GuiControl.FILLER_COLOR);
		upperauthors.setLayout(new FlowLayout(FlowLayout.CENTER));

		JLabel mainLabel = new JLabel(MAIN_LABEL);
		Font f = GuiControl.makeVeryLargeFont(mainLabel.getFont());
		f = GuiControl.makeBoldFont(f);
		mainLabel.setFont(f);
		upperauthors.add(mainLabel);
	}

	// middle -- table and combo box
	public void defineMiddlePanel() {

		middleauthors = new JPanel();
		middleauthors.setLayout(new BorderLayout());

		// table
		createTableAndTablePane();
		GuiControl.createCustomColumns(tableauthor, TABLE_WIDTH, COL_WIDTH_PROPORTIONS, DEFAULT_COLUMN_HEADERS);

		middleauthors.add(GuiControl.createStandardTablePanePanel(tableauthor, tablePaneauthors), BorderLayout.CENTER);

	}

	private void createTableAndTablePane() {
		tableauthor = new JTable(modelauthors);

		updateModel();
		tablePaneauthors = new JScrollPane();
		tablePaneauthors.setPreferredSize(new Dimension(TABLE_WIDTH, DEFAULT_TABLE_HEIGHT));
		tablePaneauthors.getViewport().add(tableauthor);

	}

	public void updateModel(List<String[]> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i)[0]);
		}
		modelauthors = new CustomTableModel();
		modelauthors.setTableValues(list);
	}

	/**
	 * If default data is being used, this method obtains it and then passes it to
	 * updateModel(List). If real data is being used, the public updateModel(List)
	 * should be called by the controller class.
	 */
	private void updateModel() {
		ControllerInterface ci = new SystemController();
		List<String[]> theData = ci.allAuthorTable();
		updateModel(theData);
		updateTable();
	}

	private void updateTable() {
		tableauthor.setModel(modelauthors);
		tableauthor.updateUI();
		repaint();

	}

	class AddButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {

			// no field values need to be passed into AddEditProduct when adding a new
			// product
			// so we create an empty Properties instance
			Properties emptyAuthor = new Properties();

			AddEditAuthor addProd = new AddEditAuthor(GuiControl.ADD_NEW, emptyAuthor);
			// setVisible(false);
			// addProd.setParentWindow(UserSystem.this);
			addProd.setVisible(true);

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

	@Override
	public boolean isInitialized() {
		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		isInitialized = val;

	}

	public static void main(String[] args) {
		(new AuthorWindow()).setVisible(true);
	}

}
