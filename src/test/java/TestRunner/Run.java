package TestRunner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//import io.cucumber.junit.Cucumber;
//import org.junit.runner.RunWith;

//import io.cucumber.junit.CucumberOptions;


//@RunWith(Cucumber.class)
@CucumberOptions(
		
		//features={".//Features/LoginFeature.feature"},
		
	   features={".//Features"},     //EXICUTE all feature files.
		
		glue="StepDefination",
		dryRun=false,
		monochrome=true,
		tags="@Sanity",
				
		
		//tags="@Regression and @Sanity",  //AND operator
		//tags="@Regression or @Sanity",  //OR operator
		//tags="@Regression and not@Sanity",  //NOT operator
		
		//plugin= {"pretty","html:target/cucumber-report/report111.html"} 
		
		//plugin= {"pretty","html:target/cucumber-report/report111.html","com.aventstack.extentreports.cucumber.adater.ExtentCucumberAdapter:"} 
	   plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
		
		//plugin= {"pretty","html:target/cucumber-report/report1.html"}    //report format html.
		//plugin= {"pretty","json:target/cucumber-report/report2.json"}  //report format json.
		//plugin= {"pretty","junit:target/cucumber-report/report3.xml"}  //report format xml.
		
		
		)


public class Run extends AbstractTestNGCucumberTests {

	//this class will be empty
}

