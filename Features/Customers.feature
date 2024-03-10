Feature: Customers

Background: Steps common for all scenrios
    Given User launch Chrome browser
    When  User open URL "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F"
    And   User enter Email as "admin@yourstore.com" and Password as "admin"
    And   User click on Login button
    Then  User can view Dashboad
@Sanity
Scenario Outline: Add New Customer
    When  User click on customers Menu
    And   click on customers Menu Item
    And   click on Add new button
    Then  User can view Add new customer page
    When  User enter customer info from sheetname "<SheetName>" and rownumber <RowNumber>
    And   click on Save button
    Then  User can view confirmation message "The new customer has been added successfully"
    And   close browser
    
    Examples:
    |SheetName|RowNumber|
    |contactus|1|
    |contactus|2|
    |contactus|3|
    |contactus|4|    
    |contactus|5|
    
@Regression
Scenario: Search Customer by Email
	When User click on customers Menu 
	And click on customers Menu Item 
	And Enter customer EMail
	When Click on search button
	Then User should found Email in the Search table
	And close browser
	

Scenario: Search Customer by Name
	
	When User click on customers Menu 
	And click on customers Menu Item 
	And Enter customer FirstName
	And Enter customer LastName
	When Click on search button
	Then User should found Name in the Search table
	And close browser 