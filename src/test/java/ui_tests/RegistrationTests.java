package ui_tests;

import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;

import static utils.PropertiesReader.*;
import static utils.UserFactory.positiveUser;


public class RegistrationTests extends AppManager {
    RegistrationPage registrationPage;

    @BeforeMethod
    public void goToRegistrationSignupPage() {
        new HomePage(getDriver()).clickBtnSignup();
        registrationPage = new RegistrationPage(getDriver());
    }

    @Test
    public void registrationPositiveTest() {
        UserLombok user = positiveUser();
        System.out.println(user);
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickIAgree();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(registrationPage.isPopUpSuccessLoggedInDisplayed());

    }
}


//        UserLombok user = UserLombok.builder()
//                .firstName("David")
//                .lastName("Brown")
//                .username(getProperty("base.properties", "email"))
//                .password(getProperty("base.properties", "password"))
//                .build();

