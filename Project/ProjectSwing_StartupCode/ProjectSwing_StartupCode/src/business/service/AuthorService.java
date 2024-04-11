package business.service;

import business.model.Author;
import business.model.Response;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author kush
 */
public class AuthorService {
    public static Author authorById(String id) {
        DataAccess da = new DataAccessFacade();
        Collection<Author> authors = da.readAuthorMap().values();
        for (Author author : authors) {
            if (author.getAuthorId().equals(id)) {
                return author;
            }
        }
        return null;
    }
    public static List<Author> getAllAuthors() {
        DataAccess da = new DataAccessFacade();
        List<Author> authors = new ArrayList<>();
        Collection<Author> members = da.readAuthorMap().values();
        authors.addAll(members);
        return authors;
    }

    public static Response addAuthor(Author author) {
        Response rsp = new Response();
        rsp = validateFields(author);
        if (!rsp.isStatus()) {
            return rsp;
        }
        DataAccess da = new DataAccessFacade();
        da.saveNewAuthor(author);
        rsp.setMessage("Author added successfully");
        return rsp;
    }

    public static Response validateFields(Author author){
        return Response.getRsp("Error in validation",false);
    }
}
