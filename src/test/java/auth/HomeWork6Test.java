package auth;

import data.BrowserData;
import data.OtherData.WorkData;
import data.basicInformationData.CityData;
import data.basicInformationData.ContryData;
import data.basicInformationData.EnglishLevelData;
import data.personalData.fioData;
import exception.BrowserNotSupportedException;
import factory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.AccountPage;
import pages.InformationAboutStudent.ProfileStudentConst;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;

import java.time.LocalDate;

import static pages.InformationAboutStudent.ProfileStudentConst.EXPECTED_DATE;

public class HomeWork6Test {
    private WebDriver driver;
    public org.apache.logging.log4j.Logger log = LogManager.getLogger(helper.Logger.class);

    private MainPage mainPage;
    private LoginPage loginPage;
    private AccountPage accountPage;
    private ProfilePage profilePage;



    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void start() throws BrowserNotSupportedException {

        this.driver = new WebDriverFactory().create(BrowserData.CHROME);
        log.info("Открытие Chrome");
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        accountPage = new AccountPage(driver);
        profilePage = new ProfilePage(driver);

    }

    @AfterEach
    public void shutdown() {
        if (driver != null) {
            driver.quit();
            log.info("Закрытие драйвера");
        }
    }

   @Test
    public  void PageAboutMyself () throws BrowserNotSupportedException {
       log.info("Открытие сайта");
       mainPage.open();
       log.info("Открытие окна для ввода логин/пароль");
       mainPage.clickButtonEnter();
       log.info("Вход в личный кабинет");
       loginPage.loginInOtus();
       log.info("Переходим в раздел О себе");
       accountPage.enterLK();
       log.info("Заполняем профиль данными студента");
       profilePage.addPersonalInformation();
       shutdown();
       start();
       log.info("Открытие сайта");
       mainPage.open();
       log.info("Открытие окна для ввода логин/пароль");
       mainPage.clickButtonEnter();
       log.info("Вход в личный кабинет");
       loginPage.loginInOtus();
       log.info("Переходим в раздел О себе");
       accountPage.enterLK();
       log.info("Осуществляем проверку введенных данных");
       profilePage.assertField(ProfileStudentConst.FNAME,(profilePage.setSelector(fioData.FNAME.getFioName())).getAttribute("value"),
       "Имя не совпадает");
       profilePage.assertField(ProfileStudentConst.FNAME_LATIN,(profilePage.setSelector(fioData.FNAME_LATIN.getFioName())).getAttribute("value"),
               "Имя на латинице не совпадает");
       profilePage.assertField(ProfileStudentConst.LNAME,(profilePage.setSelector(fioData.LNAME.getFioName())).getAttribute("value"),
               "Фамилия не совпадает");
       profilePage.assertField(ProfileStudentConst.LNAME_LATIN,(profilePage.setSelector(fioData.LNAME_LATIN.getFioName())).getAttribute("value"),
               "Фамилия на латинице не совпадает");
       profilePage.assertField(ProfileStudentConst.BLOG_NAME,(profilePage.setSelector(fioData.BLOG_NAME.getFioName())).getAttribute("value"),
               "Имя блога не совпадает");
       profilePage.assertBirthDay(EXPECTED_DATE);

       profilePage.assertField(ContryData.RUSSIA.getCountryName(),(profilePage.setLocator(ContryData.RUSSIA.getCountryName())).getAttribute("title"),
               "Страна не совпадает");
       profilePage.assertField(CityData.ASTRAKHAN.getCityName(),(profilePage.setLocator(CityData.ASTRAKHAN.getCityName())).getAttribute("title"),
               "Город не совпадает");
       profilePage.assertField(EnglishLevelData.BEGINNER.getNameEnglishLevel(), (profilePage.setLocator(EnglishLevelData.BEGINNER.getNameEnglishLevel())).getAttribute("title"),
               "Уровень английского не совпадает");

       profilePage.assertField(ProfileStudentConst.COMPANY,(profilePage.setSelector(WorkData.COMPANY.getNameWork())).getAttribute("value"),
               "Компания не совпадает");
       profilePage.assertField(ProfileStudentConst.POSITION,(profilePage.setSelector(WorkData.JOB.getNameWork())).getAttribute("value"),
               "Должность не совпадает");

   }
}
