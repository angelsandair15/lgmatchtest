package Tests;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page_objects.*;
import resources.base;

public class Legal_Match_Exam extends base {

    @BeforeClass
    public void InitializeDriver() throws Exception {

        driver = initializeDriver();
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(resourcesPath + File.separator + dataprops);
        prop.load(fis);
        String url = prop.getProperty("URL");
        driver.get(url);

    }

    //www.way2automation.com/
    @Test(alwaysRun = false)
    public void ActionNames_Test() throws InterruptedException {
        HomePage HPage = new HomePage(driver);
        JSONObject obj = new JSONObject();
        List<String> list = new ArrayList<String>();
        for (int i = 2; i <= HPage.categoryNames.size(); i++) {

            list.clear();
            String categoriesXpath = "(//h1[@class='heading'])[" + i + "]";
            String categories = driver.findElement(By.xpath(categoriesXpath)).getText();
            String actions = categoriesXpath + "/../ul/li/a/h2";
            for (int y = 1; y <= driver.findElements(By.xpath(actions)).size(); y++) {
                String actionNames = driver.findElement(By.xpath("(" + actions + ")[" + y + "]")).getText();
                list.add(actionNames);
            }
            JSONArray array = new JSONArray();
            for (int x = 0; x < list.size(); x++) {
                array.put(list.get(x));
            }
            try {
                obj.put(categories, array);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        System.out.println(obj);
    }

    @Test(dependsOnMethods = "ActionNames_Test")
    public void Register_Test() throws Exception {
        HomePage HPage = new HomePage(driver);
        RegistrationPage RPage = new RegistrationPage(driver);
        String newURL = HPage.getSubmitButtonClickedURL();
        System.out.println(newURL);
        driver.get(newURL);
        RPage.name.sendKeys("Tester");
        RPage.phone.sendKeys("123456789");
        RPage.email.sendKeys("dased@boximail.com");
        RPage.selectCountry("Philippines");
        RPage.city.sendKeys("Manila");
        RPage.username.sendKeys("tester01");
        RPage.password.sendKeys("TesterPassword");
        RPage.submitButton.click();
        RPage.exploreLifeTime.click();
    }

    @Test(dependsOnMethods = "Register_Test")
    public void Enroll_Course_Test() throws Exception {
        driver.get("https://www.way2automation.com/lifetime-membership-club/");
        MembershipPage MPage = new MembershipPage(driver);
        MPage.scrollToCourses();
        MPage.selectAutomationArchitectCourse();

        CoursesPage CPage = new CoursesPage(driver);
        CPage.waitForAutomationSeleniumCourseToLoad();
        String courseURL = driver.getCurrentUrl();
        Assert.assertEquals(courseURL, "https://www.selenium-tutorial.com/p/automation-architect-in-selenium-7-live-projects");
        CPage.allCoursesButton.click();
        CPage.searchCourse("Cucumber BDD for Selenium");
        Thread.sleep(2000);
        CPage.clickImageCourse();
        CPage.waitForCucumberCourseToLoad();
        CPage.clickStartButton();
        CPage.waitForCucumberCourseToLoad2();
        driver.get(courseURL);
        CPage.selectPayInBritistPounds();
        Assert.assertEquals(CPage.courseValue.getText(), "£15");
        CPage.enrollInCourseButton.click();
        Thread.sleep(500);
        Assert.assertEquals(CPage.checkButtonIsDisabled(),0);
    }

    @Test(dependsOnMethods = "Enroll_Course_Test")
    public void Order_Summary_Test() throws Exception {
        OrderSummaryPage OPage = new OrderSummaryPage(driver);
        OPage.assertOrderSummaryPage();
        OPage.clickEmailAdd();
        OPage.name.click();
        OPage.checkFieldError("1");
        OPage.nameOnCard.click();
        OPage.checkFieldError("2");
        driver.switchTo().frame(OPage.cardNumberIframe);
        OPage.cardNumber.click();
        driver.switchTo().defaultContent();
        OPage.checkFieldError("3");
        driver.switchTo().frame(OPage.expirationDateIframe);
        OPage.expirationDate.click();
        driver.switchTo().defaultContent();
        OPage.checkFieldError("4");
        driver.switchTo().frame(OPage.cvcIframe);
        OPage.cvcCode.click();
        driver.switchTo().defaultContent();
        OPage.checkFieldError("5");
        OPage.streetAddress.click();
        OPage.checkFieldError("6");
        OPage.city.click();
        OPage.checkFieldError("7");
        OPage.postalCode.click();
        OPage.checkFieldError("8");
        OPage.city.click();
        OPage.checkFieldError("9");
        OPage.scrollToOrderSummaryTxt();
        getScreenshot();
    }


    @AfterClass(alwaysRun = true)
    public void reset() {
        driver.quit();
    }

}
