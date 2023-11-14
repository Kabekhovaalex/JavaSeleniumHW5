package pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbsBasePage {

    //    private final String LOGIN = System.getProperty("login");
//    private final String PASSWORD = System.getProperty("password");

    //mvn clean test  -Dlogin="yadaci9405@othao.com" -Dpassword=!Yadaci9405
    private String LOGIN = "yadaci9405@othao.com";
    private String PASSWORD = "!Yadaci9405";

    public LoginPage(WebDriver driver) {
        super(driver,"/");
    }
    @FindBy(css=".hGvqzc > .sc-1ij08sq-0")
    private WebElement loginField;
    @FindBy(xpath = "//input[contains(@name,'email')]")
    private WebElement userName;
    @FindBy(xpath = "//input[contains(@type,'password')]")
    private WebElement userPassword;
    @FindBy(css=".sc-177u1yy-0")
    private WebElement passwordField;
    @FindBy(xpath = "//div[contains(text(),'Войти')]")
    private WebElement buttonLogin;
    public void loginInOtus() {
        loginField.click();
        helper.clearAndEnter(userName, LOGIN);
        //userName.sendKeys(LOGIN);
        passwordField.click();
        helper.clearAndEnter(userPassword, PASSWORD);
        buttonLogin.click();
        log.info("Авторизация на сайте");
        new AccountPage(driver);
    }
}

