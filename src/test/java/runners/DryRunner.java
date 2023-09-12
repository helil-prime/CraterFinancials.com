package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="./src/test/resources/features", // this to specify the location of the feature files so cucumber can find
		glue="tests",  // this to specify the location of step definitions so that cucumber can map with the scenario steps
		dryRun=true,    // DryRun when it's true, it scans the whole future files and finds unimplemented scenario steps if any
		               // when it's false, it just turns itself off and doesn't do anything.
		tags="@newCustomers"
		)
public class DryRunner {

}
