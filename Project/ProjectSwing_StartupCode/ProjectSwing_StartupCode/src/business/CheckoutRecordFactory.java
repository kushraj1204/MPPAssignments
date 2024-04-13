package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CheckoutRecordFactory {

    public static CheckoutRecord getCheckoutRecord(LibraryMember lm, List<Book> availableBooks) {
        CheckoutRecord cr = new CheckoutRecord(UUID.randomUUID().toString(), lm, new ArrayList<>());
        List<CheckoutEntry> checkOutEntries = new ArrayList<>();
        for (int i = 0; i < availableBooks.size(); i++) {
            Book book = availableBooks.get(i);
            BookCopy cp = book.getNextAvailableCopy();
            LocalDate dueDate = LocalDate.now().plusDays(book.getMaxCheckoutLength());
            checkOutEntries.add(new CheckoutEntry(cr, dueDate, cp));
        }
        cr.addCheckoutEntries(checkOutEntries);
        return cr;
    }
}
