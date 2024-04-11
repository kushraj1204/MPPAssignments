package dataaccess;

import java.util.HashMap;

import business.model.*;

public interface DataAccess { 
	public HashMap<String, Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void saveNewMember(LibraryMember member); 
	public void saveNewUser(User user);
	public void deleteUser(String idMember);
	public void deleteLibraryMember(String idMember);

	void saveNewBook(Book book);

	HashMap<String, Author> readAuthorMap();

	void checkoutBook(CheckoutRecord checkoutRecord);

	void updateBookCopyAvailability(BookCopy bookCopy, boolean availability);

	void updateCheckoutEntry(CheckoutRecord cr, String isbn, int copyNo);

	@SuppressWarnings("unchecked")
	HashMap<String, CheckoutRecord> readCheckoutRecordsMap();

	void updateBook(Book book);

	void saveNewAuthor(Author author);
}
