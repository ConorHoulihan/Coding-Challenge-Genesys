import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DockerAssignment {

	private WebDriverWait wait;
	private WebDriver driver;
	private String expectedTitle;
	private String expectedURL;
	private String Start_URL = "https://www.dunnesstores.com/";
	private String Host_URL = "http://localhost:4444";

    public static void main(String[] args) throws MalformedURLException {
    	DockerAssignment o = new DockerAssignment();
    	
    	//TEST 1
		System.out.println("Start of test 1");
    	//Driver Setup
    	ChromeOptions dcap = new ChromeOptions(); 
        URL gamelan = new URL(o.Host_URL);
        o.driver = new RemoteWebDriver(gamelan, dcap);

        //Launch Web page
        o.driver.get(o.Start_URL);
        
        //Check the page is loaded correctly
        o.checkLoaded("Fashion, Homewares, Gifts & More", "https://www.dunnesstores.com/");

        //accept the cookies
		o.closeCookie();
		
		//search for "manor lamp"
		o.searchLamp();
		
		//Check search was successful
		o.checkLoaded("Dunnes Stores | Search", "https://www.dunnesstores.com/search?keywords=manor+lamp");
        
		//click on first result
		List<WebElement> allProduct = o.driver.findElements(By.xpath("/html/body/main/div/div[2]/div/ol/li[1]"));
		allProduct.get(0).click();

		//Check first object was clicked on
		o.checkLoaded("Dunnes Stores | Gold Manor Lamp", "https://www.dunnesstores.com/p/manor-lamp/7761162");
        
		o.driver.close();
		System.out.println("End of test 1");
		//TEST 2
		System.out.println("//////////////////////////////////////////////////////////////////");
		System.out.println("Start of test 2");
    	//Driver Setup
    	dcap = new ChromeOptions(); 
        o.driver = new RemoteWebDriver(gamelan, dcap);

        //Launch Web page
        o.driver.get(o.Start_URL);
        
        //Check the page is loaded correctly
        o.checkLoaded("Fashion, Homewares, Gifts & More", "https://www.dunnesstores.com/");
        
        //Close Cookie menu
      	o.closeCookie();

      	//Click on My Account
      	o.driver.findElement(By.id("f-loginlinks")).click();
      	System.out.println("Pressed 'My Account'");
      	
      	//Check page is loaded
      	o.checkLoaded("Dunnes Stores | Login / Register", "https://www.dunnesstores.com/customer/login");
      	
      	//Fill in fields
      	o.fillForm();
      	
		o.driver.close();
		System.out.println("End of test 2");
    }
	void checkLoaded(String title, String URL) {
		expectedTitle = title;
		expectedURL = URL;
		if (driver.getTitle().contentEquals(expectedTitle) && driver.getCurrentUrl().contentEquals(expectedURL)){
			System.out.println(title + " loaded successfully");
		} else {
			System.out.println("Page did not load correctly");
		}
	}
	void closeCookie() {//wait until cookies can be accepted then accept
		try {
			wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler")));
			driver.findElement(By.id("onetrust-accept-btn-handler")).click();
			System.out.println("accepted cookie");
		}
		catch(Exception e){
			wait = new WebDriverWait(driver, 15);
			driver.findElement(By.id("onetrust-accept-btn-handler")).click();
			System.out.println("accepted cookie");
		}
	}
	void searchLamp() {
		try {//check if page is loaded for a wide screen or not
			driver.findElement(By.id("search-toggle-button")).click();
			driver.findElement(By.id("keywords")).sendKeys("manor lamp");
			driver.findElement(By.id("search-btn")).click();
		}
		catch(Exception e){
			driver.findElement(By.id("keywords")).sendKeys("manor lamp");
			driver.findElement(By.id("search-btn")).click();
		}
		System.out.println("searched for manor lamp");
	}
	void fillForm() {
		new Select(driver.findElement(By.id("title"))).selectByVisibleText("Mr");
		driver.findElement(By.id("firstName")).sendKeys("Conor");
		driver.findElement(By.id("lastName")).sendKeys("Houlihan");
		driver.findElement(By.id("mobile")).sendKeys("0873979352");
		driver.findElement(By.id("newPassword")).sendKeys("Dunnes123");
		driver.findElement(By.id("confirmPassword")).sendKeys("Dunnes123");

		//object.driver.findElement(By.id("registryTandCs")).click();
		//this call wasn't working likely due to a JavaScript call, so I used the action class instead
		WebElement element = driver.findElement(By.id("registryTandCs"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();

		try{
			if(driver.findElement(By.id("parsley-id-21"))!=null)
				System.out.println("Email popup is there");
			}catch(Exception e) {
				System.out.println("Email popup is not there");
		}

		//Submit form
		driver.findElements(By.xpath("/html/body/main/div/div[2]/section[2]/div/form/button")).get(0).click();
		System.out.println("Form Submitted");

		try{
			if(driver.findElement(By.id("parsley-id-21"))!=null)
					System.out.println("Email popup is there");
			}catch(Exception e) {
				System.out.println("Email popup is not there");
		}
	}
}