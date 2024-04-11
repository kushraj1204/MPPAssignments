package business.model;

import java.io.Serializable;

final public class Author extends Person implements Serializable {
    private String bio;
    private String authorId;

    public Author(String f, String l, String t, Address a, String bio, String authorId) {
        super(f, l, t, a);
        this.bio = bio;
        this.authorId = authorId;
    }

    public String getBio() {
        return bio;
    }

    public String getAuthorId() {
        return authorId;
    }

    private static final long serialVersionUID = 7508481940058530471L;
}
