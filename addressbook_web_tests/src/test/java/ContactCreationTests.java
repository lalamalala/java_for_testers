import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase{

    @Test
    public void canCreateContact() {
        openCreateContactPage();
        createContact(new ContactData("Lala", "Byby", "GUG"));
    }

    @Test
    public void canCreateGroupWithFirstNameOnly() {
        openCreateContactPage();
        var emptyContact = new ContactData();
        var contactWithFirstName = emptyContact.withFirstName("some name");
        createContact(contactWithFirstName);

    }

    @Test
    public void canCreateGroupWithMiddleNameOnly() {
        openCreateContactPage();
        var emptyContact = new ContactData();
        var contactWithMiddleName = emptyContact.withMiddleName("some name");
        createContact(contactWithMiddleName);

    }

    @Test
    public void canCreateGroupWithLastNameOnly() {
        openCreateContactPage();
        var emptyContact = new ContactData();
        var contactWithLastName = emptyContact.withLastName("some name");
        createContact(contactWithLastName);

    }
}
