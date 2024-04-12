package business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List<String[]> allAuhtorTable() {
		// TODO Auto-generated method stub
		DataAccess da = new DataAccessFacade();
		HashMap<String, Author> map = da.readAuthorMap();
		String[][] authors = new String[map.size()][4];
		int cont = 0;
		for (Map.Entry<String, Author> entry : map.entrySet()) {
			authors[cont][3] = entry.getValue().getTelephone();
			authors[cont][2] = entry.getValue().getBio();
			authors[cont][1] = entry.getValue().getLastName();
			authors[cont][0] = entry.getValue().getFirstName();
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
				tmpname += "Author " + i + 1 + ": " + entry.getValue().getAuthors().get(i).getFirstName();
			}
			// books[cont][2] = entry.getValue().getAuthors().get(0).getFirstName();
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
	public void deleteBook(String idbook) {
		// TODO Auto-generated method stub
		DataAccess da = new DataAccessFacade();
		// da.deleteBook(idbook);
	}

}
