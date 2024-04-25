import java.util.List;
import java.util.Date;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Clerk {
    public void searchBook(String title, List<Book> books) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Book found: " + book.getTitle() + " by " + book.getAuthor());
                return;
            }
        }
        System.out.println("Book not found with title: " + title);
    }

    public void searchBook(String subject, List<Book> books, boolean bySubject) {
        for (Book book : books) {
            if (book.getSubject().equalsIgnoreCase(subject)) {
                System.out.println("Book found: " + book.getTitle() + " by " + book.getAuthor());
                return;
            }
        }
        System.out.println("No book found for subject: " + subject);
    }

    public void searchBook(String authorName, List<Book> books, int dummy) {
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(authorName)) {
                System.out.println("Book found: " + book.getTitle() + " by " + book.getAuthor());
                return;
            }
        }
        System.out.println("No book found for author: " + authorName);
    }


    public void checkoutItem(Book book, Borrower borrower, List<Loan> loans) {

        //I MUST ADD A TABLE TO ADD BORROWER ID , BOOK ID, DATE ,DUE DATE , RETURNED, FINE
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter due date (yyyy-mm-dd): ");
        String dueDateStr = scanner.next();
        Date dueDate = null;
        try {
            dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(dueDateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter in yyyy-mm-dd format.");
            return;
        }

        System.out.println("Has the book been returned? (true/false): ");
        boolean returned = scanner.nextBoolean();

        System.out.println("Enter fine amount: ");
        double fine = scanner.nextDouble();

        Loan loan = new Loan(loans.size() + 1, book.getBookId(), borrower.getBorrowerId(), new Date(), dueDate, returned, fine);
        loans.add(loan);

        System.out.println("Book checked out successfully!");

        System.out.println("List of Loans:");
        for (Loan l : loans) {
            System.out.println("Loan ID: " + l.getLoanId());
            System.out.println("Book ID: " + l.getBookId());
            System.out.println("Borrower ID: " + l.getBorrowerId());
            System.out.println("Due Date: " + l.getDueDate());
            System.out.println("Returned: " + l.isReturned());
            System.out.println("Fine: " + l.getFine());
            System.out.println(); // Empty line for readability
        }
    }

    public void checkInItem(Book book, Borrower borrower, List<Loan> loans) {

        // IN THE TABLE CREATED FOR CHECKOUT ADD A WAY TO REMOVE THE BOOK ID COLUMN IF IT EXISTS WHEN CHECKING OUT AND DISPLAY THE FINE IN THE TABLE
        for (Loan loan : loans) {
            if (loan.getBookId() == book.getBookId() && loan.getBorrowerId() == borrower.getBorrowerId() && !loan.isReturned()) {
                loan.setReturned(true);
                System.out.println("Book checked in successfully!");
                return;
            }
        }
        System.out.println("Book not checked out by this borrower.");
    }

    public void recordFine(Borrower borrower, double fineAmount) {
        borrower.recordFine(fineAmount);
        System.out.println("Fine recorded successfully for borrower: " + borrower.getName());
    }

}
