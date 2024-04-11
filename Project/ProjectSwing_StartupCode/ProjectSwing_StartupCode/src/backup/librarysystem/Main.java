package librarysystem;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

import business.ControllerInterface;
import business.LoginException;
import business.SystemController;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
	            LibrarySystem.INSTANCE.setTitle("Sample Library Application");
	            LibrarySystem.INSTANCE.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            
	            LibrarySystem.INSTANCE.init();
	            centerFrameOnDesktop(LibrarySystem.INSTANCE);
	            LibrarySystem.INSTANCE.setVisible(true);

//			MajorWindow.INSTANCE.setTitle("Sample Library Application");
//			MajorWindow.INSTANCE.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			ControllerInterface ci = new SystemController();
//			try {
//				ci.login("101", "xyz");
//			} catch (LoginException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			MajorWindow.INSTANCE.init();
//			centerFrameOnDesktop(MajorWindow.INSTANCE);
//			MajorWindow.INSTANCE.setVisible(true);
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
