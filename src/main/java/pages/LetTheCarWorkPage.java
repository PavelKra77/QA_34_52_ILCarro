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


    public void typeLetTheCarWorkForm(Car car) {
        fieldLocation.sendKeys(car.getLocation());
        fieldManufacture.sendKeys(car.getManufacture());
        fieldModel.sendKeys(car.getModel());
        fieldYear.sendKeys(car.getYear());
//      fieldFuel.sendKeys(car.getFuel());
        chooseFuel(car.getFuel());
        fieldSeats.sendKeys(String.valueOf(car.getSeats()));
//      fieldSeats.sendKeys(car.getSeats().toString());
        fieldCarClass.sendKeys(car.getCarClass());
        fieldCarRegistrationNumber.sendKeys(car.getCarRegistrationNumber());
        fieldPrice.sendKeys(String.valueOf(car.getPrice()));
        fieldAbout.sendKeys(car.getAbout());


    }

    private void chooseFuel(Fuel fuel){
        fieldFuel.click();
        driver.findElement(By.xpath(fuel.getLocator())).click();

    }

    public void downloadImage(String fileName){
        inputImage.sendKeys(new File("src/test/resources/"
                +fileName).getAbsolutePath());

    }

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
        btnSubmit.click();
    }


}
