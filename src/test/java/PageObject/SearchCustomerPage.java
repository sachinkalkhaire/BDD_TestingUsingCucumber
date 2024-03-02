package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchCustomerPage {
	
	WebDriver ldriver;
	public SearchCustomerPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(id="SearchEmail")
	WebElement searchEmail;
	
	@FindBy(xpath="//button[@id='search-customers']")
	WebElement searchButton;
	
	@FindBy(xpath="//table[@role='grid']")
	WebElement searchResult;
	
	@FindBy(xpath="//table[@id='customers-grid']//tbody/tr")
	List<WebElement> tableRows;
	
	@FindBy(id="SearchFirstName")
	WebElement firstName;

	@FindBy(id="SearchLastName")
	WebElement lastName;

	
	//Action method to enter email
	public void enterEmailAdd(String email)
	{
		searchEmail.sendKeys(email);	
	}
	
	//action method to click search button
	public void clickOnSearchBtn()
	{
		searchButton.click();	
	}
	
	//action method to click search button
		public boolean searchCustomerByEmail(String email)
		{
				boolean found= true;
				//total no. of rows 
				int ttlRows = tableRows.size();


				//total no. of columns
				//int ttlColumns = tableColumns.size();
				for(int i=1;i<=ttlRows;i++)//to iterate all the rows of the grid
				{
					System.out.println("Searching row:" +i );

					WebElement webElementEmail = ldriver.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr["+ i +"]/td[2]"));
					String actualEmailAdd = webElementEmail.getText();
					System.out.println("actual email id: "+actualEmailAdd);

					if(actualEmailAdd.equals(email))
					{
						found=true;
					}


				}

				return found;
				
				
		}
		
		///////////////////////search customer by name///////////////////////////////
		//action method to enter first name
		public void enterFirstName(String firstNameText)
		{
			firstName.sendKeys(firstNameText);
		}

		//action method to enter last name
		public void enterLastName(String LastNameText)
		{
			lastName.sendKeys(LastNameText);
		}
		
		public boolean searchCustomerByName(String name)
		{
			boolean found=false;

			//total no. of rows in a grid
			int ttlRows = tableRows.size();


			for(int i=1;i<=ttlRows;i++)//to iterate all the rows of the grid
			{
				WebElement webElementName = ldriver.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr[" + i  + "]/td[3]"));
				String actualName = webElementName.getText();
				System.out.println("actual name: "+actualName);
				if(actualName.equals(name))
				{
					found=true;
					break;
				}


			}

			return found;

		}

}
