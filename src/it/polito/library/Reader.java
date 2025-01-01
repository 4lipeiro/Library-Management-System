package it.polito.library;

public class Reader {
	static int currentUniqueIDReader=1000;
	int ID;
	String Firstname;
	String Lastname;
	boolean alreadyBooking=false;
	int rentcounts=0;
	public Reader(String first,String last) {
		super();
		Firstname=first;
		Lastname=last;
		this.ID=currentUniqueIDReader;
		currentUniqueIDReader++;
	}
	public void whatsID(int x) {this.ID=x;}
	public int getTheID() {
		return ID;
	}
	public int getrentcount() {return rentcounts;}
}
