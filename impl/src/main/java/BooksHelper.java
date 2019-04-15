import model.IBook;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;


@Singleton
public class BooksHelper{


    public Book CreateBook(int id_author, int isbn, String title,int year,int price){
        Book book = new Book();

        book.setISBN(isbn);
        book.setTitle(title);
        book.setYear(year);

        return book;
    }

    public List<IBook> initIBookList(){
        return new ArrayList<IBook>();
    }

    public List<Book> GetBooksFromDB(){
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("BooksJPA");
////        EntityManager entityManager = factory.createEntityManager();
////
////
////        List<Book> bks = entityManager.createQuery("SELECT b from Book b",Book.class).getResultList();
////        entityManager.close();
////        factory.close();
////        return bks;
        return null;
    }

    public List<IBook> ConvertListToIBook(List<Book> books){
        List<IBook> bk = new ArrayList<IBook>();
        for(Book c : books){
            bk.add((IBook) c);
        }
        return bk;
    }

    public IBook ConvertToIBook(Book book){
        return (IBook)book;
    }
}
