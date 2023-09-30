package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactModificationTest extends TestBase {
    @Test
    void canModifyContact () {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData("", "", ""));
        }
        app.contacts().modifyContact(new ContactData().withFirstName("modified name2"));
    }
}
