package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
//        for (var firstname : List.of("","first name")) {
//            for (var middlename : List.of("","middle name")) {
//                for (var lastname : List.of("", "last name")) {
//                    result.add(new ContactData()
//                            .withFirstName(firstname)
//                            .withMiddleName(middlename)
//                            .withLastName(lastname));
//                }
//            }
//        }
        ObjectMapper mapper = new ObjectMapper(); // create once, reus
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>(){});
        result.addAll(value);
        return result;
    }
    @Test
    public void canCreateContacts() {
        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                        .withLastName(CommonFunctions.randomString(10))
                                .withPhoto(randomFile("src/test/resources/images"));
        app.contacts().createContact(contact);


    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };

        newContacts.sort(compareById);


        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()).withMiddleName("").withPhoto("src/test/resources/images/avatar.png"));
        expectedList.sort(compareById);

        Assertions.assertEquals(newContacts, expectedList);

    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("", "first name'", "sfsfds","sdfdsf", "src/test/resources/images/avatar.png")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Assertions.assertEquals(newContacts, oldContacts);
    }
}
