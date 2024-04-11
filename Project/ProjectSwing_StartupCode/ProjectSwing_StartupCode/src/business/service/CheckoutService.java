package business.service;

import business.model.*;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

import java.time.LocalDate;
import java.util.*;

/**
 * @author kush
 */
public class CheckoutService {

    public static Response checkoutBook(String lmId, String isbns) {
        List<String> isbnList = List.of(isbns.split(","));
        List<String> isbn = new ArrayList<>(new HashSet<>(isbnList));

        /*lmId = "4e491238-910c-4d0e-a04e-c47158cf467f";
        isbn = List.of("28-12331,99-22223");*/
        LibraryMember lm = MemberService.memberById(lmId);
        if (lm == null) {
            return Response.getRsp("Library member with given id not found", false);
        }
        List<Book> b = BookService.booksByIsbn(isbn);
        List<Book> availableBooks = BookService.getAvailableBooksFromList(b);
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
            checkOutEntries.add(new CheckoutEntry(cr, dueDate, cp
            ));
            checkoutMsg+= "Book " + book.getTitle() + " with copyNo. " + cp.getCopyNum()
                    + " issued to " + lm.getFirstName() + ". Due date is " + dueDate + ".\n";
        }
        cr.addCheckoutEntries(checkOutEntries);
        boolean checkoutStatus = checkoutBook(cr);
        if (!checkoutStatus) {
            return Response.getRsp("Failed to checkout book.Try again later", false);
        } else {
            return Response.getRsp(checkoutMsg, true, cr);
        }
    }


    private static boolean checkoutBook(CheckoutRecord cr) {
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

    private static Response returnBook(String lmId, String isbn, int copyNo) {
        lmId = "4e491238-910c-4d0e-a04e-c47158cf467f";
        isbn = "99-22223";
        Response rsp = new Response();
        LibraryMember lm = MemberService.memberById(lmId);
        if (lm == null) {
            return Response.getRsp("Library member with given id not found", false);
        }
        Book b = BookService.bookByIsbn(isbn);
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

    private static boolean returnBook(CheckoutRecord cr, int copyNo, Book b) {
        try {
            List<CheckoutEntry> checkoutEntries = cr.getCheckoutEntries();
            for (int i = 0; i < checkoutEntries.size(); i++) {
                CheckoutEntry en = checkoutEntries.get(i);
                if (en.getBookCopy().getCopyNum() == copyNo && en.getBookCopy().getBook().getIsbn().equals(b.getIsbn())) {
                    DataAccess da = new DataAccessFacade();
                    da.updateBookCopyAvailability(cr.getCheckoutEntries().get(i).getBookCopy(), true);
                    da.updateCheckoutEntry(cr,b.getIsbn(),copyNo);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    private static CheckoutRecord findCheckOutRecordBy(LibraryMember lm, Book b, int copyNo) {
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
                    if (en.getBookCopy().getCopyNum() == copyNo && en.getBookCopy().getBook().getIsbn().equals(b.getIsbn())) {
                        return entry.getValue();
                    }
                }
            }
        }
        return null;
    }


}
