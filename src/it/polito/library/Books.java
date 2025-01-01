package it.polito.library;

public class Books {
	static int currentUniqueID=1000;
	int ID;
	String title;
	//int archiveNumber=1;
	public Books(String title) {
		super();
		this.title = title;
		this.ID=currentUniqueID;
		currentUniqueID++;
	}

	public int getTheID() {
		return ID;
	}
	
//	public void addNumberOfCopy() {
//		this.archiveNumber++;
//	}
	public void whatsID(int x) {this.ID=x;}	
}
