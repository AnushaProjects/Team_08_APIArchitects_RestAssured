package test_runner_team08;



import io.cucumber.testng.CucumberOptions;
import utilities_team08.ConfigReader;
import utilities_team08.ReusableVariables;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import utilities_team08.LoggerLoad;


import org.testng.annotations.BeforeTest;

import io.cucumber.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features ={"src/test/resources/features/UserModule1.feature"}, 
				glue = {"step_definition_team08","hooks_team_08"}, 
				monochrome = true)
				//tags = (""))

public class TestRunner extends AbstractTestNGCucumberTests{
	

}	



