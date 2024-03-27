package testRunner;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features ={"src/test/resources/features/"}, 
glue = {"StepDefinition"}, 

monochrome = true, 
plugin = {"pretty","html:target/dsalgoproject.html"})

public class TestRunner {

}
