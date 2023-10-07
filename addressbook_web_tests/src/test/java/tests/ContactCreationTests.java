package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase{

    @Test
    public void canCreateContact() {
        int contactCount = app.contacts().getCount();
        app.contacts().openCreateContactPage();
        app.contacts().createContact(new ContactData("Lala", "Byby", "GUG"));
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }

    @Test
    public void canCreateMultipleContacts() {
        int n = 5;
        int contactCount = app.contacts().getCount();

        for (int i = 0; i < n ; i++) {
            app.contacts().openCreateContactPage();
            app.contacts().createContact(new ContactData(randomString(i), "Byby", "GUG"));

        }

        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + n, newContactCount);

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
