import java.sql.*;
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
    public void addToDatabase() {
        // Establish database connection
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Prepare SQL statement for inserting book information
            String sql = "INSERT INTO books (title, author, subject, quantity) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Set parameters for the SQL statement
                statement.setString(1, this.title);
                statement.setString(2, this.author);
                statement.setString(3, this.subject);
                statement.setInt(4, this.quantity);

                // Execute the SQL statement
                statement.executeUpdate();
                System.out.println("Book added to the database successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error adding book to the database: " + e.getMessage());
    }
    }

    
    }

