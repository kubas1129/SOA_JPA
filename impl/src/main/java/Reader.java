import model.IReader;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "reader")
public class Reader implements Serializable, IReader {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "CUST_GEN")
    private int id_reader;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "reader")
    private List<Transaction> transactions;


    public Reader(){

    }

    public Reader(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public int getId_user() {
        return id_reader;
    }

    public void setId_user(int id_reader) {
        this.id_reader = id_reader;
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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
