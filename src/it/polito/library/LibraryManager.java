package it.polito.library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

//import com.sun.tools.javac.util.List;

//import com.sun.tools.javac.code.Attribute.Array;


public class LibraryManager {
	int calc=1000,calcreader=1000;
	TreeMap<String,ArrayList<Books>> booksArr=new TreeMap<>();
	TreeMap<String,Integer> numberOfCopy=new TreeMap<>();
	TreeMap<Integer,TreeMap<String,String>> rentals=new TreeMap<>();
	TreeMap<Integer,Books> booksByID=new TreeMap<>();
	TreeMap<Integer,Reader> ReadersByID=new TreeMap<>();
	TreeMap<Integer,Integer> booksToRent=new TreeMap<>(); //value is the availability. 1 available, 0 not available
	TreeMap<String,String> renteArchive=new TreeMap<>(), getOngoingR4=new TreeMap<>();
	Set<String> tempoli= new HashSet<>();
	
	// R1: Readers and Books 
    
    /**
	 * adds a book to the library archive
	 * The method can be invoked multiple times.
	 * If a book with the same title is already present,
	 * it increases the number of copies available for the book
	 * 
	 * @param title the title of the added book
	 * @return the ID of the book added 
	 */
    public String addBook(String title) {
    	//in case the added book is a duplicate
    	//the method updates the number of copies available for the book.
    	if(numberOfCopy.containsKey(title)) {
    		//booksArr.get(title).addNumberOfCopy();
    		int x=numberOfCopy.get(title);
    		x++;
    		numberOfCopy.put(title, x);
    		Books temp=new Books(title); temp.whatsID(calc++);
    		booksArr.get(title).add(temp);
    		
    		booksByID.put(temp.ID, temp);
    		
    		booksToRent.put(temp.ID,1);
    		
    		return String.valueOf(temp.ID);
    	}
    	
    	numberOfCopy.put(title, 1);
    	
    	Books temp=new Books(title); temp.whatsID(calc++);
    	ArrayList<Books> z=new ArrayList<>();
    	z.add(temp);
    	booksArr.put(title, z);
    	
    	booksByID.put(temp.ID, temp);
    	
    	booksToRent.put(temp.ID,1);
    	
    	//The method returns the unique book ID assigned to the book as a String value. 
    	return String.valueOf(temp.ID);
    }
    
    /**
	 * Returns the book titles available in the library
	 * sorted alphabetically, each one linked to the
	 * number of copies available for that title.
	 * 
	 * @return a map of the titles liked to the number of available copies
	 */
    public SortedMap<String, Integer> getTitles() {    	
    	
        return numberOfCopy;
    }
    
    /**
	 * Returns the books available in the library
	 * 
	 * @return a set of the titles liked to the number of available copies
	 */
    public Set<String> getBooks() {   
    	ArrayList<String> returnable=new ArrayList<String>(); 
    	//System.out.println(booksByID.keySet());
    	for(Integer x:booksByID.keySet()) {
    		returnable.add(String.valueOf(x));
    		//System.err.print("set:"+returnable+"   --------    ");
    	}
    //	Set<String> temp = new Set<String>();
    	Set<String> temp=new HashSet<>(returnable);
    	//temp.clear();
    	temp.addAll(returnable);
        return temp;
    }
    
    /**
	 * Adds a new reader
	 * 
	 * @param name first name of the reader
	 * @param surname last name of the reader
	 */
    public void addReader(String name, String surname) {
    	Reader temp=new Reader(name, surname); temp.whatsID(calcreader++);
    	ReadersByID.put(temp.ID, temp);
    }
    
    
    /**
	 * Returns the reader name associated to a unique reader ID
	 * 
	 * @param readerID the unique reader ID
	 * @return the reader name
	 * @throws LibException if the readerID is not present in the archive
	 */
    public String getReaderName(String readerID) throws LibException {
    	Integer readerIDINT=Integer.parseInt(readerID);
    	if(!ReadersByID.containsKey(readerIDINT)) {throw new LibException("the ID does not exist in the archive");}
    	
    	
        return (ReadersByID.get(readerIDINT).Firstname+" "+ReadersByID.get(readerIDINT).Lastname);
    }    
    
    
    // R2: Rentals Management
    
    
    /**
	 * Retrieves the bookID of a copy of a book if available
	 * 
	 * @param bookTitle the title of the book
	 * @return the unique book ID of a copy of the book or the message "Not available"
	 * @throws LibException  an exception if the book is not present in the archive
	 */
    public String getAvailableBook(String bookTitle) throws LibException {
    	if(!booksArr.containsKey(bookTitle)) {throw new LibException("the title is not present in the library archive");}
    	//int bookIDtoRent=0;
 
    	
    	/*
    	 * 	TreeMap<String,ArrayList<Books>> booksArr=new TreeMap<>();
			TreeMap<String,Integer> numberOfCopy=new TreeMap<>();
			TreeMap<Integer,Books> booksByID=new TreeMap<>();
			TreeMap<Integer,Reader> ReadersByID=new TreeMap<>();
			TreeMap<Integer,Integer> booksToRent=new TreeMap<>();*/
    	//booksArr.get(bookTitle);
    	List<Books> temp=booksArr.get(bookTitle);
    	
    	
    	for(int i=0;i<temp.size();i++) {
    		int idnew=temp.get(i).ID;
    		if(booksToRent.get(idnew)==1){
    		/**/   //booksToRent.put(idnew, 0);
    			//System.err.println(String.valueOf(idnew));
    			return String.valueOf(idnew);
    		}
    	}
    	
    	return "Not available";
    	//booksArr.get(bookTitle).stream().filter(x->x.ID==).findFirst();
    	
    	/*
    	 * The getAvailableBook() method retrieves the bookID of an available book copy.
    	 *  The method takes the book title as input, and returns the book ID of the first copy available, in order of book ID,
    	 *  for a specific book as a String value. If all copies of the title are currently rented,
    	 *   it returns value "Not available". */
       
    	//return String.valueOf(bookIDtoRent);
    }   

    /**
	 * Starts a rental of a specific book copy for a specific reader
	 * 
	 * @param bookID the unique book ID of the book copy
	 * @param readerID the unique reader ID of the reader
	 * @param startingDate the starting date of the rental
	 * @throws LibException  an exception if the book copy or the reader are not present in the archive,
	 * if the reader is already renting a book, or if the book copy is already rented
	 */
	public void startRental(String bookID, String readerID, String startingDate) throws LibException {
		if(!ReadersByID.containsKey(Integer.parseInt(readerID))) {throw new LibException("the ID does not exist in the archive");}
    	if(!booksByID.containsKey(Integer.parseInt(bookID))) {throw new LibException("the title is not present in the library archive");}
    	if((ReadersByID.get(Integer.parseInt(readerID)).alreadyBooking==true)||(booksToRent.get(Integer.parseInt(bookID))==0))throw new LibException("EXCEPTION!");
    	booksToRent.put(Integer.parseInt(bookID), 0);
    	
    	TreeMap<String,String> temp=new TreeMap<>();
    	temp.put(readerID, startingDate+" ONGOING");
    	rentals.put(Integer.parseInt(bookID), temp);
    	renteArchive.put((bookID+"+"+readerID), temp.get(readerID));
    	ReadersByID.get(Integer.parseInt(readerID)).alreadyBooking=true;
    	getOngoingR4.put(readerID, bookID);
    	ReadersByID.get(Integer.parseInt(readerID)).rentcounts++;
	}
    
	/**
	 * Ends a rental of a specific book copy for a specific reader
	 * 
	 * @param bookID the unique book ID of the book copy
	 * @param readerID the unique reader ID of the reader
	 * @param endingDate the ending date of the rental
	 * @throws LibException  an exception if the book copy or the reader are not present in the archive,
	 * if the reader is not renting a book, or if the book copy is not rented
	 */
    public void endRental(String bookID, String readerID, String endingDate) throws LibException {
    	if(!ReadersByID.containsKey(Integer.parseInt(readerID))) {throw new LibException("the ID does not exist in the archive");}
    	if(!booksByID.containsKey(Integer.parseInt(bookID))) {throw new LibException("the title is not present in the library archive");}
    	if(booksToRent.get(Integer.parseInt(bookID))==1) {throw new LibException("the book is not currently rented");}
    	
    	booksToRent.put(Integer.parseInt(bookID), 1);
    	
    	TreeMap<String,String> temp=new TreeMap<>();
    	temp.put(readerID, rentals.get(Integer.parseInt(bookID)).values().toString().substring(1).split(" ")[0]+" "+endingDate);
    	rentals.put(Integer.parseInt(bookID), temp);
    	ReadersByID.get(Integer.parseInt(readerID)).alreadyBooking=false;
    	renteArchive.put((bookID+"+"+readerID), rentals.get(Integer.parseInt(bookID)).values().toString().substring(1).split(" ")[0]+" "+endingDate);
    	getOngoingR4.remove(readerID, bookID);    	
    }
    
    
   /**
	* Retrieves the list of readers that rented a specific book.
	* It takes a unique book ID as input, and returns the readers' reader IDs and the starting and ending dates of each rental
	* 
	* @param bookID the unique book ID of the book copy
	* @return the map linking reader IDs with rentals starting and ending dates
	* @throws LibException  an exception if the book copy or the reader are not present in the archive,
	* if the reader is not renting a book, or if the book copy is not rented
	*/
    public SortedMap<String, String> getRentals(String bookID) throws LibException {
    	if (!booksByID.containsKey(Integer.parseInt(bookID))) throw new LibException("new exception!");
    	SortedMap<String, String> result = new TreeMap<>();
    	renteArchive.forEach((key, value) -> { if (key.startsWith((bookID + "+"))) result.put(key.substring((bookID + "+").length()), value);  });
        return result;
    }
    
    
    // R3: Book Donations
    
    /**
	* Collects books donated to the library.
	* 
	* @param donatedTitles It takes in input book titles in the format "First title,Second title"
	*/
    public void receiveDonation(String donatedTitles) { for (String title : Arrays.asList(donatedTitles.split(","))) addBook(title);
    }
    
    // R4: Archive Management

    /**
	* Retrieves all the active rentals.
	* 
	* @return the map linking reader IDs with their active rentals

	*/
    public Map<String, String> getOngoingRentals() {
        return getOngoingR4;
    }
    
    /**
	* Removes from the archives all book copies, independently of the title, that were never rented.
	* 
	*/
    public void removeBooks() {
    	booksByID.keySet().removeIf(bookID -> !rentals.containsKey(bookID));
    	booksArr.entrySet().removeIf(entry -> entry.getValue().removeIf(book -> !rentals.containsKey(book.ID)));
    }
    	
    // R5: Stats
    
    /**
	* Finds the reader with the highest number of rentals
	* and returns their unique ID.
	* 
	* @return the uniqueID of the reader with the highest number of rentals
	*/
    public String findBookWorm() {
        return ""+ReadersByID.entrySet()
        .stream()
        .max(Map.Entry.comparingByValue(Comparator.comparingInt(Reader::getrentcount)))
        .map(Map.Entry::getKey)
        .orElse(-1);
    }
    
    /**
	* Returns the total number of rentals by title. 
	* 
	* @return the map linking a title with the number of rentals
	*/
    public Map<String,Integer> rentalCounts() {
    	   Map<String, Integer> returnable = new TreeMap<>();
    	    ArrayList<String> Y = new ArrayList<>();
    	    
    	    renteArchive.forEach((s, value) -> {
    	        returnable.merge(booksByID.get(Integer.parseInt(s.substring(0, 4))).title, 1, Integer::sum);
    	        Y.add(booksByID.get(Integer.parseInt(s.substring(0, 4))).title);
    	    });

    	    booksArr.keySet().stream()
    	        .filter(s -> !returnable.containsKey(s) && !tempoli.contains(s))
    	        .forEach(s -> returnable.put(s, 0));

    	    return returnable;
    	    
    	    
/*    	Map<String, Integer> returnable = new TreeMap<>();
    	ArrayList<String> Y=new ArrayList<>();
    	for (String s : renteArchive.keySet()) {
    	    returnable.compute(booksByID.get(Integer.parseInt(s.substring(0, 4))).title, (key, value) -> value == null ? 1 : value + 1);
    	    Y.add(booksByID.get(Integer.parseInt(s.substring(0, 4))).title);
    	}
    	for(String s: booksArr.keySet())
    		if(!returnable.containsKey(s)&&!tempoli.contains(s))
    			returnable.put(s, 0);
    	return returnable;
 */   }

}
