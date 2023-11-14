package auth;

import data.BrowserData;
import exception.BrowserNotSupportedException;
import factory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import waiters.Waiters;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;


public class AuthorizationTest {
    private final String BASE_URL = System.getProperty("base.url", "https://otus.ru");
//    private final String LOGIN = System.getProperty("login");
//    private final String PASSWORD = System.getProperty("password");

//mvn clean test  -Dlogin="yadaci9405@othao.com" -Dpassword=!Yadaci9405
    private String LOGIN = "yadaci9405@othao.com";
    private String PASSWORD = "!Yadaci9405";
    private WebDriver driver;
    private Actions actions;
    private Waiters waiters;
    private Waiters cityWaiters;
    private final LocalDate expectedDate = LocalDate.of(1990, Month.OCTOBER,10);

    public org.apache.logging.log4j.Logger log = LogManager.getLogger(helper.Logger.class);

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void start() throws BrowserNotSupportedException {

        this.driver = new WebDriverFactory().create(BrowserData.CHROME);
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
    public void authorizationTest() throws BrowserNotSupportedException {
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
        waiters.waitElementVisible(By.cssSelector(".sc-mrx253-0.enxKCy.sc-945rct-0.iOoJwQ"));

    }

    private void clearAndEnter(By by, String text) {
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(text);
    }

    private void enterLK() {
        actions = new Actions(driver);

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
////        Выбор страны
//        driver.findElement(By.cssSelector(".select.lk-cv-block__input.lk-cv-block__input_full.js-lk-cv-dependent-master.js-lk-cv-custom-select")).click();
//        driver.findElement(By.cssSelector("button[title='Россия']")).click();
//        WebElement cityDropdownElement = driver.findElement(By.cssSelector(".select.lk-cv-block__input.lk-cv-block__input_full.js-lk-cv-dependent-slave-city.js-lk-cv-custom-select"));
//        waiters.waitForCondition(ExpectedConditions.not(ExpectedConditions.attributeToBe(cityDropdownElement,"disabled","disabled")));

//        Имя, фамилия
        clearAndEnter(By.id("id_lname"), "Куликова");
        clearAndEnter(By.id("id_fname_latin"), "Alexandra");
        clearAndEnter(By.id("id_lname_latin"), "Kulikova");
        clearAndEnter(By.id("id_blog_name"), "Sandre");

//               День рождения
        String date = expectedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        clearAndEnter(By.name("date_of_birth"), date);

//        Выбор страны
        driver.findElement(By.cssSelector(".select.lk-cv-block__input.lk-cv-block__input_full.js-lk-cv-dependent-master.js-lk-cv-custom-select")).click();
        driver.findElement(By.cssSelector("button[title='Россия']")).click();
        cityWaiters = new Waiters(driver,1);
        WebElement cityDropdownElement = driver.findElement(By.cssSelector(".select.lk-cv-block__input.lk-cv-block__input_full.js-lk-cv-dependent-slave-city.js-lk-cv-custom-select"));
        cityWaiters.waitForCondition(ExpectedConditions.attributeToBe(cityDropdownElement,"disabled","disabled"));
        cityWaiters.waitForCondition(ExpectedConditions.not(ExpectedConditions.attributeToBe(cityDropdownElement,"disabled","disabled")));

//        Выбор города
        cityDropdownElement.click();
        driver.findElement(By.xpath("//button[@data-value='61']")).click();

//        Уровень английского
        //input[@data-title='Уровень знания английского языка']/ancestor::label/ancestor::div[contains(@class,'select lk-cv-block__input lk-cv-block__input_full js-lk-cv-custom-select')]
        driver.findElement(By.xpath("//input[@data-title='Уровень знания английского языка']/ancestor::label/ancestor::div[contains(@class,'select lk-cv-block__input lk-cv-block__input_full js-lk-cv-custom-select')]")).click();
        //driver.findElement(By.xpath("//input[@data-title='Уровень знания английского языка']/../..")).click();
        driver.findElement(By.xpath("//button[contains(@title,'Начальный уровень (Beginner)')]")).click();

//       Готовность к переезду
        WebElement elMoving = driver.findElement(By.xpath("//input[contains(@id,'id_ready_to_relocate_1')]"));
        if (!elMoving.isSelected()) {
            driver.findElement(By.xpath("//span[contains(text(),'Да')]")).click();
        }

//             Полный день
        WebElement elFullDay = driver.findElement(By.xpath("//input[@title='Полный день']"));
        if (!elFullDay.isSelected()) {
            driver.findElement(By.xpath("//span[contains(text(), 'Полный день')]")).click();
        }

//              Гибкий график
        WebElement elflexiblesSchedule = driver.findElement(By.xpath("//input[@title = 'Гибкий график']"));
        if (!elflexiblesSchedule.isSelected()) {
            driver.findElement(By.xpath("//span[contains(text(), 'Гибкий график')]")).click();
        }

//         Удаленно
        WebElement elDist = driver.findElement(By.xpath("//input[@title = 'Удаленно']"));
        if (!elDist.isSelected()) {
            driver.findElement(By.xpath("//span[contains(text(), 'Удаленно')]")).click();

        }
        log.info("Добавление пользовательских данных");
    }

    private void addContacts() {
        deleteContacts();
        driver.findElement(By.xpath("//span[@class='placeholder']")).click();
        driver.findElement(By.cssSelector("div[class='lk-cv-block__select-options lk-cv-block__select-options_left js-custom-select-options-container'] button[title='VK']")).click();
        clearAndEnter(By.id("id_contact-0-value"), "https://vk.com/kulikova_alex");
        driver.findElement(By.xpath("//button[contains(text(),'Добавить')]")).click();
        driver.findElement(By.xpath("//span[@class='placeholder']")).click();
        driver.findElement(By.cssSelector("div[class='lk-cv-block__select-options lk-cv-block__select-options_left js-custom-select-options-container'] button[title='Тelegram']")).click();
        clearAndEnter(By.id("id_contact-1-value"), "@Sandre");
        log.info("Добавление основной информации");
    }

    private void deleteContacts() {
        int i = 1;
        do {
            String strSelector = "div.js-formset-row:nth-child(" + i + ") > div:nth-child(4) > div:nth-child(2) > button:nth-child(1)";
//        log.info("  Контакты для удаления найдены =  ");
//        log.info(strSelector);
            if (!isDisplayed(By.cssSelector(strSelector))) {
                break;
            } else {
//            log.info(" Отображаются контакты =  ");
//            log.info(isDisplayed(By.cssSelector(strSelector)));
                driver.findElement(By.cssSelector(strSelector)).click();
            }
            i++;
        } while (i < 20);
        driver.findElement(By.xpath("//button[contains(@title,'Сохранить и продолжить')]")).submit();
        driver.findElement(By.xpath("//div[@class='nav-sidebar']//a[@title='Персональные данные']")).click();

    }

    boolean isDisplayed(By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

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
        deleteDevelopmentExperience();
        driver.findElement(By.cssSelector("a[title='Добавить']")).click();
        driver.findElement(By.cssSelector("#id_experience-0-experience")).click();
        driver.findElement(By.cssSelector("#id_experience-0-experience > option:nth-child(3)")).click();
        driver.findElement(By.cssSelector("#id_experience-0-level > option:nth-child(1)")).click();
        log.info("Добавление информации об опыте разработки");
//        Нажать сохранить
        driver.findElement(By.cssSelector("button[title=\"Сохранить и заполнить позже\"]")).submit();
        log.info("Сохранение страницы");
    }
    private void deleteDevelopmentExperience() {
        int i = 1;
        do {
            String strSelector = "div:nth-child(" + i + ") > div.experience-row__remove.ic-close.js-formset-delete";
//        log.info("  Контакты для удаления найдены =  ");
//        log.info(strSelector);
            if (!isDisplayed(By.cssSelector(strSelector))) {
                break;
            } else {
//            log.info(" Отображаются контакты =  ");
//            log.info(isDisplayed(By.cssSelector(strSelector)));
                driver.findElement(By.cssSelector(strSelector)).click();
            }
            i++;
        } while (i < 20);
        driver.findElement(By.xpath("//button[contains(@title,'Сохранить и продолжить')]")).submit();
        driver.findElement(By.xpath("//div[@class='nav-sidebar']//a[@title='Персональные данные']")).click();
    }
    private void assertionsLK() {
        Assertions.assertEquals("Александра", driver.findElement(By.id("id_fname")).getAttribute("value"));
        Assertions.assertEquals("Куликова", driver.findElement(By.id("id_lname")).getAttribute("value"));
        Assertions.assertEquals("Alexandra", driver.findElement(By.id("id_fname_latin")).getAttribute("value"));
        Assertions.assertEquals("Kulikova", driver.findElement(By.id("id_lname_latin")).getAttribute("value"));
        Assertions.assertEquals("Sandre", driver.findElement(By.id("id_blog_name")).getAttribute("value"));

        String actual =  driver.findElement(By.name("date_of_birth")).getAttribute("value");
        LocalDate actualData = LocalDate.parse(actual, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        Assertions.assertTrue(expectedDate.isEqual(actualData),"Дата рождения не совпадает");
        //Assertions.assertEquals("10.10.1990", driver.findElement(By.name("date_of_birth")).getAttribute("value"));
        Assertions.assertEquals("Россия", driver.findElement(By.xpath("//button[contains(@title, 'Россия')]"))
                .getAttribute("title"), "Страна не совпадает");
        Assertions.assertEquals("Астрахань", driver.findElement(By.xpath("//button[contains(@title, 'Астрахань')]"))
                .getAttribute("title"), "Город не совпадает");
        Assertions.assertEquals("Начальный уровень (Beginner)", driver.findElement(By.xpath("//button[contains(@title,'Начальный уровень (Beginner)')]"))
                .getAttribute("title"));
        Assertions.assertTrue(driver.findElement(By.xpath("//input[contains(@id,'id_ready_to_relocate_1')]")).isSelected(), "Готовность к переезду не совпадает");
        Assertions.assertTrue(driver.findElement(By.xpath("//input[@title='Полный день']")).isSelected(), "Полный день не совпадает");
        Assertions.assertTrue(driver.findElement(By.xpath("//input[@title = 'Гибкий график']")).isSelected(), "Гибкий график не совпадает");
        Assertions.assertTrue(driver.findElement(By.xpath("//input[@title = 'Удаленно']")).isSelected(), "Удаленно не совпадает");
        Assertions.assertEquals("yadaci9405@othao.com", driver.findElement(By.xpath("//input[contains(@name,'email')]")).getAttribute("value"), "Email не совпадает");
        Assertions.assertEquals("yadaci9405@othao.com", driver.findElement(By.xpath("//input[contains(@name,'email')]")).getAttribute("value"), "Email не совпадает");

        Assertions.assertTrue(
                "https://vk.com/kulikova_alex".equals(driver.findElement(By.id("id_contact-0-value")).getAttribute("value")) ||
                        "https://vk.com/kulikova_alex".equals(driver.findElement(By.id("id_contact-1-value")).getAttribute("value")), "Контактная информация указанна неверно");
        Assertions.assertTrue(
                "@Sandre".equals(driver.findElement(By.id("id_contact-0-value")).getAttribute("value")) ||
                        "@Sandre".equals(driver.findElement(By.id("id_contact-1-value")).getAttribute("value")), "Контактная информация указанна неверно");
        Assertions.assertEquals("f", driver.findElement(By.xpath("//option[@value='f']")).getAttribute("value"), "Пол не совпадает");
        Assertions.assertEquals("MaxFactor", driver.findElement(By.id("id_company")).getAttribute("value"), "Компания не совпадает");
        Assertions.assertEquals("Team lead", driver.findElement(By.id("id_work")).getAttribute("value"), "Должность не совпадает");
        Assertions.assertTrue(driver.findElement(By.cssSelector("#id_experience-0-experience > option:nth-child(3)")).isSelected(), "Язык не совпадает");
        Assertions.assertEquals("Только начал", driver.findElement(By.cssSelector("#id_experience-0-level > option:nth-child(1)")).getText(), "Опыт не совпадает");
        log.info("В разделе о себе отображаются указанные ранее данные");
    }
}

