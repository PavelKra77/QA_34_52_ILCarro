package ui_tests;

import dto.Car;
import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import static utils.PropertiesReader.getProperty;

public class LetTheCarWorkTests extends AppManager {
    LoginPage loginPage;
    LetTheCarWorkPage letTheCarWorkPage;

    @BeforeMethod
    public void goToLoginPage() {
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
        letTheCarWorkPage = new LetTheCarWorkPage(getDriver());
    }


    @Test
    public void letTheCarWorkPositiveTest() {
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        loginPage.clickBtnOk();
        new HomePage(getDriver()).clickBtnLetTheCarWork();

        Car car = Car.builder()
                .location("Rome")
                .manufacture("Italy")
                .model("Alfa romeo")
                .year("2020")
                .seats("2")
                .carClass("Cabriolet")
                .carRegistrationNumber("444678537")
                .price("100")
                .about("Red")
                .build();
        letTheCarWorkPage.typeLetTheCarWorkForm(car);
        letTheCarWorkPage.clickFuelField();
        letTheCarWorkPage.clickValueDiesel();
        letTheCarWorkPage.clickBtnSubmitWithJS();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessageCarAddingFailedPresent("Car adding failed"));
    }

}
