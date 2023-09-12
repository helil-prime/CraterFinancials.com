package tests;

import org.junit.Assert;
import org.openqa.selenium.By;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CustomersPage;
import pages.DashboardPage;
import utils.BrowserUtils;
import utils.Driver;

public class CustomerManagementSteps {
	BrowserUtils utils = new BrowserUtils();
	DashboardPage dashPage = new DashboardPage();
	CustomersPage customerPage = new CustomersPage();
	
	static String name;
	
	// @newCustomerBTNTest scenario start - 
	@When("I navigate to the customers tab")
	public void i_navigate_to_the_customers_tab() {
		utils.waitForElementToBeVisible(dashPage.customers_tab);
		dashPage.customers_tab.click();
		utils.waitForElementToBeVisible(customerPage.customer_page_Customers_TextHeader);
		Assert.assertTrue(customerPage.customer_page_Customers_TextHeader.isDisplayed());
	}
	@Then("I should see the New Customer button displayed")
	public void i_should_see_the_new_customer_button_displayed() {
		Assert.assertTrue(customerPage.customer_page_NewCustomerBTN.isDisplayed());
	}
	
	// @newCustomerBTNTest scenario end - 
	
	// @newCustomerBasicInfoFormLabel start - 
	@When("I click on the New Customer button")
	public void i_click_on_the_new_customer_button() {
		customerPage.customer_page_NewCustomerBTN.click();
		utils.waitForElementToBeVisible(customerPage.customer_page_NewCustomer_TextHeader);
		Assert.assertTrue(customerPage.customer_page_NewCustomer_TextHeader.isDisplayed());
	}
	@Then("I should see the Basic Info form label")
	public void i_should_see_the_basic_info_form_label() {
		utils.waitForElementToBeVisible(customerPage.customer_page_BasicInfo_Form_Label);
		Assert.assertTrue(customerPage.customer_page_BasicInfo_Form_Label.isDisplayed());
	}
	
	// @newCustomerBasicInfoFormLabel end - 
	
	// @newCustomerBasicInfoFields start -
	@Then("I should see the fields Display Name, Primary Contact Name, Email, Primary Currency, Website, and Prefix")
	public void i_should_see_the_fields_display_name_primary_contact_name_email_primary_currency_website_and_prefix() {
		utils.waitForElementToBeVisible(customerPage.customer_page_BasicInfo_DisplayName_Field);
		if(customerPage.customer_page_BasicInfo_DisplayName_Field.isDisplayed()) {
			Assert.assertTrue(true);
			System.out.println("Display Name Field visible, PASS");
		} else {
			Assert.assertTrue(false);
			System.out.println("Display Name Field not visible, FAIL");
		}
		Assert.assertTrue(customerPage.customer_page_BasicInfo_DisplayName_Field.isDisplayed());
		Assert.assertTrue(customerPage.customer_page_BasicInfo_PrimaryContactName_Field.isDisplayed());
		Assert.assertTrue(customerPage.customer_page_BasicInfo_Email_Field.isDisplayed());
		Assert.assertTrue(customerPage.customer_page_BasicInfo_PrimaryCurrency_Field.isDisplayed());
		Assert.assertTrue(customerPage.customer_page_BasicInfo_Website_Field.isDisplayed());
		Assert.assertTrue(customerPage.customer_page_BasicInfo_Prefix_Field.isDisplayed());
		Driver.quitDriver();
	}

	// @newCustomerBasicInfoFields end -
	
	// @newCustomerTableList start -
	@Then("I should see the Customer List Table")
	public void i_should_see_the_customer_list_table() throws InterruptedException {
		Thread.sleep(2000);
		
		if (customerPage.customer_page_NoCustomersYet_text.isDisplayed()) {
			customerPage.customer_page_NewCustomerBTN.click();
			utils.waitForElementToBeVisible(customerPage.customer_page_BasicInfo_DisplayName_Field);
			customerPage.customer_page_BasicInfo_DisplayName_Field.sendKeys("Student");
			customerPage.customer_page_NewCustomerSubmit_BTN.click();
			Thread.sleep(1000);
			dashPage.customers_tab.click();
//			Thread.sleep(1000);
			utils.waitForElementToBeVisible(customerPage.customer_page_Customers_TextHeader);
			Assert.assertTrue(customerPage.customer_page_CustomerTable.isDisplayed());
		} else {
			Assert.assertTrue(customerPage.customer_page_CustomerTable.isDisplayed());
		}
//		Driver.quitDriver();
	}
	
	// @newCustomerTableList end -
	
	// @customerTableColumn start -
	@Then("the following columns: NAME, PHONE , AMOUNT DUE, ADDED ON")
	public void the_following_columns_name_phone_amount_due_added_on() {
		if(customerPage.customer_page_customerTable_Name_Column.isDisplayed()) {
			Assert.assertTrue(true);
			System.out.println("Name Column is present");
		} else {
			Assert.assertTrue(false);
			System.out.println("Name Column is NOT present");
		}
		if(customerPage.customer_page_customerTable_Phone_Column.isDisplayed()) {
			Assert.assertTrue(true);
			System.out.println("Phone Column is present");
		} else {
			Assert.assertTrue(false);
			System.out.println("Phone Column is NOT present");
		}
		if(customerPage.customer_page_customerTable_AmountDue_Column.isDisplayed()) {
			Assert.assertTrue(true);
			System.out.println("Amount Due Column is present");
		} else {
			Assert.assertTrue(false);
			System.out.println("Amount Due Column is NOT present");
		}
		if(customerPage.customer_page_customerTable_AddedOn_Column.isDisplayed()) {
			Assert.assertTrue(true);
			System.out.println("Added On Column is present");
		} else {
			Assert.assertTrue(false);
			System.out.println("Added On Column is NOT present");
		}
	}
	// @customerTableColumn end -
	
	// @customerTableMoreOptions start -
	@Then("a more link option for each customer with the options: Delete, Edit, View")
	public void a_more_link_option_for_each_customer_with_the_options_delete_edit_view() {
		utils.waitForElementToBeVisible(customerPage.customer_page_customerTable_3dotMoreLink);
		if(customerPage.customer_page_customerTable_3dotMoreLink.isDisplayed()) {
			Assert.assertTrue(true);
			System.out.println("The customer 3 dot options link is displayed");
		} else {
			Assert.assertTrue(false);
			System.out.println("The customer 3 dot options link is displayed");
		}
		customerPage.customer_page_customerTable_3dotMoreLink.click();
		utils.waitForElementToBeVisible(customerPage.customer_page_customerTable_3dotMoreLink_View);
		Assert.assertTrue(customerPage.customer_page_customerTable_3dotMoreLink_View.isDisplayed());
		Assert.assertTrue(customerPage.customer_page_customerTable_3dotMoreLink_Edit.isDisplayed());
		Assert.assertTrue(customerPage.customer_page_customerTable_3dotMoreLink_Delete.isDisplayed());
		
	}
	// @customerTableMoreOptions end -
	
	// @newCustomerCreatedMessage start -
	@Then("I enter a display name")
	public void i_enter_a_display_name() {
		customerPage.customer_page_BasicInfo_DisplayName_Field.sendKeys("Student");
	}
	@Then("click save")
	public void click_save() {
		customerPage.customer_page_NewCustomerSubmit_BTN.click();
	}
	@Then("I should see the pop up message {string}")
	public void i_should_see_the_pop_up_message(String message) {
		utils.waitForElementToBeVisible(customerPage.customer_page_newCustomer_Success_Message);
		Assert.assertEquals(customerPage.customer_page_newCustomer_Success_Message.getText(), message);
	}
	@Then("I delete the customer")
	public void i_delete_the_customer() {
		utils.waitForElementToBeVisible(dashPage.customers_tab);
		dashPage.customers_tab.click();
		utils.waitForElementToBeVisible(customerPage.customer_page_customerTable_3dotMoreLink);
		customerPage.customer_page_customerTable_3dotMoreLink.click();
		utils.waitForElementToBeVisible(customerPage.customer_page_customerTable_3dotMoreLink_Delete);
		customerPage.customer_page_customerTable_3dotMoreLink_Delete.click();
		utils.waitForElementToBeVisible(customerPage.customer_page_customerTable_3dotMoreLink_Delete_OK_BTN);
		customerPage.customer_page_customerTable_3dotMoreLink_Delete_OK_BTN.click();
		Driver.quitDriver();
	}
	// @newCustomerCreatedMessage end -
	
	// @newCustomerInvalidNoInfo start -
	@Then("I should see the error message “Field is required” below the Display Name field")
	public void i_should_see_the_error_message_field_is_required_below_the_display_name_field() {
		utils.waitForElementToBeVisible(customerPage.customer_page_newCustomer_FieldIsRequired_Error_Message);
		Assert.assertTrue(customerPage.customer_page_newCustomer_FieldIsRequired_Error_Message.isDisplayed());
	}
	// @newCustomerInvalidNoInfo end -
	
	// @newCustomers start - 
	@When("I enter a valid {string}, {string}, {string}, {string}, and {string}")
	public void i_enter_a_valid_and(String displayName, String email, String state, String city, String zipcode) throws InterruptedException {
		name = displayName;
		
		utils.waitForElementToBeVisible(customerPage.customer_page_BasicInfo_DisplayName_Field);
		customerPage.customer_page_BasicInfo_DisplayName_Field.sendKeys(name);
		customerPage.customer_page_BasicInfo_Email_Field.sendKeys(email);
		customerPage.customer_page_newCustomer_Billing_NameField.sendKeys(name);
		customerPage.customer_page_newCustomer_Billing_CityField.sendKeys(city);
		customerPage.customer_page_newCustomer_Billing_ZipcodeField.sendKeys(zipcode);
		utils.clickWithActionsClass(customerPage.customer_page_newCustomer_Billing_CountryDropDown);
		utils.waitForElementToBeVisible(customerPage.customer_page_newCustomer_Billing_CountryDropDown_UnitedStates);
		utils.clickWithActionsClass(customerPage.customer_page_newCustomer_Billing_CountryDropDown_UnitedStates);
//		customerPage.customer_page_newCustomer_CopyFromBillingBTN.click();
		utils.clickWithActionsClass(customerPage.customer_page_newCustomer_CopyFromBillingBTN);
//		utils.waitForElementToBeInputed(customerPage.customer_page_newCustomer_Shipping_NameField, name);
//		Thread.sleep(5000);
		utils.moveToWithActionsClass(customerPage.customer_page_NewCustomerSubmit_BTN);
		utils.waitUntilElementClickable(customerPage.customer_page_NewCustomerSubmit_BTN);
		customerPage.customer_page_NewCustomerSubmit_BTN.click();
	}
	@Then("I should see the new customer in the data table")
	public void i_should_see_the_new_customer_in_the_data_table() {
		utils.waitForElementToBeVisible(customerPage.customer_page_Sales_and_Expenses_header);
		Assert.assertTrue(Driver.getDriver().findElement(By.xpath("//p[contains(text(), '"+name+"')]")).isDisplayed());
	}
	// @newCustomers end - 
}
