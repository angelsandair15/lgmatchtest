package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CoursesPage {

    public WebDriver driver;

    public CoursesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[normalize-space()='All Courses']")
    public WebElement allCoursesButton;

    @FindBy(xpath = "//input[@id='search-courses']")
    public WebElement searchCourseTextBox;

    @FindBy(xpath = "//i[@title='Search']")
    public WebElement searchButton;

    @FindBy(xpath = "//img[@role='presentation']")
    public WebElement courseImage;

    @FindBy(xpath = "(//div[@class='btn-primary btn-sm pull-right lecture-start'][contains(text(),'Start')])[1]")
    public WebElement startButton;

    @FindBy(xpath = "//h1[normalize-space()='Automation Architect in Selenium - 7 Live Projects']")
    public WebElement courseTitleAutomationSelenium;

    @FindBy(xpath = "//h1[contains(text(),'Cucumber BDD for Selenium & Appium with Live Proje')]")
    public WebElement courseTitleCucumber;

    @FindBy(xpath = "//h2[contains(text(),'Cucumber BDD for Selenium & Appium with Live Proje')]")
    public WebElement courseTitleCucumberh2;

    @FindBy(xpath = "//label[contains(@class,'btn checkout-button-group product_1310108')]//h3[@class='product-name']")
    public WebElement britishPounds;

    @FindBy(xpath = "//div/h3[@class='product-name'][contains(text(),'Pay in British Pounds')]/../following-sibling::div/span[1]")
    public WebElement courseValue;

    @FindBy(xpath = "//button[@id='enroll-button']")
    public WebElement enrollInCourseButton;

    public void searchCourse(String Course) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(searchCourseTextBox));
        searchCourseTextBox.sendKeys(Course);
        searchButton.click();
    }

    public void clickStartButton() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(startButton));
        startButton.click();
    }

    public void clickImageCourse() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(courseImage));
        courseImage.click();
    }

    public void waitForCucumberCourseToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(courseTitleCucumber));
    }

    public void waitForCucumberCourseToLoad2() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(courseTitleCucumberh2));
    }

    public void waitForAutomationSeleniumCourseToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(courseTitleAutomationSelenium));
    }

    public void selectPayInBritistPounds() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(britishPounds));
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].scrollIntoView(true);", britishPounds);
        js.executeScript("arguments[0].click();", britishPounds);

    }

    public int checkButtonIsDisabled() {
        List<WebElement> button = driver.findElements(By.xpath("//button[@id='enroll-button']"));
        return button.size();

    }


}
