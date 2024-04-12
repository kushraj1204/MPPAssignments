package business.service;

import business.model.LibraryMember;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

import java.util.Collection;

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
}
