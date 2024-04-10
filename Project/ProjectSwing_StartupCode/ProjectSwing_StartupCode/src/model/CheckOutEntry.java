package model;

public class CheckOutEntry {

	private BookCopy bc;
	private CheckOutRecord checkout;
	
	public CheckOutEntry(BookCopy bc, CheckOutRecord checkout) {
		super();
		this.bc = bc;
		this.checkout = checkout;
	}
	public BookCopy getBc() {
		return bc;
	}
	public void setBc(BookCopy bc) {
		this.bc = bc;
	}
	public CheckOutRecord getCheckout() {
		return checkout;
	}
	public void setCheckout(CheckOutRecord checkout) {
		this.checkout = checkout;
	}

	
}
