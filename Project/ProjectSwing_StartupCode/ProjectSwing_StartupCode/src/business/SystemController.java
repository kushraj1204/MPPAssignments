package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.UUID;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;

	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if (!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if (!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
	}

	public String getPassword(String id) {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		return map.get(id).getPassword();

	}

	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}

	@Override
	public List<String[]> allMemberIdsTable() {
		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> map = da.readMemberMap();
		String[][] members = new String[map.size()][5];
		int cont = 0;
		for (Map.Entry<String, LibraryMember> entry : map.entrySet()) {
			members[cont][4] = entry.getValue().getAddress().getStreet();
			members[cont][3] = entry.getValue().getTelephone();
			members[cont][2] = entry.getValue().getLastName();
			members[cont][1] = entry.getValue().getFirstName();
			members[cont][0] = entry.getValue().getMemberId();
			cont++;
		}

		return Arrays.asList(members);
	}

	@Override
	public List<String[]> allCheckOutTable() {
		// TODO Auto-generated method stub
		DataAccess da = new DataAccessFacade();
		HashMap<String, CheckoutRecord> map = da.readCheckoutRecordsMap();
		int cont = 0;
		for (Map.Entry<String, CheckoutRecord> entry : map.entrySet()) {
			cont += entry.getValue().getCheckoutEntries().size();
		}
		System.out.println("contco" + cont);
		String[][] checkouts = new String[cont][5];
		cont = 0;
		for (Map.Entry<String, CheckoutRecord> entry : map.entrySet()) {
			for (int i = 0; i < entry.getValue().getCheckoutEntries().size(); i++) {
				if (entry.getValue().getCheckoutEntries().get(i).getReturnDate() != null)
					checkouts[cont][4] = "" + entry.getValue().getCheckoutEntries().get(i).getReturnDate();
				else
					checkouts[cont][4] = "";
				checkouts[cont][3] = "" + entry.getValue().getCheckoutEntries().get(i).getDueDate();
				checkouts[cont][2] = entry.getValue().getCheckoutEntries().get(i).getBookCopy().getBook().getTitle();
				checkouts[cont][1] = entry.getValue().getLibraryMember().getFirstName();
				checkouts[cont][0] = entry.getValue().getId();
				cont++;
			}
		}

		return Arrays.asList(checkouts);
	}

	@Override
	public List<String[]> allAuthorTable() {
		// TODO Auto-generated method stub
		DataAccess da = new DataAccessFacade();
		HashMap<String, Author> map = da.readAuthorMap();
		String[][] authors = new String[map.size()][5];
		int cont = 0;
		for (Map.Entry<String, Author> entry : map.entrySet()) {
			authors[cont][4] = entry.getValue().getTelephone();
			authors[cont][3] = entry.getValue().getBio();
			authors[cont][2] = entry.getValue().getLastName();
			authors[cont][1] = entry.getValue().getFirstName();
			authors[cont][0] = entry.getValue().getidAuthor();
			cont++;
		}

		return Arrays.asList(authors);
	}

	@Override
	public List<String[]> allBooksIdsTable() {
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> map = da.readBooksMap();
		String[][] books = new String[map.size()][5];
		String tmpname = "";
		int cont = 0;
		for (Map.Entry<String, Book> entry : map.entrySet()) {
			books[cont][4] = "" + entry.getValue().getNumCopies();
			books[cont][3] = "" + entry.getValue().getMaxCheckoutLength();
			tmpname = "";
			for (int i = 0; i < entry.getValue().getAuthors().size(); i++) {
				tmpname += "Author " + (i + 1) + ": " + entry.getValue().getAuthors().get(i).getFirstName() + " ";
			}
			books[cont][2] = tmpname;
			books[cont][1] = entry.getValue().getTitle();
			books[cont][0] = entry.getValue().getIsbn();
			cont++;
		}

		return Arrays.asList(books);
	}

	@Override
	public List<String[]> allUsers() {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		String[][] Users1 = new String[map.size()][2];
		int cont = 0;
		for (Map.Entry<String, User> entry : map.entrySet()) {
			Users1[cont][1] = entry.getValue().getAuthorization().name();
			Users1[cont][0] = entry.getValue().getId();
			cont++;
		}
		return Arrays.asList(Users1);
	}

	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}

	@Override
	public void saveBook(Book b) {
		// TODO Auto-generated method stub
		DataAccess da = new DataAccessFacade();
		da.saveNewBook(b);
	}

	@Override
	public void saveAuthor(Author b) {
		// TODO Auto-generated method stub
		DataAccess da = new DataAccessFacade();
		da.saveNewAuthor(b);
	}

	@Override
	public void saveUser(String id, String password, Auth auth) {
		// TODO Auto-generated method stub
		User us = new User(id, password, auth);
		DataAccess da = new DataAccessFacade();
		da.saveNewUser(us);
	}

	@Override
	public void deleteUser(String valueAt) {
		// TODO Auto-generated method stub
		DataAccess da = new DataAccessFacade();
		da.deleteUser(valueAt);

	}

	@Override
	public void saveLibraryMember(LibraryMember lm) {
		// TODO Auto-generated method stub
		DataAccess da = new DataAccessFacade();
		da.saveNewMember(lm);
	}

	@Override
	public void deleteLibraryMember(String idLibraryMember) {
		// TODO Auto-generated method stub
		DataAccess da = new DataAccessFacade();
		da.deleteLibraryMember(idLibraryMember);

	}

	@Override
	public LibraryMember getLibraryMemberbyId(String idLibraryMember) {
		// TODO Auto-generated method stub
		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> map = da.readMemberMap();
		return map.get(idLibraryMember);

	}

	@Override
	public Book getBookbyisbn(String isbnBook) {
		// TODO Auto-generated method stub
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> map = da.readBooksMap();
		return map.get(isbnBook);
	}

	@Override
	public List<Book> getBookbyisbns(List<String> isbnBooks) {
		// TODO Auto-generated method stub
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> map = da.readBooksMap();
		List<Book> bookList = new ArrayList<>();

		for (Map.Entry<String, Book> entry : map.entrySet()) {
			if (isbnBooks.contains(entry.getValue().getIsbn()))
				bookList.add(entry.getValue());
		}

		return bookList;
	}

	@Override
	public void deleteBook(String idbook) {
		// TODO Auto-generated method stub
		DataAccess da = new DataAccessFacade();
		// da.deleteBook(idbook);
	}

	@Override
	public Response checkoutBook(String lmId, String isbns) {
		List<String> isbnList = List.of(isbns.split(","));
		List<String> isbn = new ArrayList<>(new HashSet<>(isbnList));
		LibraryMember lm = getLibraryMemberbyId(lmId);
		if (lm == null) {
			return Response.getRsp("Library member with given id not found", false);
		}
		List<Book> b = getBookbyisbns(isbn);
		List<Book> availableBooks = getAvailableBooksFromList(b);
		if (b.isEmpty()) {
			return Response.getRsp("Books with given isbns not found", false);
		}
		if (availableBooks.isEmpty()) {
			return Response.getRsp("Selected Books is not available currently", false);
		}
		CheckoutRecord cr = new CheckoutRecord(UUID.randomUUID().toString(), lm, new ArrayList<>());
		List<CheckoutEntry> checkOutEntries = new ArrayList<>();
		String checkoutMsg = "";
		for (int i = 0; i < availableBooks.size(); i++) {
			Book book = availableBooks.get(i);
			BookCopy cp = book.getNextAvailableCopy();
			LocalDate dueDate = LocalDate.now().plusDays(book.getMaxCheckoutLength());
			checkOutEntries.add(new CheckoutEntry(cr, dueDate, cp));
			checkoutMsg += "Book " + book.getTitle() + " with copyNo. " + cp.getCopyNum() + " issued to "
					+ lm.getFirstName() + ". Due date is " + dueDate + ".\n";
		}
		cr.addCheckoutEntries(checkOutEntries);
		boolean checkoutStatus = checkoutBook(cr);
		if (!checkoutStatus) {
			return Response.getRsp("Failed to checkout book.Try again later", false);
		} else {
			return Response.getRsp(checkoutMsg, true, cr);
		}
	}

	@Override
	public boolean checkoutBook(CheckoutRecord cr) {
		try {
			DataAccess da = new DataAccessFacade();
			da.checkoutBook(cr);
			for (int i = 0; i < cr.getCheckoutEntries().size(); i++) {
				da.updateBookCopyAvailability(cr.getCheckoutEntries().get(i).getBookCopy(), false);
			}
			return true;
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			return false;
		}
	}

	// below functions is not necessary for our current requirement, just kept as a
	// room for enhancement if possible
	@Override
	public Response returnBook(String lmId, String isbn, int copyNo) {
		Response rsp = new Response();
		LibraryMember lm = getLibraryMemberbyId(lmId);
		if (lm == null) {
			return Response.getRsp("Library member with given id not found", false);
		}
		Book b = getBookbyisbn(isbn);
		if (b == null) {
			return Response.getRsp("Book with given isbn not found", false);
		}
		BookCopy[] copies = b.getCopies();
		BookCopy copy = null;
		for (int i = 0; i < b.getNumCopies(); i++) {
			if (copies[i].getCopyNum() == copyNo) {
				copy = copies[i];
			}
		}
		if (copy == null) {
			return Response.getRsp("Book with given copy number doesnt exist", false);
		}
		CheckoutRecord cr = findCheckOutRecordBy(lm, b, copyNo);
		if (cr == null) {
			return Response.getRsp("Checkout with given params doesnt exist", false);
		}
		boolean bookReturnStatus = returnBook(cr, copyNo, b);
		if (!bookReturnStatus) {
			return Response.getRsp("Failed to complete process.Try again later", false);
		} else {
			return Response.getRsp("Book returned successfully.", true);
		}
	}

	@Override
	public Response addBookCopies(String isbn, int count) {
		Book book = getBookbyisbn(isbn);
		Response rsp = new Response();
		if (book == null) {
			return Response.getRsp("Book with provided ISBN not found", false);
		}
		book.addCopies(count);
		DataAccess da = new DataAccessFacade();
		da.updateBook(book);
		rsp.setMessage("Book copies added successfully");
		return rsp;
	}

	@Override
	public boolean returnBook(CheckoutRecord cr, int copyNo, Book b) {
		try {
			List<CheckoutEntry> checkoutEntries = cr.getCheckoutEntries();
			for (int i = 0; i < checkoutEntries.size(); i++) {
				CheckoutEntry en = checkoutEntries.get(i);
				if (en.getBookCopy().getCopyNum() == copyNo
						&& en.getBookCopy().getBook().getIsbn().equals(b.getIsbn())) {
					DataAccess da = new DataAccessFacade();
					da.updateBookCopyAvailability(cr.getCheckoutEntries().get(i).getBookCopy(), true);
					da.updateCheckoutEntry(cr, b.getIsbn(), copyNo);
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public CheckoutRecord findCheckOutRecordBy(LibraryMember lm, Book b, int copyNo) {
		DataAccess da = new DataAccessFacade();
		HashMap<String, CheckoutRecord> checkoutRecordsMap = da.readCheckoutRecordsMap();
		List<Map.Entry<String, CheckoutRecord>> entryList = new ArrayList<>(checkoutRecordsMap.entrySet());
		ListIterator<Map.Entry<String, CheckoutRecord>> iterator = entryList.listIterator(entryList.size());

		while (iterator.hasPrevious()) {
			Map.Entry<String, CheckoutRecord> entry = iterator.previous();
			LibraryMember libM = entry.getValue().getLibraryMember();
			if (libM.getMemberId().equals(lm.getMemberId())) {
				List<CheckoutEntry> entries = entry.getValue().getCheckoutEntries();
				for (int i = 0; i < entries.size(); i++) {
					CheckoutEntry en = entries.get(i);
					if (en.getBookCopy().getCopyNum() == copyNo
							&& en.getBookCopy().getBook().getIsbn().equals(b.getIsbn())) {
						return entry.getValue();
					}
				}
			}
		}
		return null;
	}

	@Override
	public Response findCheckOutRecordsByMember(String memberId) {
		DataAccess da = new DataAccessFacade();
		List<CheckoutRecord> cr = new ArrayList<>();
		HashMap<String, CheckoutRecord> checkoutRecordsMap = da.readCheckoutRecordsMap();
		for (Map.Entry<String, CheckoutRecord> entry : checkoutRecordsMap.entrySet()) {
			CheckoutRecord value = entry.getValue();
			if (value.getLibraryMember().getMemberId().equals(memberId)) {
				cr.add(value);
			}
		}
		Response rsp = Response.getRsp("", true, cr);
		return rsp;
	}

	@Override
	public Response findCheckOutRecordsByBook(String isbn) {
		DataAccess da = new DataAccessFacade();
		List<CheckoutRecord> cr = new ArrayList<>();
		HashMap<String, CheckoutRecord> checkoutRecordsMap = da.readCheckoutRecordsMap();
		for (Map.Entry<String, CheckoutRecord> entry : checkoutRecordsMap.entrySet()) {
			CheckoutRecord value = entry.getValue();
			List<CheckoutEntry> ce = value.getCheckoutEntries();
			boolean hasAny = false;
			for (int i = 0; i < ce.size(); i++) {
				if (ce.get(i).getBookCopy().getBook().getIsbn().equals(isbn)) {
					hasAny = true;
				} else {
					ce.remove(ce.get(i));
				}
			}
			if (hasAny) {
				cr.add(value);
			}
		}
		Response rsp = Response.getRsp("", true, cr);
		return rsp;
	}

	public static List<Book> getAvailableBooksFromList(List<Book> b) {
		List<Book> availableBooks = new ArrayList<>();
		for (int i = 0; i < b.size(); i++) {
			if (b.get(i).isAvailable())
				availableBooks.add(b.get(i));
		}
		return availableBooks;
	}

}
