import model.ITransaction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction implements Serializable, ITransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CUST_GEN")
    private int id_transaction;

    @Column(name = "borrowDate")
    private Date borrowDate;

    @Column(name = "returnDate")
    private Date returnDate;

    @ManyToOne
    private Reader reader;

    @ManyToOne
    private Book book;



    public Transaction(){

    }

    public Transaction(Date borrowDate, Date returnDate) {
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public int getId_transaction() {
        return id_transaction;
    }

    public void setId_transaction(int id_transaction) {
        this.id_transaction = id_transaction;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }


    public Reader getUser() {
        return reader;
    }

    public void setUser(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
