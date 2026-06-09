package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends BasePage {

    private static final By CHECKOUT_INDICATOR = By.xpath("//*[contains(@class,'checkout')]");

    public CheckoutPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isLoaded() {
        return isDisplayed(CHECKOUT_INDICATOR);
    }
}