package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmOrderPage extends BasePage {

    public ConfirmOrderPage(WebDriver driver) {
        super(driver);
    }

    //Кнопка подтверждения оформления заказа "Да"
    private String confirmOrderButton = ".//div[@class = 'Order_Buttons__1xGrp']/button[text() = 'Да']";

    //Окно Заказ оформлен
    private String confirmationOfOrder = ".//div[@class = 'Order_Modal__YZ-d3']/div[text() = 'Заказ оформлен']";

    public void pressConfirmOrderButton() {
        driver.findElement(By.xpath(confirmOrderButton)).click();

    }

    public boolean checkConfirmIsDisplayed() {
        return driver.findElements(By.xpath(confirmationOfOrder)).size() > 0;
    }
}

