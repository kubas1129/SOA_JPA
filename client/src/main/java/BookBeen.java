import model.IBook;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@ManagedBean
@Named("BookBean")
public class BookBeen implements Serializable {

    @EJB(lookup = "java:global/impl-1.0-SNAPSHOT/BooksManager")
    private IBooksManager booksManager;

    //Modyfing variables
    private int modifyId;
    private int modifyISBN;
    private String modifyTitle;
    private int modifyYear;
    private String firstName;
    private String lastName;

    private Object selectedBook = new Object();

    public String AddBook(){
        booksManager.AddBook(modifyISBN,modifyTitle,modifyYear,firstName,lastName);

        return "/index.xhtml?faces-redirect=true";
    }

    public String GoToModify(Object book){
        selectedBook=book;
        return "/modifyBook.xhtml?faces-redirect=true";
    }

    public String ModifyBook(){
        booksManager.ModifyBook(selectedBook);
        return"/index.xhtml?faces-redirect=true";
    }

    public void DeleteBook(Object book){
        booksManager.DeleteBook(book);
    }


    public IBooksManager getBooksManager() {
        return booksManager;
    }


    public int getModifyId() {
        return modifyId;
    }

    public void setModifyId(int modifyId) {
        this.modifyId = modifyId;
    }

    public int getModifyISBN() {
        return modifyISBN;
    }

    public void setModifyISBN(int modifyISBN) {
        this.modifyISBN = modifyISBN;
    }

    public String getModifyTitle() {
        return modifyTitle;
    }

    public void setModifyTitle(String modifyTitle) {
        this.modifyTitle = modifyTitle;
    }

    public int getModifyYear() {
        return modifyYear;
    }

    public void setModifyYear(int modifyYear) {
        this.modifyYear = modifyYear;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Object getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(IBook selectedBook) {
        this.selectedBook = selectedBook;
    }
}
