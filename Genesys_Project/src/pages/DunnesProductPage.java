package pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DunnesProductPage {

	WebDriver driver;
	String title = "Dunnes Stores | Gold Manor Lamp";
	String URL = "https://www.dunnesstores.com/p/manor-lamp/7761162";
	
	public DunnesProductPage(WebDriver driver) {
		this.driver = driver;
	}
	public void checkLoaded() {
		Assert.assertEquals(title, driver.getTitle(), "Wrong Title on page title should have been: " + title);
		Assert.assertEquals(URL, driver.getCurrentUrl(), "Wrong URl on page title should have been: " + URL);
		System.out.println("Loaded Page: " + driver.getTitle());
	}
}
