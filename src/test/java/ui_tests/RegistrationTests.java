package ui_tests;

import dto.UserLombok;
import manager.AppManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.PopUpPage;
import pages.RegistrationPage;

import static utils.PropertiesReader.*;
import static utils.UserFactory.positiveUser;


public class RegistrationTests extends AppManager {
    RegistrationPage registrationPage;


    @BeforeMethod
    public void goToRegistrationSignupPage() {
        logger.info("Start registration test");
        new HomePage(getDriver()).clickBtnSignup();
        registrationPage = new RegistrationPage(getDriver());
    }

    @Test
    public void registrationPositiveWithJSTest() {
        UserLombok user = positiveUser();
        System.out.println(user);
        registrationPage.typeRegistrationForm(user);
//        registrationPage.clickIAgree();
        registrationPage.clickCheckboxTermsOfUse();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("You are logged in success"));
//        Assert.assertTrue(registrationPage.isPopUpSuccessLoggedInDisplayed());

    }



    @Test
    public void registrationPositiveWithActionsTest() {
        UserLombok user = positiveUser();
        System.out.println(user);
        registrationPage.typeRegistrationForm(user);
//        registrationPage.clickIAgree();
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("You are logged in success"));
//        Assert.assertTrue(registrationPage.isPopUpSuccessLoggedInDisplayed());


}
}


//        UserLombok user = UserLombok.builder()
//                .firstName("David")
//                .lastName("Brown")
//                .username(getProperty("base.properties", "email"))
//                .password(getProperty("base.properties", "password"))
//                .build();

