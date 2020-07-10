package seleniumActivities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SuiteCRM {
	
	WebDriver driver;
	
	@BeforeMethod(alwaysRun = true)
	public void LaunchBrowser() {
		
		driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		
		//Launch Browser
		driver.get("http://alchemy.hguy.co/crm");
		
	}
	
	@Test(priority = 0)
	public void VerifyWebSiteTitle() {
		
		//Get The Title
		String WebsiteTitle = driver.getTitle();

		//Match the Title
		if(WebsiteTitle.equals("SuiteCRM")) 
			System.out.println("Title Matches Exactly and also close the browser");
		else
			System.out.println("Title Not Matches Exactly");
		
	}
	
	@Test(priority = 1)
	public void HeaderImageUrl() {
		
		//Get the Header URL
		String HeaderImageUrl = driver.findElement(By.xpath("//div[@class='companylogo']/img")).getAttribute("src");
		
		//Print the Header URL
		System.out.println("Url of the Header Image-"+HeaderImageUrl);
		
				
	}
	
	@Test(priority = 2)
	public void FirstCopyRightText() {
		
		//Get the First Copy Right Text
		String FirstCopyRightText = driver.findElement(By.xpath("//div[@class='p_login_bottom']/a[1]")).getText();
		
		//Print the First Copy Right Text
		System.out.println("First CopyRight Text in the Footer.-"+FirstCopyRightText);
		
				
	}
	
	@Test(priority = 3)
	public void LoggingSite() {
		
		//Login into the Application
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("username_password")).sendKeys("pa$$w0rd");
		driver.findElement(By.xpath("//input[@name='Login']")).click();
		
		//Verify the HomePage
		WebElement findElement = driver.findElement(By.xpath("//a[contains(@class,'action-home')]"));
		Assert.assertTrue(findElement.isDisplayed());
		if (findElement.isDisplayed()==true) 
			System.out.println("Verify that the homepage has opened");
		else
			System.out.println("Verification Failed that the homepage has opened");
		
					
	}
	
	@Test(priority = 4)
	public void NavigationMenuColor() {
		
		//Login into the Application
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("username_password")).sendKeys("pa$$w0rd");
		driver.findElement(By.xpath("//input[@name='Login']")).click();
		
		//Get the navigation color
		String NavigationMenuColor = driver.findElement(By.xpath("//div[@id='toolbar']")).getCssValue("color");
		
		//Print the navigation color
		System.out.println("Get the color of the navigation menu-"+NavigationMenuColor);
		
		
	}
	
	@Test(priority = 5)
	public void ActivitiesMenuChecking() {
		
		//Login into the Application
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("username_password")).sendKeys("pa$$w0rd");
		driver.findElement(By.xpath("//input[@name='Login']")).click();
		
		//Check the Activities Menu Exist
		WebElement ActivitiesMenu = driver.findElement(By.xpath("//a[text()='Activities']"));
		if (ActivitiesMenu.isDisplayed() && ActivitiesMenu.isEnabled())
			System.out.println("Activities menu item exists and is clickable");
		else
			System.out.println("Activities” menu item is not exists and is not clickable");
		
		Assert.assertTrue(ActivitiesMenu.isDisplayed());
		Assert.assertTrue(ActivitiesMenu.isEnabled());
		
	}
	
	@Test(priority = 6)
	public void ReadingAdditionalInformation() throws InterruptedException {
		
		//Login into the Application
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("username_password")).sendKeys("pa$$w0rd");
		driver.findElement(By.xpath("//input[@name='Login']")).click();
		Thread.sleep(2000);
		
		//Navigate to Leads Page
		driver.findElement(By.xpath("//a[text()='Sales']")).click();
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()",		
		driver.findElement(By.xpath("(//a[text()='Leads'])[3]")));
		Thread.sleep(2000);
		
		//Create Lead
		driver.findElement(By.xpath("//div[text()='Create Lead']")).click();
		Thread.sleep(2000);
		Select salution=new Select(driver.findElement(By.id("salutation")));
		salution.selectByVisibleText("Mr.");
		driver.findElement(By.id("first_name")).sendKeys("Veera2");
		driver.findElement(By.id("last_name")).sendKeys("Ganesh");
		driver.findElement(By.id("phone_work")).sendKeys("9003185314");
		driver.findElement(By.id("phone_mobile")).sendKeys("9962066316");
		driver.findElement(By.id("SAVE")).click();
		Thread.sleep(5000);
		
		//Navigate to View Leads Page
		driver.findElement(By.xpath("//div[text()='View Leads']")).click();
		Thread.sleep(5000);
		
		//Get the Last Row
		int size = driver.findElements(By.xpath("//span[@title='Additional Details']")).size();
		driver.findElement(By.xpath("(//span[@title='Additional Details'])["+size+"]")).click();
		
		//Get the Last Row Phone Number
		String sphoneNumber = driver.findElement(By.xpath("//span[@class='phone']")).getText();
		
		//Print the Phone Number
		System.out.println("Phone number-"+sphoneNumber);
		
				
	}
	
	
	@Test(priority = 7)
	public void TraversingTables() throws InterruptedException {
		
		//Login into the Application
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("username_password")).sendKeys("pa$$w0rd");
		driver.findElement(By.xpath("//input[@name='Login']")).click();
		Thread.sleep(2000);
		
		//Navigate to the Account Page 
		driver.findElement(By.xpath("//a[text()='Sales']")).click();
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()",		
		driver.findElement(By.xpath("(//a[text()='Accounts'])[1]")));
		Thread.sleep(6000);
		
		
		System.out.println("Names of the odd-numbered rows of the table");
		int increament=0;
		//List<WebElement> eleOddTable = driver.findElements(By.xpath("//tr[contains(@class,'oddListRow')]/td[3]//a"));
		
		//Print the First Odd Number Rows Names
		for (int i = 1; i <= 5; i++) {
			increament++;
			String OddNumberedRow = driver.findElement(By.xpath("(//tr[contains(@class,'oddListRow')]/td[3]//a)["+increament+"]")).getText();
			System.out.println(i+")Name-"+OddNumberedRow);
		}
		
		

	}
	
	@Test(priority = 8)
	public void TraversingTables2() throws InterruptedException {
		
		//Login into the Application
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("username_password")).sendKeys("pa$$w0rd");
		driver.findElement(By.xpath("//input[@name='Login']")).click();
		Thread.sleep(2000);
		
		//Navigate to Leads Page
		driver.findElement(By.xpath("//a[text()='Sales']")).click();
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()",		
		driver.findElement(By.xpath("//a[text()='Leads']")));
		Thread.sleep(6000);
		
		//Print the First 10 Rows Names and their Users
		int increament=1;
		for (int i = 1; i <=10; i++) {
			increament++;
			String sName = driver.findElement(By.xpath("(//table[@class='list view table-responsive']//tr/td[3])["+increament+"]")).getText();
			String sUser = driver.findElement(By.xpath("(//table[@class='list view table-responsive']//tr/td[8])["+increament+"]")).getText();
			System.out.println(i+")Name-"+sName+" and the User-"+sUser);
			
		}
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void CloseBrowser() {
		
		//To Close the Browser
		driver.close();
	}
	
	
	
}
