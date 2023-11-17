package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobject.AbsPageObject;

public abstract class AbsBasePage extends AbsPageObject {
    private final String BASE_URL = System.getProperty("base.url", "https://otus.ru");
    private String path = "";
    public AbsBasePage(WebDriver driver,String path) {
        super(driver);
        this.path = path;
    }
//    //@FindBy(xpath = "//div[./h2[text()='Авторские онлайн‑курсы для профессионалов']]")
//    @FindBy(tagName = "h1")
//   private WebElement header;
//
//    public void pageHeaderShouldBeSameAs(String header) {
//        Assertions.assertEquals(header,this.header.getText(),String.format("Заголовок должен быть %s",header));
//    }

    private String normilizBaseUrl() {
        return  BASE_URL.endsWith("/") ? BASE_URL.replaceAll("/$",""):BASE_URL;
    }
  public void open() {
      driver.get(normilizBaseUrl()+path);
  }
    String selectorId = "#id_%s";
    public WebElement setSelector(String string) {
        String elementsId = String.format(this.selectorId, string);
        WebElement element = driver.findElement(By.cssSelector(elementsId));
        return element;
    }
    String locator = "//button[contains(@title,'%s')]";

    public WebElement setLocator(String string) {
        String elementsId = String.format(this.locator, string);
        WebElement element = driver.findElement(By.xpath(elementsId));
        return element;
    }


}
