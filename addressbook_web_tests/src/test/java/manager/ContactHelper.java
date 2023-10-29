package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }


    public void openHomePage() {
        if (!manager.isElementPresent(By.name("searchstring"))) {
            click(By.linkText("home"));
        }
    }

    public void openCreateContactPage() {
        click(By.linkText("add new"));
    }

    public void createContact(ContactData contact) {
        openCreateContactPage();
        fillContactForm(contact);
        submitContactCreation();
        openHomePage();
    }

    public void createContact(ContactData contact, GroupData group) {
        openCreateContactPage();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        openHomePage();
    }

    public void deleteContactFromGroup(GroupData group) {
        click(By.linkText("home"));
        selectGroupOnHomePage(group);
        click(By.name("selected[]"));
        submitRemoveFromGroup();
        openHomePage();
    }

    public void addGroup(GroupData group) {
        click(By.linkText("home"));
        selectGroupForAddOnHomePage(group);
        click(By.name("selected[]"));
        submitAddFromGroup();
        click(By.linkText("home"));
    }

    private void selectGroupOnHomePage(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    private void selectAllGroupOnHomePage() {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue("");
    }

    private void selectGroupForAddOnHomePage(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    public void removeContact(ContactData contact) {
        openHomePage();
        selectAllGroupOnHomePage();
        selectContact(contact);
        removeSelectedContacts();
        manager.driver.switchTo().alert().accept();
        openHomePage();
    }

    public void removeAllContacts() {
        click(By.linkText("home"));
        selectAllGroupOnHomePage();
        selectAllContacts();
        removeSelectedContacts();
        manager.driver.switchTo().alert().accept();
        openHomePage();
    }

    public void modifyContact (ContactData contact,ContactData modifiedContact) {
        openHomePage();
        selectContact(contact);
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        openHomePage();

    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }



    private void selectAllContacts() {
//        var checkboxes =  manager.driver.findElements(By.name("selected[]"));
//        for (var checkbox: checkboxes) {
//            checkbox.click();
//        }
        manager.driver
                .findElements(By.name("selected[]"))
                .forEach(WebElement::click);
    }
    private void submitContactModification() {
        click(By.name("update"));
    }

    private void initContactModification(ContactData contact) {
        click(By.cssSelector(String.format("a[href='edit.php?id=%s']", contact.id())));
      //  click(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[8]/a/img"));
    }

    private void removeSelectedContacts() {
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    public List <ContactData> getList() {
        openHomePage();
        var contacts = new ArrayList<ContactData>();
        var tableRows = manager.driver.findElements(By.xpath("//tr[@name=\'entry\']"));
        for (var tableRow : tableRows) {

            var lastname = tableRow.findElement(By.cssSelector("td:nth-child(2)")).getText();
            var firstname = tableRow.findElement(By.cssSelector("td:nth-child(3)")).getText();
            var checkbox = tableRow.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname));
        }

        return contacts;
    }

    private void submitContactCreation() {
        click(By.xpath("//input[@name='submit']"));
    }

    private void submitRemoveFromGroup() {
        click(By.xpath("//input[@name='remove']"));
    }

    private void submitAddFromGroup() {
        click(By.xpath("//input[@name='add']"));
    }

    private void fillContactForm (ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("middlename"), contact.middlename());
        type(By.name("lastname"), contact.lastname());
        attach(By.name("photo"),contact.photo());

    }


    public String getPhones(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]", contact.id()))).getText();
    }

    public Map<String, String > getPhones() {
        openHomePage();
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }


    public String getAddresses(ContactData contact) {
        openHomePage();
        selectContact(contact);
        initContactModification(contact);
        var address = manager.driver.findElement(By.name("address")).getText();
        var address2 = manager.driver.findElement(By.name("address2")).getText();

        return address+address2;
    }

    public String getAddress(ContactData contact) {
        openHomePage();
        selectContact(contact);
        initContactModification(contact);
        var address = manager.driver.findElement(By.name("address")).getText();
        return address;
    }

    public String getEmailsFromEditForm(ContactData contact) {
        openHomePage();
        selectContact(contact);
        initContactModification(contact);
        var email = manager.driver.findElement(By.name("email")).getAttribute("value");
        var email2 = manager.driver.findElement(By.name("email2")).getAttribute("value");
        var email3 = manager.driver.findElement(By.name("email3")).getAttribute("value");

        return email+email2+email3;

    }

    public String getEmailsFromHomePage(ContactData contact) {
        openHomePage();
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[5]", contact.id()))).getText().replaceAll("\\r\\n|\\r|\\n", "");
    }

    public String getAddressFromHomePage(ContactData contact) {
        openHomePage();
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[4]", contact.id()))).getText().replaceAll("\\r\\n|\\r|\\n", "");
    }


}
