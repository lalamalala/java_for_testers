package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase{

    @Test
    public void canCreateContact() {
        app.openCreateContactPage();
        app.createContact(new ContactData("Lala", "Byby", "GUG"));
    }

    @Test
    public void canCreateGroupWithFirstNameOnly() {
        app.openCreateContactPage();
        var emptyContact = new ContactData();
        var contactWithFirstName = emptyContact.withFirstName("some name");
        app.createContact(contactWithFirstName);

    }

    @Test
    public void canCreateGroupWithMiddleNameOnly() {
        app.openCreateContactPage();
        var emptyContact = new ContactData();
        var contactWithMiddleName = emptyContact.withMiddleName("some name");
        app.createContact(contactWithMiddleName);

    }

    @Test
    public void canCreateGroupWithLastNameOnly() {
        app.openCreateContactPage();
        var emptyContact = new ContactData();
        var contactWithLastName = emptyContact.withLastName("some name");
        app.createContact(contactWithLastName);

    }
}
