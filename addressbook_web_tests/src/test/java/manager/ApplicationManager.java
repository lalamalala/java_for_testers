package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApplicationManager {
    protected static WebDriver driver;

    public void init() {
        if (driver == null) {
            driver = new ChromeDriver();
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook/");
            driver.manage().window().setSize(new Dimension(516, 254));
            driver.findElement(By.name("user")).sendKeys("admin");
            driver.findElement(By.name("pass")).sendKeys("secret");
            driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
        }
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public void createGroup(GroupData group) {
        driver.findElement(By.name("new")).click();
        driver.findElement(By.name("group_name")).click();
        driver.findElement(By.name("group_name")).sendKeys(group.name());
        driver.findElement(By.name("group_header")).click();
        driver.findElement(By.name("group_header")).sendKeys(group.header());
        driver.findElement(By.name("group_footer")).click();
        driver.findElement(By.name("group_footer")).sendKeys(group.footer());
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.linkText("group page")).click();
    }

    public void openGroupsPage() {
        if (!isElementPresent(By.name("new"))) {
            driver.findElement(By.linkText("groups")).click();
        }
    }

    public void openCreateContactPage() {
        driver.findElement(By.linkText("add new")).click();
    }

    public void createContact(ContactData contact) {
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).sendKeys(contact.firstname());
        driver.findElement(By.name("middlename")).click();
        driver.findElement(By.name("middlename")).sendKeys(contact.middlename());
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).sendKeys(contact.lastname());
        driver.findElement(By.name("nickname")).click();
        driver.findElement(By.name("nickname")).sendKeys("nickname");
        driver.findElement(By.name("title")).click();
        driver.findElement(By.name("title")).sendKeys("title");
        driver.findElement(By.name("company")).click();
        driver.findElement(By.name("company")).sendKeys("company");
        driver.findElement(By.name("address")).click();
        driver.findElement(By.name("address")).sendKeys("address");
        driver.findElement(By.name("theform")).click();
        driver.findElement(By.name("home")).click();
        driver.findElement(By.name("home")).sendKeys("home");
        driver.findElement(By.name("theform")).click();
        driver.findElement(By.name("mobile")).click();
        driver.findElement(By.name("mobile")).sendKeys("mobile");
        driver.findElement(By.name("work")).click();
        driver.findElement(By.name("work")).sendKeys("work");
        driver.findElement(By.name("fax")).click();
        driver.findElement(By.name("fax")).sendKeys("fax");
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).sendKeys("email");
        driver.findElement(By.name("theform")).click();
        driver.findElement(By.name("email2")).click();
        driver.findElement(By.name("email2")).sendKeys("email2");
        driver.findElement(By.name("theform")).click();
        driver.findElement(By.name("email3")).click();
        driver.findElement(By.name("email3")).sendKeys("email3");
        driver.findElement(By.name("homepage")).click();
        driver.findElement(By.name("homepage")).sendKeys("homepage");
        driver.findElement(By.name("bday")).click();
        {
            WebElement dropdown = driver.findElement(By.name("bday"));
            dropdown.findElement(By.xpath("//option[. = '1']")).click();
        }
        driver.findElement(By.name("bmonth")).click();
        {
            WebElement dropdown = driver.findElement(By.name("bmonth"));
            dropdown.findElement(By.xpath("//option[. = 'January']")).click();
        }
        driver.findElement(By.name("theform")).click();
        driver.findElement(By.name("byear")).click();
        driver.findElement(By.name("byear")).sendKeys("byear");
        driver.findElement(By.name("aday")).click();
        {
            WebElement dropdown = driver.findElement(By.name("aday"));
            dropdown.findElement(By.xpath("//option[. = '-']")).click();
        }
        driver.findElement(By.name("aday")).click();
        {
            WebElement dropdown = driver.findElement(By.name("aday"));
            dropdown.findElement(By.xpath("//option[. = '2']")).click();
        }
        driver.findElement(By.name("amonth")).click();
        {
            WebElement dropdown = driver.findElement(By.name("amonth"));
            dropdown.findElement(By.xpath("//option[. = 'March']")).click();
        }
        driver.findElement(By.name("ayear")).click();
        driver.findElement(By.name("ayear")).sendKeys("ayear");
        driver.findElement(By.name("address2")).click();
        driver.findElement(By.name("address2")).sendKeys("address2");
        driver.findElement(By.name("phone2")).click();
        driver.findElement(By.name("phone2")).sendKeys("phone2");
        driver.findElement(By.name("notes")).click();
        driver.findElement(By.name("notes")).sendKeys("notes");
        driver.findElement(By.xpath("//input[@name='submit']")).click();
        driver.findElement(By.linkText("home page")).click();
    }

    public void openHomePage() {
        if (!isElementPresent(By.name("searchstring"))) {
            driver.findElement(By.linkText("home")).click();
        }
    }

    public boolean isGroupPresent() {
        return isElementPresent(By.name("selected[]"));
    }

    public void removeContact() {
      driver.findElement(By.name("selected[]")).click();
      driver.findElement(By.xpath("//input[@value=\'Delete\']")).click();
      driver.switchTo().alert().accept();
      driver.findElement(By.linkText("home")).click();
    }

    public void removeGroup() {
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.name("delete")).click();
        driver.findElement(By.linkText("group page")).click();
    }

    public boolean isContactPresent() {
        return isElementPresent(By.name("selected[]"));
    }
}
