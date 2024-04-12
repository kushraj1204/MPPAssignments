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

public class LibraryMemberWindow extends JFrame implements LibWindow {

	ControllerInterface ci = new SystemController();

	public final static LibraryMemberWindow INSTANCE = new LibraryMemberWindow();

	JPanel mainPanellm;
	JMenuBar menuBarlm;
	JMenu adminlm;

	private boolean isInitialized = false;
	JTable tablelm;
	JScrollPane tablePanelm;
	CustomTableModel modellm;

	// JPanels
	JPanel upperlm, middlelm, comboPanellm, lowerlm;

	// Columns
	private final String ID = "Id";
	private final String FIRSTNAME = "First name";
	private final String LASTNAME = "Last name";
	private final String TELEPHONE = "Telephone";
	private final String ADDRESS = "Address";

	// Title
	private final String MAIN_LABEL = "Maintain Members";

	// Buttons
	private final String ADD_BUTN = "Add";
	private final String EDIT_BUTN = "Edit";
	private final String DELETE_BUTN = "Delete";
	private final String SEARCH_BUTN = "Search";
	private final String BACK_TO_MAIN = "Back to Main";

	// Table Config
	private final String[] DEFAULT_COLUMN_HEADERS = { ID, FIRSTNAME, LASTNAME, TELEPHONE, ADDRESS };
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
		mainPanellm = new JPanel();
		mainPanellm.setLayout(new BorderLayout());
		mainPanellm.setBackground(GuiControl.FILLER_COLOR);
		mainPanellm.setBorder(new WindowBorder(GuiControl.WINDOW_BORDER));
		defineUpperPanel();
		defineMiddlePanel();
		defineLowerPanel();
		mainPanellm.add(upperlm, BorderLayout.NORTH);
		mainPanellm.add(middlelm, BorderLayout.CENTER);
		mainPanellm.add(lowerlm, BorderLayout.SOUTH);
		getContentPane().add(mainPanellm);
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
		lowerlm = GuiControl.createStandardButtonPanel(buttons);
	}

	// label
	public void defineUpperPanel() {
		upperlm = new JPanel();
		upperlm.setBackground(GuiControl.FILLER_COLOR);
		upperlm.setLayout(new FlowLayout(FlowLayout.CENTER));

		JLabel mainLabel = new JLabel(MAIN_LABEL);
		Font f = GuiControl.makeVeryLargeFont(mainLabel.getFont());
		f = GuiControl.makeBoldFont(f);
		mainLabel.setFont(f);
		upperlm.add(mainLabel);
	}

	// middle -- table and combo box
	public void defineMiddlePanel() {

		middlelm = new JPanel();
		middlelm.setLayout(new BorderLayout());

		// table
		createTableAndTablePane();
		GuiControl.createCustomColumns(tablelm, TABLE_WIDTH, COL_WIDTH_PROPORTIONS, DEFAULT_COLUMN_HEADERS);

		middlelm.add(GuiControl.createStandardTablePanePanel(tablelm, tablePanelm), BorderLayout.CENTER);

	}

	private void createTableAndTablePane() {
		tablelm = new JTable(modellm);

		updateModel();
		tablePanelm = new JScrollPane();
		tablePanelm.setPreferredSize(new Dimension(TABLE_WIDTH, DEFAULT_TABLE_HEIGHT));
		tablePanelm.getViewport().add(tablelm);

	}

	public void updateModel(List<String[]> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i)[0]);
		}
		modellm = new CustomTableModel();
		modellm.setTableValues(list);
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
		tablelm.setModel(modellm);
		tablelm.updateUI();
		repaint();

	}

	class AddButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {

			// no field values need to be passed into AddEditLibraryMember when adding a new
			// LibraryMember
			// so we create an empty Properties instance
			Properties emptyLibraryMember = new Properties();

			AddEditLibraryMember addLibraryMember = new AddEditLibraryMember(GuiControl.ADD_NEW, emptyLibraryMember);
			// setVisible(false);
			// addProd.setParentWindow(UserSystem.this);
			addLibraryMember.setVisible(true);

		}

	}

	class EditButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {

			int selectedRow = tablelm.getSelectedRow();

			if (selectedRow >= 0) {

				Properties LibraryMember = new Properties();

				// index for id Library Member
				LibraryMember.setProperty("LibraryMember Id", (String) modellm.getValueAt(selectedRow, 0));

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
			int selectedRow = tablelm.getSelectedRow();

			if (selectedRow >= 0) {
				ci.deleteLibraryMember(modellm.getValueAt(selectedRow, 0).toString());
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
