package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    private static final By SEARCH_BAR         = By.xpath("//input[@placeholder='What are you looking for?']");
    private static final By CART_BUTTON         = By.xpath("//button[contains(@aria-label,'Cart')]");
    private static final By WISHLIST_NAV_BUTTON = By.xpath("//button[.//span[text()='Wishlist']]");
    private static final By MY_ACCOUNT_BUTTON   = By.xpath("//button[.//span[text()='My Account']]");
    private static final By LOGOUT_MENU_ITEM    = By.xpath("//div[@role='menuitem'][.//span[text()='Logout']]");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public HomePage searchFor(String productName) {
        clickTypeAndSubmit(SEARCH_BAR, productName);
        return this;
    }

    public boolean isSearchResultVisible(String productName) {
        By resultLocator = By.xpath("//*[contains(text(),'" + productName + "')]");
        return isDisplayed(resultLocator);
    }

    public ProductPage openProductResult(String productName) {
        By productLocator = By.xpath("//p[contains(text(),'" + productName + "')]");
        click(productLocator);
        return new ProductPage(driver, wait);
    }

    public CartPage openCart() {
        click(CART_BUTTON);
        return new CartPage(driver, wait);
    }

    public WishlistPage goToWishlist() {
        click(WISHLIST_NAV_BUTTON);
        return new WishlistPage(driver, wait);
    }

    public void logout() {
        click(MY_ACCOUNT_BUTTON);
        click(LOGOUT_MENU_ITEM);
    }
}