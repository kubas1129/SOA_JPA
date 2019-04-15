import model.IAuthor;
import model.ITransaction;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SessionScoped
@ManagedBean
@Named("LibraryBean")
public class LibraryBean implements Serializable {

    @EJB(lookup = "java:global/impl-1.0-SNAPSHOT/LibraryManager")
    ILibraryManager libraryManager;

    private String firstName;
    private String lastName;
    private boolean isLogged;

    private Object book = new Object();

    //Filters
    private String selectedFilterOption="books";
    private String filterReaderFirstName;
    private String filterReaderLastName;
    private String filterAuthorFirstName;
    private String filterAuthorLastName;
    private Date filterDateStart;
    private Date filterDateEnd;
    private String filterTitle;


    public void LogIn(){
        isLogged = libraryManager.LogIn(firstName,lastName);
    }

    public void LogOut(){
        isLogged=false;
        firstName="";
        lastName="";
    }

    public void Register(){
        isLogged = libraryManager.RegisterReader(firstName,lastName);
    }

    public void Filter(){
        System.out.println("FILTER RUN");
    }

    public String BorrowBook(Object book){
        this.book = book;
        libraryManager.BorrowBook(this.book, firstName,lastName);
        return "/index.xhtml?faces-redirect=true";
    }

    public void ReturnBook(Object transaction){

    }

    public List<ITransaction> FilteredData(){
        if(selectedFilterOption.equals("authors")){
            return libraryManager.filterByAuthors(filterAuthorFirstName,filterAuthorLastName);
        }
        else if(selectedFilterOption.equals("readers")){
            return libraryManager.filterByReaders(filterReaderFirstName,filterReaderLastName);
        }
        else{
            return libraryManager.filterByTitle(filterTitle);
        }
    }




    /* GET&SET */

    public ILibraryManager getLibraryManager() {
        return libraryManager;
    }

    public void setLibraryManager(ILibraryManager libraryManager) {
        this.libraryManager = libraryManager;
    }

    public Object getBook() {
        return book;
    }

    public void setBook(Object book) {
        this.book = book;
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

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }


    public String getSelectedFilterOption() {
        return selectedFilterOption;
    }

    public void setSelectedFilterOption(String selectedFilterOption) {
        this.selectedFilterOption = selectedFilterOption;
    }

    public String getFilterReaderFirstName() {
        return filterReaderFirstName;
    }

    public void setFilterReaderFirstName(String filterReaderFirstName) {
        this.filterReaderFirstName = filterReaderFirstName;
    }

    public String getFilterReaderLastName() {
        return filterReaderLastName;
    }

    public void setFilterReaderLastName(String filterReaderLastName) {
        this.filterReaderLastName = filterReaderLastName;
    }

    public String getFilterAuthorFirstName() {
        return filterAuthorFirstName;
    }

    public void setFilterAuthorFirstName(String filterAuthorFirstName) {
        this.filterAuthorFirstName = filterAuthorFirstName;
    }

    public String getFilterAuthorLastName() {
        return filterAuthorLastName;
    }

    public void setFilterAuthorLastName(String filterAuthorLastName) {
        this.filterAuthorLastName = filterAuthorLastName;
    }

    public Date getFilterDateStart() {
        return filterDateStart;
    }

    public void setFilterDateStart(Date filterDateStart) {
        this.filterDateStart = filterDateStart;
    }

    public Date getFilterDateEnd() {
        return filterDateEnd;
    }

    public void setFilterDateEnd(Date filterDateEnd) {
        this.filterDateEnd = filterDateEnd;
    }

    public String getFilterTitle() {
        return filterTitle;
    }

    public void setFilterTitle(String filterTitle) {
        this.filterTitle = filterTitle;
    }
}
