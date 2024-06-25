import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import page_objects.MainPage;
import page_objects.OrderPage;

@RunWith(Parameterized.class)
public class OrderFlowTest {

    private WebDriver driver;
    private String name;
    private String surname;
    private boolean isBlack;

    public OrderFlowTest(WebDriver driver, String name, String surname, boolean isBlack) {
        this.driver = driver;
        this.name = name;
        this.surname = surname;
        this.isBlack = isBlack;
    }

    private static WebDriver chromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        return driver;
    }

    private static WebDriver firefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        WebDriver driver = new FirefoxDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        return driver;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                {chromeDriver(), "Ульяна", "Русева", true},
                {firefoxDriver(), "Ульяна", "Русева", true},
                {chromeDriver(), "Максимов", "Николай", false},
                {firefoxDriver(), "Максимов", "Николай", false},
        };
    }

    @Test
    public void checkFlowOverHeaderButton() {
        MainPage mainPageObj = new MainPage(driver);
        mainPageObj.onHeaderOrderButtonClick();

        OrderPage orderPageObj = new OrderPage(driver);
        orderPageObj.checkOrderFlowIsSuccess(name, surname, isBlack);
    }

    @Test
    public void checkFlowOverBigButton() {
        MainPage mainPageObj = new MainPage(driver);
        mainPageObj.onOrderButtonClick();

        OrderPage orderPageObj = new OrderPage(driver);
        orderPageObj.checkOrderFlowIsSuccess(name, surname, isBlack);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
