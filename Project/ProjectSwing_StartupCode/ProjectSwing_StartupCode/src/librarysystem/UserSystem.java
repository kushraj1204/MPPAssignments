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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import business.ControllerInterface;
import business.SystemController;

public class UserSystem extends JFrame implements LibWindow {

	ControllerInterface ci = new SystemController();
	public final static UserSystem INSTANCE = new UserSystem();
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
	private final String USER = "User";
	private final String CREDENTIAl = "Credential";

	// Title
	private final String MAIN_LABEL = "Maintain Users";

	// Buttons
	private final String ADD_BUTN = "Add";
	private final String EDIT_BUTN = "Edit";
	private final String DELETE_BUTN = "Delete";
	private final String SEARCH_BUTN = "Search";
	private final String BACK_TO_MAIN = "Back to Main";

	// table config
	private final String[] DEFAULT_COLUMN_HEADERS = { USER, CREDENTIAl };
	private final int TABLE_WIDTH = GuiControl.SCREEN_WIDTH;
	private final int DEFAULT_TABLE_HEIGHT = Math.round(0.75f * GuiControl.SCREEN_HEIGHT);

	// these numbers specify relative widths of the columns -- they must add up to 1
	private final float[] COL_WIDTH_PROPORTIONS = { 0.4f, 0.2f };

	private static LibWindow[] allWindows = { UserSystem.INSTANCE, LoginWindow.INSTANCE, AllMemberIdsWindow.INSTANCE,
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
		// editButton.addActionListener(new EditButtonListener());

		// delete button
		JButton deleteButton = new JButton(DELETE_BUTN);
		// deleteButton.addActionListener(new DeleteButtonListener());

		// search button
		JButton searchButton = new JButton(SEARCH_BUTN);
		// searchButton.addActionListener(new SearchButtonListener());
		searchButton.setEnabled(false);

		// exit button
		JButton backToMainButton = new JButton(BACK_TO_MAIN);
		// backToMainButton.addActionListener(new BackToMainButtonListener());

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

		// defineComboPanel();
		// middle.add(comboPanel, BorderLayout.NORTH);

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
		List<String[]> theData = ci.allUsers();

		updateModel(theData);
		updateTable();
	}

	private void updateTable() {
		table.setModel(model);
		table.updateUI();
		repaint();

	}

	private void setPathToImage() {
		String currDirectory = System.getProperty("user.dir");
		pathToImage = currDirectory + "\\src\\librarysystem\\library.jpg";
	}

	private void insertSplashImage() {
		ImageIcon image = new ImageIcon(pathToImage);
		mainPanel.add(new JLabel(image));
	}

	private void createMenus() {
		menuBar = new JMenuBar();
		menuBar.setBorder(BorderFactory.createRaisedBevelBorder());
		addMenuItems();
		setJMenuBar(menuBar);
	}

	private void addMenuItems() {
		admin = new JMenu("Admin");
		menuBar.add(admin);
		libraryMembers = new JMenuItem("Library Members");
		// libraryMembers.addActionListener(new LibraryMember());
		// allBookIds = new JMenuItem("All Book Ids");
		// allBookIds.addActionListener(new AllBookIdsListener());
		// allMemberIds = new JMenuItem("All Member Ids");
		// allMemberIds.addActionListener(new AllMemberIdsListener());
		// options.add(login);
		// options.add(allBookIds);
		// options.add(allMemberIds);
		admin.add(libraryMembers);
	}

	class LibraryMemberListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			class MaintainCatalogTypesActionListener implements ActionListener {

				public void actionPerformed(ActionEvent e) {

					UserSystem mct = new UserSystem();
					mct.setVisible(true);
					setVisible(false);

				}
			}
//		    LibraryMember lm = new Li
//			MajorWindow.hideAllWindows();
//			LibraryMember.INSTANCE.init();
//			Util.centerFrameOnDesktop(LibraryMember.INSTANCE);
//			LibraryMember.INSTANCE.setVisible(true);

		}

	}

	class AddButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {

			// no field values need to be passed into AddEditProduct when adding a new
			// product
			// so we create an empty Properties instance
			Properties emptyUserInfo = new Properties();

			AddEditUserSystem addProd = new AddEditUserSystem(GuiControl.ADD_NEW, emptyUserInfo);
			setVisible(false);
			// addProd.setParentWindow(UserSystem.this);
			addProd.setVisible(true);

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
		(new UserSystem()).setVisible(true);
	}

}
