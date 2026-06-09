package tests;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import utils.TestListener;

@Listeners(TestListener.class)
public class ShoppingTest extends TestBase {

    private static final String EMAIL        = "mohamed.tawfik@paysky.io";
    private static final String PASSWORD     = "password";
    private static final String PRODUCT_NAME = "SATIN FLOW BEADED DRESS";

    private HomePage homePage;
    private CartPage cartPage;

    @Test(priority = 1)
    public void login() {
        homePage = new LoginPage(driver, wait)
                .login(EMAIL, PASSWORD, getBaseUrl());
    }

    @Test(priority = 2, dependsOnMethods = "login")
    public void searchProduct() {
        Assert.assertTrue(
                homePage.searchFor(PRODUCT_NAME).isSearchResultVisible(PRODUCT_NAME),
                "Search results should contain: " + PRODUCT_NAME
        );
    }

    @Test(priority = 3, dependsOnMethods = "searchProduct")
    public void addToCartAndVerify() {
        cartPage = homePage.openProductResult(PRODUCT_NAME)
                           .addToCart()
                           .openCart();

        Assert.assertTrue(
                cartPage.isProductDisplayed(PRODUCT_NAME),
                "Cart should contain: " + PRODUCT_NAME
        );
    }

    @Test(priority = 4, dependsOnMethods = "addToCartAndVerify")
    public void moveToWishlistAndVerify() {
        cartPage.moveItemToWishlist();

        WishlistPage wishlistPage = new HomePage(driver, wait).goToWishlist();

        Assert.assertTrue(
                wishlistPage.isProductDisplayed(PRODUCT_NAME),
                "Wishlist should contain: " + PRODUCT_NAME
        );
    }

    @Test(priority = 5, dependsOnMethods = "moveToWishlistAndVerify")
    public void proceedToCheckout() {
        new HomePage(driver, wait).openCart().proceedToCheckout();
    }

    @Test(priority = 6, dependsOnMethods = "proceedToCheckout")
    public void removeFromWishlistViaHeart() {
        navigateToBaseUrl();

        new HomePage(driver, wait)
                .searchFor(PRODUCT_NAME)
                .openProductResult(PRODUCT_NAME)
                .removeFromWishlistViaHeart();
    }

    @Test(priority = 7, dependsOnMethods = "removeFromWishlistViaHeart")
    public void logout() {
        new HomePage(driver, wait).logout();
    }
}