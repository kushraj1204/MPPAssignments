package business;

import java.util.List;

import business.model.LibraryMember;
import dataaccess.Auth;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;

	public List<String> allMemberIds();

	public List<String> allBookIds();

	public List<String[]> allUsers();

	public void saveUser(String a, String b, Auth c);

	public void deleteUser(String valueAt);

	public String getPassword(String id);
	
	public List<String[]> allMemberIdsTable();

	public void saveLibraryMember(LibraryMember lm);

	public void deleteLibraryMember(String string);

	public LibraryMember getLibraryMemberbyId(String idLibraryMember);
}
