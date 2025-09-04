package yatra.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class DriverManager {
	
	private static WebDriver driver;

	public static void initializeDriver(String browser) {
		
		switch (browser.toUpperCase()) {
		
		case "CHROME": 
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
			break;
			
		case "EDGE": 
			driver = new EdgeDriver();
			break;
			
		case "FIREFOX":
			driver = new FirefoxDriver();
			break;
			
		default: throw new IllegalArgumentException("Invalid Browser type !");
		
		}
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	public static void terminateDriver() {
		driver.quit();
	}
}
