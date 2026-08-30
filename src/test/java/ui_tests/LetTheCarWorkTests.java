package ui_tests;

import dto.Car;
import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utils.enums.HeaderMenu;
import static utils.CarFactory.*;

import static utils.PropertiesReader.getProperty;

public class LetTheCarWorkTests extends AppManager {
    LoginPage loginPage;
    LetTheCarWorkPage letTheCarWorkPage;

    @BeforeMethod
    public void goToLoginPage() {
        //new HomePage(getDriver()).clickBtnLogin();
        //loginPage = new LoginPage(getDriver());
        letTheCarWorkPage = new LetTheCarWorkPage(getDriver());
        loginPage = new HomePage(getDriver()).clickHeaderButtons(HeaderMenu.LOGIN);
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
        //new HomePage(getDriver()).clickBtnLetTheCarWork();
        letTheCarWorkPage = new HomePage(getDriver()).clickHeaderButtons(HeaderMenu.LET_THE_CAR_WORK);

        Car car = positiveCar();
//        Car car = Car.builder()
//                .location("Rome")
//                .manufacture("Italy")
//                .model("Alfa romeo")
//                .year("2020")
//                .seats(2)
//                .carClass("Cabriolet")
//                .carRegistrationNumber("444678537")
//                .price(100.00)
//                .about("Red")
//                .build();
        letTheCarWorkPage.typeLetTheCarWorkForm(car);
//        letTheCarWorkPage.clickFuelField();
//        letTheCarWorkPage.clickValueDiesel();
        letTheCarWorkPage.downloadImage("Image20260830184215.jpg");
        letTheCarWorkPage.clickBtnSubmitWithJS();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("{\"city\":\"не должно быть пустым\"}"));
//        Assert.assertTrue(new PopUpPage(getDriver())
//                .isTextInPopUpMessageCarAddingFailedPresent("Car adding failed"));
    }

//    Homework Negative Test
//    1. only click btn Submit
//    2. click all fields and btnSubmit
//    3. leave one field empty and other fields type with valid data
//    4. wrong year


}
