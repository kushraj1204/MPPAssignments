package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

import business.ControllerInterface;
import business.LoginException;
import business.SystemController;
import librarysystem.GuiControl;

public class LoginWindow extends JFrame implements LibWindow {
	public static final LoginWindow INSTANCE = new LoginWindow();

	private boolean isInitialized = false;

	private JPanel mainPanel;
	private JPanel upperHalf;
	private JPanel middleHalf;
	private JPanel lowerHalf;
	private JPanel container;

	private JPanel topPanel;
	private JPanel middlePanel;
	private JPanel lowerPanel;
	private JPanel leftTextPanel;
	private JPanel rightTextPanel;

	private JTextField username;
	private JTextField password;
	private JLabel label;
	private JButton loginButton;
	private JButton logoutButton;

	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;

	public boolean isInitialized() {
		return isInitialized;
	}

	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	private JTextField messageBar = new JTextField();

	public void clear() {
		messageBar.setText("");
	}

	/* This class is a singleton */
	private LoginWindow() {
	}

	public void init() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(GuiControl.SCREEN_BACKGROUND);
		mainPanel.setBorder(new WindowBorder(GuiControl.WINDOW_BORDER));
		defineUpperHalf();
		defineMiddleHalf();
		defineLowerHalf();
		BorderLayout bl = new BorderLayout();
		bl.setVgap(30);
		mainPanel.setLayout(bl);

		mainPanel.add(upperHalf, BorderLayout.NORTH);
		mainPanel.add(middleHalf, BorderLayout.CENTER);
		mainPanel.add(lowerHalf, BorderLayout.SOUTH);
		getContentPane().add(mainPanel);
		isInitialized(true);
		pack();
		// setSize(660, 500);

	}

	private void defineUpperHalf() {
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();
		jLabel4 = new javax.swing.JLabel();
		username = new javax.swing.JTextField();
		password = new javax.swing.JPasswordField(10);

		final String CURR_DIR = System.getProperty("user.dir");
		final String SPLASH_IMAGE = CURR_DIR + "/src/librarysystem/logo.jpg";
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/librarysystem/book.png"))); // NOI18N
		jLabel1.setText("Library Management System");
		jLabel2.setText("Username");
		jLabel3.setText("Password");
		username.setText("");
		jButton1.setText("Login");

		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ControllerInterface ci = new SystemController();
				try {
					ci.login(username.getText(), password.getText());
					if (SystemController.currentAuth != null) {
						username.setText("");
						password.setText("");
						LibrarySystemWindow.hideAllWindows();
						MajorWindow.INSTANCE.init();
						Util.centerFrameOnDesktop(MajorWindow.INSTANCE);
						MajorWindow.INSTANCE.setVisible(true);
					}
				} catch (LoginException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(LoginWindow.this, "Wrong credentials!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		jLabel4.setFont(new java.awt.Font("Liberation Sans", 1, 24));
		jLabel4.setText("Login");
		jLabel4.setName("");
		jLabel4.setRequestFocusEnabled(false);
		password.setText("");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup().addGap(53, 53, 53).addGroup(layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel2).addComponent(jLabel3))
										.addGap(91, 91, 91)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(username).addComponent(password)))
								.addGroup(layout.createSequentialGroup()
										.addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 147,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, Short.MAX_VALUE))))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addContainerGap(142, Short.MAX_VALUE)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING,
														javax.swing.GroupLayout.PREFERRED_SIZE, 311,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))))
				.addGap(113, 113, 113)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(17, 17, 17).addComponent(jLabel1).addGap(24, 24, 24)
						.addComponent(jLabel4).addGap(36, 36, 36)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel2).addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE,
										32, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(53, 53, 53)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel3).addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE,
										32, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(52, 52, 52).addComponent(jButton1).addContainerGap(31, Short.MAX_VALUE)));

		pack();

		upperHalf = new JPanel();
		upperHalf.setLayout(new BorderLayout());
		defineTopPanel();
		defineMiddlePanel();
		defineLowerPanel();
		upperHalf.add(topPanel, BorderLayout.NORTH);
		upperHalf.add(middlePanel, BorderLayout.CENTER);
		upperHalf.add(lowerPanel, BorderLayout.SOUTH);

	}

	private void defineMiddleHalf() {
		middleHalf = new JPanel();
		middleHalf.setLayout(new BorderLayout());
		JSeparator s = new JSeparator();
		s.setOrientation(SwingConstants.HORIZONTAL);
		// middleHalf.add(Box.createRigidArea(new Dimension(0,50)));
		middleHalf.add(s, BorderLayout.SOUTH);

	}

	private void defineLowerHalf() {

		lowerHalf = new JPanel();
		lowerHalf.setLayout(new FlowLayout(FlowLayout.LEFT));

		JButton backButton = new JButton("<= Back to Main");
		addBackButtonListener(backButton);
		lowerHalf.add(backButton);

	}

	private void defineTopPanel() {
		topPanel = new JPanel();
		JPanel intPanel = new JPanel(new BorderLayout());
		intPanel.add(Box.createRigidArea(new Dimension(0, 20)), BorderLayout.NORTH);
		JLabel loginLabel = new JLabel("Login");
		Util.adjustLabelFont(loginLabel, Color.BLUE.darker(), true);
		intPanel.add(loginLabel, BorderLayout.CENTER);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(intPanel);

	}

	private void defineMiddlePanel() {
		middlePanel = new JPanel();
		middlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		defineLeftTextPanel();
		defineRightTextPanel();
		middlePanel.add(leftTextPanel);
		middlePanel.add(rightTextPanel);
	}

	private void defineLowerPanel() {
		lowerPanel = new JPanel();
	}

	private void defineLeftTextPanel() {

		JPanel topText = new JPanel();
		JPanel bottomText = new JPanel();
		topText.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
		bottomText.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

		leftTextPanel = new JPanel();
		leftTextPanel.setLayout(new BorderLayout());
		leftTextPanel.add(topText, BorderLayout.NORTH);
		leftTextPanel.add(bottomText, BorderLayout.CENTER);
	}

	private void defineRightTextPanel() {

		JPanel topText = new JPanel();
		JPanel bottomText = new JPanel();
		topText.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
		bottomText.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

		rightTextPanel = new JPanel();
		rightTextPanel.setLayout(new BorderLayout());
		rightTextPanel.add(topText, BorderLayout.NORTH);
		rightTextPanel.add(bottomText, BorderLayout.CENTER);
	}

	private void addBackButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			LibrarySystemWindow.hideAllWindows();
			LibrarySystemWindow.INSTANCE.setVisible(true);
		});
	}

}
