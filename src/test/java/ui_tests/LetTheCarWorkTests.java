package ui_tests;

import dto.Car;
import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.enums.HeaderMenu;

import java.time.LocalDate;

import static utils.CarFactory.*;

import static utils.CarFactory.positiveCar;
import static utils.PropertiesReader.getProperty;

public class LetTheCarWorkTests extends AppManager {
    LoginPage loginPage;
    LetTheCarWorkPage letTheCarWorkPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToLoginPage() {
        //new HomePage(getDriver()).clickBtnLogin();
        //loginPage = new LoginPage(getDriver());
        loginPage = new HomePage(getDriver()).clickHeaderButtons(HeaderMenu.LOGIN);
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties",
                        "email"))
                .password(getProperty("base.properties",
                        "password"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        loginPage.clickBtnOk();
//        letTheCarWorkPage = loginPage.clickHeaderButtons(HeaderMenu.LET_THE_CAR_WORK);
        letTheCarWorkPage = new HomePage(getDriver())
                .clickHeaderButtons(HeaderMenu.LET_THE_CAR_WORK);
    }


    @Test
    public void letTheCarWorkPositiveTest() {
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
        letTheCarWorkPage.downloadImage("Image20260830184215.jpg");
        letTheCarWorkPage.clickBtnSubmitWithJS();
       Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("{\"city\":\"must not be blank\"}"));
//        Assert.assertTrue(new PopUpPage(getDriver())
//                .isTextInPopUpMessageCarAddingFailedPresent("Car adding failed"));
    }

//    Homework Negative Test
//    1. only click btn Submit
//    2. click all fields and btnSubmit
//    3. leave one field empty and other fields type with valid data
//    4. wrong year

    @Test
    public void letTheCarWorkNegativeClickBtnSubmitTest(){
        letTheCarWorkPage.clickBtnSubmitWithJS();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("\"year\":\"must not be blank\""));
    }

    @Test
    public void letTheCarWorkClickAllFieldsAndBtnSubmitDisabledTest(){
        letTheCarWorkPage.clickAllFields();
        letTheCarWorkPage.clickBtnSubmit();
        softAssert.assertFalse(letTheCarWorkPage.isBtnSubmitEnabled(),
                "validate isBtnSubmitEnabled");
        softAssert.assertTrue(letTheCarWorkPage.isTextInErrorPresent("Wrong address"),
                "validate message: Wrong address");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Make is required"),
                "validate message Make is required");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Model is required"),
                "validate message Model is required");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Fuel is required"),
                "validate message Fuel is required");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Number of seats is required"),
                "validate message Number of seats is required");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Car class is required"),
                "validate message Car class is required");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Car registration number is required"),
                "validate message Car registration number is required");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Price is required"),
                "Price is required");
        softAssert.assertAll();
    }

    @Test
    public void letTheCarWorkClickAllFieldsAndBtnSubmitEnabledTest(){
        letTheCarWorkPage.clickAllFields();

        softAssert.assertTrue(letTheCarWorkPage.isTextInErrorPresent("Wrong address"),
                "validate message: Wrong address");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Make is required"),
                "validate message Make is required");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Model is required"),
                "validate message Model is required");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Fuel is required"),
                "validate message Fuel is required");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Number of seats is required"),
                "validate message Number of seats is required");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Car class is required"),
                "validate message Car class is required");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Car registration number is required"),
                "validate message Car registration number is required");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Price is required"),
                "Price is required");
        letTheCarWorkPage.clickBtnSubmitWithJS();

        softAssert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("\"year\":\"must not be blank\""));
        softAssert.assertTrue(letTheCarWorkPage.isBtnSubmitEnabled(),
                "validate isBtnSubmitEnabled");
        softAssert.assertAll();
    }

    @Test
    public void letTheCarWorkClickOneEmptyFieldAndBtnSubmitEnabledTest(){
        Car car = positiveCar();
        car.setManufacture("");
        letTheCarWorkPage.typeLetTheCarWorkForm(car);
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Make is required"),
                "validate message Make is required");
        letTheCarWorkPage.clickBtnSubmitWithJS();
        softAssert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("\"manufacture\":\"must not be blank\""));
        softAssert.assertTrue(letTheCarWorkPage.isBtnSubmitEnabled(),
                "validate isBtnSubmitEnabled");
        softAssert.assertAll();
    }

    @Test
    public void letTheCarWorkClickOneEmptyFieldAndBtnSubmitDisabledTest(){
        Car car = positiveCar();
        car.setManufacture("");
        letTheCarWorkPage.typeLetTheCarWorkForm(car);
        letTheCarWorkPage.clickBtnSubmit();
        softAssert.assertFalse(letTheCarWorkPage.isBtnSubmitEnabled(),
                "validate isBtnSubmitEnabled");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Make is required"),
                "validate message Make is required");
        softAssert.assertAll();
    }

    @Test
    public void letTheCarWorkClickWrongNextYearFieldAndBtnSubmitDisabledTest(){
        Car car = positiveCar();
        int wrongYear = LocalDate.now().getYear() + 1;
        car.setYear(String.valueOf(wrongYear));

        letTheCarWorkPage.typeLetTheCarWorkForm(car);
        letTheCarWorkPage.clickBtnSubmit();
        softAssert.assertFalse(letTheCarWorkPage.isBtnSubmitEnabled(),
                "validate isBtnSubmitEnabled");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Wrong year"),
                "validate message  Wrong year");
        softAssert.assertAll();
    }
    @Test
    public void letTheCarWorkClickWrongLettersInYearFieldAndBtnSubmitDisabledTest(){
        Car car = positiveCar();
        car.setYear(".abcd");
        letTheCarWorkPage.typeLetTheCarWorkForm(car);
        letTheCarWorkPage.clickBtnSubmit();
        softAssert.assertFalse(letTheCarWorkPage.isBtnSubmitEnabled(),
                "validate isBtnSubmitEnabled");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Year required"),
                "validate message  Year required");
        softAssert.assertAll();
    }
}

