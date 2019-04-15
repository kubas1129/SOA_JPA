import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class LibraryRepository {

    private EntityManagerFactory factory;
    EntityManager em;
    CriteriaBuilder cb;


    public LibraryRepository() {
        factory= Persistence.createEntityManagerFactory("BooksJPA");
        em=factory.createEntityManager();
        cb=em.getCriteriaBuilder();
    }

    public List<Transaction> getTransactionsByAuthors(String firstName,String lastName){
        em.getTransaction().begin();
        Query query = em.createQuery("FROM Transaction t where t.book.author.firstName  = :fN and t.book.author.lastName = :lN",Transaction.class).setParameter("fN",firstName).setParameter("lN",lastName);
        em.getTransaction().commit();
        return query.getResultList();
    }

    public List<Transaction> getTransactionsByReaders(String firstName,String lastName){
        em.getTransaction().begin();
        Query query = em.createQuery("FROM Transaction t where t.reader.firstName  = :fN and t.reader.lastName = :lN",Transaction.class).setParameter("fN",firstName).setParameter("lN",lastName);
        em.getTransaction().commit();
        return query.getResultList();
    }

    public List<Transaction> getTransactionsByTitle(String title){
        em.getTransaction().begin();
        Query query = em.createQuery("FROM Transaction t where t.book.title  = :tt ",Transaction.class).setParameter("tt",title);
        em.getTransaction().commit();
        return query.getResultList();
    }



    public Reader getReaderByNames(String firstName,String lastName){
        em.getTransaction().begin();
        Query query = em.createQuery("FROM Reader where firstName = :fN and lastName = :lN",Reader.class).setParameter("fN",firstName).setParameter("lN",lastName);
        em.getTransaction().commit();
        if(query.getResultList().isEmpty()){
            return null;
        }
        else
        {
            return (Reader)query.getSingleResult();
        }
    }

    public boolean checkReaderExist(String firstName,String lastName){
        em.getTransaction().begin();
        Query query = em.createQuery("FROM Reader where firstName = :fN and lastName = :lN",Reader.class).setParameter("fN",firstName).setParameter("lN",lastName);
        em.getTransaction().commit();
        if(query.getResultList().isEmpty()){
            return false;
        }
        else{
            return true;
        }

    }

    public void createReader(Reader reader){
        em.getTransaction().begin();
        em.persist(reader);
        em.getTransaction().commit();
    }

    public void createTransaction(Transaction transaction){
        em.getTransaction().begin();
        em.persist(transaction);
        em.getTransaction().commit();
    }

    public List<Transaction> getAllTransaction(){
        em.getTransaction().begin();
        Query query = em.createQuery("from Transaction", Transaction.class);
        em.getTransaction().commit();
        return query.getResultList();
    }

    public List<Transaction> getAllTransactionForReader(Reader reader){
        em.getTransaction().begin();
        Query query = em.createQuery("FROM Transaction where reader.id_reader = :id",Transaction.class).setParameter("id",reader.getId_user());
        em.getTransaction().commit();
        return query.getResultList();
    }

}
