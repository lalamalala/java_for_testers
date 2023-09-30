package manager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ApplicationManager {
    protected  WebDriver driver;
    private LoginHelper session;
    private GroupHelper groups;
    private ContactHelper contacts;

    public LoginHelper session () { // returns a link to the helper
        if (session == null) {
            session = new LoginHelper(this);
        }
        return session;
    }


    public GroupHelper groups () { // returns a link to the helper
        if (groups == null) {
            groups = new GroupHelper(this);
        }
        return groups;
    }

    public ContactHelper contacts () { // returns a link to the helper
        if (contacts == null) {
            contacts = new ContactHelper(this);
        }
        return contacts;
    }

    public void init(String browser) {
        if (driver == null) {
            if ("chrome".equals(browser)) {
                driver = new ChromeDriver();
            } else if ("firefox".equals(browser)) {
                    driver = new FirefoxDriver();

            } else {
                throw  new IllegalArgumentException(String.format("Unknown browser %s", browser));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook/");
            driver.manage().window().setSize(new Dimension(516, 254));
            session().login("admin", "secret");
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

}
