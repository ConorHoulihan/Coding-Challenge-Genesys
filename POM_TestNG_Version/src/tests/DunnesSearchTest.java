package tests;

import org.openqa.selenium.WebDriver;

import pages.DunnesSearchPage;

public class DunnesSearchTest {
	WebDriver driver;

	public DunnesSearchTest(WebDriver driver) {
		this.driver = driver;

	}
	public void Test() {
		DunnesSearchPage searchPage = new DunnesSearchPage(driver);
		searchPage.checkLoaded();
		//click on first result
		searchPage.ClickFirstProduct();
		DunnesProductTest productTest = new DunnesProductTest(driver);
		productTest.Test();
	}
}
