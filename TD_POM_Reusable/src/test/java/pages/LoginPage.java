package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class LoginPage {
	WebDriver driver;
	
	By userNameLocator = By.xpath("//input[@id='username100']");
	By passwordLocator = By.xpath("//div[@class='otp-box-content']//input[@id='password']");
	By clickLogin = By.xpath("//div[@class='otp-box light-green hidden-xs otp-separate-right-nav']//button[1]");
	By specialErrorTextLocator = By.xpath("//span[@id='error']");
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public WebElement getElement(By locator, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(locator)));
		return element;
	}
	
	public void loginMethod(String userName, String password) {
		getElement(userNameLocator, 2).sendKeys(userName);
		SoftAssert sfa = new SoftAssert();
		sfa.assertEquals(getElement(userNameLocator, 2).getAttribute("value"), userName);
		
		getElement(passwordLocator, 2).sendKeys(password);
		sfa.assertEquals(getElement(passwordLocator, 2).getAttribute("value"), password);
		
		getElement(clickLogin, 2).click();
		sfa.assertAll();
	}
	
	public String getSpecialErrorText() {
		String specialErrorText = getElement(specialErrorTextLocator, 2).getText();
		return specialErrorText;
	}

}

