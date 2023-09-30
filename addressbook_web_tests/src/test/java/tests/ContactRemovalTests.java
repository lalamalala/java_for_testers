package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {
 
  @Test
  public void canRemoveContact() {
    app.contacts().openHomePage();
    if (!app.contacts().isContactPresent()) {
      app.contacts().createContact(new ContactData("Lala", "Byby", "GUG"));
    }
    app.contacts().removeContact();
  }

}
