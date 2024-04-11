package business.service;

import business.model.Book;
import business.model.Response;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author kush
 */
public class BookService {

    public static List<Book> getAllBooks() {
        DataAccess da = new DataAccessFacade();
        List<Book> retval = new ArrayList<>();
        retval.addAll(da.readBooksMap().values());
        return retval;
    }
    public static Book bookByIsbn(String isbn) {
        DataAccess da = new DataAccessFacade();
        Collection<Book> books = da.readBooksMap().values();
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }
    public static List<Book> booksByIsbn(List<String> isbn) {
        DataAccess da = new DataAccessFacade();
        Collection<Book> books = da.readBooksMap().values();
        List<Book> bookList=new ArrayList<>();
        for (Book book : books) {
            if (isbn.contains(book.getIsbn())) {
                bookList.add(book);
            }
        }
        return bookList;
    }


    public static List<Book> getAvailableBooksFromList(List<Book> b) {
        List<Book> availableBooks = new ArrayList<>();
        for (int i = 0; i < b.size(); i++) {
            if (b.get(i).isAvailable())
                availableBooks.add(b.get(i));
        }
        return availableBooks;
    }

    public static Response addBook(Book book) {
        Response rsp = new Response();
        rsp = validateFields(book);
        if (!rsp.isStatus()) {
            return rsp;
        }
        DataAccess da = new DataAccessFacade();
        Book b1 = bookByIsbn(book.getIsbn());
        if (b1 != null) {
            return Response.getRsp("Book with provided ISBN already exists. Add copies instead", false);
        }
        da.saveNewBook(book);
        rsp.setMessage("Book added successfully");
        return rsp;
    }
    public static Response addBookCopies(String isbn, int count) {
        Book book = BookService.bookByIsbn(isbn);
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


    private static Response validateFields(Book book) {
        return Response.getRsp("Error in validation",false);
    }
}
