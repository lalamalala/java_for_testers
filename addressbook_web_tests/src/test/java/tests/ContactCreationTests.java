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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

//        var json = "";
//        try (var reader = new FileReader("contacts.json");
//        var breader = new BufferedReader(reader)
//        ) {
//            var line = breader.readLine();
//            while (line != null) {
//                json =json + line;
//                line = breader.readLine();
//            }
//        }

        var json = Files.readString(Paths.get("contacts.json"));
        ObjectMapper mapper = new ObjectMapper(); // create once, reus
        var value = mapper.readValue(json, new TypeReference<List<ContactData>>(){});
        result.addAll(value);
        return result;
    }
    @Test
    public void canCreateSingleRandomContact() {
        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                        .withLastName(CommonFunctions.randomString(10))
                                .withPhoto(randomFile("src/test/resources/images"));
        app.contacts().createContact(contact);
    }

    @Test
    public void canCreateContactsInGroup() {
        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));

        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "1", "2", "3"));
        }
        var group = app.hbm().getGroupList().get(0);

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContact(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateContacts(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };

        newContacts.sort(compareById);


        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id())
                .withPhoto("src/test/resources/images/avatar.png")
                .withHome("")
                .withMobile("")
                .withWork("")
                .withEmail("")
                .withEmail2("")
                .withEmail3("")
                .withSecondary("")
                .withPostAddress("")
                .withPostAddress2(""));
        expectedList.sort(compareById);

        Assertions.assertEquals(newContacts, expectedList);

    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("", "first name'", "sfsfds","sdfdsf", "src/test/resources/images/avatar.png", "", "", "", "", "", "", "", "", "")));
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
