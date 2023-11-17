package pages;

import data.ContactInformationData.SocialNetworkCommunData;
import data.OtherData.WorkData;
import data.basicInformationData.CityData;
import data.basicInformationData.ContryData;
import data.basicInformationData.EnglishLevelData;
import data.personalData.fioData;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.InformationAboutStudent.ProfileStudentConst;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static pages.InformationAboutStudent.ProfileStudentConst.EXPECTED_DATE;

public class ProfilePage extends AbsBasePage {
    public ProfilePage(WebDriver driver) {
        super(driver, "/");
    }

    @FindBy(xpath = "//input[contains(@name, 'date_of_birth')]")
    private WebElement birthday;
    String date = EXPECTED_DATE.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    @FindBy(xpath = "//input[@name= 'country']/following::div[1]")
    private WebElement countryInfo;
    @FindBy(xpath = "//input[@data-title= 'Город']/following::div[1]")
    private WebElement cityInfo;
    @FindBy(xpath = "//input[@data-title='Уровень знания английского языка']/ancestor::label/ancestor::div[contains(@class,'select lk-cv-block__input lk-cv-block__input_full js-lk-cv-custom-select')]")
    private WebElement englishLevelInfo;
    @FindBy(xpath = "//input[contains(@id,'id_ready_to_relocate_1')]")
    private WebElement readyRelocate;
    @FindBy(xpath = "//span[contains(text(),'Да')]")
    private WebElement readyRelocateYes;
    @FindBy(xpath = "//input[@title='Полный день']")
    private WebElement elFullDay;
    @FindBy(xpath = "//span[contains(text(), 'Полный день')]")
    private WebElement elFullDayClick;
    @FindBy(xpath = "//input[@title = 'Гибкий график']")
    private WebElement elflexiblesSchedule;
    @FindBy(xpath = "//span[contains(text(), 'Гибкий график')]")
    private WebElement elflexiblesScheduleClick;
    @FindBy(xpath = "//input[@title = 'Удаленно']")
    private WebElement elDist;
    @FindBy(xpath = "//span[contains(text(), 'Удаленно')]")
    private WebElement elDistClick;
    @FindBy(xpath = "//button[contains(@title,'Сохранить и продолжить')]")
    private WebElement SaveAndContinue;
    @FindBy(xpath = "//div[@class='nav-sidebar']//a[@title='Персональные данные']")
    private WebElement PersonalData;
    @FindBy(xpath = "//span[@class='placeholder']")
    private WebElement listContact;
    @FindBy(xpath = "(//button[@data-value='vk'])[last()]")
    private WebElement listContactVK;

    @FindBy(xpath = "(//button[@data-value='telegram'])[last()]")
    private WebElement listContactTelegram;

    @FindBy(xpath = "//button[contains(text(),'Добавить')]")
    private WebElement buttonAdd;

    @FindBy(xpath = "//option[contains(@value, 'f')]")
    private WebElement gender;
    @FindBy(css = "a[title='Добавить']")
    private WebElement addButtonDevelop;

    @FindBy(css = "#id_experience-0-experience")
    private WebElement languageDevelopField;
    @FindBy(css = "#id_experience-0-experience > option:nth-child(3)")
    private WebElement timeDevelopField;

    @FindBy(xpath = "//option[contains(text(), 'Только начал')]")
    private WebElement timeDevelop;
    @FindBy(css = "button[title='Сохранить и заполнить позже']")
    private WebElement SaveAndFillLater;



    public void addPersonalInformation() {
        //Имя
        helper.clearAndEnter(setSelector(fioData.FNAME.getFioName()), ProfileStudentConst.FNAME);
        helper.clearAndEnter(setSelector(fioData.LNAME.getFioName()), ProfileStudentConst.LNAME);
        helper.clearAndEnter(setSelector(fioData.FNAME_LATIN.getFioName()), ProfileStudentConst.FNAME_LATIN);
        helper.clearAndEnter(setSelector(fioData.LNAME_LATIN.getFioName()), ProfileStudentConst.LNAME_LATIN);
        helper.clearAndEnter(setSelector(fioData.BLOG_NAME.getFioName()), ProfileStudentConst.BLOG_NAME);
        helper.clearAndEnter(birthday, date);

        countryInfo.click(); //Выбор страны
        setLocator(ContryData.RUSSIA.getCountryName()).click();
        waiters.waitForCondition(ExpectedConditions.attributeToBe(cityInfo, "disabled", "disabled"));
        waiters.waitForCondition(ExpectedConditions.not(ExpectedConditions.attributeToBe(cityInfo, "disabled", "disabled")));

//        Выбор города
        cityInfo.click();
        setLocator(CityData.ASTRAKHAN.getCityName()).click();

//        Уровень английского
        englishLevelInfo.click();
        waiters.waitElementVisible(setLocator(EnglishLevelData.BEGINNER.getNameEnglishLevel()));
        setLocator(EnglishLevelData.BEGINNER.getNameEnglishLevel()).click();

//       Готовность к переезду
        choiceRadioButton();
//        График работы
        clickCheckBox(elFullDay, elFullDayClick);
        clickCheckBox(elflexiblesSchedule, elflexiblesScheduleClick);
        clickCheckBox(elDist, elDistClick);
        log.info("Добавление пользовательских данных");

        deleteContacts();
        listContact.click();
        listContactVK.click();
        helper.clearAndEnter(setSelector(SocialNetworkCommunData.VK.getNameSocialNetworkCommun()), ProfileStudentConst.CONTACT_VK);
        buttonAdd.click();
        listContact.click();
        listContactTelegram.click();
        helper.clearAndEnter(setSelector(SocialNetworkCommunData.TELEGRAM.getNameSocialNetworkCommun()), ProfileStudentConst.CONTACT_TG);
        log.info("Добавление основной информации");

        //       Другое
        setSelector(fioData.GENDER.getFioName()).click();
        gender.click();
        helper.clearAndEnter(setSelector(WorkData.COMPANY.getNameWork()), ProfileStudentConst.COMPANY);
        helper.clearAndEnter(setSelector(WorkData.JOB.getNameWork()), ProfileStudentConst.POSITION);
        log.info("Добавление другой информации");

//        Опыт разработки
        deleteDevelopmentExperience();
        addButtonDevelop.click();
        languageDevelopField.click();
        timeDevelopField.click();
        timeDevelop.click();
        log.info("Добавление информации об опыте разработки");
//        Нажать сохранить
        SaveAndFillLater.submit();
        log.info("Сохранение страницы");
    }

    public void choiceRadioButton() {
        if (!readyRelocateYes.isSelected()) {
            readyRelocateYes.click();
        }
    }

    public void clickCheckBox(WebElement el, WebElement elClick) {
        if (!el.isSelected()) {
            elClick.click();
        }
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
        SaveAndContinue.submit();
        PersonalData.click();

    }

    boolean isDisplayed(By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
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
        SaveAndContinue.submit();
        PersonalData.click();
    }
    public void assertField(String actual, String expected, String message) {
        Assertions.assertEquals(expected,actual,message);

    }
  public void assertBirthDay(LocalDate date) {
    LocalDate actualData = LocalDate.parse(birthday.getAttribute("value"), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    Assertions.assertTrue(date.isEqual(actualData),"Дата рождения не совпадает");
}
}


