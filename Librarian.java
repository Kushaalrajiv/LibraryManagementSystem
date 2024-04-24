import java.util.List;

public class Librarian extends Clerk {

    public void viewLoanHistory(Borrower borrower, List<Loan> loans) {
        System.out.println("Loan History for Borrower " + borrower.getBorrowerId() + ":");

        boolean found = false;
        for (Loan loan : loans) {
            if (loan.getBorrowerId() == borrower.getBorrowerId()) {
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

    public void addBorrower(Borrower borrower, List<Borrower> borrowers) {
        borrowers.add(borrower);
        System.out.println("Borrower added successfully!");
    }

    public void updateBorrowerInformation(Borrower borrower, String name, String address, String phone) {
        borrower.setName(name);
        borrower.setAddress(address);
        borrower.setPhone(phone);
        System.out.println("Borrower information updated successfully!");
    }
}
