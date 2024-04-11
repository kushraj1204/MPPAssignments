package librarysystem;

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
import javax.swing.JPanel;

import business.ControllerInterface;
import business.SystemController;

public class MajorWindow extends JFrame implements LibWindow {
	ControllerInterface ci = new SystemController();
	public final static MajorWindow INSTANCE = new MajorWindow();
	JPanel mainPanel;
	JMenuBar menuBar;
	JMenu admin;
	JMenuItem libraryMembers, books, copy;
	String pathToImage;
	private boolean isInitialized = false;

	private static LibWindow[] allWindows = { LibrarySystem.INSTANCE, LoginWindow.INSTANCE, AllMemberIdsWindow.INSTANCE,
			AllBookIdsWindow.INSTANCE, MajorWindow.INSTANCE

	};

	public static void hideAllWindows() {
		for (LibWindow frame : allWindows) {
			frame.setVisible(false);
		}
	}

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
		libraryMembers.addActionListener(new LibraryMemberListener());
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

			LibrarySystem.hideAllWindows();
			UserSystem.INSTANCE.init();
			Util.centerFrameOnDesktop(UserSystem.INSTANCE);
			UserSystem.INSTANCE.setVisible(true);
//
//			tt mct = new tt();
//			mct.setVisible(true);
//			// mct.setParentWindow(Start.this);
//			setVisible(false);
//			
//			LibraryMember mct = new LibraryMember();
//			mct.setVisible(true);
//			// mct.setParentWindow(Start.this);
//			setVisible(false);
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
