package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {

    private static final By MOVE_TO_WISHLIST_BUTTON    = By.xpath("//button[contains(.,'Move to Wishlist') or contains(.,'MOVE TO WISHLIST')]");
    private static final By PROCEED_TO_CHECKOUT_BUTTON = By.xpath("//button[contains(@aria-label,'Proceed to checkout')]");

    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isProductDisplayed(String productName) {
        By productHeading = By.xpath("//h4[contains(text(),'" + productName + "')]");
        return isDisplayed(productHeading);
    }

    public CartPage moveItemToWishlist() {
        click(MOVE_TO_WISHLIST_BUTTON);
        return this;
    }

    public CheckoutPage proceedToCheckout() {
        click(PROCEED_TO_CHECKOUT_BUTTON);
        return new CheckoutPage(driver, wait);
    }
}