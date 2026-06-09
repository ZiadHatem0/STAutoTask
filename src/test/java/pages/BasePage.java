package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ExtentReportManager;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // ── Locator description helper ────────────────────────────────────────────
    private static String describe(By locator) {
        String s = locator.toString();
        int idx = s.indexOf(": ");
        return idx >= 0 ? s.substring(idx + 2) : s;
    }

    // ── Wait helpers ──────────────────────────────────────────────────────────
    protected WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitForPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // ── Action helpers (each logs a step to the Extent Report) ───────────────
    protected void click(By locator) {
        ExtentReportManager.log("Click: " + describe(locator));
        waitForClickable(locator).click();
    }

    protected void type(By locator, String text) {
        String display = describe(locator).toLowerCase().contains("password") ? "****" : text;
        ExtentReportManager.log("Type: \"" + display + "\"  into: " + describe(locator));
        WebElement el = waitForVisible(locator);
        el.clear();
        el.sendKeys(text);
    }

    protected void clickTypeAndSubmit(By locator, String text) {
        ExtentReportManager.log("Search: \"" + text + "\"  via: " + describe(locator));
        WebElement el = waitForClickable(locator);
        el.click();
        el.sendKeys(text);
        el.sendKeys(Keys.ENTER);
    }

    protected String getText(By locator) {
        String text = waitForVisible(locator).getText();
        ExtentReportManager.log("Read text: \"" + text + "\"  from: " + describe(locator));
        return text;
    }

    protected boolean isDisplayed(By locator) {
        try {
            boolean visible = waitForVisible(locator).isDisplayed();
            ExtentReportManager.log("Element visible: " + describe(locator));
            return visible;
        } catch (Exception e) {
            ExtentReportManager.log("Element not found: " + describe(locator));
            return false;
        }
    }

    protected void jsClick(By locator) {
        ExtentReportManager.log("JS Click (hidden element): " + describe(locator));
        WebElement el = waitForPresence(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    protected void waitForUrlToBe(String url) {
        wait.until(ExpectedConditions.urlToBe(url));
        ExtentReportManager.log("Page URL confirmed: " + url);
    }
}