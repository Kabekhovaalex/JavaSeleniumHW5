package pageobject;

import helper.Helper;
import io.netty.resolver.InetNameResolver;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import waiters.Waiters;

public abstract class AbsPageObject {
    protected WebDriver driver;
    protected Actions actions;
    protected Waiters waiters;
    protected Helper helper;
    public org.apache.logging.log4j.Logger log = LogManager.getLogger(helper.Logger.class);

    public AbsPageObject(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.waiters = new Waiters (driver,10);
        this.helper = new Helper(driver,actions);
        PageFactory.initElements(driver,this);
    }


}
