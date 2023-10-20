package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTest extends TestBase {
    @Test
    void canModifyContact () {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "", "", "","src/test/resources/images/avatar.png"));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());


        var testData = new ContactData().withFirstName("modified name2");
        app.contacts().modifyContact(oldContacts.get(index), testData);

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()));

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        
        var newContacts = app.hbm().getContactList();
        newContacts.sort(compareById);
        expectedList.sort(compareById);

        Assertions.assertEquals(newContacts, expectedList);
    }
}
