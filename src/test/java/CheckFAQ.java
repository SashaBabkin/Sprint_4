import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.pageobjects.MainPage;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CheckFAQ {

    WebDriver driver;
    int questionNumber;
    String expectedQuestion;
    String expectedAnswer;

    //Вопросы
    private static final String QUESTION_1 = "Сколько это стоит? И как оплатить?";
    private static final String QUESTION_2 = "Хочу сразу несколько самокатов! Так можно?";
    private static final String QUESTION_3 = "Как рассчитывается время аренды?";
    private static final String QUESTION_4 = "Можно ли заказать самокат прямо на сегодня?";
    private static final String QUESTION_5 = "Можно ли продлить заказ или вернуть самокат раньше?";
    private static final String QUESTION_6 = "Вы привозите зарядку вместе с самокатом?";
    private static final String QUESTION_7 = "Можно ли отменить заказ?";
    private static final String QUESTION_8 = "Я жизу за МКАДом, привезёте?";

    //Ответы на вопросы
    private static final String ANSWER_1 = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    private static final String ANSWER_2 = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    private static final String ANSWER_3 = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    private static final String ANSWER_4 = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    private static final String ANSWER_5 = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    private static final String ANSWER_6 = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    private static final String ANSWER_7 = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    private static final String ANSWER_8 = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

    public CheckFAQ(int questionNumber, String expectedQuestion, String expectedAnswer) {
        this.questionNumber = questionNumber;
        this.expectedQuestion = expectedQuestion;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][] {
                {0, QUESTION_1, ANSWER_1},
                {1, QUESTION_2, ANSWER_2},
                {2, QUESTION_3, ANSWER_3},
                {3, QUESTION_4, ANSWER_4},
                {4, QUESTION_5, ANSWER_5},
                {5, QUESTION_6, ANSWER_6},
                {6, QUESTION_7, ANSWER_7},
                {7, QUESTION_8, ANSWER_8}
        };
    }

    @Before
    public void prepare() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

//        WebDriverManager.firefoxdriver().setup();
//        driver = new FirefoxDriver();

    }

    @Test
    public void checkFAQTest() {
        driver.get(MainPage.URL);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); //Неявное ожидание перед каждым методом findElement
        MainPage mainPage = new MainPage(driver);
        mainPage.acceptCookies();
        //Проверка вопросов
        String actualQuestion = mainPage.getQuestion(questionNumber);
        assertEquals(expectedQuestion, actualQuestion);
        //Проверка ответов
        String actualAnswer = mainPage.getAnswer(questionNumber);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
