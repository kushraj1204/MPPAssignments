package model;

public class Author extends Person {

	private boolean credentials;
	private String shortBio;

	public Author(boolean credentials, String shortBio) {
		super();
		this.credentials = credentials;
		this.shortBio = shortBio;
	}

}
