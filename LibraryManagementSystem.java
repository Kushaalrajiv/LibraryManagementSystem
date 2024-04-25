import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class LibraryManagementSystem {
    private List<Book> books;
    private List<Borrower> borrowers;
    private List<Loan> loans;
    private Clerk clerk;
    private Librarian librarian;
    private Connection connection;


    public LibraryManagementSystem() {
        this.books = new ArrayList<>();
        this.borrowers = new ArrayList<>();
        this.loans = new ArrayList<>();
        this.clerk = new Clerk();
        this.librarian = new Librarian();

    }

    public void addBook(Book book) {
        books.add(book);
        book.addToDatabase();
        System.out.println("Book added successfully!");
    }

    public void addBorrower(Borrower borrower) {
        borrowers.add(borrower);
        borrower.addToDatabase();
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
        Scanner in=new Scanner(System.in);
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add Book");
            System.out.println("2. Add Borrower");
            System.out.println("3. Checkout Book");
            System.out.println("4. Checkin Book");
            System.out.println("5. Search Book By Title");
            System.out.println("6. Search Book By Subject");
            System.out.println("7. Search Book By Author");
            System.out.println("8. View Loan History");
            System.out.println("9. Exit");

            int choice = in.nextInt();
            in.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("Enter book details:");
                    System.out.println("Enter the bookID: ");
                    int id = in.nextInt();
                    System.out.println("Enter the title: ");
                    String title = in.next();
                    System.out.println("Enter the author: ");
                    String author = in.next();
                    System.out.println("Enter the subject: ");
                    String subject = in.next();
                    System.out.println("Enter the quantity: ");
                    int quantity = in.nextInt();
                    Book book = new Book(id, title, author, subject, quantity);
                    librarySystem.addBook(book);
                    break;
                case 2:

                    System.out.println("Enter borrower details:");
//                    System.out.println("Enter the borrowerId: ");
                    int borrowerid = 0;
                    System.out.println("Enter the borrower name: ");
                    String borrower_name = in.next();
                    System.out.println("Enter the address: ");
                    String address = in.next();
                    System.out.println("Enter the phone number of the borrower: ");
                    String phoneno = in.next();
                    Borrower borrower = new Borrower(borrowerid, borrower_name, address, phoneno);
                    librarySystem.addBorrower(borrower);
                    break;

                case 3:
                   
                    
                    break;
            }

//        librarySystem.checkoutBook(book1, borrower1);
//        librarySystem.checkoutBook(book2, borrower1);
//        librarySystem.checkoutBook(book2, borrower2);
//
//        librarySystem.checkInBook(book1, borrower1);
//        librarySystem.checkInBook(book2, borrower1);

        librarySystem.searchBookByTitle("Java Programming");
        librarySystem.searchBookBySubject("Computer Science");
        librarySystem.searchBookByAuthor("Jane Smith");

//        librarySystem.viewLoanHistory(borrower1);
    }
}}
