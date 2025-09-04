package yatra.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends BasePage {
	
	@FindBy (css = "div[aria-label=\"Departure Date inputbox\"][role='button']")
	private WebElement departureDateElement;
	
	@FindBy (xpath = "//div[@class=\"react-datepicker__month-container\"]")
	private List<WebElement> monthsContainerElements;
	
	@FindBy (xpath = "(//div[contains(@class,\"style_popup\")])[1]")
	private WebElement loginPopupElement;
	
	@FindBy (xpath = "")
	private WebElement dateElement;
	
	
	private By loginPopupCloseLocator = By.xpath("//img[@alt=\"cross\"]");
	private By priceElementsLocator = By.xpath(".//span[contains(@class,\"custom-day-content \")]");
	private By dateLocator = By.xpath("./../..");
	
	
	public SearchPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}		
		
	
	public void clickOnDepartureDate() {
		departureDateElement.click();
	}
	
	public String getLowestPriceFromCurrentMonth() throws InterruptedException {
		WebElement currentMonthContainerElement = monthsContainerElements.get(0);
		Thread.sleep(3000);
		return getLowestPrice(currentMonthContainerElement);
	}
	
	public String getLowestPriceFromNextMonth() {
		WebElement nextMonthContainerElement = monthsContainerElements.get(1);
		return getLowestPrice(nextMonthContainerElement);
	}
	
	public String getLowestPriceInBothMonths() throws InterruptedException {
		String lowestPriceCurrentMonth = getLowestPriceFromCurrentMonth();
		String lowestPriceNextMonth = getLowestPriceFromNextMonth();
		int priceCurrentMonth = Integer.parseInt(lowestPriceCurrentMonth.substring(lowestPriceCurrentMonth.indexOf("Rs. ") + 4));
		int priceNextMonth = Integer.parseInt(lowestPriceNextMonth.substring(lowestPriceNextMonth.indexOf("Rs. ") + 4));
		if (priceCurrentMonth <= priceNextMonth) {
			return lowestPriceCurrentMonth;
		} else {
			return lowestPriceNextMonth;
		}
	}
	
	public String getLowestPrice(WebElement monthContainerElement) {
		List<WebElement> priceElements = monthContainerElement.findElements(priceElementsLocator);
		int lowestPrice = Integer.MAX_VALUE;
		WebElement dateLowestElement = null;
		String dateLowest = null;
		for (WebElement priceElement: priceElements) {
			String priceString = priceElement.getText();
			if (priceString.length() > 0) {
				priceString = priceString.replaceAll("[₹,]", "");
				int price = Integer.parseInt(priceString);
				if(price < lowestPrice) {
					lowestPrice = price;
					dateLowestElement = priceElement.findElement(dateLocator);
					dateLowest = dateLowestElement.getAttribute("aria-label");
				}
			}
		}
		return dateLowest + " - Rs. " + String.valueOf(lowestPrice);
	}
	
	public void closePopup() {
		try {
			waitUntilVisible(loginPopupElement, 20);
			loginPopupElement.findElement(loginPopupCloseLocator).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
