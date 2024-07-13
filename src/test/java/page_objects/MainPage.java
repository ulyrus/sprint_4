package page_objects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;

    // кнопка "заказать" в заголовке страницы
    private By orderButtonInHeader = By.xpath(".//div[@class='Header_Nav__AGCXC']/button");

    // большая кнопка "заказать"
    private By orderButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private void checkAccordionEnabled(By accordionFinder) {
        Assert.assertTrue("Стрелочка не кликабельна", driver.findElement(accordionFinder).isEnabled());
    }

    private void scrollToAccordion(By accordionFinder) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(accordionFinder));
    }

    private void clickAtAccordion(By accordionFinder) {
        driver.findElement(accordionFinder).click();
    }

    private void waitUntilTextAppears(By shownTextFinder) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(shownTextFinder));
    }

    private void checkShownTextContainsTargetText(By shownTextFinder, String expectedText) {
        String actualText = driver.findElement(shownTextFinder).getText();

        Assert.assertEquals("Показан другой текст", expectedText, actualText);
    }

    public void checkTextShowsOnAccordeonClick(int accordeonIndex, String expectedText) {
        // заголовок аккордеона
        By accordion = By.xpath(".//div[@id='accordion__heading-" + accordeonIndex + "']");
        scrollToAccordion(accordion);
        checkAccordionEnabled(accordion);
        clickAtAccordion(accordion);

        // показываемый текст
        By textFinder = By.xpath(".//div[@id='accordion__panel-" + accordeonIndex + "']");
        waitUntilTextAppears(textFinder);
        checkShownTextContainsTargetText(textFinder, expectedText);
    }

    public void onHeaderOrderButtonClick() {
        driver.findElement(orderButtonInHeader).click();
    }

    public void onOrderButtonClick() {
        WebElement button = driver.findElement(orderButton);
        ((JavascriptExecutor)(driver)).executeScript("arguments[0].scrollIntoView();", button);
        driver.findElement(orderButton).click();
    }

    public static final String HOW_MUCH = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    public static final String WANT_MULTIPLE = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    public static final String HOW_CALCULATES_TIME = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    public static final String CAN_ORDER_FOR_TODAY = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    public static final String CAN_PROLONG_OR_BACK_EARLIER = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    public static final String DELIVER_CHARGER = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    public static final String CAN_CANCEL_ORDER = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    public static final String MKAD = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";
}
