package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {

    private static final By ADD_TO_CART_BUTTON         = By.xpath("//button[contains(text(),'ADD TO CART')]");
    private static final By CART_BUTTON                = By.xpath("//button[contains(@aria-label,'Cart')]");
    private static final By REMOVE_FROM_WISHLIST_HEART = By.xpath("//button[@aria-label='Remove from wishlist']");

    public ProductPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public ProductPage addToCart() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(ADD_TO_CART_BUTTON));
        btn.click();
        return this;
    }

    public CartPage openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(CART_BUTTON)).click();
        return new CartPage(driver, wait);
    }

    // Heart button is hidden on desktop — presence check + JavaScript click required
    public void removeFromWishlistViaHeart() {
        WebElement heart = wait.until(ExpectedConditions.presenceOfElementLocated(REMOVE_FROM_WISHLIST_HEART));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", heart);
    }
}