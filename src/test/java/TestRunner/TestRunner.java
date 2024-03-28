package TestRunner;



import io.cucumber.testng.CucumberOptions;




import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features ={"src/test/resources/features/User_Login.feature"}, 
				glue = {"StepDefinition"}, 
				monochrome = true)
				//tags = (""))

public class TestRunner extends AbstractTestNGCucumberTests{

}	



