package pages;

import dto.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utils.enums.Fuel;

import java.io.File;

public class LetTheCarWorkPage extends BasePage {
    public LetTheCarWorkPage(WebDriver driver) {
        PageFactory.initElements
                (new AjaxElementLocatorFactory(driver, 10), this);

    }

    @FindBy(xpath = "//input[@id='pickUpPlace']")
    WebElement fieldLocation;
    @FindBy(xpath = "//input[@id='make']")
    WebElement fieldManufacture;
    @FindBy(xpath = "//*[@formcontrolname = 'model']")
    WebElement fieldModel;
    @FindBy(xpath = "//*[@formcontrolname = 'year']")
    WebElement fieldYear;
    @FindBy(xpath = "//*[@id='fuel']")
    WebElement fieldFuel;
    @FindBy(xpath = "//*[@ng-reflect-value='Diesel']")
    WebElement valueDiesel;
    @FindBy(xpath = "//*[@id='seats']")
    WebElement fieldSeats;
    @FindBy(xpath = "//*[@id='class']")
    WebElement fieldCarClass;
    @FindBy(xpath = "//*[@id='serialNumber']")
    WebElement fieldCarRegistrationNumber;
    @FindBy(xpath = "//*[@id='price']")
    WebElement fieldPrice;
    @FindBy(xpath = "//*[@id='about']")
    WebElement fieldAbout;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSubmit;
    @FindBy(id="photos")
    WebElement inputImage;

    private void chooseFuel(Fuel fuel){             // класс enum
        fieldFuel.click();
        driver.findElement(By.xpath(fuel.getLocator())).click();
    }

    public void typeLetTheCarWorkForm(Car car) {
        fieldLocation.sendKeys(car.getLocation());
        fieldManufacture.sendKeys(car.getManufacture());
        fieldModel.sendKeys(car.getModel());
        fieldYear.sendKeys(car.getYear());
        chooseFuel(car.getFuel());
//      fieldFuel.sendKeys(car.getFuel());
        fieldSeats.sendKeys(String.valueOf(car.getSeats()));
//        fieldSeats.sendKeys(Integer.toString(car.getSeats()));
//        fieldSeats.sendKeys(car.getSeats().toString());
//        fieldSeats.sendKeys(car.getSeats()+"");
        fieldCarClass.sendKeys(car.getCarClass());
        fieldCarRegistrationNumber.sendKeys(car.getCarRegistrationNumber());
        fieldPrice.sendKeys(String.valueOf(car.getPrice()));
        fieldAbout.sendKeys(car.getAbout());
    }

    public void clickAllFields(){
        fieldLocation.click();
        fieldManufacture.click();
        fieldModel.click();
        fieldYear.click();
        fieldFuel.click();
        fieldSeats.click();
        fieldCarClass.click();
        fieldCarRegistrationNumber.click();
        fieldPrice.click();
        fieldAbout.click();
    }

    public void downloadImage(String fileName){
        inputImage.sendKeys(new File("src/test/resources/"
                +fileName).getAbsolutePath());
    }
    // .getAbsolutePath() — превращает относительный путь в абсолютный на жестком диске компьютера.

    public void clickFuelField() {
        fieldFuel.click();
    }

    public void clickValueDiesel() {
        valueDiesel.click();
    }

    public void clickBtnSubmitWithJS() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript
                ("document.querySelector(\"button[type='submit']\").removeAttribute('disabled')");
        btnSubmit.click();}

    public void clickBtnSubmit() {
            btnSubmit.click();
    }

    public boolean isBtnSubmitEnabled() {
        return btnSubmit.isEnabled();
    }

}
