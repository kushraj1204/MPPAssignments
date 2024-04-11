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

import business.model.*;

public class DataAccessFacade implements DataAccess {

	enum StorageType {
		BOOKS, MEMBERS, USERS,AUTHORS,CHECKOUTRECORD;
	}
	// Windows user can use

	/*
	 * public static final String OUTPUT_DIR = System.getProperty("user.dir") +
	 * "\\src\\dataaccess\\storage";
	 */

	// For Mac Users path can use /
	public static final String OUTPUT_DIR = System.getProperty("user.dir") + "/src/dataaccess/storage";

	public static final String DATE_PATTERN = "MM/dd/yyyy";

	// implement: other save operations
	public void saveNewMember(LibraryMember member) {
		HashMap<String, LibraryMember> mems = readMemberMap();
		String memberId = member.getMemberId();
		mems.put(memberId, member);
		saveToStorage(StorageType.MEMBERS, mems);
	}

	public void saveNewUser(User user) {
		HashMap<String, User> us = readUserMap();
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
		return (HashMap<String, Book>) readFromStorage(StorageType.BOOKS);
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, LibraryMember> readMemberMap() {
		// Returns a Map with name/value pairs being
		// memberId -> LibraryMember
		return (HashMap<String, LibraryMember>) readFromStorage(StorageType.MEMBERS);
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, User> readUserMap() {
		// Returns a Map with name/value pairs being
		// userId -> User
		return (HashMap<String, User>) readFromStorage(StorageType.USERS);
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


	public void saveNewAuthor(Author author) {
		HashMap<String, Author> authors = readAuthorMap();
		String authorId = author.getBio();
		authors.put(authorId, author);
		saveToStorage(StorageType.AUTHORS, authors);
	}

	@Override
	public void saveNewBook(Book book) {
		HashMap<String, Book> books = readBooksMap();
		String bookId = book.getIsbn();
		books.put(bookId, book);
		saveToStorage(StorageType.BOOKS, books);
	}

	@Override
	public HashMap<String, Author> readAuthorMap() {
		HashMap<String, Author> authors = (HashMap<String, Author>) readFromStorage(
				StorageType.AUTHORS);
		authors = authors == null ? new HashMap<String, Author>() : authors;
		return authors;
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

		saveToStorage(StorageType.BOOKS, books);
	}

	@Override
	public void updateCheckoutEntry(CheckoutRecord cr, String isbn, int copyNo) {
		HashMap<String, CheckoutRecord> checkoutRecordsMap = readCheckoutRecordsMap();//checkoutRecordsMap
		for (Map.Entry<String, CheckoutRecord> entry : checkoutRecordsMap.entrySet()) {
			CheckoutRecord checkoutRecord = entry.getValue();
			if(checkoutRecord.getId().equals(cr.getId())){
				for (int j = 0; j < checkoutRecord.getCheckoutEntries().size(); j++) {
					if(checkoutRecord.getCheckoutEntries().get(j).getBookCopy().getCopyNum()==copyNo){
						CheckoutEntry e=checkoutRecord.getCheckoutEntries().get(j);
						e.setReturnDate(LocalDate.now());
						break;
					}
				}
				break;
			}
		}
		saveToStorage(StorageType.CHECKOUTRECORD, checkoutRecordsMap);
	}
	@Override

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
			if(value.getIsbn().equals(bookId)){
				books.put(bookId,book);
			}
		}
		saveToStorage(StorageType.BOOKS, books);
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

}
