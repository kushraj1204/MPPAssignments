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

public class UserSystem extends JFrame implements LibWindow {

	ControllerInterface ci = new SystemController();
	public final static UserSystem INSTANCE = new UserSystem();

	private boolean isInitialized = false;
	JPanel mainPanelus;
	JTable tableus;
	JScrollPane tablePaneus;
	CustomTableModel modelus;

	// JPanels
	JPanel upperus, middleus, comboPanelus, lowerus;

	// Columns
	private final String USER = "User";
	private final String CREDENTIAl = "Credential";

	// Title
	private final String MAIN_LABEL = "Manage Users";

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
		mainPanelus = new JPanel();
		mainPanelus.setLayout(new BorderLayout());
		mainPanelus.setBackground(GuiControl.FILLER_COLOR);
		mainPanelus.setBorder(new WindowBorder(GuiControl.WINDOW_BORDER));
		defineUpperPanel();
		defineMiddlePanel();
		defineLowerPanel();
		mainPanelus.add(upperus, BorderLayout.NORTH);
		mainPanelus.add(middleus, BorderLayout.CENTER);
		mainPanelus.add(lowerus, BorderLayout.SOUTH);
		getContentPane().add(mainPanelus);
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
		lowerus = GuiControl.createStandardButtonPanel(buttons);
	}

	// label
	public void defineUpperPanel() {

		upperus = new JPanel();
		upperus.setBackground(GuiControl.FILLER_COLOR);
		upperus.setLayout(new FlowLayout(FlowLayout.CENTER));

		JLabel mainLabel = new JLabel(MAIN_LABEL);
		Font f = GuiControl.makeVeryLargeFont(mainLabel.getFont());
		f = GuiControl.makeBoldFont(f);
		mainLabel.setFont(f);
		upperus.add(mainLabel);

	}

	// middle -- table and combo box
	public void defineMiddlePanel() {

		middleus = new JPanel();
		middleus.setLayout(new BorderLayout());

		// table
		createTableAndTablePane();
		GuiControl.createCustomColumns(tableus, TABLE_WIDTH, COL_WIDTH_PROPORTIONS, DEFAULT_COLUMN_HEADERS);

		middleus.add(GuiControl.createStandardTablePanePanel(tableus, tablePaneus), BorderLayout.CENTER);

	}

	private void createTableAndTablePane() {

		tableus = new JTable(modelus);
		updateModel();
		tablePaneus = new JScrollPane();
		tablePaneus.setPreferredSize(new Dimension(TABLE_WIDTH, DEFAULT_TABLE_HEIGHT));
		tablePaneus.getViewport().add(tableus);

	}

	public void updateModel(List<String[]> list) {
		modelus = new CustomTableModel();
		modelus.setTableValues(list);
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
		tableus.setModel(modelus);
		tableus.updateUI();
		repaint();
	}

	class AddButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {

			Properties emptyUserInfo = new Properties();
			AddEditUserSystem addProd = new AddEditUserSystem(GuiControl.ADD_NEW, emptyUserInfo);
			addProd.setVisible(true);

		}

	}

	class EditButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {

			int selectedRow = tableus.getSelectedRow();
			System.out.println("Selected Row" + selectedRow);

			if (selectedRow >= 0) {

				String[] fldNames = { "id User" };
				Properties UserInfo = new Properties();
				// index for id User
				UserInfo.setProperty("id User", (String) modelus.getValueAt(selectedRow, 0));
				// index for Auth
				UserInfo.setProperty("Auth", (String) modelus.getValueAt(selectedRow, 1));
				AddEditUserSystem editProd = new AddEditUserSystem(GuiControl.EDIT, UserInfo);
				editProd.setVisible(true);

			} else {
				JOptionPane.showMessageDialog(UserSystem.this, "Need to select a valid row!", "Error",
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
			int selectedRow = tableus.getSelectedRow();
			System.out.println("Selected Row" + selectedRow);
			if (selectedRow >= 0) {
				ci.deleteUser(modelus.getValueAt(selectedRow, 0).toString());
				updateModel();
			} else {
				JOptionPane.showMessageDialog(UserSystem.this, "Need to select a valid row!", "Error",
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
		(new UserSystem()).setVisible(true);
	}

}
