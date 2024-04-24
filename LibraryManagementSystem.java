import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LibraryManagementSystem {
    private List<Book> books;
    private List<Borrower> borrowers;
    private List<Loan> loans;
    private Clerk clerk;
    private Librarian librarian;
    private Connection connection;
    
    // Constructor
    
    
    // Method to add a book to the database
    // public void addBookToDatabase(Book book) {
    //     String sql = "INSERT INTO library (title, author, subject, quantity) VALUES (?, ?, ?, ?)";
        
    //     try (PreparedStatement statement = connection.prepareStatement(sql)) {
    //         statement.setString(1, book.getTitle());
    //         statement.setString(2, book.getAuthor());
    //         statement.setString(3, book.getSubject());
    //         statement.setInt(4, book.getQuantity());
            
    //         statement.executeUpdate();
    //         System.out.println("Book added to the database successfully!");
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }


    public LibraryManagementSystem() {
        this.books = new ArrayList<>();
        this.borrowers = new ArrayList<>();
        this.loans = new ArrayList<>();
        this.clerk = new Clerk();
        this.librarian = new Librarian();
        
        // try {
        //     String url = "jdbc:mysql://localhost:3306/my_db";
        //     String userName = "root";
        //     String password = "GTAvicecity123!";
        //     connection = DriverManager.getConnection(url, userName, password);
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }
    }

    public void addBook(Book book) {
        books.add(book);
        book.addToDatabase(); // Call the addToDatabase() method
        System.out.println("Book added successfully!");
    }

    public void addBorrower(Borrower borrower) {
        borrowers.add(borrower);
        System.out.println("Borrower added successfully!");
    }

    public void checkoutBook(Book book, Borrower borrower) {
        clerk.checkoutItem(book, borrower, loans);
    }

    public void checkInBook(Book book, Borrower borrower) {
        clerk.checkInItem(book, borrower, loans);
    }

    public void searchBookByTitle(String title) {
        clerk.searchBook(title, books);
    }

    public void searchBookBySubject(String subject) {
        clerk.searchBook(subject, books, true);
    }

    public void searchBookByAuthor(String author) {
        clerk.searchBook(author, books, 0);
    }

    public void viewLoanHistory(Borrower borrower) {
        borrower.viewLoanHistory(loans);
    }

    public void viewLoanHistory(Borrower borrower, List<Loan> loans) {
        librarian.viewLoanHistory(borrower, loans);
    }
    public void simulateBorrowerCheckout(Borrower borrower, Book book, List<Loan> loans) {
        Thread checkoutThread = new BorrowerCheckoutThread(borrower, book, loans);
        checkoutThread.start();
    }


    public static void main(String[] args) {
        LibraryManagementSystem librarySystem = new LibraryManagementSystem();
        
        Book book1 = new Book(1, "Java Programming", "John Doe", "Programming", 5);
        Book book2 = new Book(2, "Data Structures", "Jane Smith", "Computer Science", 3);
        librarySystem.addBook(book1);
        librarySystem.addBook(book2);

        Borrower borrower1 = new Borrower(101, "Alice", "123 Main St", "123-456-7890");
        Borrower borrower2 = new Borrower(102, "Bob", "456 Elm St", "987-654-3210");
        librarySystem.addBorrower(borrower1);
        librarySystem.addBorrower(borrower2);

        librarySystem.checkoutBook(book1, borrower1);
        librarySystem.checkoutBook(book2, borrower1);
        librarySystem.checkoutBook(book2, borrower2);

        librarySystem.checkInBook(book1, borrower1);
        librarySystem.checkInBook(book2, borrower1);

        librarySystem.searchBookByTitle("Java Programming");
        librarySystem.searchBookBySubject("Computer Science");
        librarySystem.searchBookByAuthor("Jane Smith");

        librarySystem.viewLoanHistory(borrower1);
    }
}
