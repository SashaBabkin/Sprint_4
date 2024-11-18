import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObject.ConfirmOrderPage;
import pageObject.MainPage;
import pageObject.OrderPageAbout;
import pageObject.OrderPageForWho;


import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderTest {

    WebDriver driver;

    String firstName;
    String lastName;
    String adress;
    String metroStation;
    String phoneNumber;
    String dataOfDelivery;
    String rentPeriod;
    String comment;
    Boolean orderStatus;

    public OrderTest(String firstName, String lastName, String adress, String metroStation, String phoneNumber, String dataOfDelivery, String rentPeriod, String comment, Boolean orderStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.adress = adress;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.dataOfDelivery = dataOfDelivery;
        this.rentPeriod = rentPeriod;
        this.comment = comment;
        this.orderStatus = orderStatus;
    }

    @Parameterized.Parameters
    public static Object[][] orderTestData() {
        return new Object[][] {
                {"Иван", "Петров", "Тверская, 15", "Тверская", "+77777777777", "01.12.2024", "сутки", "Скорее", true },
                {"Андрей", "Иванов", "Покрышкина, 25", "Юго-Западная", "89034569874", "05.12.2024", "двое суток", "Хочу", true}
        };
    }

    @Before
    public void prepare() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

//        WebDriverManager.firefoxdriver().setup();
//        driver = new FirefoxDriver();
    }

    // Точка входа - Кнопка Заказать вверху страницы
    @Test
    public void orderTestWithButtonUp() {
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage mainPage = new MainPage(driver);
        mainPage.acceptCookies();

        OrderPageForWho orderPageForWho = new OrderPageForWho(driver);
        mainPage.clickMakeOrderButtonUp();
        // new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(".//input[@placeholder = '* Имя']"))));
        orderPageForWho.fillOrderFormForWho(firstName, lastName, adress, metroStation, phoneNumber);
        OrderPageAbout orderPageAbout = new OrderPageAbout(driver);
        orderPageAbout.fillOrderFormAbout(dataOfDelivery, rentPeriod, comment);

        ConfirmOrderPage confirmOrderPage = new ConfirmOrderPage(driver);
        confirmOrderPage.pressConfirmOrderButton();

        Boolean actualResult = confirmOrderPage.checkConfirmIsDisplayed();

        assertEquals("Ожидается окно подтверждения заказа", orderStatus, actualResult);

    }


    // Точка входа - Кнопка Заказать внизу страницы
    @Test
    public void orderTestWithButtonDown() {
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage mainPage = new MainPage(driver);
        mainPage.acceptCookies();

        OrderPageForWho orderPageForWho = new OrderPageForWho(driver);
        mainPage.clickMakeOrderButtonDown();
        // new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(".//input[@placeholder = '* Имя']"))));
        orderPageForWho.fillOrderFormForWho(firstName, lastName, adress, metroStation, phoneNumber);
        OrderPageAbout orderPageAbout = new OrderPageAbout(driver);
        orderPageAbout.fillOrderFormAbout(dataOfDelivery, rentPeriod, comment);

        ConfirmOrderPage confirmOrderPage = new ConfirmOrderPage(driver);
        confirmOrderPage.pressConfirmOrderButton();

        Boolean actualResult = confirmOrderPage.checkConfirmIsDisplayed();

        assertEquals("Ожидается окно подтверждения заказа", orderStatus, actualResult);

    }


    @After
    public void tearDown() {
        driver.quit();
    }


}

