package business.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author kush
 */
public class CheckoutRecord implements Serializable {
    private static final long serialVersionUID = -63976228084869815L;

    private LibraryMember libraryMember;
    private String id;
    private List<CheckoutEntry> checkoutEntries;
//    private SystemUser user;

    public CheckoutRecord(String id, LibraryMember libraryMember, List<CheckoutEntry> checkoutEntries) {
        this.id=id;
        this.libraryMember = libraryMember;
        this.checkoutEntries = checkoutEntries;
//        this.user = user;
    }

    public LibraryMember getLibraryMember() {
        return libraryMember;
    }

    public List<CheckoutEntry> getCheckoutEntries() {
        return checkoutEntries;
    }

    public void addCheckoutEntries(CheckoutEntry checkoutEntry) {
        this.checkoutEntries.add(checkoutEntry);
    }

    public void addCheckoutEntries(List<CheckoutEntry> checkoutEntries) {
        this.checkoutEntries.addAll(checkoutEntries);
    }

    public String getId() {
        return id;
    }
    //    public SystemUser getUser() {
//        return user;
//    }
}
