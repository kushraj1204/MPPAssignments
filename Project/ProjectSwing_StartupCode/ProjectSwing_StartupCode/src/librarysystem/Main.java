package librarysystem;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

import UI.LoginWindow;
import UI.MajorWindow;
import business.ControllerInterface;
import business.LoginException;
import business.SystemController;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {

			MajorWindow.INSTANCE.setTitle("Library Management System");
			MajorWindow.INSTANCE.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ControllerInterface ci = new SystemController();

			LoginWindow.INSTANCE.init();
			centerFrameOnDesktop(LoginWindow.INSTANCE);
			LoginWindow.INSTANCE.setVisible(true);
		});
	}

	public static void centerFrameOnDesktop(Component f) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int height = toolkit.getScreenSize().height;
		int width = toolkit.getScreenSize().width;
		int frameHeight = f.getSize().height;
		int frameWidth = f.getSize().width;
		f.setLocation(((width - frameWidth) / 2), (height - frameHeight) / 3);
	}
}
