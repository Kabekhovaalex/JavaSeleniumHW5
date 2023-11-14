package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends AbsBasePage{
    public MainPage(WebDriver driver) {
        super(driver,"/");
    }

    @FindBy(css = ".sc-mrx253-0.enxKCy.sc-945rct-0.iOoJwQ")
    private WebElement buttonEnter;


    public void clickButtonEnter() {
        waiters.waitElementVisible(buttonEnter);
        buttonEnter.click();
        new LoginPage(driver);

    }
}
