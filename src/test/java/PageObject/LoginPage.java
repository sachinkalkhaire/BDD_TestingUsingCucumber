package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver ldriver;
	
 public LoginPage(WebDriver rdriver )
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(id="Email")
	WebElement email;

	@FindBy(id="Password")
	WebElement password;
	

	@FindBy(xpath="//button[normalize-space()='Log in']")
	WebElement button;
	
	@FindBy(linkText="Logout")
	WebElement logout;
	
	public void enterEmail(String emailAdd)
	{   
		email.clear();
		email.sendKeys(emailAdd);
	}
	
	public void enterPassword(String pwrd)
	{
		password.clear();
		password.sendKeys(pwrd);
	}
	
	public void clickOnLoginButton()
	{
		button.click();
	}
	
	public void clickOnLogoutButton()
	{
		logout.click();
	}

}
