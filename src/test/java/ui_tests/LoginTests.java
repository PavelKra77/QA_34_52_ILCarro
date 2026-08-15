package ui_tests;

import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import static utils.UserFactory.*;
import static utils.PropertiesReader.*;

import java.util.Random;

public class LoginTests extends AppManager {
    LoginPage loginPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToLoginPage() {
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }


    @Test
    public void loginPositiveTest() {
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties","email"))
                .password(getProperty("base.properties","password"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(loginPage.isPopUpSuccessLoginDisplayed());

    }

    @Test
    public void loginPositiveWithFakerTest() {
        UserLombok user = positiveUser();
        System.out.println(user);


    }
    @Test
    public void loginNegativeEmptyAllFieldsTest(){
        UserLombok emptyUser = UserLombok.builder()
                .username("")
                .password("")
                .build();
        loginPage.typeLoginForm(emptyUser);
        loginPage.clickBtnYalla();
        Assert.assertTrue(new LoginPage(getDriver())
                .validateTextInLoginEmail("Email is required"));
        Assert.assertTrue(new LoginPage(getDriver())
                 .validateTextInLoginPassword("Password is required"));
    }
    @Test
    public void loginNegativeEmailFieldTest(){
        UserLombok emptyUser = UserLombok.builder()
                .username("123")
                .password("Absent45!")
                .build();
        loginPage.typeLoginForm(emptyUser);
        loginPage.clickBtnYalla();
        Assert.assertTrue(new LoginPage(getDriver())
                .validateTextInLoginEmail2("It'snot look like email"));

    }

    @Test
    public void loginNegativePasswordFieldTest(){
        UserLombok emptyUser = UserLombok.builder()
                .username(getProperty("base.properties",
                        "email"))
                .password("AAbsent45!")
                .build();
        loginPage.typeLoginForm(emptyUser);
        loginPage.clickBtnYalla();
        Assert.assertTrue(loginPage.isPopUpLoginFailedDisplayed());

    }

    @Test
    public void loginNegativeWrongEmailTest() {
        UserLombok user = UserLombok.builder()
                .username("av.kravets86@gmail.com")
                .password(getProperty("base.properties","password"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(loginPage.isPopUpLoginFailedDisplayed());
    }

    @Test
    public void loginNegativeEmptyAllFieldsWOClickInFieldsTest(){
        loginPage.clickBtnYalla();
        Assert.assertFalse(loginPage.isBtnYallaEnabled());
    }

    @Test
    public void loginNegativeEmptyAllFieldsWithClickInFieldsTest(){
        UserLombok emptyUser = UserLombok.builder()
                .username("")
                .password("")
                .build();
        loginPage.typeLoginForm(emptyUser);
        loginPage.clickBtnYalla();
        softAssert.assertFalse(loginPage.isBtnYallaEnabled(),
                "validate isBtnYallaEnabled");
        System.out.println("test working");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Email is required"),
                "validate message: Email is required");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Password is required"),
                "validate message Password is required");
        softAssert.assertAll();
    }

    @Test
    public void loginNegativeEmptyEmailTest(){
        UserLombok emptyUser = UserLombok.builder()
                .username("")
                .password(getProperty("base.properties", "password"))
                .build();
        loginPage.typeLoginForm(emptyUser);
        loginPage.clickBtnYalla();
        Assert.assertTrue(new LoginPage(getDriver())
                .validateTextInLoginEmail("Email is required"));

    }

    @Test
    public void loginNegativeEmptyPasswordTest(){
        UserLombok emptyUser = UserLombok.builder()
                .username(getProperty("base.properties", "email"))
                .password("")
                .build();
        loginPage.typeLoginForm(emptyUser);
        loginPage.clickBtnYalla();
        Assert.assertTrue(new LoginPage(getDriver())
                .validateTextInLoginPassword("Password is required"));

    }

}

