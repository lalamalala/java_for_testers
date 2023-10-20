package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {
 
  @Test
  public void canRemoveContact() {
    app.contacts().openHomePage();
    if (app.hbm().getContactCount() == 0) {
      app.hbm().createContact(new ContactData("", "Lala", "Byby", "GUG","src/test/resources/images/avatar.png"));
    }

    var oldContacts = app.hbm().getContactList();
    var rnd = new Random();
    var index = rnd.nextInt(oldContacts.size());
    app.contacts().removeContact(oldContacts.get(index));
    var newContacts = app.hbm().getContactList();
    var expectedList = new ArrayList<>(oldContacts);
    expectedList.remove(index);

    Assertions.assertEquals(newContacts, expectedList);
  }

  @Test
  void canRemoveAllContactsAtOnce () {
    if (app.hbm().getGroupCount() == 0) {
      app.hbm().createContact(new ContactData("", "Lala", "Byby", "GUG","src/test/resources/images/avatar.png"));
    }
    app.contacts().removeAllContacts();
    Assertions.assertEquals(0,app.hbm().getContactCount());
  }

}
