package dataaccess;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.Author;
import business.Book;
import business.BookCopy;
import business.LibraryMember;
import business.CheckoutEntry;
import business.CheckoutRecord;
import dataaccess.DataAccessFacade.StorageType;
import librarysystem.Util;

public class DataAccessFacade implements DataAccess {

	enum StorageType {
		BOOKS, MEMBERS, USERS, AUTHOR, CHECKOUTRECORD;
	}
	// Windows user can use

	/*
	 * public static final String OUTPUT_DIR = System.getProperty("user.dir") +
	 * "\\src\\dataaccess\\storage";
	 */

	// For Mac Users path can use /
//	public static final String OUTPUT_DIR = System.getProperty("user.dir") + "/src/dataaccess/storage";
	public static final String OUTPUT_DIR = System.getProperty("user.dir") + "/dataaccess/storage";
	public static final String DATE_PATTERN = "MM/dd/yyyy";

	// implement: other save operations
	public void saveNewMember(LibraryMember member) {
		HashMap<String, LibraryMember> mems = readMemberMap();
		if (mems == null)
			mems = new HashMap<String, LibraryMember>();
		String memberId = "" + Util.getNextKey(mems);
		member.setMemberId(memberId);
		mems.put(memberId, member);
		saveToStorage(StorageType.MEMBERS, mems);
	}

	@Override
	public void saveNewBook(Book book) {
		// TODO Auto-generated method stub
		HashMap<String, Book> books = readBooksMap();
		if (books == null)
			books = new HashMap<String, Book>();
		String bookisbn = book.getIsbn();
		books.put(bookisbn, book);
		saveToStorage(StorageType.BOOKS, books);
	}

	public void saveNewUser(User user) {
		HashMap<String, User> us = readUserMap();
		if (us == null)
			us = new HashMap<String, User>();
		for (Map.Entry<String, User> entry : us.entrySet()) {
			System.out.println(entry.getKey());
		}

		String usID = user.getId();
		us.put(usID, user);
		saveToStorage(StorageType.USERS, us);
	}

	public void deleteUser(String key) {
		HashMap<String, User> us = readUserMap();
		us.remove(key);
		saveToStorage(StorageType.USERS, us);
	}

	public void deleteLibraryMember(String key) {
		HashMap<String, LibraryMember> us = readMemberMap();
		us.remove(key);
		saveToStorage(StorageType.MEMBERS, us);
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, Book> readBooksMap() {
		// Returns a Map with name/value pairs being
		// isbn -> Book
		HashMap<String, Book> map = (HashMap<String, Book>) readFromStorage(StorageType.BOOKS);
		if (map == null) {
			map = new HashMap<>();
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, LibraryMember> readMemberMap() {
		// Returns a Map with name/value pairs being
		// memberId -> LibraryMember
		HashMap<String, LibraryMember> map = (HashMap<String, LibraryMember>) readFromStorage(StorageType.MEMBERS);
		if (map == null) {
			map = new HashMap<>();
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, User> readUserMap() {
		// Returns a Map with name/value pairs being
		// userId -> User
		HashMap<String, User> map = (HashMap<String, User>) readFromStorage(StorageType.USERS);
		if (map == null) {
			map = new HashMap<>();
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, Author> readAuthorMap() {
		// Returns a Map with name/value pairs being
		// userId -> User
		HashMap<String, Author> map = (HashMap<String, Author>) readFromStorage(StorageType.AUTHOR);
		if (map == null) {
			map = new HashMap<>();
		}
		return map;
	}

	///// load methods - these place test data into the storage area
	///// - used just once at startup

	static void loadBookMap(List<Book> bookList) {
		HashMap<String, Book> books = new HashMap<String, Book>();
		bookList.forEach(book -> books.put(book.getIsbn(), book));
		saveToStorage(StorageType.BOOKS, books);
	}

	static void loadUserMap(List<User> userList) {
		HashMap<String, User> users = new HashMap<String, User>();
		userList.forEach(user -> users.put(user.getId(), user));
		saveToStorage(StorageType.USERS, users);
	}

	static void loadMemberMap(List<LibraryMember> memberList) {
		HashMap<String, LibraryMember> members = new HashMap<String, LibraryMember>();
		memberList.forEach(member -> members.put(member.getMemberId(), member));
		saveToStorage(StorageType.MEMBERS, members);
	}

	static void loadAuthorMap(List<Author> authorList) {
		HashMap<String, Author> authors = new HashMap<String, Author>();
		authorList.forEach(author -> authors.put(author.getFirstName(), author));
		saveToStorage(StorageType.AUTHOR, authors);
	}

	@Override
	public void checkoutBook(CheckoutRecord checkoutRecord) {
		HashMap<String, CheckoutRecord> checkoutRecords = readCheckoutRecordsMap();
		String checkoutRecordId = checkoutRecord.getId();
		checkoutRecords.put(checkoutRecordId, checkoutRecord);
		saveToStorage(StorageType.CHECKOUTRECORD, checkoutRecords);
	}

	@Override
	public void updateBookCopyAvailability(BookCopy bookCopy, boolean availability) {
		HashMap<String, Book> books = readBooksMap();
		for (Map.Entry<String, Book> entry : books.entrySet()) {
			Book book = entry.getValue();
			if (book.getIsbn().equals(bookCopy.getBook().getIsbn())) {
				for (int j = 0; j < book.getNumCopies(); j++) {
					if (book.getCopies()[j].getCopyNum() == bookCopy.getCopyNum()) {
						BookCopy bCopy = book.getCopies()[j];
						bCopy.changeAvailability();
						book.getCopies()[j] = bCopy;
						break;
					}
				}
				break;
			}
		}
		saveToStorage(StorageType.BOOKS, books);
	}

	@Override
	public void updateCheckoutEntry(CheckoutRecord cr, String isbn, int copyNo) {
		HashMap<String, CheckoutRecord> checkoutRecordsMap = readCheckoutRecordsMap();// checkoutRecordsMap
		for (Map.Entry<String, CheckoutRecord> entry : checkoutRecordsMap.entrySet()) {
			CheckoutRecord checkoutRecord = entry.getValue();
			if (checkoutRecord.getId().equals(cr.getId())) {
				for (int j = 0; j < checkoutRecord.getCheckoutEntries().size(); j++) {
					if (checkoutRecord.getCheckoutEntries().get(j).getBookCopy().getCopyNum() == copyNo) {
						CheckoutEntry e = checkoutRecord.getCheckoutEntries().get(j);
						e.setReturnDate(LocalDate.now());
						break;
					}
				}
				break;
			}
		}
		saveToStorage(StorageType.CHECKOUTRECORD, checkoutRecordsMap);
	}

	static void saveToStorage(StorageType type, Object ob) {
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(ob);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
				}
			}
		}
	}

	static Object readFromStorage(StorageType type) {
		ObjectInputStream in = null;
		Object retVal = null;
		try {

			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			in = new ObjectInputStream(Files.newInputStream(path));
			retVal = in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}
		return retVal;
	}

	final static class Pair<S, T> implements Serializable {

		S first;
		T second;

		Pair(S s, T t) {
			first = s;
			second = t;
		}

		@Override
		public boolean equals(Object ob) {
			if (ob == null)
				return false;
			if (this == ob)
				return true;
			if (ob.getClass() != getClass())
				return false;
			@SuppressWarnings("unchecked")
			Pair<S, T> p = (Pair<S, T>) ob;
			return p.first.equals(first) && p.second.equals(second);
		}

		@Override
		public int hashCode() {
			return first.hashCode() + 5 * second.hashCode();
		}

		@Override
		public String toString() {
			return "(" + first.toString() + ", " + second.toString() + ")";
		}

		private static final long serialVersionUID = 5399827794066637059L;
	}

	@Override
	public void saveNewAuthor(Author author) {
		// TODO Auto-generated method stub
		HashMap<String, Author> us = readAuthorMap();
		if (us == null)
			us = new HashMap<String, Author>();
		for (Map.Entry<String, Author> entry : us.entrySet()) {
			System.out.println(entry.getKey());
		}

		String usID = author.getFirstName();
		us.put(usID, author);
		saveToStorage(StorageType.AUTHOR, us);
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, CheckoutRecord> readCheckoutRecordsMap() {
		HashMap<String, CheckoutRecord> checkoutRecords = (HashMap<String, CheckoutRecord>) readFromStorage(
				StorageType.CHECKOUTRECORD);
		checkoutRecords = checkoutRecords == null ? new HashMap<String, CheckoutRecord>() : checkoutRecords;
		return checkoutRecords;
	}

	@Override
	public void updateBook(Book book) {
		HashMap<String, Book> books = readBooksMap();
		String bookId = book.getIsbn();
		for (Map.Entry<String, Book> entry : books.entrySet()) {
			Book value = entry.getValue();
			if (value.getIsbn().equals(bookId)) {
				books.put(bookId, book);
			}
		}
		saveToStorage(StorageType.BOOKS, books);
	}

}
