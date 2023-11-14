package auth;

import data.BrowserData;
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
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;

public class Authorization2Test {
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

//    @AfterEach
//    public void shutdown() {
//        if (driver != null) {
//            driver.quit();
//            log.info("Закрытие драйвера");
//        }
//    }

   @Test
    public  void PageAboutMyself () {
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
       //new MainMenu(driver).moveCursir(MainDta.Courses).pageHeaderShouldBeSameAs(QACourceDATA.QAlead.getName())
   }
}
