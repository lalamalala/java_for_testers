package tests;

import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {
 
  @Test
  public void canRemoveContact() {
    app.contacts().openHomePage();
    if (app.hbm().getContactCount() == 0) {
      app.hbm().createContact(new ContactData()
              .withFirstName(CommonFunctions.randomString(10))
              .withLastName(CommonFunctions.randomString(10))
              .withPhoto(randomFile("src/test/resources/images")));
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
  public void canDeleteContactFromGroup() {
    if (app.hbm().getGroupCount() == 0) {
      app.hbm().createGroup(new GroupData("", "1", "2", "3"));
    }
    if (app.hbm().getContactCount() == 0) {
      app.hbm().createContact(new ContactData()
              .withFirstName(CommonFunctions.randomString(10))
              .withLastName(CommonFunctions.randomString(10))
              .withPhoto(randomFile("src/test/resources/images")));
    }


    var group = app.hbm().getGroupList().get(0);
    app.contacts().addGroup(group);

    var oldRelated = app.hbm().getContactsInGroup(group);
    app.contacts().deleteContactFromGroup(group);
    var newRelated = app.hbm().getContactsInGroup(group);
    Assertions.assertEquals(oldRelated.size() - 1, newRelated.size());
  }


  @Test
  void canRemoveAllContactsAtOnce () {
    if (app.hbm().getContactCount() == 0) {
      app.hbm().createContact(new ContactData()
              .withFirstName(CommonFunctions.randomString(10))
              .withLastName(CommonFunctions.randomString(10))
              .withPhoto(randomFile("src/test/resources/images")));
    }
    app.contacts().removeAllContacts();
    Assertions.assertEquals(0,app.hbm().getContactCount());
  }
}
