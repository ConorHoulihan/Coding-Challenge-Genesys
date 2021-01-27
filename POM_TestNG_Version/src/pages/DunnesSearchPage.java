package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DunnesSearchPage {
	WebDriver driver;
	String title = "Dunnes Stores | Search";
	String URL = "https://www.dunnesstores.com/search?keywords=manor+lamp";
	By products = By.xpath("/html/body/main/div/div[2]/div/ol/li[1]");
	
	public DunnesSearchPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void ClickFirstProduct() {
		driver.findElements(products).get(0).click();
		System.out.println("Clicked first product");
	}
	public void checkLoaded() {
		Assert.assertEquals(title, driver.getTitle(), "Wrong Title on page title should have been: " + title);
		Assert.assertEquals(URL, driver.getCurrentUrl(), "Wrong URl on page title should have been: " + URL);
		System.out.println("Loaded Page: " + driver.getTitle());
	}
}
