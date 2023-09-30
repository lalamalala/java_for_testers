package manager;

import model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ContactHelper {
    private ApplicationManager manager;

    public ContactHelper(ApplicationManager manager) {
        this.manager = manager;
    }

    public boolean isContactPresent() {
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void openHomePage() {
        if (!manager.isElementPresent(By.name("searchstring"))) {
            manager.driver.findElement(By.linkText("home")).click();
        }
    }
    public void openCreateContactPage() {
        manager.driver.findElement(By.linkText("add new")).click();
    }


    public void removeContact() {
        manager.driver.findElement(By.name("selected[]")).click();
        manager.driver.findElement(By.xpath("//input[@value=\'Delete\']")).click();
        manager.driver.switchTo().alert().accept();
        manager.driver.findElement(By.linkText("home")).click();
    }

    public void createContact(ContactData contact) {
        manager.driver.findElement(By.name("firstname")).click();
        manager.driver.findElement(By.name("firstname")).sendKeys(contact.firstname());
        manager.driver.findElement(By.name("middlename")).click();
        manager.driver.findElement(By.name("middlename")).sendKeys(contact.middlename());
        manager.driver.findElement(By.name("lastname")).click();
        manager.driver.findElement(By.name("lastname")).sendKeys(contact.lastname());
        manager.driver.findElement(By.name("nickname")).click();
        manager.driver.findElement(By.name("nickname")).sendKeys("nickname");
        manager.driver.findElement(By.name("title")).click();
        manager.driver.findElement(By.name("title")).sendKeys("title");
        manager.driver.findElement(By.name("company")).click();
        manager.driver.findElement(By.name("company")).sendKeys("company");
        manager.driver.findElement(By.name("address")).click();
        manager.driver.findElement(By.name("address")).sendKeys("address");
        manager.driver.findElement(By.name("theform")).click();
        manager.driver.findElement(By.name("home")).click();
        manager.driver.findElement(By.name("home")).sendKeys("home");
        manager.driver.findElement(By.name("theform")).click();
        manager.driver.findElement(By.name("mobile")).click();
        manager.driver.findElement(By.name("mobile")).sendKeys("mobile");
        manager.driver.findElement(By.name("work")).click();
        manager.driver.findElement(By.name("work")).sendKeys("work");
        manager.driver.findElement(By.name("fax")).click();
        manager.driver.findElement(By.name("fax")).sendKeys("fax");
        manager.driver.findElement(By.name("email")).click();
        manager.driver.findElement(By.name("email")).sendKeys("email");
        manager.driver.findElement(By.name("theform")).click();
        manager.driver.findElement(By.name("email2")).click();
        manager.driver.findElement(By.name("email2")).sendKeys("email2");
        manager.driver.findElement(By.name("theform")).click();
        manager.driver.findElement(By.name("email3")).click();
        manager.driver.findElement(By.name("email3")).sendKeys("email3");
        manager.driver.findElement(By.name("homepage")).click();
        manager.driver.findElement(By.name("homepage")).sendKeys("homepage");
        manager.driver.findElement(By.name("bday")).click();
        {
            WebElement dropdown = manager.driver.findElement(By.name("bday"));
            dropdown.findElement(By.xpath("//option[. = '1']")).click();
        }
        manager.driver.findElement(By.name("bmonth")).click();
        {
            WebElement dropdown = manager.driver.findElement(By.name("bmonth"));
            dropdown.findElement(By.xpath("//option[. = 'January']")).click();
        }
        manager.driver.findElement(By.name("theform")).click();
        manager.driver.findElement(By.name("byear")).click();
        manager.driver.findElement(By.name("byear")).sendKeys("byear");
        manager.driver.findElement(By.name("aday")).click();
        {
            WebElement dropdown = manager.driver.findElement(By.name("aday"));
            dropdown.findElement(By.xpath("//option[. = '-']")).click();
        }
        manager.driver.findElement(By.name("aday")).click();
        {
            WebElement dropdown = manager.driver.findElement(By.name("aday"));
            dropdown.findElement(By.xpath("//option[. = '2']")).click();
        }
        manager.driver.findElement(By.name("amonth")).click();
        {
            WebElement dropdown = manager.driver.findElement(By.name("amonth"));
            dropdown.findElement(By.xpath("//option[. = 'March']")).click();
        }
        manager.driver.findElement(By.name("ayear")).click();
        manager.driver.findElement(By.name("ayear")).sendKeys("ayear");
        manager.driver.findElement(By.name("address2")).click();
        manager.driver.findElement(By.name("address2")).sendKeys("address2");
        manager.driver.findElement(By.name("phone2")).click();
        manager.driver.findElement(By.name("phone2")).sendKeys("phone2");
        manager.driver.findElement(By.name("notes")).click();
        manager.driver.findElement(By.name("notes")).sendKeys("notes");
        manager.driver.findElement(By.xpath("//input[@name='submit']")).click();
        manager.driver.findElement(By.linkText("home page")).click();
    }
}
