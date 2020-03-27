package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.Base;
import pages.LoginPage;

public class LoginPageTests extends Base {
	
	WebDriver driver;
	LoginPage hp;
	
	@BeforeClass
	public void getDriver() {
		driver = initializeDriver();
	}
	
	@BeforeMethod
	public void openPage() {
		driver.get("https://authentication.td.com/uap-ui/index.html?consumer=easyweb&locale=en_CA#/login/easyweb-getting-started");
	}
	
	@Test(dataProvider="getData")
	public void doValidUserNameValidPasswordLogin(Map<String, String> mp) throws InterruptedException {
		LoginPage lg = new LoginPage(driver);
		
		lg.loginMethod(mp.get("UserName"), mp.get("Password"));
		String expectedText = "This should be working, but something's gone wrong. Please try again soon. [500.GENERIC]";
		String acctualText = lg.getSpecialErrorText();
		SoftAssert sfa = new SoftAssert();
		sfa.assertEquals(acctualText, expectedText);
		sfa.assertAll();
	}
	
	@DataProvider
	public Object[][] getData() throws IOException{
		FileInputStream fis = new FileInputStream("C:\\Users\\sebas\\Desktop\\Selenium Workspace\\TD_POM_Reusable\\src\\test\\resources\\TestingData\\TD_POM_Reusable.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("LoginValidUserNameValidPassword");
		
		int rowNum = sh.getLastRowNum();
		int colNum = sh.getRow(0).getLastCellNum();
		
		Object[][] obj = new Object[rowNum][1];
		
		for(int i=0; i<rowNum; i++) {
			Map<String, String> mp = new HashMap<String, String>();
			
			for(int j=0; j<colNum; j++) {
				String colName = sh.getRow(0).getCell(j).getStringCellValue();
				String cellValue = sh.getRow(i+1).getCell(j).getStringCellValue();
				mp.put(colName, cellValue);
			}
			
			obj[i][0] = mp;
		}
		return obj;
	}

}
