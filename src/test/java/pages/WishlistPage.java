package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WishlistPage extends BasePage {

    public WishlistPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isProductDisplayed(String productName) {
        By productHeading = By.xpath("//h4[contains(text(),'" + productName + "')]");
        return isDisplayed(productHeading);
    }
}