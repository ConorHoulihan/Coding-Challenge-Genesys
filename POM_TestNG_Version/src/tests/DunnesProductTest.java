package tests;

import org.openqa.selenium.WebDriver;

import pages.DunnesProductPage;

public class DunnesProductTest {
	WebDriver driver;

	public DunnesProductTest(WebDriver driver) {
		this.driver = driver;

	}
	public void Test() {
		DunnesProductPage productPage = new DunnesProductPage(driver);
		productPage.checkLoaded();
	}
}
