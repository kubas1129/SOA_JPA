import model.IBook;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;


@Stateful
@Remote(IBooksManager.class)
public class BooksManager implements IBooksManager{


    private BooksRepository booksRepository;


    public BooksManager(){
        booksRepository = new BooksRepository();
    }


    public void AddBook(int isbn, String title, int year, String firstName, String lastName) {
        Book book = new Book();
        book.setISBN(isbn);
        book.setTitle(title);
        book.setYear(year);
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        book.setAuthor(author);
        booksRepository.createBook(book);
    }

    public void ModifyBook(Object book) {
        booksRepository.updateBook((Book)book);
    }

    public void DeleteBook(Object book) {
        booksRepository.deleteBook((Book)book);
    }

    public List<IBook> GetAllBooks() {
        List<IBook> books = new ArrayList<IBook>();
        for(Book book : booksRepository.getAll()){
            books.add((IBook)book);
        }
        return books;
    }

}
