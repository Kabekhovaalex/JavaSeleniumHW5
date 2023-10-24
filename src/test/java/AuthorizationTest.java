import factory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import waiters.Waiters;


import java.util.concurrent.TimeUnit;


public class AuthorizationTest {
    private final String BASE_URL = System.getProperty("base.url", "https://otus.ru");
    //    private final String PASSWORD = System.getProperty("password");//mvn clean test -Dpassword=12345 -Dlogin=test
//    private final String LOGIN = System.getProperty("login");
    private String login = "diveroj856@htoal.com";
    private String password = "!Diveroj856";
    private WebDriver driver;
    private Actions actions;
    Waiters waiters;
    private final Logger log = LogManager.getLogger(AuthorizationTest.class);

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void start() {
        this.driver = new WebDriverFactory().create();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        log.info("Открытие Chrome");
        this.waiters = new Waiters(driver);
    }

    @AfterEach
    public void shutdown() {
        if (driver != null) {
            driver.quit();
            log.info("Закрытие драйвера");
        }
    }

    @Test
    public void authorizationTest() {
//        Открыть https://otus.ru
        driver.get(BASE_URL + "/");
//        Авторизоваться на сайте
        loginInOtus();
//        Войти в личный кабинет
        enterLK();
//        В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
        addPersonalInformation();
//        Контактная информация
        addContacts();
//        Другое
        addOther();
//        Опыт разработки
        addDevelopmentExperience();
//        Нажать сохранить
        driver.findElement(By.cssSelector("button[title=\"Сохранить и заполнить позже\"]")).submit();
        log.info("Сохранение страницы");

//        Открыть https://otus.ru в “чистом браузере”
        shutdown();
        start();
        driver.get(BASE_URL + "/");
//        Авторизоваться на сайте
        loginInOtus();
//        Войти в личный кабинет
        enterLK();
//        Проверить, что в разделе "О себе" отображаются указанны ранее данные
        assertionsLK();

    }

    private void loginInOtus() {
        driver.findElement(By.cssSelector(".sc-mrx253-0.enxKCy.sc-945rct-0.iOoJwQ")).click();
        clearAndEnter(By.cssSelector("input[name=\"email\"]"), login);
        clearAndEnter(By.cssSelector("input[type=\"password\"]"), password);
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

    private void addPersonalInformation() {
        //Имя
        clearAndEnter(By.id("id_fname"), "Александра");
//        Выбор страны
        driver.findElement(By.cssSelector(".select.lk-cv-block__input.lk-cv-block__input_full.js-lk-cv-dependent-master.js-lk-cv-custom-select")).click();
        driver.findElement(By.cssSelector("button[title='Россия']")).click();

//        Имя, фамилия
        clearAndEnter(By.id("id_lname"), "Куликова");
        clearAndEnter(By.id("id_fname_latin"), "Alexandra");
        clearAndEnter(By.id("id_lname_latin"), "Kulikova");
        clearAndEnter(By.id("id_blog_name"), "Sandre");

        //       День рождения
        clearAndEnter(By.name("date_of_birth"), "10.10.1990");

//        Выбор города
        driver.findElement(By.cssSelector(".select.lk-cv-block__input.lk-cv-block__input_full.js-lk-cv-dependent-slave-city.js-lk-cv-custom-select")).click();
        driver.findElement(By.xpath("//button[@data-value='61']")).click();

//        Уровень английского
        driver.findElement(By.xpath("//input[@data-title='Уровень знания английского языка']/../..")).click();
        driver.findElement(By.xpath("//button[contains(@title,'Начальный уровень (Beginner)')]")).click();

//       Готовность к переезду
        WebElement elMoving = driver.findElement(By.xpath("//span[contains(text(),'Да')]"));
        if (!elMoving.isSelected()) {
            elMoving.click();
        }

        ////        Полный день
        //driver.findElement(By.xpath("//input[@title='Полный день']/../..")).isSelected();
        WebElement elFullDay = driver.findElement(By.xpath("//input[@title='Полный день']/../.."));
        //WebElement elFullDay = driver.findElement(By.xpath("//input[contains(@title,'Полный день')]/following::span[contains(@class, 'checkbox__label lk-cv-block__text lk-cv-block__text_work-schedule')]"));

        boolean a = elFullDay.isSelected(); // isSelected возвращает все время false
        if (a) {
            elFullDay.click();

        }


//       Гибкий график
        //driver.findElement(By.xpath("//input[@title='Гибкий график']/../..")).isSelected();
        WebElement elflexiblesSchedule = driver.findElement(By.xpath("//input[@title='Гибкий график']/../.."));
        boolean a1 = elflexiblesSchedule.isSelected();
        if (a1) {
            elflexiblesSchedule.click();
        }

//         Удаленно
        //driver.findElement(By.xpath("//input[@title='Удаленно']/../..")).isSelected();
        WebElement elDist = driver.findElement(By.xpath("//input[@title='Удаленно']/../.."));
        if (elDist.isSelected()) {
            elDist.click();
            log.info("Добавление пользовательских данных");
        }
    }

    private void addContacts() {

        driver.findElement(By.xpath("//span[@class='placeholder']")).click();
        driver.findElement(By.cssSelector("div[class='lk-cv-block__select-options lk-cv-block__select-options_left js-custom-select-options-container'] button[title='VK']")).click();
        clearAndEnter(By.id("id_contact-0-value"), "https://vk.com/kulikova_alex");
        driver.findElement(By.xpath("//button[contains(text(),'Добавить')]")).click();
        driver.findElement(By.xpath("//span[@class='placeholder']")).click();
        driver.findElement(By.cssSelector("div[class='lk-cv-block__select-options lk-cv-block__select-options_left js-custom-select-options-container'] button[title='Тelegram']")).click();
        clearAndEnter(By.id("id_contact-1-value"), "@Sandre");

        log.info("Добавление основной информации");

        //driver.findElement(By.cssSelector("button[title=\"Сохранить и заполнить позже\"]")).submit();
        //driver.findElement(By.xpath("//div[@class='nav-sidebar']//a[@title='Персональные данные']")).click();
    }
//private void deleteContacts() {
//    div.js-formset-row:nth-child(1) > div:nth-child(4) > div:nth-child(2) > button:nth-child(1)
//}
    private void addOther() {
        // Пол
        if (!(driver.findElement(By.xpath("//div[@class='select select_full']")).isSelected())) {
            driver.findElement(By.xpath("//option[@value='f']")).click();
        }
        clearAndEnter(By.id("id_company"), "MaxFactor");
        clearAndEnter(By.id("id_work"), "Team lead");

        log.info("Добавление другой информации");
    }

    private void addDevelopmentExperience() {
        driver.findElement(By.cssSelector("a[title='Добавить']")).click();
        driver.findElement(By.cssSelector("#id_experience-0-experience")).click();
        driver.findElement(By.cssSelector("#id_experience-0-experience > option:nth-child(3)")).click();
        driver.findElement(By.cssSelector("#id_experience-0-level > option:nth-child(1)")).click();
        log.info("Добавление информации об опыте разработки");
    }

    private void assertionsLK() {
        Assertions.assertEquals("Александра", driver.findElement(By.id("id_fname")).getAttribute("value"));
        Assertions.assertEquals("Куликова", driver.findElement(By.id("id_lname")).getAttribute("value"));
        Assertions.assertEquals("Alexandra", driver.findElement(By.id("id_fname_latin")).getAttribute("value"));
        Assertions.assertEquals("Kulikova", driver.findElement(By.id("id_lname_latin")).getAttribute("value"));
        Assertions.assertEquals("Sandre", driver.findElement(By.id("id_blog_name")).getAttribute("value"));
        Assertions.assertEquals("10.10.1990", driver.findElement(By.name("date_of_birth")).getAttribute("value"));


        Assertions.assertEquals("Россия", driver.findElement(By.cssSelector(".select.lk-cv-block__input.lk-cv-block__input_full.js-lk-cv-dependent-master.js-lk-cv-custom-select"))
                .getAttribute("value"));
        Assertions.assertEquals("Астрахань",driver.findElement(By.cssSelector(".select.lk-cv-block__input.lk-cv-block__input_full.js-lk-cv-dependent-slave-city.js-lk-cv-custom-select"))
                .getAttribute("value"));
        Assertions.assertEquals("Начальный уровень (Beginner)", driver.findElement(By.xpath("//button[contains(@title,'Начальный уровень (Beginner)')]"))
                .getAttribute("value"));

        log.info("В разделе о себе отображаются указанные ранее данные");
    }
}

