package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class ItemsPage {
	
    public ItemsPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
    
    @FindBy (xpath = "//button[text()=' Add Item']")
    public WebElement items_page_addItem_btn;
    
    @FindBy (xpath = "//h3[text()='Items']")
    public WebElement items_page_item_headerText;
    
    @FindBy (xpath = "//h3[text()='New Item']")
    public WebElement items_Input_page_newItem_text;
    
    @FindBy (xpath = "//div[@class='relative rounded-md shadow-sm font-base']/input")
    public WebElement items_input_page_name_box;
    
    @FindBy (xpath = "//input[@id='0']")
    public WebElement items_input_page_price_box;
    
    @FindBy (xpath = "//div[@tabindex='-1']/input")
    public WebElement items_input_page_unit_dropdown;
    
    @FindBy (xpath = "//span[text()='pc']")
    public WebElement items_input_page_unit_pc_option;
    
    @FindBy (name = "description")
    public WebElement items_input_page_description;
    
    @FindBy (xpath = "//button[text()=' Save Item']")
    public WebElement items_page_saveItem_btn;
  
    @FindBy (xpath = "//button[text()='Filter ']")
    public WebElement items_page_filter_btn;
    
    @FindBy (xpath = "//input[@name='name']")
    public WebElement items_page_filter_name_box;
    
    @FindBy (xpath = "(//button[contains(@id, 'headlessui-menu-button')])[3]")
    public WebElement items_page_3dot_menu;
    
    @FindBy (xpath = "//a[text()=' Edit']")
    public WebElement items_page_3dot_edit_option;
    
    @FindBy (xpath = "//a[text()=' Delete']")
    public WebElement items_page_3dot_delete_option;
    
    @FindBy (xpath = "//h3[text()='Are you sure?']")
    public WebElement items_Input_delete_youSure_text;
    
    @FindBy (xpath = "//button[text()='Ok']")
    public WebElement items_page_delete_ok_btn;
    
    @FindBy (xpath = "//button[text()='Cancel']")
    public WebElement items_page_delete_cancel_btn;
    
    @FindBy (xpath = "//span[text()='No Results Found']")
    public WebElement items_Input_noResultFound_text;
    
    @FindBy (xpath = "//h3[text()='Edit Item']")
    public WebElement items_page_edit_item_headerText;
    
    @FindBy (xpath = "//button[text()=' Update Item']")
    public WebElement items_page_update_item_btn;
    
    
    
}
