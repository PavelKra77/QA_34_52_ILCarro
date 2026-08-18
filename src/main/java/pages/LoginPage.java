package pages;

import dto.User;
import dto.UserLombok;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        PageFactory.initElements
                (new AjaxElementLocatorFactory(driver, 10), this);

    }

    @FindBy(xpath = "//input[@id='email']")
    WebElement inputEmail;
    @FindBy(css = "input[formcontrolname='password']")
    WebElement inputPassword;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;
    @FindBy(xpath = "//div[text()=' Email is required ']")
    WebElement emailIsRequired;
    @FindBy(xpath = "//div[text()=' Password is required ']")
    WebElement passwordIsRequired;
    @FindBy(xpath = "//*[contains(text(),'look like email')]")
    WebElement emailIsNotEmail;
    @FindBy(xpath = "//h1[text()='Logged in']")
    WebElement popUpSuccessLogin;
    @FindBy(xpath = "//h1[text()='Login failed']")
    WebElement popUpLoginFailed;


    public void typeLoginForm(UserLombok user) {
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());

    }

    public boolean validateTextInLoginEmail(String text) {
        return isTextInElementPresent(emailIsRequired, text);
    }
    public boolean validateTextInLoginPassword(String text) {
        return isTextInElementPresent(passwordIsRequired, text);
    }
    public boolean validateTextInLoginEmail2(String text) {
        return isTextInElementPresent(emailIsNotEmail, text);
    }

    public void clickBtnYalla() {
            btnYalla.click();
        }

    public boolean isPopUpSuccessLoginDisplayed(){
        return isElementDisplayed(popUpSuccessLogin);
    }
    public boolean isPopUpLoginFailedDisplayed() {
        return isElementDisplayed(popUpLoginFailed);
    }

    public boolean isBtnYallaEnabled(){
        return btnYalla.isEnabled();
    }



}
