import model.IBook;

import java.util.List;

public interface IBooksManager {

    public void AddBook(int isbn, String title, int year,String firstName, String lastName);
    public void ModifyBook(Object book);
    public void DeleteBook(Object book);
    public List<IBook> GetAllBooks();

}
