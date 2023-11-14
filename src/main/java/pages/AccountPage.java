package pages;

import io.netty.channel.AdaptiveRecvByteBufAllocator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends AbsBasePage {
    public AccountPage(WebDriver driver) {
        super(driver,"/");
    }

    @FindBy(css = ".sc-199a3eq-0.fJMWHf")
    private WebElement nameProfile;
    @FindBy(xpath="//a[contains(text(), 'Мой профиль')]")
    private WebElement elProfile;


    public void enterLK() {
        waiters.waitElementVisible(nameProfile);
        actions.moveToElement(nameProfile).perform();
        waiters.waitElementVisible(elProfile);
        elProfile.click();
        log.info("Вход в Мой Профиль");
        new ProfilePage(driver);
    }
}
