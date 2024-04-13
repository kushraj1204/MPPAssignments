package business;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author kush
 */
public class CheckoutEntry implements Serializable {

    private static final long serialVersionUID = 6110690276685962829L;

    private CheckoutRecord checkoutRecord;
    private LocalDate dueReturnDate;
    private LocalDate returnDate;
    private BookCopy bookCopy;

    CheckoutEntry(CheckoutRecord checkoutRecord, LocalDate returnDate, BookCopy bookCopy) {
        this.checkoutRecord = checkoutRecord;
        this.dueReturnDate = returnDate;
        this.bookCopy = bookCopy;
    }
    CheckoutEntry( LocalDate returnDate, BookCopy bookCopy) {
        this.dueReturnDate = returnDate;
        this.bookCopy = bookCopy;
    }
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public CheckoutRecord getCheckoutRecord() {
        return checkoutRecord;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public LocalDate getDueDate() {
        return dueReturnDate;
    }
    public BookCopy getBookCopy() {
        return bookCopy;
    }
}