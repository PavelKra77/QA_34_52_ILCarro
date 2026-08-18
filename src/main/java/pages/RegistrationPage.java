package pages;

import dto.UserLombok;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements
                (new AjaxElementLocatorFactory(driver, 10), this);

    }
    @FindBy(xpath = "//input[@id='email']")
    WebElement inputEmail;
    @FindBy(css = "input[formcontrolname='password']")
    WebElement inputPassword;
    @FindBy(xpath = "//input[@id='name']")
    WebElement inputName;
    @FindBy(xpath = "//input[@id='lastName']")
    WebElement inputLastName;
    @FindBy(xpath = "//label[contains(text(), 'I agree')]")
    WebElement checkboxIAgree;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;
    @FindBy(xpath = "//h2[text()='You are logged in success']")
    WebElement popUpSuccessLoggedIn;

    public void clickIAgree(){checkboxIAgree.click();}
    public void clickBtnYalla() {
        btnYalla.click();
    }
    public boolean isPopUpSuccessLoggedInDisplayed(){
        return isElementDisplayed(popUpSuccessLoggedIn);
    }

    public void typeRegistrationForm(UserLombok user) {
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
        inputName.sendKeys(user.getFirstName());
        inputLastName.sendKeys(user.getLastName());}


}
