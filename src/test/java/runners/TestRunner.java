package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty", 
				"html:Cucumber_reports/cucumber_report.html",
				"json:Cucumber_reports/cucumber_report.json"},
		features="./src/test/resources/features", // this to specify the location of the feature files so cucumber can find
		glue="tests",  // this to specify the location of step definitions so that cucumber can map with the scenario steps
		tags="@newCustomers"  
		)
public class TestRunner {

}
