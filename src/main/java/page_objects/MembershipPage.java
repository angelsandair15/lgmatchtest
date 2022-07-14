package page_objects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MembershipPage {

    public WebDriver driver;

    public MembershipPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//i[@class='fas fa-angle-left']")
    public WebElement leftArrowButton;

    @FindBy(xpath = "//h2[normalize-space()='30+ Courses video library FREE ACCESS']")
    public WebElement coursesLibrary;
    @FindBy(xpath = "//a[@href='https://www.selenium-tutorial.com/p/automation-architect-in-selenium-7-live-projects']/span")
    public WebElement automationArchitechGetStarted;

    public void selectAutomationArchitectCourse() throws InterruptedException {
        while (true) {
            try {
                leftArrowButton.click();
                automationArchitechGetStarted.click();
                break;
            } catch (Exception e) {
                System.out.println("Course not yet displayed!");
            }
        }

    }

    public void scrollToCourses() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(coursesLibrary));
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].scrollIntoView(true);",coursesLibrary);
    }

}
