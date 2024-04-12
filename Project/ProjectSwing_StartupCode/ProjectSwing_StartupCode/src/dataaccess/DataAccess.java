package dataaccess;

import java.util.HashMap;


import business.Author;
import business.Book;
import business.LibraryMember;
import business.BookCopy;
import business.CheckoutRecord;
import dataaccess.DataAccessFacade.StorageType;

public interface DataAccess {
	public HashMap<String, Book> readBooksMap();

	public HashMap<String, User> readUserMap();

	public HashMap<String, LibraryMember> readMemberMap();
	
	public HashMap<String, Author> readAuthorMap();

	public void saveNewMember(LibraryMember member);

	public void saveNewUser(User user);
	
	public void saveNewAuthor(Author author);

	public void saveNewBook(Book book);

	public void deleteUser(String idMember);

	public void deleteLibraryMember(String idMember);
	
	@SuppressWarnings("unchecked")
	HashMap<String, CheckoutRecord> readCheckoutRecordsMap();

	void checkoutBook(CheckoutRecord cr);

	void updateBookCopyAvailability(BookCopy bookCopy,boolean status);

	void updateCheckoutEntry(CheckoutRecord cr, String isbn, int copyNo);

	void updateBook(Book book);

}
