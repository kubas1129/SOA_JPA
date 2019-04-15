import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class BooksRepository {

    private EntityManagerFactory factory;
    EntityManager em;


    public BooksRepository() {
        factory= Persistence.createEntityManagerFactory("BooksJPA");
        em=factory.createEntityManager();
    }


    public Book getBook(int id){
        em.getTransaction().begin();
        Query query = em.createQuery("FROM Book where id_book = :var", Book.class);
        query.setParameter("var",id);
        em.getTransaction().commit();
        if(query.getResultList().isEmpty())
        {
            return null;
        }
        else
        {
            return (Book)query.getSingleResult();
        }
    }


    public List<Book> getAll(){
        em.getTransaction().begin();
        Query query = em.createQuery("FROM Book ", Book.class);
        em.getTransaction().commit();
        return  query.getResultList();
    }


    public void createBook(Book book){
        em.getTransaction().begin();
        Query query = em.createQuery("from Author a where a.firstName = :first and a.lastName = :last",Author.class).setParameter("first",book.getAuthor().getFirstName()).setParameter("last",book.getAuthor().getLastName());
        em.getTransaction().commit();

        //Creating author or set existing one
        if(!query.getResultList().isEmpty())
        {
            book.setAuthor((Author)query.getSingleResult());
        }
        else
        {
            em.getTransaction().begin();
            em.persist(book.getAuthor());
            em.getTransaction().commit();
        }

        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();
    }


    public void updateBook(Book book){
        em.getTransaction().begin();
        em.merge((Book)book);
        em.getTransaction().commit();
    }


    public void deleteBook(Object book){
        em.getTransaction().begin();
        em.remove(em.contains((Book)book) ? book : em.merge((Book)book));
        em.getTransaction().commit();

    }

}
