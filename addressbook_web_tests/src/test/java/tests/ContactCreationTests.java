package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase{

    @Test
    public void canCreateContact() {
        app.contacts().openCreateContactPage();
        app.contacts().createContact(new ContactData("Lala", "Byby", "GUG"));
    }

    @Test
    public void canCreateGroupWithFirstNameOnly() {
        app.contacts().openCreateContactPage();
        var emptyContact = new ContactData();
        var contactWithFirstName = emptyContact.withFirstName("some name");
        app.contacts().createContact(contactWithFirstName);

    }

    @Test
    public void canCreateGroupWithMiddleNameOnly() {
        app.contacts().openCreateContactPage();
        app.contacts().createContact(new ContactData().withMiddleName("some name"));

    }

    @Test
    public void canCreateGroupWithLastNameOnly() {
        app.contacts().openCreateContactPage();
        app.contacts().createContact(new ContactData().withLastName("some name"));

    }
}
