package UI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import business.ControllerInterface;
import business.SystemController;

public class MajorWindow extends JFrame implements LibWindow {
	ControllerInterface ci = new SystemController();
	public final static MajorWindow INSTANCE = new MajorWindow();
	JPanel mainPanel;
	JMenuBar menuBar;
	JMenu admin, logout, book;
	JMenuItem userSystems, librarymember, books, checkout, copy, logoutit, author;
	String pathToImage;
	private boolean isInitialized = false;

	private MajorWindow() {
	}

	public void init() {
		formatContentPane();
		setPathToImage();
		insertSplashImage();

		createMenus();
		// pack();
		setSize(660, 500);
		isInitialized = true;
	}

	private void formatContentPane() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1, 1));
		getContentPane().add(mainPanel);
	}

	private void setPathToImage() {
		String currDirectory = System.getProperty("user.dir");
		pathToImage = currDirectory + "/src/librarysystem/library.jpg";
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
		userSystems = new JMenuItem("User Systems");
		userSystems.addActionListener(new userSystemListener());

		book = new JMenu("Book");
		menuBar.add(book);
		books = new JMenuItem("Maintain Book");
		books.addActionListener(new maintainBooksListener());
		book.add(books);
		author = new JMenuItem("Author Book");
		author.addActionListener(new maintainAuthorListener());
		book.add(author);
		checkout = new JMenuItem("Checkout Book");
		checkout.addActionListener(new maintainCheckOutBookListener());
		book.add(checkout);

		librarymember = new JMenuItem("Library Member");
		librarymember.addActionListener(new libraryMemberListener());
		admin.add(librarymember);
		admin.add(userSystems);
		logout = new JMenu("Logout");
		menuBar.add(logout);
		logoutit = new JMenuItem("User Logout");
		logoutit.addActionListener(new logoutUserListener());
		logout.add(logoutit);

	}

	class logoutUserListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			SystemController.currentAuth = null;
			LibrarySystemWindow.hideAllWindows();
			Util.centerFrameOnDesktop(LoginWindow.INSTANCE);
			LoginWindow.INSTANCE.setVisible(true);
			
		}
	}

	class maintainCheckOutBookListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!SystemController.currentAuth.toString().equals("ADMIN")) {
				LibrarySystemWindow.hideAllWindows();
				CheckOutBookWindow.INSTANCE.init();
				Util.centerFrameOnDesktop(CheckOutBookWindow.INSTANCE);
				CheckOutBookWindow.INSTANCE.setVisible(true);
			} else
				JOptionPane.showMessageDialog(MajorWindow.this, "Forbidden section!", "Error",
						JOptionPane.ERROR_MESSAGE);
		}
	}

	class libraryMemberListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!SystemController.currentAuth.toString().equals("LIBRARIAN")) {
				LibrarySystemWindow.hideAllWindows();
				LibraryMemberWindow.INSTANCE.init();
				Util.centerFrameOnDesktop(LibraryMemberWindow.INSTANCE);
				LibraryMemberWindow.INSTANCE.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(MajorWindow.this, "Forbidden section!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	class maintainAuthorListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (!SystemController.currentAuth.toString().equals("LIBRARIAN")) {
				LibrarySystemWindow.hideAllWindows();
				AuthorWindow.INSTANCE.init();
				Util.centerFrameOnDesktop(AuthorWindow.INSTANCE);
				AuthorWindow.INSTANCE.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(MajorWindow.this, "Forbidden section!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	class userSystemListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!SystemController.currentAuth.toString().equals("LIBRARIAN")) {
				LibrarySystemWindow.hideAllWindows();
				UserSystem.INSTANCE.init();
				Util.centerFrameOnDesktop(UserSystem.INSTANCE);
				UserSystem.INSTANCE.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(MajorWindow.this, "Forbidden section!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	class maintainBooksListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!SystemController.currentAuth.toString().equals("LIBRARIAN")) {
				LibrarySystemWindow.hideAllWindows();
				BookWindow.INSTANCE.init();
				Util.centerFrameOnDesktop(BookWindow.INSTANCE);
				BookWindow.INSTANCE.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(MajorWindow.this, "Forbidden section!", "Error",
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

}
