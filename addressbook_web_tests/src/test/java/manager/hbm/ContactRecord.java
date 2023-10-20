package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table (name = "addressbook")
public class ContactRecord {

    @Id
    public  int id;
    public String firstname;
    public String middlename;
    public String lastname;


    public ContactRecord() {}
    public ContactRecord(int id, String firstname, String middlename, String lastname) {

        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
    }

}
