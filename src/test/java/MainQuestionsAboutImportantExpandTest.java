import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page_objects.MainPage;

public class MainQuestionsAboutImportantExpandTest {

    private WebDriver driver;

    @Test
    public void clickAtAccordionExpandsTargetText() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage mainPageObj = new MainPage(driver);
        String expectedText = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
        mainPageObj.checkTextShowsOnAccordeonClick(expectedText);

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
