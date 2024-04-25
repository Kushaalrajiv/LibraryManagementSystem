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
        Scanner in = new Scanner(System.in);
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
                    System.out.println("Enter the borrowerId: ");
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
                    System.out.println("Enter check out book details:");
                    System.out.println("Enter the bookID: ");
                    int id1 = in.nextInt();
                    System.out.println("Enter the title: ");
                    String title1 = in.next();
                    System.out.println("Enter the author: ");
                    String author1 = in.next();
                    System.out.println("Enter the subject: ");
                    String subject1 = in.next();
                    System.out.println("Enter the quantity: ");
                    int quantity1 = in.nextInt();
                    Book book1 = new Book(id1, title1, author1, subject1, quantity1);

                    System.out.println("Enter borrower details:");
                    System.out.println("Enter the borrowerId: ");
                    int borrowerid1 = 0;
                    System.out.println("Enter the borrower name: ");
                    String borrower_name1 = in.next();
                    System.out.println("Enter the address: ");
                    String address1 = in.next();
                    System.out.println("Enter the phone number of the borrower: ");
                    String phoneno1 = in.next();
                    Borrower borrower1 = new Borrower(borrowerid1, borrower_name1, address1, phoneno1);

                    librarySystem.checkoutBook(book1, borrower1);

                    break;

                case 4:
                    System.out.println("Enter check out book details:");
                    System.out.println("Enter the bookID: ");
                    int id2 = in.nextInt();
                    System.out.println("Enter the title: ");
                    String title2 = in.next();
                    System.out.println("Enter the author: ");
                    String author2 = in.next();
                    System.out.println("Enter the subject: ");
                    String subject2 = in.next();
                    System.out.println("Enter the quantity: ");
                    int quantity2 = in.nextInt();
                    Book book2 = new Book(id2, title2, author2, subject2, quantity2);

                    System.out.println("Enter borrower details:");
                    System.out.println("Enter the borrowerId: ");
                    int borrowerid2 = 0;
                    System.out.println("Enter the borrower name: ");
                    String borrower_name2 = in.next();
                    System.out.println("Enter the address: ");
                    String address2 = in.next();
                    System.out.println("Enter the phone number of the borrower: ");
                    String phoneno2 = in.next();
                    Borrower borrower2 = new Borrower(borrowerid2, borrower_name2, address2, phoneno2);

                    librarySystem.checkInBook(book2, borrower2);

                    break;
                case 5:
                    System.out.println("Enter the title of the book:");
                    String title_book = in.next();
                    librarySystem.searchBookByTitle(title_book);
                    break;

                case 6:
                    System.out.println("Enter the subject of the book:");
                    String title_sub = in.next();
                    librarySystem.searchBookBySubject(title_sub);
                    break;

                case 7:
                    System.out.println("Enter the author of the book:");
                    String title_author = in.next();
                    librarySystem.searchBookByAuthor(title_author);
                    break;

                case 8:
                    System.out.println("Enter borrower details:");
                    System.out.println("Enter the borrowerId: ");
                    int borrowerid3 = 0;
                    System.out.println("Enter the borrower name: ");
                    String borrower_name3 = in.next();
                    System.out.println("Enter the address: ");
                    String address3 = in.next();
                    System.out.println("Enter the phone number of the borrower: ");
                    String phoneno3 = in.next();
                    Borrower borrower3 = new Borrower(borrowerid3, borrower_name3, address3, phoneno3);

                    librarySystem.viewLoanHistory(borrower3);

                    break;

                case 9:

                    System.out.println("Exiting...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option. Please choose a valid option.");
            }


        }
    }

}
