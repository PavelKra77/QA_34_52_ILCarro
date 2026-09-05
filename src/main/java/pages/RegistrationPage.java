package pages;

import dto.UserLombok;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
    @FindBy(xpath = "//label[contains(text(), 'I agree')]") //"div[@class='checkbox-container']"
    WebElement checkboxIAgree;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;
    @FindBy(xpath = "//h2[text()='You are logged in success']")
    WebElement popUpSuccessLoggedIn;

    @FindBy(xpath = "//input[@id='terms-of-use']")
    WebElement checkBoxTermsOfUse;
    @FindBy(xpath = "//label[@for='terms-of-use']")
    WebElement checkBoxLabel;

    public void clickIAgree(){checkboxIAgree.click();}

    public void clickCheckboxTermsOfUse() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", checkBoxTermsOfUse);
    }
//Так делают если стандартный метод element.click() не срабатывает и вызывает ошибку
// например, если чекбокс скрыт за кастомными стилями, другим невидимым слоем или плавающей шапкой сайта

    public void clickCheckBoxWithActions(){
        int x = checkBoxLabel.getSize().getWidth();
        int y = checkBoxLabel.getSize().getHeight();
        System.out.println(x + "X" + y);
        Actions actions = new Actions(driver);
//        actions.moveToElement(checkBoxLabel,x / 10, y / 4).click().perform();
        actions.moveToElement(checkBoxLabel,-x / 10*3, -y / 2).click().perform();
    }

    public void clickBtnYalla() {
        btnYalla.click();
    }
    public boolean isPopUpSuccessLoggedInDisplayed(){
        return isElementDisplayed(popUpSuccessLoggedIn);
    }
    // метод проверяет, отображается ли на экране всплывающее окно (попап) об успешном входе в систему

    public void typeRegistrationForm(UserLombok user) {
        inputName.sendKeys(user.getFirstName());
        inputLastName.sendKeys(user.getLastName());
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());

        }


}
