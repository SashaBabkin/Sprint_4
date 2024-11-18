package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class OrderPageForWho extends BasePage{

    //поле Имя
    private String inputFirstName = ".//input[@placeholder = '* Имя']";
    //поле Фамилия
    private String inputLastName = ".//input[@placeholder = '* Фамилия']";
    //поле Адрес (куда привезти заказ)
    private String inputAdress = ".//input[@placeholder = '* Адрес: куда привезти заказ']";
    //поле Выбор станции метро
    private String chooseMetroStation = ".//input[@placeholder = '* Станция метро']";
    //поле Телефон
    private String inputPhoneNumber = ".//input[@placeholder = '* Телефон: на него позвонит курьер']";
    //Кнопка Далее
    private String furtherOrderButton = ".//button[text() = 'Далее']";

    public OrderPageForWho(WebDriver driver) {
        super(driver);
    }

    public void fillOrderFormForWho(String firstName, String lastName, String adress, String metroStation, String phoneNumber) {
        driver.findElement(By.xpath(inputFirstName)).sendKeys(firstName);
        driver.findElement(By.xpath(inputLastName)).sendKeys(lastName);
        driver.findElement(By.xpath(inputAdress)).sendKeys(adress);
        driver.findElement(By.xpath(chooseMetroStation)).sendKeys(metroStation, Keys.ARROW_DOWN, Keys.ENTER);
        driver.findElement(By.xpath(inputPhoneNumber)).sendKeys(phoneNumber);
        // new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.xpath(furtherOrderButton)));

        driver.findElement(By.xpath(furtherOrderButton)).click();

    }

}
