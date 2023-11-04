package ru.stqa.mantis.manager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Objects;
import java.util.Properties;

public class ApplicationManager {
    private WebDriver driver;
    private String string;
    private Properties properties;
    private SessionHelper sessionHelper;
    private HttpSessionHelper httpSessionHelper;

    public void init(String browser,Properties properties) {
        this.string = browser;
        this.properties = properties;
    }

    public WebDriver driver() {
        if (driver == null) {
            if ("chrome".equals(string)) {
                this.driver = new ChromeDriver();
            } else {
                if (!"firefox".equals(string)) {
                    throw new IllegalArgumentException(String.format("Unknown browser %s", string));
                }
                this.driver = new FirefoxDriver();
            }

            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            this.driver.get(properties.getProperty("web.baseUrl"));
            this.driver.manage().window().setSize(new Dimension(1076, 640));
            //       this.session().login(properties.getProperty("web.username"), properties.getProperty("web.password"));
        }
        return driver;
        }

        public SessionHelper session() {
        if (sessionHelper == null) {
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
        }

    public HttpSessionHelper http() {
        if (httpSessionHelper == null) {
            httpSessionHelper = new HttpSessionHelper(this);
        }
        return httpSessionHelper;
    }

    public String property(String name) {
        return properties.getProperty(name);
    }
}
