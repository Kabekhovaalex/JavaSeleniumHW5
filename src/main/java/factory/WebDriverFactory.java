package factory;
import data.BrowserData;
import exception.BrowserNotSupportedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Locale;

public class WebDriverFactory {

    private  String browserName  = System.getProperty("browser.name","chrome");



    public WebDriver create(BrowserData chrome) throws BrowserNotSupportedException {
        switch (BrowserData.valueOf(browserName .trim().toUpperCase(Locale.ROOT))) {
            case CHROME :
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                return new ChromeDriver(chromeOptions);

            default:
            throw new BrowserNotSupportedException(browserName);
            }
        }
    }

