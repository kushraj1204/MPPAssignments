package UI;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class JNumberTextField extends JTextField {
	private static final long serialVersionUID = 1L;

	public JNumberTextField(int i) {
		// TODO Auto-generated constructor stub
		super(i);
	}

	public JNumberTextField() {
		// TODO Auto-generated constructor stub
		super();
	}

	@Override
	public void processKeyEvent(KeyEvent ev) {

		char c = ev.getKeyChar();

		if ((Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
			super.processKeyEvent(ev);
			ev.consume();

		}
		return;

	}

	/**
	 * As the user is not even able to enter a dot ("."), only integers (whole
	 * numbers) may be entered.
	 */
	public Long getNumber() {
		Long result = null;
		String text = getText();
		if (text != null && !"".equals(text)) {
			result = Long.valueOf(text);
		}
		return result;
	}
}