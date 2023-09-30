package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {
 
  @Test
  public void canRemoveContact() {
    app.openHomePage();
    if (!app.isContactPresent()) {
      app.createContact(new ContactData("Lala", "Byby", "GUG"));
    }
    app.removeContact();
  }

}
