package manager;

import model.ContactData;
import org.openqa.selenium.By;

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

    public void removeContact() {
        openHomePage();
        selectContact();
        removeSelectedContacts();
        manager.driver.switchTo().alert().accept();
        openHomePage();
    }

    public void removeAllContacts() {
        openHomePage();
        selectAllContacts();
        removeSelectedContacts();
        manager.driver.switchTo().alert().accept();
        openHomePage();
    }

    public void modifyContact (ContactData modifiedContact) {
        openHomePage();
        selectContact();
        initContactModification();
        fillContactForm(modifiedContact);
        submitContactModification();
        openHomePage();

    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }



    private void selectAllContacts() {
        var checkboxes =  manager.driver.findElements(By.name("selected[]"));
        for (var checkbox: checkboxes) {
            checkbox.click();
        }
    }
    private void submitContactModification() {
        click(By.name("update"));
    }

    private void initContactModification() {
        click(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[8]/a/img"));
    }

    private void removeSelectedContacts() {
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    private void selectContact() {
        click(By.name("selected[]"));
    }



    private void submitContactCreation() {
        click(By.xpath("//input[@name='submit']"));
    }

    private void fillContactForm (ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("middlename"), contact.middlename());
        type(By.name("lastname"), contact.lastname());


//        manager.driver.findElement(By.name("nickname")).click();
//        manager.driver.findElement(By.name("nickname")).sendKeys("nickname");
//        manager.driver.findElement(By.name("title")).click();
//        manager.driver.findElement(By.name("title")).sendKeys("title");
//        manager.driver.findElement(By.name("company")).click();
//        manager.driver.findElement(By.name("company")).sendKeys("company");
//        manager.driver.findElement(By.name("address")).click();
//        manager.driver.findElement(By.name("address")).sendKeys("address");
//        manager.driver.findElement(By.name("theform")).click();
//        manager.driver.findElement(By.name("home")).click();
//        manager.driver.findElement(By.name("home")).sendKeys("home");
//        manager.driver.findElement(By.name("theform")).click();
//        manager.driver.findElement(By.name("mobile")).click();
//        manager.driver.findElement(By.name("mobile")).sendKeys("mobile");
//        manager.driver.findElement(By.name("work")).click();
//        manager.driver.findElement(By.name("work")).sendKeys("work");
//        manager.driver.findElement(By.name("fax")).click();
//        manager.driver.findElement(By.name("fax")).sendKeys("fax");
//        manager.driver.findElement(By.name("email")).click();
//        manager.driver.findElement(By.name("email")).sendKeys("email");
//        manager.driver.findElement(By.name("theform")).click();
//        manager.driver.findElement(By.name("email2")).click();
//        manager.driver.findElement(By.name("email2")).sendKeys("email2");
//        manager.driver.findElement(By.name("theform")).click();
//        manager.driver.findElement(By.name("email3")).click();
//        manager.driver.findElement(By.name("email3")).sendKeys("email3");
//        manager.driver.findElement(By.name("homepage")).click();
//        manager.driver.findElement(By.name("homepage")).sendKeys("homepage");
    }


}
