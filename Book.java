public class Book {
    private int bookId;
    private String title;
    private String author;
    private String subject;
    private int quantity;

    // Constructor
    public Book(int bookId, String title, String author, String subject, int quantity) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.subject = subject;
        this.quantity = quantity;
    }

    // Getters and setters
    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getSubject() {
        return subject;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) throws QuantityException {
        if (quantity < 0) {
            throw new QuantityException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }

    public void increaseQuantity(int amount) throws QuantityException {
        if (amount < 0) {
            throw new QuantityException("Quantity to increase cannot be negative");
        }
        this.quantity += amount;
    }

    public void decreaseQuantity(int amount) throws QuantityException {
        if (amount < 0) {
            throw new QuantityException("Quantity to decrease cannot be negative");
        }
        if (this.quantity - amount < 0) {
            throw new QuantityException("Not enough quantity available");
        }
        this.quantity -= amount;
    }
}
