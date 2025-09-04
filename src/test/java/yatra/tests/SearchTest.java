package yatra.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import yatra.pages.SearchPage;

public class SearchTest extends BaseTest {
	
	private SearchPage searchPage;
	
	@BeforeClass
	public void initialize() {
		searchPage = new SearchPage(driver);
	}
	
	@Test
	public void selectLowestPriceInLeftCalendar() throws InterruptedException {
		searchPage.closePopup();
		searchPage.clickOnDepartureDate();
		String lowestPriceCurrentMonth = searchPage.getLowestPriceFromCurrentMonth();
		System.out.println(lowestPriceCurrentMonth);
		String lowestPriceNextMonth = searchPage.getLowestPriceFromNextMonth();
		System.out.println(lowestPriceNextMonth);
		String lowestPriceInBothMonths = searchPage.getLowestPriceInBothMonths();
		System.out.println(lowestPriceInBothMonths);
	}
	
	

}
