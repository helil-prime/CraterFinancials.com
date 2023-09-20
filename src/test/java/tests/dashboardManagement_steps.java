package tests;

import org.junit.Assert;

import io.cucumber.java.en.Then;
import pages.DashboardPage;
import utils.BrowserUtils;
import utils.Driver;

public class dashboardManagement_steps {
	BrowserUtils utils = new BrowserUtils();
	DashboardPage dashPage = new DashboardPage();
	
	@Then("I should be on the Dashboard home page")
	public void i_should_be_on_the_dashboard_home_page() {
		utils.waitForElementToBeVisible(dashPage.dashboard_page_SalesAndExpensesHeader);
//		System.out.println(Driver.getDriver().getTitle());
	}
	@Then("I should see the header {string}")
	public void i_should_see_the_header(String string) {
//		utils.waitForElementToBeVisible(dashPage.dashboard_page_SalesAndExpensesHeader);
		Assert.assertTrue(dashPage.dashboard_page_SalesAndExpensesHeader.isDisplayed());
		Driver.quitDriver();
	}
	
	
	@Then("I should see the buttons {string}, {string}, {string}, and {string}")
	public void i_should_see_the_buttons_and(String amountDue, String customers, String invoices, String estimates) {
		if(dashPage.dashboard_page_AmountDue_Button.getText().contains(amountDue)) {
			Assert.assertTrue(true);
		}
		if(dashPage.dashboard_page_Customers_Button.getText().contains(customers)) {
			Assert.assertTrue(true);
		}
		if(dashPage.dashboard_page_Invoices_Button.getText().contains(invoices)) {
			Assert.assertTrue(true);
		}
		if(dashPage.dashboard_page_Estimates_Button.getText().contains(estimates)) {
			Assert.assertTrue(true);
		}
		Driver.quitDriver();
	}
	
	@Then("I should see the headers {string} and {string}")
	public void i_should_see_the_headers_and(String dueInvoices, String recentEstimates) {
		if(dashPage.dashboard_page_DueInvoices_Header.getText().contains(dueInvoices)) {
			Assert.assertTrue(true);
			System.out.println("The header Due Invoices is displayed");
		}
		if(dashPage.dashboard_page_RecentEstimates_Header.getText().contains(recentEstimates)) {
			Assert.assertTrue(true);
			System.out.println("The header Recent Estimates is displayed");
		}
		Driver.quitDriver();
	}
	
	@Then("I should see the graph with a drop down menu having the options {string} and {string}")
	public void i_should_see_the_graph_with_a_drop_down_menu_having_the_options_and(String thisYear, String previousYear) {
		utils.waitForElementToBeVisible(dashPage.dashboard_page_graph);
		Assert.assertTrue(dashPage.dashboard_page_graph.isDisplayed());
		Assert.assertTrue(dashPage.dashboard_page_year_dropdown.isDisplayed());
		dashPage.dashboard_page_year_dropdown.click();
		utils.waitForElementToBeVisible(dashPage.dashboard_page_PreviousYear_dropdown_option);
		Assert.assertTrue(dashPage.dashboard_page_ThisYear_dropdown_option.isDisplayed());
		Assert.assertTrue(dashPage.dashboard_page_PreviousYear_dropdown_option.isDisplayed());
		Driver.quitDriver();
	}
}
