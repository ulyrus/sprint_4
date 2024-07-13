import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page_objects.MainPage;

import static page_objects.MainPage.*;

public class MainQuestionsAboutImportantExpandTest {

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void clicksAtAccordionsExpandsTargetTexts() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");

        mainPage = new MainPage(driver);
    }

    @Test
    public void howMuchAccordeonTextAppears() {
        mainPage.checkTextShowsOnAccordeonClick(0, HOW_MUCH);
    }

    @Test
    public void wantMultipleAccordeonTextAppears() {
        mainPage.checkTextShowsOnAccordeonClick(1, WANT_MULTIPLE);
    }

    @Test
    public void howCalculateTimeAccordeonTextAppears() {
        mainPage.checkTextShowsOnAccordeonClick(2, HOW_CALCULATES_TIME);
    }

    @Test
    public void canOrderForTodayAccordeonTextAppears() {
        mainPage.checkTextShowsOnAccordeonClick(3, CAN_ORDER_FOR_TODAY);
    }

    @Test
    public void canProlongOrBackEarlierAccordeonTextAppears() {
        mainPage.checkTextShowsOnAccordeonClick(4, CAN_PROLONG_OR_BACK_EARLIER);
    }

    @Test
    public void deliverChargerAccordeonTextAppears() {
        mainPage.checkTextShowsOnAccordeonClick(5, DELIVER_CHARGER);
    }

    @Test
    public void canCancelOrderAccordeonTextAppears() {
        mainPage.checkTextShowsOnAccordeonClick(6, CAN_CANCEL_ORDER);
    }

    @Test
    public void mkadAccordeonTextAppears() {
        mainPage.checkTextShowsOnAccordeonClick(7, MKAD);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
