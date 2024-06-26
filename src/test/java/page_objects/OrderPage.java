package page_objects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    private WebDriver driver;

    // поле ввода имени
    private By name = By.xpath(".//div[starts-with(@class, 'Order_Form')]/*[1]/input");

    // поле ввода фамилии
    private By surname = By.xpath(".//div[starts-with(@class, 'Order_Form')]/*[2]/input");

    // поле ввода адреса
    private By address = By.xpath(".//div[starts-with(@class, 'Order_Form')]/*[3]/input");

    // поле выбора станции метро
    private By metro = By.xpath(".//div[@class='select-search__value']");

    // пункт метро Сокольники
    private By metroStation = By.xpath(".//div[contains(text(),'Сокольники')]");

    // поле ввода номера телефона
    private By phoneNumber = By.xpath(".//div[starts-with(@class, 'Order_Form')]/*[5]/input");

    // пнопка "Далее"
    private By buttonNext = By.xpath(".//button[text()='Далее']");

    // поле выбора даты аренды
    private By datepicker = By.className("react-datepicker__input-container");

    // 1й день месяца в выборе даты
    private By datepickerFirstDayOfMonth = By.xpath(".//div[text()='1' and contains(@class,'react-datepicker__day')]");

    // выпадающее менб выбора срока аренды
    private By rentPeriod = By.className("Dropdown-root");

    // пункт "сутки" в выпадающем меню выбора срока аренды
    private By rentPeriodFirst = By.xpath(".//div[text()='сутки']");

    // цвет "черный жемчуг"
    private By colorBlack = By.id("black");

    // цвет "серая безысходнось"
    private By colorGrey = By.id("grey");

    // кнопка "Заказать"
    private By buttonOrder = By.xpath(".//button[text()='Заказать' and contains(@class,'Button_Middle__1CSJM')]");

    // кнопка Да в модалке подтверждения
    private By buttonYes = By.xpath(".//button[text()='Да']");

    // контейнер модалки
    private By orderModal = By.xpath(".//div[starts-with(@class,'Order_Modal')]");

    // заголовок модалки
    private By orderModalTextSuccess = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setName(String name) {
        driver.findElement(this.name).sendKeys(name);
    }

    public void setSurname(String surname) {
        driver.findElement(this.surname).sendKeys(surname);
    }

    public void setAddress(String address) {
        driver.findElement(this.address).sendKeys(address);
    }

    public void selectMetro() {
        driver.findElement(metro).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(metroStation));
        driver.findElement(metroStation).click();
    }

    public void setPhoneNumber(String phoneNumber) {
        driver.findElement(this.phoneNumber).sendKeys(phoneNumber);
    }

    public void clickNext() {
        driver.findElement(buttonNext).click();
    }

    public void selectDate() {
        driver.findElement(datepicker).click();
        driver.findElement(datepickerFirstDayOfMonth).click();
    }

    public void selectRentPeriod() {
        driver.findElement(rentPeriod).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(rentPeriodFirst));
        driver.findElement(rentPeriodFirst).click();
    }

    public void selectColor(boolean isBlack) {
        if (isBlack)
            driver.findElement(colorBlack).click();
        else
            driver.findElement(colorGrey).click();
    }

    public void clickOrder() {
        driver.findElement(buttonOrder).click();
    }

    public void clickYes() {
        driver.findElement(buttonYes).click();
    }

    public void checkOrderSuccess() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(orderModal));
        String modalHeaderText = driver.findElement(orderModalTextSuccess).getText();
        Assert.assertEquals("Ошибка при оформлении заказа", "Заказ оформлен", modalHeaderText);
    }

    public void checkOrderFlowIsSuccess(String name, String surname, boolean isBlack) {
        setName(name);
        setSurname(surname);
        setAddress("Казанский");
        selectMetro();
        setPhoneNumber("79009009900");
        clickNext();

        selectDate();
        selectRentPeriod();
        selectColor(isBlack);
        clickOrder();
        clickYes();

        checkOrderSuccess();
    }
}
