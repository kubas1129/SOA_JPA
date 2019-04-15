import model.IBook;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

import static javax.persistence.GenerationType.TABLE;

@Entity
@Table(name="Book")
public class Book implements Serializable, IBook {

    @Id
    @GeneratedValue(strategy=TABLE, generator="CUST_GEN")
    @Column(name = "id",nullable = false)
    private int id_book;

    @Column(name = "isbn", nullable = false, unique = true)
    private int ISBN;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "year")
    private int year;

    @ManyToOne
    private Author author;

    @OneToMany(mappedBy = "book")
    private List<Transaction> transactions;


    public Book(){

    }

    public Book(int ISBN, String title, int year, int id_author) {
        this.ISBN = ISBN;
        this.title = title;
        this.year = year;
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
