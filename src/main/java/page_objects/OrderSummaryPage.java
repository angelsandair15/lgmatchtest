package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class OrderSummaryPage {

    public WebDriver driver;

    public OrderSummaryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='email']")
    public WebElement emailAdd;

    @FindBy(xpath = "//input[@id='username']")
    public WebElement name;

    @FindBy(xpath = "//input[@id='cardName']")
    public WebElement nameOnCard;

    @FindBy(xpath = "//iframe[@title='Secure card number input frame']")
    public WebElement cardNumberIframe;

    @FindBy(xpath = "//input[@name='cardnumber']")
    public WebElement cardNumber;

    @FindBy(xpath = "//iframe[@title='Secure expiration date input frame']")
    public WebElement expirationDateIframe;

    @FindBy(xpath = "//input[@name='exp-date']")
    public WebElement expirationDate;

    @FindBy(xpath = "//iframe[@title='Secure CVC input frame']")
    public WebElement cvcIframe;

    @FindBy(xpath = "//input[@name='cvc']")
    public WebElement cvcCode;

    @FindBy(xpath = "//input[@id='billingStreetAddressLine1']")
    public WebElement streetAddress;

    @FindBy(xpath = "//input[@id='billingCity']")
    public WebElement city;

    @FindBy(xpath = "//input[@id='billingPostalCode']")
    public WebElement postalCode;


    @FindBy(xpath = "//span[normalize-space()='Order Summary']")
    public WebElement orderSummaryText;

    public void checkFieldError(String ErrorNumber) {
        String Error = driver.findElement(By.xpath("(//div//div//div//div//div//span[@role='alert'][normalize-space()='Cannot be blank'])["+ ErrorNumber + "]")).getText();
        System.out.println("Error");
        Assert.assertEquals(Error,"Cannot be blank");

    }

    public void scrollToOrderSummaryTxt() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(orderSummaryText));
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].scrollIntoView(true);",orderSummaryText);
    }
    public void assertOrderSummaryPage() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(orderSummaryText));
        Assert.assertTrue(orderSummaryText.isDisplayed());
    }

    public void clickEmailAdd() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(emailAdd));
        emailAdd.click();
    }





}
