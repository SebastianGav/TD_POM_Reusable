package base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {
	public WebDriver initializeDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sebas\\Desktop\\Selenium Workspace\\TD_POM_Reusable\\src\\test\\resources\\Browsers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}
}
