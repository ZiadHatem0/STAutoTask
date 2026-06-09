package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class TestBase {

    public WebDriver driver;
    public WebDriverWait wait;

    private static final String BASE_URL = "https://storefront-consumer-frontend-store-front-dev.skynodes.dev/";
    private static final int WAIT_TIMEOUT_SECONDS = 15;

    @BeforeTest
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
        driver.get(BASE_URL);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void navigateToBaseUrl() {
        driver.navigate().to(BASE_URL);
    }

    public String getBaseUrl() {
        return BASE_URL;
    }
}