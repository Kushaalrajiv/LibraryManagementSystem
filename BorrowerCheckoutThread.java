import java.util.Date;
import java.util.List;

public class BorrowerCheckoutThread extends Thread {
    private Borrower borrower;
    private Book book;
    private List<Loan> loans;

    public BorrowerCheckoutThread(Borrower borrower, Book book, List<Loan> loans) {
        this.borrower = borrower;
        this.book = book;
        this.loans = loans;
    }

    public void run() {
        synchronized (loans) {
           
            if (book.getQuantity() > 0) {
                
                try {
                    book.decreaseQuantity(1);
                } catch (QuantityException e) {
                    System.out.println("Error: " + e.getMessage());
                    return;
                }

                Loan loan = new Loan(loans.size() + 1, book.getBookId(), borrower.getBorrowerId(), new Date(), null, false, 0.0);
                loans.add(loan);
                System.out.println(borrower.getName() + " checked out " + book.getTitle());
            } else {
                System.out.println(book.getTitle() + " is not available for " + borrower.getName());
            }
        }
    }
}