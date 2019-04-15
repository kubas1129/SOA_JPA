import model.IBook;
import model.ITransaction;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateful
@Remote(ILibraryManager.class)
public class LibraryManager implements ILibraryManager {

    LibraryRepository libraryRepository;

    public LibraryManager(){
        libraryRepository = new LibraryRepository();
    }

    public boolean LogIn(String firstName, String lastName) {
        return libraryRepository.checkReaderExist(firstName,lastName);
    }

    public boolean RegisterReader(String firstName, String lastName) {
        boolean isReaderExist = libraryRepository.checkReaderExist(firstName,lastName);
        if(!isReaderExist){
            Reader reader = new Reader();
            reader.setFirstName(firstName);
            reader.setLastName(lastName);
            libraryRepository.createReader(reader);
            return true;
        }
        else
            return false;
    }

    public void BorrowBook(Object book, String firstName,String lastName){
        Transaction transaction = new Transaction();
        Reader reader = libraryRepository.getReaderByNames(firstName,lastName);
        transaction.setBook((Book)book);
        transaction.setUser(reader);
        Date date = new Date();
        transaction.setBorrowDate(date);
        libraryRepository.createTransaction(transaction);
    }

    public List<ITransaction> getAllTransaction() {
        List<ITransaction> tr = new ArrayList<ITransaction>();
        for(Transaction it : libraryRepository.getAllTransaction()){
            tr.add((ITransaction)it);
        }
        return tr;
    }

    public List<ITransaction> getTransactionForReader(String firstName, String lastName) {
        Reader reader = libraryRepository.getReaderByNames(firstName,lastName);
        List<ITransaction> list = new ArrayList<ITransaction>();
        if(reader!=null){
            for(Transaction it:libraryRepository.getAllTransactionForReader(reader)){
                list.add((ITransaction)it);
            }
        }
        return list;
    }


    public List<ITransaction> filterByAuthors(String firstName, String lastName) {
        List<ITransaction> tr = new ArrayList<ITransaction>();
        for(Transaction it : libraryRepository.getTransactionsByAuthors(firstName,lastName)){
            tr.add((ITransaction)it);
        }
        return tr;
    }

    public List<ITransaction> filterByReaders(String firstName, String lastName) {
        List<ITransaction> tr = new ArrayList<ITransaction>();
        for(Transaction it : libraryRepository.getTransactionsByReaders(firstName,lastName)){
            tr.add((ITransaction)it);
        }
        return tr;
    }

    public List<ITransaction> filterByTitle(String title) {
        List<ITransaction> tr = new ArrayList<ITransaction>();
        for(Transaction it : libraryRepository.getTransactionsByTitle(title)){
            tr.add((ITransaction)it);
        }
        return tr;
    }
}
