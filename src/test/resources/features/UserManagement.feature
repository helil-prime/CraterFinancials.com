#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key action
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@userManagement
Feature: User management / access management

  @validLogin @smokeTest
  Scenario: User should be able to login with valid credentials
    Given As a user, I am on the login page
    When I enter valid useremail and valid password
    And Click on login button
    Then I should be logged in

# Cucumber reuses the steps that are already implemented. 
# if there are same two steps, 
# cucumber only generates one step definition and reuses that step definition between that two steps

  @invalidEmailLogin
  Scenario: Invalid email login attempts
    Given As a user, I am on the login page
    When I enter invalid username and valid password
    And Click on login button
    Then I should see an error message "These credentials do not match our records." displays
    And I should not be logged in 
    
    
    
    
    
    
    
    
