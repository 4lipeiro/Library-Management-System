# Library Management System

This project is a Library Management System developed as part of an exam. It provides functionalities to manage books, readers, rentals, and donations in a library. The system is implemented in Java and uses JUnit for testing.

**Note:** This project was initially developed in GitLab and has been moved to GitHub.

## Features

### R1: Readers and Books
- **Add Book**: Adds a book to the library archive. If a book with the same title is already present, it increases the number of copies available for the book.
- **Get Titles**: Returns the book titles available in the library sorted alphabetically, each one linked to the number of copies available for that title.
- **Get Books**: Returns the books available in the library.
- **Add Reader**: Adds a new reader to the library.
- **Get Reader Name**: Returns the reader name associated with a unique reader ID.

### R2: Rentals Management
- **Get Available Book**: Retrieves the bookID of a copy of a book if available.
- **Start Rental**: Starts a rental of a specific book copy for a specific reader.
- **End Rental**: Ends a rental of a specific book copy for a specific reader.
- **Get Rentals**: Retrieves the list of readers that rented a specific book.

### R3: Book Donations
- **Receive Donation**: Collects books donated to the library.

### R4: Archive Management
- **Get Ongoing Rentals**: Retrieves all the active rentals.
- **Remove Books**: Removes from the archives all book copies that were never rented.

### R5: Stats
- **Find Book Worm**: Finds the reader with the highest number of rentals.
- **Rental Counts**: Returns the total number of rentals by title.

## Project Structure

The project is structured as follows:
- **src/it/polito/library**: Contains the main classes for the library management system.
  - `LibraryManager.java`: Main class managing the library operations.
  - `Books.java`: Class representing a book.
  - `Reader.java`: Class representing a reader.
  - `LibException.java`: Custom exception class for library-related exceptions.
- **test/it/polito/po/test**: Contains the JUnit test cases for the library management system.
  - `TestR1_Readers_and_Books.java`: Tests for readers and books functionalities.
  - `TestR2_Rentals_Management.java`: Tests for rentals management functionalities.
  - `TestR3_Book_Donations.java`: Tests for book donations functionalities.
- **test/example**: Contains an example test case.
  - `TestExample.java`: Example test case covering all functionalities.

## Build and Test

The project uses Maven for build and dependency management. To build and test the project, use the following commands:

```sh
mvn clean install
mvn test
```

## Dependencies

- **JUnit 4.13.2**: Used for writing and running test cases.

## How to Run

1. Clone the repository.
2. Navigate to the project directory.
3. Run the Maven build and test commands.

## License

This project is licensed under the MIT License.

## Contact

For any questions or inquiries, please contact [Your Name] at [Your Email].
