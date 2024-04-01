package test_runner_team08;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features/", 
glue = {"stepDefinition"}, 

monochrome = true, 

plugin = {"pretty","html:target/Cucumberreport.html"})

public class TestRunner {

}
