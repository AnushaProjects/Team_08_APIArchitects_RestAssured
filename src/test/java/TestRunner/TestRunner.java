package TestRunner;



import io.cucumber.testng.CucumberOptions;
import utilities.ConfigReader;
import utilities.ReusableVariables;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


import org.testng.annotations.BeforeTest;

import io.cucumber.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features ={"src/test/resources/features/UserModule1.feature"}, 
				glue = {"StepDefinition"}, 
				monochrome = true)
				//tags = (""))

public class TestRunner extends AbstractTestNGCucumberTests{
	@BeforeTest
	public void clearAllData() throws IOException {
		ReusableVariables reuseVariables=new ReusableVariables();
		Properties prop;
		prop = new Properties();
		 FileOutputStream out = new FileOutputStream(ConfigReader.configpath);
		FileInputStream ip = new FileInputStream(ConfigReader.configpath);
		prop.load(ip);
		System.out.println("before clearing: "+prop);
		System.out.println("Clearing out the data");
		prop.clear();
		System.out.println("AfterClearing:"+  prop);
		prop.load(ip);
		System.out.println("AfterClearing:"+  prop);
		prop.store(out, null);
		
		
	}

}	



