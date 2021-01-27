package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class DunnesHomePage {
	WebDriver driver;
	WebDriverWait wait;
	String title = "Fashion, Homewares, Gifts & More"; 
	String URL = "https://www.dunnesstores.com/";
	By myAccount = By.id("f-loginlinks");
	By acceptCookie = By.id("onetrust-accept-btn-handler");
	By searchRevealButton = By.id("search-toggle-button");
	By searchField = By.id("keywords");
	By searchButton = By.id("search-btn");
	
	public DunnesHomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void CheckLoaded() {
		Assert.assertEquals(title, driver.getTitle(), "Wrong Title on page title should have been: " + title);
		Assert.assertEquals(URL, driver.getCurrentUrl(), "Wrong URl on page title should have been: " + URL);
		System.out.println("Loaded Page: " + driver.getTitle());
	}
	
	public void AcceptCookies() {
		try {
			wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.elementToBeClickable(acceptCookie));
			driver.findElement(acceptCookie).click();
			System.out.println("accepted cookie");
		}
		catch(Exception e){
			wait = new WebDriverWait(driver, 15);
			driver.findElement(acceptCookie).click();
			System.out.println("accepted cookie");
		}
	}
	public void Search(String term) {
		try {//check if page is loaded for a wide screen or not, alternatively I could have used driver.manage().window().maximise();
			driver.findElement(searchRevealButton).click();
			driver.findElement(searchField).sendKeys(term);
			driver.findElement(searchButton).click();
		}
		catch(Exception e){
			driver.findElement(searchField).sendKeys(term);
			driver.findElement(searchButton).click();
		}
		System.out.println("Searched for " + term);
	}
	public void ClickAccount() {
		driver.findElement(myAccount).click();
	}
}
