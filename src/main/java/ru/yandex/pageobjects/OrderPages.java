package ru.yandex.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class OrderPages extends BasePage {
    //Элементы формы заказа "Для кого самокат"
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

    //Элементы формы заказа "Про аренду"
    //поле Когда привезти самокат
    private String chooseData = ".//input[@placeholder = '* Когда привезти самокат']";
    //поле Срок аренды
    private String inputRentPeriod = ".//span[@class = 'Dropdown-arrow']";
    // меню выбора срока аренды
    private String chooseRentDays = ".//div[@class = 'Dropdown-menu']";
    //поле Цвет самоката (не обязательно для выбора)
    private By scooterColor = By.className("Order_Title__3EKne");
    //поле Комментарий (не обязательно для заполнения)
    private String inputComment = ".//input[@placeholder = 'Комментарий для курьера']";
    //кнопка Назад
    private By backButton = By.className("Button_Inverted__3IF-i");
    //кнопка Заказать финальная
    private String makeOrderButtonFinal = ".//div[@class = 'Order_Buttons__1xGrp']/button[text() = 'Заказать']";

    //Элементы окна подтверждения намерения забронировать самокат ("Хотите оформить заказ?"
    //Кнопка подтверждения оформления заказа "Да"
    private String confirmOrderButton = ".//div[@class = 'Order_Buttons__1xGrp']/button[text() = 'Да']";
    //Окно Заказ оформлен
    private String confirmationOfOrder = ".//div[@class = 'Order_Modal__YZ-d3']/div[text() = 'Заказ оформлен']";

    public OrderPages(WebDriver driver) {
        super(driver);
    }

    //Метод заполнения формы "Для кого самокат?
    public void fillOrderFormForWho(String firstName, String lastName, String adress, String metroStation, String phoneNumber) {
        driver.findElement(By.xpath(inputFirstName)).sendKeys(firstName);
        driver.findElement(By.xpath(inputLastName)).sendKeys(lastName);
        driver.findElement(By.xpath(inputAdress)).sendKeys(adress);
        driver.findElement(By.xpath(chooseMetroStation)).sendKeys(metroStation, Keys.ARROW_DOWN, Keys.ENTER);
        driver.findElement(By.xpath(inputPhoneNumber)).sendKeys(phoneNumber);
        driver.findElement(By.xpath(furtherOrderButton)).click();

    }

    //Метод заполнения формы "Про аренду"
    public void fillOrderFormAbout(String data, String rentPeriod, String comment) {
        driver.findElement(By.xpath(chooseData)).sendKeys(data);
        driver.findElement(By.xpath(inputRentPeriod)).click();
        driver.findElement(By.xpath(".//div[@class = 'Dropdown-menu']/div[text() = '"+rentPeriod+"']")).click();
        driver.findElement(By.xpath(inputComment)).sendKeys(comment);
        driver.findElement(By.xpath(makeOrderButtonFinal)).click();
    }

    //Методы подтверждения заказа
    public void pressConfirmOrderButton() {
        driver.findElement(By.xpath(confirmOrderButton)).click();

    }

    public boolean checkConfirmIsDisplayed() {
        return driver.findElements(By.xpath(confirmationOfOrder)).size() > 0;
    }
}
