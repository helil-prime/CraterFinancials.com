# CraterFinancials.com - Test Automation Framework - UI & API
Sample end 2 end test automation framework for UI and API testing


This sample Software Test Automation Framework is built as a Maven project using Behavior Driven Development principles with Cucumber and Junit in Java. Page Object Model structure is also utilized to maximize the framework re-usability and maintainability. 

And the UI test scenarios are written in Gherkin language that could benefit connecting the team.

And also this framework is structured to support API test automation using RestAssured library. 


## Project Structure

src/test/java
 - api_tests - where API tests resides (API tests written using RestAssured library in TestNG test structure)
 - pages - Page objects are collected/created and defined in this folder
 - runners - one or more test runner classes are created within this folder
 - step_definitions - Test step implementation for the cucumber scenario steps defined in this folder
 - utils - Any utility and support classes and functions for testing activities are in this folder
 
src/test/resources
 - features - Cucumber feature files are created in this folder to define the user scenarios for the app features
 - test_files - Any test data files that are used for testing purposes are stored in this folder
 - testdata - Any test data or properties files are stored in this folder
 
Cucumber_reports - Cucumber report files generated by the cucumber are stored in this folder

pom.xml - pom.xml is used to define the project configurations, libraries/content/dependencies management and build/execution configurations.


## Tools used 

 Maven - is used to define the project configurations, libraries/content/dependencies management and build/execution configurations, and used for both UI and API framework.

 UI - tools used for UI test automation:
 
  - Cucumber - is used to define feature scenarios in gherkin, and also to create test suites and execution flow by cucumber tagging mechanism, and also for reporting.
  - Junit - is used to run the cucumber scenarios with cucumber options, and also used for assertion.
  - Selenium WebDriver 4 - is used to automate web application by implementing the cucumber step definitions, and managing the page objects.
  - Faker - is used to provide fake test data for the tests.
  
 API - tools used for API test automation:
  
   - TestNG - is used to create and manage the API test cases, test suites and test execution flow and Assertion, as well as reporting. 
   - RestAssured - is used to define the API tests in a very simple and readable manner.
   - Faker - is used to provide fake test data for the tests
   
 Other tools used to achieve end 2 end testing flow:
   
   - GIT - is used to manage source code and version control
   - Github - is used as a remote source code management platform and version control tool
   - Jenkins - is CI/CD tool which we have used to run the test suites remotely, by either on-demand basis, or periodic schedule basis.
   - Eclipse - IDE for the project development


## Creating tests

##### Creating UI tests: [Cucumber reference documentation](https://cucumber.io/docs/cucumber/api/?lang=java#running-cucumber)
 
 1. Create a feature file in `features` folder under `src/test/resources` folder with file extention as `.feature`
 2. Define user scenarios with cucumber key words like Given, When and Then structure
 3. Generate step definitions code snippet using cucumber `dryrun`
 4. Create a step definition class under `tests or step_definitions` folder  and put those generated steps snippet
 5. Create page classes (if necessary) under `pages` folder and collect/define the page objects (`web elements`)
 6. Implement the step definitions based on the user scenario behavior

##### Running the UI tests
 1. Create a runner class under `runners` folder
 2. Define the RunWith  cucumber options with necessary options like: feature files location, and glue...
 3. Pass a scenario tag to execute tests locally using cucumber tags, and run as JUnit test
 4. To run the tests remotely via CI/CD tools like `Jenkins`, a `Maven build action` has to be defined with `SureFire plugin` which points to execute the cucumber runner class in `pom.xml`
 5. On Jenkins job under build workflow, select the `top level maven targets` from `Build Steps` action, and pass the following command: 
 
 ```bash
  clean test -Dcucumber.filter.tags="@smokeTest"
  ```
  
##### Creating API tests: [RestAssured Documentation](https://rest-assured.io/)
 1. Create a class in `api_tests` folder under `src/test/java` folder
 2. Create TestNG test/tests to define the API test cases with RestAssured library. (refer to the existing API tests in the folder)
 
##### Running the API tests
 1. Locally, run a particular API test as a TestNG test, or run a suite of API tests as a TestNG suite via `testng.xml` file
 2. To run the API tests remotely via CI/CD tools like Jenkins, create a TestNG xml file, define a TestNG suite that includes one or more API test classes to execute
 3. In the pom.xml file, create a Maven profile that includes the TestNG xml file with Maven SureFire plugin
 4. On Jenkins job under Build Workflow, select the top level maven targets from `Build Steps` action, and pass the following command: 
 
 ```bash
 clean test -P<profile_id>
 ```
Profile Example Script: 

```
<profiles>
		<profile>
			<id>api_tests</id>
			<build>
				<plugins>
					<plugin>
						<groupId>org.apache.maven.plugins</groupId>
						<artifactId>maven-surefire-plugin</artifactId>
						<version>3.0.0-M5</version>
						<configuration>
							<suiteXmlFiles>
								<suiteXmlFile>testng.xml</suiteXmlFile>
							</suiteXmlFiles>
						</configuration>
					</plugin>
				</plugins>
			</build>
		</profile>
	</profiles>
```


