import model.IAuthor;
import model.ITransaction;

import java.util.List;

public interface ILibraryManager {

    public boolean LogIn(String firstName,String lastName);

    public boolean RegisterReader(String firstName, String lastName);

    public void BorrowBook(Object book, String firstName,String lastName);

    public List<ITransaction> getAllTransaction();

    public List<ITransaction> getTransactionForReader(String firstName,String lastName);

    //Filterting

    public List<ITransaction> filterByAuthors(String firstName, String lastName);

    public List<ITransaction> filterByReaders(String firstName, String lastName);

    public List<ITransaction> filterByTitle(String title);

}
