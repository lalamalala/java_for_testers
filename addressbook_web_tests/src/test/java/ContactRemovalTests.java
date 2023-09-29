import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {
 
  @Test
  public void canRemoveContact() {
    openHomePage();
    if (!isContactPresent()) {
      createContact(new ContactData("Lala", "Byby", "GUG"));
    }
    removeContact();
  }

}
