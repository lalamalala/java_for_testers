package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.DeveloperMailUser;
import ru.stqa.mantis.model.IssueData;
import ru.stqa.mantis.model.UserData;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase{


    @Test
    void canRegisterUser() throws InterruptedException {
        var username = String.format(CommonFunctions.randomString(8));
        var email = String.format("%s@localhost", username);
        var password = "password";

        app.jamesApi().addUser(email, password);
        app.rest().createUser(new UserData(username,password,email));


        var messages = app.mail().receive(email, password, Duration.ofSeconds(10));
        var text = messages.get(0).content();
        var url = CommonFunctions.extractUrl(text);

        app.session().finishedRegistration(url, username, password);

        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
        
    }

}
