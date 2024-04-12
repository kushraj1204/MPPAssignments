package librarysystem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JWindow;

import business.Address;
import business.Author;
import business.Book;
import business.BookCopy;
import business.CheckoutEntry;
import business.CheckoutRecord;
import business.ControllerInterface;
import business.LibraryMember;
import business.SystemController;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

/**
 * 
 * @author klevi, pcorazza
 * @since Oct 22, 2004
 *        <p>
 *        Class Description: This class displays all available products for a
 *        particular catalog group. When a catalog group is selected, the table
 *        is updated to display the products in this group. The screen provides
 *        Add, Edit and Delete buttons for modifying the choices of products.
 *        <p>
 *        <table border="1">
 *        <tr>
 *        <th colspan="3">Change Log</th>
 *        </tr>
 *        <tr>
 *        <th>Date</th>
 *        <th>Author</th>
 *        <th>Change</th>
 *        </tr>
 *        <tr>
 *        <td>Oct 22, 2004</td>
 *        <td>klevi, pcorazza</td>
 *        <td>New class file</td>
 *        </tr>
 *        <tr>
 *        <td>Jan 19, 2005</td>
 *        <td>klevi</td>
 *        <td>modifed the readdata comments</td>
 *        </tr>
 *        </table>
 *
 */
public class tt {

	public static void main(String args[]) {

		DataAccess da = new DataAccessFacade();
		Address a = new Address("A", "b", "c", "d");
		// LibraryMember lm = new LibraryMember(idMemberField.getText(),
		// firstName.getText(), lastName.getText(),
		// telephone.getText(), a);
		Author b = new Author("a", "b", "c", a, "d");
		LibraryMember lm = new LibraryMember("aa", "aa", "aa", "ntel", a);
		List<Author> auth = new ArrayList<Author>();
		auth.add(b);
		List<CheckoutEntry> ce = new ArrayList<CheckoutEntry>();

		BookCopy bc = new business.BookCopy(new Book("a", "a", 1, auth), 2);
		BookCopy bc1 = new business.BookCopy(new Book("b", "b", 1, auth), 3);
		BookCopy bc2 = new business.BookCopy(new Book("c", "c", 1, auth), 2);

		ce.add(new CheckoutEntry(null, bc));
		ce.add(new CheckoutEntry(null, bc1));
		ce.add(new CheckoutEntry(null, bc2));
		CheckoutRecord cr = new CheckoutRecord("a", lm, ce);
		da.checkoutBook(cr);

		System.out.println();
	}

	private static final long serialVersionUID = 3257569511937880631L;

}
