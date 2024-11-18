package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPageAbout extends BasePage {

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


    public OrderPageAbout(WebDriver driver) {
        super(driver);
    }

    public void fillOrderFormAbout(String data, String rentPeriod, String comment) {
        driver.findElement(By.xpath(chooseData)).sendKeys(data);
        driver.findElement(By.xpath(inputRentPeriod)).click();
        driver.findElement(By.xpath(".//div[@class = 'Dropdown-menu']/div[text() = '"+rentPeriod+"']")).click();

        driver.findElement(By.xpath(inputComment)).sendKeys(comment);

        driver.findElement(By.xpath(makeOrderButtonFinal)).click();
    }
}
