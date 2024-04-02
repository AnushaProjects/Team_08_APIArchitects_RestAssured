package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/resources/features", 
				glue = {"stepDefinition"}, 
				monochrome = true, 
				plugin = {"pretty","html:target/Cucumberreport.html"})
				//tags = "@Get")

public class TestRunner extends AbstractTestNGCucumberTests{

}
