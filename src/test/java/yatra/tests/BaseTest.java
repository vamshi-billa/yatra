package yatra.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import yatra.config.DriverManager;

public abstract class BaseTest {
	
	protected WebDriver driver;
	
	@BeforeTest (alwaysRun = true)
	@Parameters ({"browser"})
	public void setup(String browser) {
		DriverManager.initializeDriver(browser);
		driver = DriverManager.getDriver();
		driver.get("https://www.yatra.com/");
	}
	
	@AfterTest (alwaysRun = true)
	public void tearDown() {
		DriverManager.terminateDriver();
	}

}
