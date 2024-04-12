package service;

import business.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author kush
 */
public class BookService {
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
}