package ui_tests;

import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

import java.time.LocalDate;

public class SearchCarTests extends AppManager {
    HomePage homePage;

    @BeforeMethod
    public void openHomePage() {
        homePage = new HomePage(getDriver());

    }

    @Test
    public void searchCarPositiveTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(8);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYallaWithJS();
        Assert.assertTrue(homePage.validateTextLabelSearchCar("No available cars in"));
    }

    @Test
    public void searchCarNegativeDateTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(-2);
        LocalDate endDate = LocalDate.now().plusDays(8);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYallaWithJS();
        Assert.assertTrue(homePage.isTextInErrorPresent("You can't pick date before today"));
    }

    @Test
    public void searchCarNegativeDateLettersTest() {
        String city = "Haifa";
        homePage.typeSearchFormString(city, "abc", "9/13/2026");
        homePage.clickBtnYallaWithJS();
        Assert.assertTrue(homePage.isTextInErrorPresent("Dates are required"));
    }
}
