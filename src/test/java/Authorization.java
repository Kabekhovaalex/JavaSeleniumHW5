import factory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import waiters.Waiters;


import javax.swing.*;
import java.util.concurrent.TimeUnit;


public class Authorization {
    private final String BASE_URL = System.getProperty("base.url","https://otus.ru");
//    private final String PASSWORD = System.getProperty("password");//mvn clean test -Dpassword=12345 -Dlogin=test
//    private final String LOGIN = System.getProperty("login");
    private String login = "diveroj856@htoal.com";
    private String password = "!Diveroj856";
    private WebDriver driver;
    private Actions actions;
    Waiters waiters;
    private final Logger log = LogManager.getLogger(Authorization.class);

    @BeforeAll
    public static void setup(){
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    public void start() {
        this.driver =new WebDriverFactory().create();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        log.info("Открытие Chrome");
        this.waiters = new Waiters(driver);
    }
//    @AfterEach
//    public void shutdown() {
//        if (driver != null) {
//            driver.quit();
//            log.info("Закрытие драйвера");
//        }
//    }

    @Test
    public void AuthorizationTest() {
//        Открыть https://otus.ru
        driver.get(BASE_URL + "/");
//        Авторизоваться на сайте
        loginInOtus();
//        Войти в личный кабинет
        enterLK();
//        В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
        clearAndEnter(By.id("id_fname"), "Александра");
        clearAndEnter(By.id("id_lname"), "Куликова");
        clearAndEnter(By.id("id_fname_latin"), "Alexandra");
        clearAndEnter(By.id("id_lname_latin"), "Kulikova");
        clearAndEnter(By.id("id_blog_name"), "Sandre");
        clearAndEnter(By.name("date_of_birth"), "10.10.1990");
//        Основная информация
        driver.findElement(By.cssSelector(".select.lk-cv-block__input.lk-cv-block__input_full.js-lk-cv-dependent-master.js-lk-cv-custom-select")).click();
        waiters.waitElementVisible(By.cssSelector("button[title='Россия']"));
        driver.findElement(By.cssSelector("button[title='Россия']")).click();

        //driver.findElement(By.cssSelector(".select.lk-cv-block__input.lk-cv-block__input_full.js-lk-cv-dependent-slave-city.js-lk-cv-custom-select")).click();

        //driver.findElement(By.xpath(" //input[contains(@data-title,'Город') and not(@disabled='disabled')]")).click();
        //driver.findElement(By.xpath("//input[@data-title= 'Город']/following::div[1]")).click();
        //"//div/span[@class='placeholder']"

        waiters.waitElementVisible(By.xpath("//input[@data-title= 'Город']/following::div[1]"));
        WebElement elCity = driver.findElement(By.xpath("//input[@data-title= 'Город']/following::div[1]"));
        elCity.click();
        //waiters.waitElementToBeClickable(By.cssSelector("button[title='Москва']"));
        WebElement elMoscow= driver.findElement(By.xpath("//button[contains(@title,'Москва')]"));
        elMoscow.click();
        // waiters.waitInvisibilityOf(driver.findElement(By.cssSelector("button[title='Москва']")));
       // waiters.waitElementVisible(By.cssSelector("button[title='Москва']"));

//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].scrollIntoView();",(By.cssSelector("button[title='Астрахань']")));



        driver.findElement(By.cssSelector("button[title=\"Сохранить и заполнить позже\"]")).submit();

    }
    private void loginInOtus() {
        driver.findElement(By.cssSelector(".sc-mrx253-0.enxKCy.sc-945rct-0.iOoJwQ")).click();

        clearAndEnter(By.cssSelector("input[name=\"email\"]"),login);
        clearAndEnter(By.cssSelector("input[type=\"password\"]"),password);
        driver.findElement(By.xpath("//div[contains(text(),'Войти')]")).click();
        log.info("Авторизация на сайте");
    }
    private void clearAndEnter(By by, String text) {
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(text);
    }
    private void enterLK() {

        actions = new Actions(driver);
        //WebElement elProfile = driver.findElement(By.xpath("(//section/div[.//img[contains(@src, 'next/')] or .//img[contains(@src, 'cdn.otus.ru')][not(@alt)]] | //div[@data-name='user-info'][contains(@class, 'popup-trigger')])"));
        WebElement elProfile = driver.findElement(By.cssSelector(".sc-199a3eq-0.fJMWHf"));
        waiters.waitElementVisible(elProfile);
        actions.moveToElement(elProfile).perform();

        WebElement elMyProfile = driver.findElement(By.xpath(" //a[contains(text(), 'Мой профиль')]"));
        waiters.waitElementVisible(elMyProfile);
        elMyProfile.click();

        log.info("Вход в Мой Профиль");
    }
}
