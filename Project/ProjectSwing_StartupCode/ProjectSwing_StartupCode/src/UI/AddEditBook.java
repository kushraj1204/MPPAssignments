package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
import librarysystem.Util;

public class AddEditBook extends JFrame {

	private static final long serialVersionUID = 1L;
	/** final value of label will be set in the constructor */
	private String mainLabel = " Book";
	private final String SAVE_BUTN = "Save";
	private final String BACK_BUTN = "Close";

	private JTextField isbn;
	private JTextField title;
	private JTextField author;
	private JNumberTextField numberofcopies;
	private JComboBox maxcheckoutGroupField;

	private String maxcheckoutGroup;

	/** value is "Add New" or "Edit" */
	private String addOrEdit = GuiControl.ADD_NEW;

	/** map of initial field values */
	private Properties fieldValues;

	// Table
	JTable tableauthor, tableauthorchoosed;
	JScrollPane tablePaneauthors, tablePaneauthorschoosed;
	CustomTableModel modelauthors, modelauthorschoosed;

	// Columns
	private final String IDAUTHOR = "Author Id";
	private final String FIRSTNAME = "First name";
	private final String LASTNAME = "Last name";
	private final String BIO = "Bio";
	private final String TELEPHONE = "Telephone";

	// Title
	private final String MAIN_LABEL = "Maintain Authors";

	// table config
	private final String[] DEFAULT_COLUMN_HEADERS = { IDAUTHOR, FIRSTNAME, LASTNAME, BIO, TELEPHONE };
	private final int TABLE_WIDTH = GuiControl.SCREEN_WIDTH;
	private final int DEFAULT_TABLE_HEIGHT = Math.round(0.75f * GuiControl.SCREEN_HEIGHT);

	// these numbers specify relative widths of the columns -- they must add up to 1
	private final float[] COL_WIDTH_PROPORTIONS = { 0.2f, 0.2f, 0.2f, 0.2f, 0.2f };

	// JPanels
	JPanel mainPanel;
	JPanel upper, middle, lower;

	//
	List<String[]> dataBooksChoosed = new ArrayList<String[]>();

	public AddEditBook(String addOrEdit, Properties fieldValues) {
		this.addOrEdit = addOrEdit;
		this.fieldValues = fieldValues;
		initializeWindow();
		defineMainPanel();
		getContentPane().add(mainPanel);

	}

	private void initializeWindow() {

		setSize(Math.round(.8f * GuiControl.SCREEN_WIDTH), Math.round(.8f * GuiControl.SCREEN_HEIGHT));
		setSize(1500, 800);
		GuiControl.centerFrameOnDesktop(this);

	}

	private void defineMainPanel() {
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

	}

	// label
	public void defineUpperPanel() {
		upper = new JPanel();
		upper.setBackground(GuiControl.FILLER_COLOR);
		upper.setLayout(new FlowLayout(FlowLayout.CENTER));

		JLabel mainLabel = new JLabel(finalMainLabelName());
		Font f = GuiControl.makeVeryLargeFont(mainLabel.getFont());
		f = GuiControl.makeBoldFont(f);
		mainLabel.setFont(f);
		upper.add(mainLabel);
	}

	private String finalMainLabelName() {
		return addOrEdit + " " + mainLabel;
	}

	// table
	public void defineMiddlePanel() {
		middle = new JPanel();
		middle.setBackground(GuiControl.FILLER_COLOR);
		middle.setLayout(new FlowLayout(FlowLayout.CENTER));
		JPanel gridPanel = new JPanel();
		gridPanel.setBackground(GuiControl.SCREEN_BACKGROUND);
		middle.add(gridPanel);
		GridLayout gl = new GridLayout(8, 4);
		gl.setHgap(8);
		gl.setVgap(8);
		gridPanel.setLayout(gl);
		gridPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		gridPanel.setBorder(new WindowBorder(GuiControl.WINDOW_BORDER));

		String labelName = "ISBN";
		makeLabel(gridPanel, labelName);
		isbn = new JTextField(15);
		isbn.setText(fieldValues.getProperty(labelName));
		gridPanel.add(isbn);
		isbn.setEditable(true);

		labelName = "Title";
		makeLabel(gridPanel, labelName);
		title = new JTextField(15);
		gridPanel.add(title);

		// catalog group is different from the other fields
		// because it plays a different role in MaintainCatalog
		// so it is set differently
		labelName = "Max Days";
		makeLabel(gridPanel, labelName);
		// System.out.println(fieldValues.getProperty("Auth"));
		maxcheckoutGroupField = new JComboBox();
		maxcheckoutGroupField.addItem("7");
		maxcheckoutGroupField.addItem("21");

		if (fieldValues.getProperty("MaxDays") != null) {
			switch (fieldValues.getProperty("MaxDays")) {
			case "":
				maxcheckoutGroupField.setSelectedIndex(0);
				break;
			case "7":
				maxcheckoutGroupField.setSelectedIndex(0);
				break;
			case "21":
				maxcheckoutGroupField.setSelectedIndex(1);
				break;
			// code block
			}
		}
		gridPanel.add(maxcheckoutGroupField);

		labelName = "No of Copies";
		makeLabel(gridPanel, labelName);
		numberofcopies = new JNumberTextField(15);
		gridPanel.add(numberofcopies);

		if (fieldValues.getProperty("ISBN") != null) {
			isbn.setEditable(false);
			ControllerInterface ci = new SystemController();
			Book lm = ci.getBookbyisbn(fieldValues.getProperty("ISBN"));
			isbn.setText(lm.getIsbn());
			author.setText(lm.getAuthors().get(0).getFirstName());
			numberofcopies.setText("" + lm.getNumCopies());

		}

		// table
		createTableAndTablePane();
		GuiControl.createCustomColumns(tableauthor, TABLE_WIDTH, COL_WIDTH_PROPORTIONS, DEFAULT_COLUMN_HEADERS);
		GuiControl.createCustomColumns(tableauthorchoosed, TABLE_WIDTH, COL_WIDTH_PROPORTIONS, DEFAULT_COLUMN_HEADERS);

		middle.add(GuiControl.createStandardTablePanePanel(tableauthor, tablePaneauthors), BorderLayout.CENTER);
		// back to cart button
//		JButton backButton = new JButton("Author -->>");
//		backButton.addActionListener(new BackListener());
//		middle.add(backButton);
		JLabel authorLabel = new JLabel("Choose Authors");
		middle.add(authorLabel);
		middle.add(GuiControl.createStandardTablePanePanel(tableauthorchoosed, tablePaneauthorschoosed),
				BorderLayout.CENTER);

		tableauthor.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					// your valueChanged overridden method
					// System.out.println("test");

					updatetablechoosed();
				}
			}

			private void updatetablechoosed() {
				// TODO Auto-generated method stub
				// String[][] Users1 = new String[map.size()][2];
//				DataAccess da = new DataAccessFacade();
//				HashMap<String, Author> map = da.readAuthorMap();
//				String[][] authors = new String[map.size()][3];
//				for (Map.Entry<String, Author> entry : map.entrySet()) {
//					authors[cont][2] = entry.getValue().getBio();
//					authors[cont][1] = entry.getValue().getLastName();
//					authors[cont][0] = entry.getValue().getFirstName();
//					cont++;
//				}
//
//				return Arrays.asList(authors);
				// index for id User
//				System.out.println((String) modelauthors.getValueAt(tableauthor.getSelectedRow(), 0));
//				System.out.println((String) modelauthors.getValueAt(tableauthor.getSelectedRow(), 1));
//				System.out.println((String) modelauthors.getValueAt(tableauthor.getSelectedRow(), 2));
//				System.out.println((String) modelauthors.getValueAt(tableauthor.getSelectedRow(), 3));

				String[][] author = new String[1][5];
				author[0][4] = (String) modelauthors.getValueAt(tableauthor.getSelectedRow(), 4);
				author[0][3] = (String) modelauthors.getValueAt(tableauthor.getSelectedRow(), 3);
				author[0][2] = (String) modelauthors.getValueAt(tableauthor.getSelectedRow(), 2);
				author[0][1] = (String) modelauthors.getValueAt(tableauthor.getSelectedRow(), 1);
				author[0][0] = (String) modelauthors.getValueAt(tableauthor.getSelectedRow(), 0);

				List<String[]> dataBooksChoosedtmp2 = new ArrayList<String[]>();
				dataBooksChoosedtmp2 = Arrays.asList(author);

				System.out.println(dataBooksChoosedtmp2.containsAll(dataBooksChoosed));
				System.out.println("PRINT databooks CHOOSED!!");
				boolean exist = false;
				for (int i = 0; i < dataBooksChoosed.size(); i++) {
					if (author[0][0].equals(dataBooksChoosed.get(i)[0])) {
						exist = true;
					}
				}

				// dataBooksChoosed = dataBooksChoosed.add();
				// dataBooksChoosed.add(0, (String[])
				// modelauthors.getValueAt(tableauthor.getSelectedRow(), 0));
				// UserInfo.setProperty("id User", (String) modelus.getValueAt(selectedRow, 0));
				if (!exist) {
					List<String[]> dataBooksChoosedtmp = new ArrayList<String[]>();
					dataBooksChoosedtmp = Arrays.asList(author);
					dataBooksChoosed.addAll(dataBooksChoosedtmp);
					// dataBooksChoosed = Arrays.asList(author);
					updateModelTableChoosed();
				}
			}
		});
	}

	private void createTableAndTablePane() {
		tableauthor = new JTable(modelauthors);
		tableauthorchoosed = new JTable(modelauthorschoosed);
		updateModel();
		updateModelTableChoosed();
		tablePaneauthors = new JScrollPane();
		tablePaneauthors.setPreferredSize(new Dimension(TABLE_WIDTH, DEFAULT_TABLE_HEIGHT));
		tablePaneauthors.getViewport().add(tableauthor);
		tablePaneauthorschoosed = new JScrollPane();
		tablePaneauthorschoosed.setPreferredSize(new Dimension(TABLE_WIDTH, DEFAULT_TABLE_HEIGHT));
		tablePaneauthorschoosed.getViewport().add(tableauthorchoosed);
	}

	public void updateModel(List<String[]> list) {
		for (int i = 0; i < list.size(); i++) {
			// System.out.println(list.get(i)[0]);
		}
		modelauthors = new CustomTableModel();
		modelauthors.setTableValues(list);
	}

	public void updateModelTableChoosed(List<String[]> list) {
		for (int i = 0; i < list.size(); i++) {
			// System.out.println(list.get(i)[0]);
		}
		modelauthorschoosed = new CustomTableModel();
		modelauthorschoosed.setTableValues(list);
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

	private void updateModelTableChoosed() {
		ControllerInterface ci = new SystemController();
		// List<String[]> theData = ci.allAuhtorTable();
		// theData.clear();
		updateModelTableChoosed(dataBooksChoosed);
		updateTableChoosed();
	}

	private void updateTable() {
		tableauthor.setModel(modelauthors);
		tableauthor.updateUI();
		repaint();

	}

	private void updateTableChoosed() {
		tableauthorchoosed.setModel(modelauthorschoosed);
		tableauthorchoosed.updateUI();
		repaint();

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
		lower = GuiControl.createStandardButtonPanel(buttons);
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

			System.out.println("ROW COUNT" + modelauthorschoosed.getRowCount());
			if ( numberofcopies.getText().length() > 0) {

				// Address a = new Address(street.getText(), city.getText(), state.getText(),
				// zip.getText());
				Address a = new Address("A", "b", "c", "d");
				// LibraryMember lm = new LibraryMember(idMemberField.getText(),
				// firstName.getText(), lastName.getText(),
				// telephone.getText(), a);
				// Author b = new Author("a", "b", "c", a, "d");
				ArrayList<Author> authors = new ArrayList<Author>();
				for (int i = 0; i < dataBooksChoosed.size(); i++) {
					Author tmp = new Author(dataBooksChoosed.get(i)[0], dataBooksChoosed.get(i)[1],
							dataBooksChoosed.get(i)[2], dataBooksChoosed.get(i)[3], a, dataBooksChoosed.get(i)[4]);
					// System.out.println(dataBooksChoosed.get(i)[0]);
					authors.add(tmp);
				}
				Book c = new Book(isbn.getText(), title.getText(),
						Integer.parseInt(maxcheckoutGroupField.getSelectedItem().toString()), authors);
				for (int i = 0; i < Integer.parseInt(numberofcopies.getText()) - 1; i++) {
					c.addCopy();
				}
				ControllerInterface ci = new SystemController();
				Response resp=ci.saveBook(c);
				if(resp.isStatus()) {
					JOptionPane.showMessageDialog(AddEditBook.this, resp.getMessage(), "Info",
							JOptionPane.INFORMATION_MESSAGE);
					BookWindow.INSTANCE.refresh();
					dispose();
					Util.centerFrameOnDesktop(BookWindow.INSTANCE);
					
				}
				else {
					if(!resp.getFormFieldMessages().isEmpty()) {
						String message=Util.getConcatnatedFieldMessages(resp.getFormFieldMessages());
						JOptionPane.showMessageDialog(AddEditBook.this, message, resp.getMessage(), 
								JOptionPane.ERROR_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(AddEditBook.this, resp.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			} else {
				JOptionPane.showMessageDialog(AddEditBook.this, "Invalid value for number of copies!", "Error",
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
