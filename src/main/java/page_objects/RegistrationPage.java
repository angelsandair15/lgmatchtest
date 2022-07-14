package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class RegistrationPage {

	public WebDriver driver;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@name='name']")
	public WebElement name;

	@FindBy(xpath = "//input[@name='phone']")
	public WebElement phone;

	@FindBy(xpath = "//input[@name='email']")
	public WebElement email;

	@FindBy(xpath = "//select[@name='country']")
	public WebElement country;

	@FindBy(xpath = "//input[@name='city']")
	public WebElement city;

	@FindBy(xpath = "//div[@id='load_box']//input[@name='username']")
	public WebElement username;

	@FindBy(xpath = "//div[@id='load_box']//input[@name='password']")
	public WebElement password;

	@FindBy(xpath = "//div[@id='load_box']//input[@value='Submit']")
	public WebElement submitButton;

	@FindBy(xpath = "//div[@id='load_box']//a[@class='fancybox'][normalize-space()='EXPLORE LIFETIME MEMBERSHIP']")
	public WebElement exploreLifeTime;

	public void selectCountry(String Country) throws InterruptedException {
		Select dropdown = new Select(country);
		dropdown.selectByValue(Country);
	}

}
