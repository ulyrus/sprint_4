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

    // иконка-стрелочка раскрываемого пункта
    private By accordion = By.id("accordion__heading-0");

    // появляемый текст
    private By shownText = By.id("accordion__panel-0");

    // кнопка "заказать" в заголовке страницы
    private By orderButtonInHeader = By.xpath(".//div[@class='Header_Nav__AGCXC']/button");

    // большая кнопка "заказать"
    private By orderButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkAccordionEnabled() {
        Assert.assertTrue("Стрелочка не кликабельна", driver.findElement(accordion).isEnabled());
    }

    public void scrollToAccordion() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(accordion));
    }

    public void clickAtAccordion() {
        driver.findElement(accordion).click();
    }

    public void waitUntilTextAppears() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(shownText));
    }

    public void checkShownTextContainsTargetText(String expectedText) {
        String actualText = driver.findElement(shownText).getText();

        Assert.assertEquals("Показан другой текст", expectedText, actualText);
    }

    public void checkTextShowsOnAccordeonClick(String expectedText) {
        scrollToAccordion();
        checkAccordionEnabled();
        clickAtAccordion();
        waitUntilTextAppears();
        checkShownTextContainsTargetText(expectedText);
    }

    public void onHeaderOrderButtonClick() {
        driver.findElement(orderButtonInHeader).click();
    }

    public void onOrderButtonClick() {
        WebElement button = driver.findElement(orderButton);
        ((JavascriptExecutor)(driver)).executeScript("arguments[0].scrollIntoView();", button);
        driver.findElement(orderButton).click();
    }
}
