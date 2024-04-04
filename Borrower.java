import java.util.List;

public class Borrower {
    private int borrowerId;
    private String name;
    private String address;
    private String phone;
    private double fine;

    // Constructor
    public Borrower(int borrowerId, String name, String address, String phone) {
        this.borrowerId = borrowerId;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    // Getters and setters
    public int getBorrowerId() {
        return borrowerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getFine() {
        return fine;
    }

    public void recordFine(double fineAmount) {
        this.fine += fineAmount;
    }

    public void updatePersonalInformation(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public void viewLoanHistory(List<Loan> loans) {
        System.out.println("Loan History for Borrower " + borrowerId + ":");

        boolean found = false;
        for (Loan loan : loans) {
            if (loan.getBorrowerId() == borrowerId) {
                found = true;
                System.out.println("Loan ID: " + loan.getLoanId());
                System.out.println("Book ID: " + loan.getBookId());
                System.out.println("Issue Date: " + loan.getIssueDate());
                System.out.println("Due Date: " + loan.getDueDate());
                System.out.println("Returned: " + (loan.isReturned() ? "Yes" : "No"));
                System.out.println("Fine: " + loan.getFine());
                System.out.println();
            }
        }

        if (!found) {
            System.out.println("No loan history found for this borrower.");
        }
    }
}
