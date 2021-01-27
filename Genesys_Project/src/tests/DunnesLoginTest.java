package tests;

import org.openqa.selenium.WebDriver;

import pages.DunnesLoginPage;

public class DunnesLoginTest {
	WebDriver driver;

	public DunnesLoginTest(WebDriver driver) {
		this.driver = driver;

	}
	public void Test() {
		DunnesLoginPage loginPage = new DunnesLoginPage(driver);
		loginPage.CheckLoaded();
		
		//Fill in fields
		loginPage.FillForm("Mr", "Conor", "Houlihan", "0873979352", "Dunnes123");
		
		//Submit and test email pop-up
		loginPage.SubmitForm();
	}
}
