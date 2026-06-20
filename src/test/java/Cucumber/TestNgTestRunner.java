package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/Cucumber",glue = "Cucumber.StepDefination",
monochrome = true,plugin = {"html:cucumberReport/cucumber.html"})
public class TestNgTestRunner extends AbstractTestNGCucumberTests{
	

}
