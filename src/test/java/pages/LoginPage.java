package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    private static final By SIGN_IN_BUTTON = By.xpath("//button[.//span[text()='Sign-in']]");
    private static final By EMAIL_FIELD    = By.id("login-email");
    private static final By PASSWORD_FIELD = By.id("login-password");
    private static final By SUBMIT_BUTTON  = By.xpath("//button[@type='submit']");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public LoginPage openLoginModal() {
        click(SIGN_IN_BUTTON);
        waitForVisible(EMAIL_FIELD);
        return this;
    }

    public LoginPage enterEmail(String email) {
        type(EMAIL_FIELD, email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(PASSWORD_FIELD, password);
        return this;
    }

    public HomePage submitLogin(String baseUrl) {
        click(SUBMIT_BUTTON);
        waitForUrlToBe(baseUrl);
        return new HomePage(driver, wait);
    }

    public HomePage login(String email, String password, String baseUrl) {
        return openLoginModal()
                .enterEmail(email)
                .enterPassword(password)
                .submitLogin(baseUrl);
    }
}