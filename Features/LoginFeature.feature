Feature: Login

@Sanity @Regression
Scenario: Successful login with valid Credentials

Given User launch Chrome browser
When  User open URL "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F"
And   User enter Email as "admin@yourstore.com" and Password as "admin"
And   User click on Login button
Then  Page title should be "Dashboard / nopCommerce administration"
When  User click on Log out link
Then  Page title should be "Your store. Login"
And   close browser


Scenario Outline: Successful login with valid Credentials DDT

Given User launch Chrome browser
When  User open URL "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F"
And   User enter Email as "<email>" and Password as "<pwrd>"
And   User click on Login button
Then  Page title should be "Dashboard / nopCommerce administration"
When  User click on Log out link
Then  Page title should be "Your store. Login"
And   close browser


Examples:
|email|pwrd|
|admin@yourstore.com|admin|
#|sachin@yourstore.com|admin|






