package StepDefination;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import PageObject.AddNewCustomerPAGE;
import PageObject.LoginPage;
import PageObject.SearchCustomerPage;
import Utilities.ExcelReader;
import Utilities.RedConfig;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;




public class StepDef extends BaseClass{
	
    @Before("@Sanity")
	public void setup1()
	{
		 
		 log= LogManager.getLogger("StepDef");
		System.out.println("setup1 method exicuted sanity");
		
		redConfig= new RedConfig();
		
		String browser=redConfig.getBrowser();
		//launch browser
		
		switch(browser.toLowerCase())
		{
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "msedge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			driver = null;
			break;

		}

	
		log.info("Step 1 exicuted");
	}
	/*@Before("@Regression")
	public void setup2()
	{
		System.out.println("setup2 method exicuted regression");
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		log.info("Step  exicuted");
	}*/

	////////////LoginPage feature Step//////////

	@Given("User launch Chrome browser")
	public void user_launch_chrome_browser() {


		loginPage=new LoginPage(driver);
		addNewCustomerPage=new AddNewCustomerPAGE(driver);
		searchCustomerPage=new SearchCustomerPage(driver);
		log.info("launch chrome browser");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(200));
	}


	@When("User open URL {string}")
	public void user_open_url(String url) {
		driver.get(url);
		log.info("url open");
		
	}

	@When("User enter Email as {string} and Password as {string}")
	public void user_enter_email_as_and_password_as(String emailadd, String passcode) {

		loginPage.enterEmail(emailadd);
		loginPage.enterPassword(passcode);
		log.info("enter email add and pwrd");

	}

	@When("User click on Login button")
	public void user_click_on_login_button() {
		loginPage.clickOnLoginButton();
		log.info("click on login button");
	}

	@Then("Page title should be {string}")
	public void page_title_should_be(String expectedTitle) {
		String actualTitle=driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		log.info("page title match");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() {
		loginPage.clickOnLogoutButton();
		log.info("click on logout button");
	}

	/*
	@Then("close browser")
	 
	public void close_browser() {
		driver.close();
		//driver.quit();
		log.info("close browser");  //shift to closeStep class
	}
*/
	
	///////////////////////Customers feature step  add new customer//////////////////

	@Then("User can view Dashboad")
	public void user_can_view_dashboad() {
		String actualTitle=addNewCustomerPage.getPageTitle();
		String expectedTitle="Dashboard / nopCommerce administration";

		if(actualTitle.equals(expectedTitle))
		{
			log.info("user can view dashbord pass");
			Assert.assertTrue(true);
		}
		else
		{
			log.warn("user can not view dashbord fail");
			Assert.assertTrue(false);
		}
		log.info("user enter email add ");
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_menu() {

		addNewCustomerPage.clickOnCustomersMenu();
		log.info("user click on customer menu");
		log.info("user enter email add ");
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@When("click on customers Menu Item")
	public void click_on_customers_menu_item() {
		addNewCustomerPage.clickOnCustomersMenuItem();
		log.info("user click on customer menu item");
		
	}

	@When("click on Add new button")
	public void click_on_add_new_button() {
		addNewCustomerPage.clickOnAddnew();
		log.info("user click on add new button");
	}

	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
		String actualTitle=addNewCustomerPage.getPageTitle();
		String expectedTitle="Add a new customer / nopCommerce administration";

		if(actualTitle.equals(expectedTitle))
		{
			log.info("user can view add new customer page pass");
			Assert.assertTrue(true);
		}
		else
		{
			log.warn("user can view add new customer page fail");
			
			Assert.assertTrue(false);
		}
		
	}
	
	//-----------read excel file data-----------//
	
	@When("User enter customer info from sheetname {string} and rownumber {int}")
	public void user_enter_customer_info_from_sheetname_and_rownumber(String sheetName, Integer rowNumber) throws InvalidFormatException, IOException  {
		ExcelReader excelReader=new ExcelReader();
		List<Map<String,String>> testData=
				excelReader.getData("C:\\Users\\Shree\\Desktop\\Cucumber\\CucumberFramework\\TestDataExcelFile\\automation.xlsx", sheetName);
		
		String email=testData.get(rowNumber).get("EmailAddres");
		String pwrd=testData.get(rowNumber).get("Password");
		String fName=testData.get(rowNumber).get("FirstName");
		String lName=testData.get(rowNumber).get("LastNmae");
		String gender=testData.get(rowNumber).get("Gender");
		//String dob=testData.get(rowNumber).get("Dob");
		String comName=testData.get(rowNumber).get("CompanyName");
		String adminContent=testData.get(rowNumber).get("AdminContent");
		String managerVender=testData.get(rowNumber).get("ManagerOfVender");
		
		addNewCustomerPage.enterEmail(email);
		addNewCustomerPage.enterPassword(pwrd);
		addNewCustomerPage.enterFirstName(fName);
		addNewCustomerPage.enterLastName(lName);
		addNewCustomerPage.enterGender(gender);
		//addNewCustomerPage.enterDob(dob);
		addNewCustomerPage.enterCompanyName(comName);
		addNewCustomerPage.enterAdminContent(adminContent);
		addNewCustomerPage.enterManagerOfVendor(managerVender);
		addNewCustomerPage.clickOnIsTax();
		
	}
	
	/*@When("User enter customer info")
	public void user_enter_customer_info() {
		//addNewCustomerPage.enterEmail("test21214@gmail.com");
		addNewCustomerPage.enterEmail(generateRandomEmail()+"@gmail.com");

		addNewCustomerPage.enterPassword("test3");
		addNewCustomerPage.enterFirstName("Sachinkk1");
		addNewCustomerPage.enterLastName("khairess1");
		addNewCustomerPage.enterGender("Male");
		addNewCustomerPage.enterDob("7/12/1994");
		addNewCustomerPage.enterCompanyName("company1");
		addNewCustomerPage.enterAdminContent("adminContent");
		addNewCustomerPage.enterManagerOfVendor("Vendor 1");
		addNewCustomerPage.clickOnIsTax();
		log.info("user can enter customer information");
	}*/

	@When("click on Save button")
	public void click_on_save_button() {
		addNewCustomerPage.clickOnSave();
		log.info("click on save button");
		
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String expectedConfirmationMsg) {
		String bodyTag=driver.findElement(By.tagName("Body")).getText();
		if(bodyTag.contains(expectedConfirmationMsg))
		{
			log.info("user can view confirmation message pass");
			Assert.assertTrue(true);
		}
		else {
			log.warn("user can view confirmation message fail");
			Assert.assertTrue(false);
		}

	}

	///////////////////////Customers feature step  search customer by email//////////////////
	@When("Enter customer EMail")
	public void enter_customer_e_mail() {
		searchCustomerPage.enterEmailAdd("victoria_victoria@nopCommerce.com");
		log.info("user enter email add ");
	try {
		Thread.sleep(200);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	@When("Click on search button")
	public void click_on_search_button() {
		searchCustomerPage.clickOnSearchBtn();
		log.info("user click on search button");
		
	}

	@Then("User should found Email in the Search table")
	public void user_should_found_email_in_the_search_table() {
		String expectedEmail="victoria_victoria@nopCommerce.com";
		if(searchCustomerPage.searchCustomerByEmail(expectedEmail)==true)
		{
			log.info("User should found Email in the Search table pass");
			Assert.assertTrue(true);
		}
		else
		{
			log.warn("User should found Email in the Search table fail");
			Assert.assertTrue(false);
		}
		
	}

	///////////////////////Customers feature step  search customer by name//////////////////

	@When("Enter customer FirstName")
	public void enter_customer_first_name() {
		searchCustomerPage.enterFirstName("Sachinkk1"); 
		log.info("Enter customer FirstName");
	}

	@When("Enter customer LastName")
	public void enter_customer_last_name() {
		searchCustomerPage.enterLastName("khairess");
		log.info("Enter customer lastName");
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	@Then("User should found Name in the Search table")
	public void user_should_found_name_in_the_search_table() {
		String expectedName="Sachinkk1 khairess";
		if(searchCustomerPage.searchCustomerByName(expectedName))
		{
			log.info("User should found Name in the Search table pass");
			Assert.assertTrue(true);
		}
		else
		{
			log.warn("User should found Name in the Search table fail");
			Assert.assertFalse(false);
		}
	}
	
	@After
	public void tearDown(Scenario sc)
	{
		
		if(sc.isFailed())
		{
			//capture full page screen shot
			TakesScreenshot screenshot=((TakesScreenshot)driver);
			File src=screenshot.getScreenshotAs(OutputType.FILE);
			File dest= new File("C:\\Users\\Shree\\Desktop\\Cucumber\\CucumberFramework\\Screenshot\\failscreenshot.png");
			try {
				FileUtils.copyFile(src, dest);
			} catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		driver.quit();
	}
	
	@AfterStep
	public void addScreenshot(Scenario scenario)
	{
		
		if(scenario.isFailed())
		{
			//capture full page screen shot
			final byte[] screenshot=((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image.png", scenario.getName());
		}
	}
	/*
@BeforeStep
public void beforeStepMethod()
{
	System.out.println("this is before step method");

	}

@AfterStep
public void afterStepMethod()
{
	System.out.println("this is after step method");

	}
	 */

}
