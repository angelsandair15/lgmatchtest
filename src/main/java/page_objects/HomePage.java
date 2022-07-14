package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {

	public WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h1[@class='heading']")
	public List<WebElement> categoryNames;


	public String getSubmitButtonClickedURL() throws InterruptedException {
		String url = driver.findElement(By.xpath("((//h1[@class='heading'])[5]/../ul/li/a)[1]")).getAttribute("origin");
		return url;
	}

}
