package ui_tests;

import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import static utils.UserFactory.*;

import java.util.Random;

public class LoginTests extends AppManager {
    LoginPage loginPage;
    @BeforeMethod
    public void goToLoginPage() {
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }


    @Test
    public void loginPositiveTest() {
        UserLombok user = UserLombok.builder()
                .username("pav.kravets86@gmail.com")
                .password("Absent45!")
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();

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
}
