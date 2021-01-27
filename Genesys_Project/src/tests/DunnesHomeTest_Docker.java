package tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.DunnesHomePage;

public class DunnesHomeTest_Docker {
	
	public WebDriver driver;
	private String Start_URL = "https://www.dunnesstores.com/";
	private String Host_URL = "http://localhost:4444";
	DunnesHomePage homePage;

	@BeforeTest
	public void InitTestSetup() {

		
		System.out.println("BEFORE TEST:Driver set up");
		System.setProperty("webdriver.chrome.driver", "chromedriver_win32/chromedriver.exe");
	}
	@BeforeMethod
	public void CommonTestSetup() throws MalformedURLException {
		System.out.println("BEFORE METHOD: Launching Browser, Checking page is loaded, and accepting cookies");
		//Launch browser and go to Dunnes web site
		//driver = new ChromeDriver();
		
		
		ChromeOptions dcap = new ChromeOptions(); 
        URL gamelan = new URL(Host_URL);
        driver = new RemoteWebDriver(gamelan, dcap);
		
		
		
		driver.get(Start_URL);
		//create instance of home page
		homePage = new DunnesHomePage(driver);
		//Test pages URL and Title are as expected
		homePage.CheckLoaded();
		//wait until cookies can be accepted then accept
		homePage.AcceptCookies();
		
	}
	
	@Test(priority=1,description="Searched for hampton lamp and opened the first result")
	public void loginApplication() {
		System.out.println("Starting Test 1");
		//search for "manor lamp"
		homePage.Search("manor lamp");
		//Check search was successful
		
		DunnesSearchTest searchTest = new DunnesSearchTest(driver);
		searchTest.Test();
	}
	
	@Test(priority=2,description="Went to account page and created an account without filling in email")
	public void loginApplication2() {
		System.out.println("Starting Test 2");
		//Click on My Account
		homePage.ClickAccount();
		System.out.println("Pressed 'My Account'");
		
		DunnesLoginTest loginTest = new DunnesLoginTest(driver);
		loginTest.Test();
	}
	
	@AfterMethod
	public void closeBrowser() {
		System.out.println("AFTER METHOD: Closing the browser");
		driver.close();
	}
	
}
