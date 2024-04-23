package prob1;

import java.util.*;
public class Group<T> {
	private T specialElement;
	private List<?> elements = new ArrayList<>();
	public Group(T special, List<?> elements2) {
		this.specialElement = special;
		this.elements = elements2;
	}
	public T getSpecialElement() {
		return specialElement;
	}
	public List<?> getElements() {
		return elements;
	}
	@Override
	public String toString() {
		return "[special : " + specialElement.toString() 
		    + ", elements :\n   " + elements.toString() + "]";
	}
	
	
}
