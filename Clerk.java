import java.util.List;
import java.util.Date;

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

        Loan loan = new Loan(loans.size() + 1, book.getBookId(), borrower.getBorrowerId(), new Date(), null, false, 0.0);
        loans.add(loan);
        System.out.println("Book checked out successfully!");
    }

    public void checkInItem(Book book, Borrower borrower, List<Loan> loans) {
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
