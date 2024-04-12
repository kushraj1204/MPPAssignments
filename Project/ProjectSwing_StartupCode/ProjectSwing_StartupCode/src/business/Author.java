package business;

import java.io.Serializable;

final public class Author extends Person implements Serializable {
	private String bio;
	private String idAuthor;

	public String getBio() {
		return bio;
	}

	public String getidAuthor() {
		return idAuthor;
	}

	public Author(String idAuthor, String f, String l, String t, Address a, String bio) {
		super(f, l, t, a);
		this.bio = bio;
		this.idAuthor = idAuthor;
	}

	private static final long serialVersionUID = 7508481940058530471L;
}
