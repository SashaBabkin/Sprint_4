package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage {

    //кнопка "Заказать" вверху страницы
    private String makeOrderButtonUp = ".//div[@class = 'Header_Nav__AGCXC']/button[@class = 'Button_Button__ra12g']";
    //кнопка "Заказать" внизу страницы
    private String makeOrderButtonDown = ".//button[@class = 'Button_Middle__1CSJM']";
    //кнопка согласия использовать куки
    private String acceptCookiesButton = ".//button[@id = 'rcc-confirm-button']";

    //Вопросы о важном
    private String questionLink = ".//div[@id = 'accordion__heading-%d']"; //Вопросы
    private String answerLink = ".//div[@id = 'accordion__panel-%d']/p"; //Ответы


    public MainPage(WebDriver driver) {
        super(driver);
    }

    public String getQuestion(int questionNumber) {
        WebElement element = driver.findElement(By.xpath(String.format(questionLink, questionNumber)));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element); //Скролл во вопроса
        driver.findElement(By.xpath(String.format(questionLink, questionNumber))).click(); //Клик на вопрос
        return driver.findElement(By.xpath(String.format(answerLink, questionNumber))).getText(); //Получаем текст ответа


    }

    public void acceptCookies() {
        driver.findElement(By.xpath(acceptCookiesButton)).click();
    }

    public void clickMakeOrderButtonUp() {
        driver.findElement(By.xpath(makeOrderButtonUp)).click();
    }

    public void clickMakeOrderButtonDown() {
        driver.findElement(By.xpath(makeOrderButtonDown)).click();
    }


}
