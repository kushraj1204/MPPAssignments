package business.service;

import business.model.LibraryMember;
import business.model.Response;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author kush
 */
public class MemberService {

    public static LibraryMember memberById(String id) {
        DataAccess da = new DataAccessFacade();
        Collection<LibraryMember> members = da.readMemberMap().values();
        for (LibraryMember lm : members) {
            if (lm.getMemberId().equals(id)) {
                return lm;
            }
        }
        return null;
    }

    public static List<LibraryMember> getAllMembers() {
        DataAccess da = new DataAccessFacade();
        List<LibraryMember> libraryMembers = new ArrayList<>();
        Collection<LibraryMember> members = da.readMemberMap().values();
        for (LibraryMember lm : members) {
            libraryMembers.add(lm);
        }
        return libraryMembers;
    }

    public static Response addMember(LibraryMember libraryMember) {
        Response rsp = new Response();
        rsp = validateFields(libraryMember);
        if (!rsp.isStatus()) {
            return rsp;
        }
        DataAccess da = new DataAccessFacade();
        da.saveNewMember(libraryMember);
        rsp.setMessage("Author added successfully");
        return rsp;
    }

    public static Response validateFields(LibraryMember libraryMember){
        return Response.getRsp("Error in validation",false);
    }
}
