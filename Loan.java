import java.util.Date;

public class Loan {
    private int loanId;
    private int bookId;
    private int borrowerId;
    private Date issueDate;
    private Date dueDate;
    private boolean returned;
    private double fine;

    // Constructor
    public Loan(int loanId, int bookId, int borrowerId, Date issueDate, Date dueDate, boolean returned, double fine) {
        this.loanId = loanId;
        this.bookId = bookId;
        this.borrowerId = borrowerId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returned = returned;
        this.fine = fine;
    }

    // Getters and setters
    public int getLoanId() {
        return loanId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getBorrowerId() {
        return borrowerId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }
}
