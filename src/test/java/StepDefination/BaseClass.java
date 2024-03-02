package StepDefination;


import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.*;
import org.openqa.selenium.WebDriver;
import PageObject.AddNewCustomerPAGE;
import PageObject.LoginPage;
import PageObject.SearchCustomerPage;
import Utilities.RedConfig;

public class BaseClass {
	public static WebDriver driver;
	public LoginPage loginPage;
	public AddNewCustomerPAGE addNewCustomerPage;
	public SearchCustomerPage searchCustomerPage;
	public static Logger log;
	public RedConfig redConfig;
	
	public String generateRandomEmail()
	{
		return(RandomStringUtils.randomAlphabetic(5));
	}

}
