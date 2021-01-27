package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class DunnesLoginPage {
	
	public WebDriver driver;
	String title = "Dunnes Stores | Login / Register"; 
	String URL = "https://www.dunnesstores.com/customer/login";
	
	By userTitle = By.id("title");
	By userName = By.id("firstName");
	By userLastName = By.id("lastName");
	By userNumber = By.id("mobile");
	By userPass = By.id("newPassword");
	By confUserPass = By.id("confirmPassword");
	By termsAndCons = By.id("registryTandCs");
	By emailPopup = By.id("parsley-id-21");
	By SubmitButton = By.xpath("/html/body/main/div/div[2]/section[2]/div/form/button");
	
	public DunnesLoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void FillForm(String title, String fName, String lName, String num, String pass) {
		new Select(driver.findElement(userTitle)).selectByVisibleText(title);
		driver.findElement(By.id("firstName")).sendKeys(fName);
		driver.findElement(By.id("lastName")).sendKeys(lName);
		driver.findElement(By.id("mobile")).sendKeys(num);
		driver.findElement(By.id("newPassword")).sendKeys(pass);
		driver.findElement(By.id("confirmPassword")).sendKeys(pass);

		//object.driver.findElement(By.id("registryTandCs")).click();
		//this call wasn't working likely due to a JavaScript call, so I used the action class instead
		new Actions(driver).moveToElement(driver.findElement(termsAndCons)).click().build().perform();
		System.out.println("Form Filled out");
	}
	public void SubmitForm() {
		try {//Passes if pop-up isn't present, will then throw error that is caught and ignored
		Assert.assertEquals(true, driver.findElement(emailPopup)==null, "Email pop-up there too early " );
		}
		catch (Exception e) {			 
		}
		//Submit form
		driver.findElements(By.xpath("/html/body/main/div/div[2]/section[2]/div/form/button")).get(0).click();
		try {//Passes if pop-up is present, fails if error is thrown(pop-up isn't present)
		Assert.assertEquals(false, driver.findElement(emailPopup)==null);
		}
		catch (Exception e) {
			Assert.fail("Email pop-up not there");
		}
		System.out.println("Form Submitted");
	}
	public void CheckLoaded() {
		Assert.assertEquals(title, driver.getTitle(), "Wrong Title on page title should have been: " + title);
		Assert.assertEquals(URL, driver.getCurrentUrl(), "Wrong URl on page title should have been: " + URL);
		System.out.println("Loaded Page: " + driver.getTitle());
	}
}
