package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

public class UserRegistrationTests extends TestBase{
    @Test
    void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
       //create user on post server
        app.jamesCli().addUser(email, "password");

        app.session().signup(username, email);


        Assertions.assertTrue(app.session().isLoggedIn());

    }
}
