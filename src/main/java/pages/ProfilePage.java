package pages;

import data.personalData.fioData;
import helper.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.InformationAboutStudent.ProfileStudentConst;
import waiters.Waiters;

import java.time.format.DateTimeFormatter;

import static pages.InformationAboutStudent.ProfileStudentConst.EXPECTED_DATE;

public class ProfilePage extends AbsBasePage {
    public ProfilePage(WebDriver driver) {
        super(driver, "/");
    }
    @FindBy(xpath = "//input[contains(@name, 'date_of_birth')]")
    private WebElement birthday;

    String date = EXPECTED_DATE.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    public void addPersonalInformation() {
        //Имя
        helper.clearAndEnter(setSelector(fioData.FNAME.getFioName()),ProfileStudentConst.FNAME);
        helper.clearAndEnter(setSelector(fioData.LNAME.getFioName()),ProfileStudentConst.LNAME);
        helper.clearAndEnter(setSelector(fioData.FNAME_LATIN.getFioName()),ProfileStudentConst.FNAME_LATIN);
        helper.clearAndEnter(setSelector(fioData.LNAME_LATIN.getFioName()),ProfileStudentConst.LNAME_LATIN);
        helper.clearAndEnter(setSelector(fioData.BLOG_NAME.getFioName()),ProfileStudentConst.BLOG_NAME);
        helper.clearAndEnter(birthday,date);

//
////               День рождения
//
//        clearAndEnter(By.name("date_of_birth"), date);
//
////        Выбор страны
//        driver.findElement(By.cssSelector(".select.lk-cv-block__input.lk-cv-block__input_full.js-lk-cv-dependent-master.js-lk-cv-custom-select")).click();
//        driver.findElement(By.cssSelector("button[title='Россия']")).click();
//        cityWaiters = new Waiters(driver,1);
//        WebElement cityDropdownElement = driver.findElement(By.cssSelector(".select.lk-cv-block__input.lk-cv-block__input_full.js-lk-cv-dependent-slave-city.js-lk-cv-custom-select"));
//        cityWaiters.waitForCondition(ExpectedConditions.attributeToBe(cityDropdownElement,"disabled","disabled"));
//        cityWaiters.waitForCondition(ExpectedConditions.not(ExpectedConditions.attributeToBe(cityDropdownElement,"disabled","disabled")));
//
////        Выбор города
//        cityDropdownElement.click();
//        driver.findElement(By.xpath("//button[@data-value='61']")).click();
//
////        Уровень английского
//        //input[@data-title='Уровень знания английского языка']/ancestor::label/ancestor::div[contains(@class,'select lk-cv-block__input lk-cv-block__input_full js-lk-cv-custom-select')]
//        driver.findElement(By.xpath("//input[@data-title='Уровень знания английского языка']/ancestor::label/ancestor::div[contains(@class,'select lk-cv-block__input lk-cv-block__input_full js-lk-cv-custom-select')]")).click();
//        //driver.findElement(By.xpath("//input[@data-title='Уровень знания английского языка']/../..")).click();
//        driver.findElement(By.xpath("//button[contains(@title,'Начальный уровень (Beginner)')]")).click();
//
////       Готовность к переезду
//        WebElement elMoving = driver.findElement(By.xpath("//input[contains(@id,'id_ready_to_relocate_1')]"));
//        if (!elMoving.isSelected()) {
//            driver.findElement(By.xpath("//span[contains(text(),'Да')]")).click();
//        }
//
////             Полный день
//        WebElement elFullDay = driver.findElement(By.xpath("//input[@title='Полный день']"));
//        if (!elFullDay.isSelected()) {
//            driver.findElement(By.xpath("//span[contains(text(), 'Полный день')]")).click();
//        }
//
////              Гибкий график
//        WebElement elflexiblesSchedule = driver.findElement(By.xpath("//input[@title = 'Гибкий график']"));
//        if (!elflexiblesSchedule.isSelected()) {
//            driver.findElement(By.xpath("//span[contains(text(), 'Гибкий график')]")).click();
//        }
//
////         Удаленно
//        WebElement elDist = driver.findElement(By.xpath("//input[@title = 'Удаленно']"));
//        if (!elDist.isSelected()) {
//            driver.findElement(By.xpath("//span[contains(text(), 'Удаленно')]")).click();
//
//        }
//        log.info("Добавление пользовательских данных");
    }
}
