package business;

import java.util.List;

import business.Book;
import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;

	public List<String> allMemberIds();

	public List<String> allBookIds();

	public List<String[]> allUsers();
	
	public List<String[]> allMemberIdsTable();

	public List<String[]> allBooksIdsTable();
	
	public List<String[]> allAuthorTable();
	
	public List<String[]> allCheckOutTable();
	
	public List<Book> getBookbyisbns(List<String> isbnBooks);

	public void saveUser(String a, String b, Auth c);

	public void deleteUser(String valueAt);

	public String getPassword(String id);

	public Response saveLibraryMember(LibraryMember lm);

	public void deleteLibraryMember(String string);

	public LibraryMember getLibraryMemberbyId(String idLibraryMember);

	public Book getBookbyisbn(String isbnBook);

	public Response saveBook(Book b);

	public void deleteBook(String idbook);

	public Response saveAuthor(Author b);

	public Response findCheckOutRecordsByBook(String isbn);

	public Response findCheckOutRecordsByMember(String memberId);

	public CheckoutRecord findCheckOutRecordBy(LibraryMember lm, Book b, int copyNo);

	public boolean returnBook(CheckoutRecord cr, int copyNo, Book b);

	public Response addBookCopies(String isbn, int count);

	public Response returnBook(String lmId, String isbn, int copyNo);

	public boolean checkoutBook(CheckoutRecord cr);

	public Response checkoutBook(String lmId, String isbns);
}
