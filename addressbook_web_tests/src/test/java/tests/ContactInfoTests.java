package tests;

import common.CommonFunctions;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {
    @Test
    void testPhones() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withPhoto(randomFile("src/test/resources/images"))
                    .withHome(CommonFunctions.randomString(10))
                    .withWork("1234567")
                    .withMobile("123")
                    .withSecondary("12"));
        }

        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
            Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                    .filter(s -> s != null && !"".equals(s))
                    .collect(Collectors.joining("\n"))
        ));

        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected,phones);

    }

    @Test
    void testPostAddressCheckWithDB() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withPhoto(randomFile("src/test/resources/images"))
                    .withHome("123456796")
                    .withWork("1234567")
                    .withMobile("123")
                    .withSecondary("12")
                    .withPostAddress("Address")
                    .withPostAddress2("Address2"));
        }

        var contacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(contacts.size());

        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.address(), contact.address2())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining(""))
        ));

        var expectedAddress = expected.get(contacts.get(index).id());
        var interfaceAddress = app.contacts().getAddresses(contacts.get(index));
        Assertions.assertEquals(expectedAddress,interfaceAddress);

    }

    @Test
    void testPostAddressCheckHomePageAndEditPage() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withPhoto(randomFile("src/test/resources/images"))
                    .withHome("123456796")
                    .withWork("1234567")
                    .withMobile("123")
                    .withSecondary("12")
                    .withPostAddress("Address")
                    .withPostAddress2("Address2"));
        }

        var contacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(contacts.size());



        var homePageAddress = app.contacts().getAddressFromHomePage(contacts.get(index));
        var interfaceAddress = app.contacts().getAddress(contacts.get(index));
        Assertions.assertEquals(interfaceAddress,homePageAddress);

    }
    @Test
    void testEmails() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withPhoto(randomFile("src/test/resources/images"))
                    .withHome("123456796")
                    .withWork("1234567")
                    .withMobile("123")
                    .withSecondary("12")
                    .withPostAddress("Address")
                    .withPostAddress2("Address2")
                    .withEmail("email1")
                    .withEmail2("email2")
                    .withEmail3("email3"));
        }

        var contacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(contacts.size());


        var homePageEmails = app.contacts().getEmailsFromHomePage(contacts.get(index));
        var interfaceEmails = app.contacts().getEmailsFromEditForm(contacts.get(index));
        Assertions.assertEquals(interfaceEmails,homePageEmails);


    }


}
