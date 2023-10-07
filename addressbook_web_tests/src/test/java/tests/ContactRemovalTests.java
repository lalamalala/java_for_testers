package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {
 
  @Test
  public void canRemoveContact() {
    app.contacts().openHomePage();
    if (app.contacts().getCount() == 0) {
      app.contacts().createContact(new ContactData("Lala", "Byby", "GUG"));
    }
    int contactCount = app.contacts().getCount();
    app.contacts().removeContact();
    int newContactCount = app.contacts().getCount();
    Assertions.assertEquals(contactCount - 1, newContactCount);
  }

  @Test
  void canRemoveAllContactsAtOnce () {
    if (app.contacts().getCount() == 0) {
      app.contacts().createContact(new ContactData("Lala", "Byby", "GUG"));
    }
    app.contacts().removeAllContacts();
    Assertions.assertEquals(0,app.contacts().getCount());
  }

}
