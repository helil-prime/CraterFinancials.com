Feature: Items Management

  Background: 
    Given As an entity user, I am logged in
    And I navigate to Items tab

  # In background, we put the first one or few given steps that are used by each scenarios so that we don't have to
  # redeclare that given steps in each scenarios.
  @createItem
  Scenario: As a user, I am able to create an item or a service
    When I click on the Add Item button
    And I should be on item input page
    When I provide item information name "", price "1800", unit "pc", and description "very nice coffee mug"
    And I click Save Item button
    Then The Item is added to the Item list table
    And I delete the item

  @editItem
  Scenario: As a user, I am able to update an item or a service
    When I click on the Add Item button
    And I should be on item input page
    When I provide item information name "Coffee mug", price "1800", unit "pc", and description "very nice coffee mug"
    And I click Save Item button
    Then The Item is added to the Item list table
    When I click on Edit button
    Then I should be on Edit items page
    When I update the items price to "2200"
    And I click on update item button
    Then Items price should be updated to "2200"
    And I delete the item

  @ScenarioOutlineDemo
  Scenario Outline: As a user, I am able to create items with data table
    When I click on the Add Item button
    And I should be on item input page
    When I provide item information name "<itemName>", price "<itemPrice>", unit "<unitType>", and description "<itemDes>"
    And I click Save Item button
    Then The Item is added to the Item list table
    And I delete the item
    
    Examples: Items Data
    | itemName        | itemPrice | unitType | itemDes                |
    | Water bottle    | 3800      | pc       | Very good water bottle |
    | Chair           | 5800      | pc       | Nice chair             |
    | Ugly coffee mug | 4500      | pc       | Very ugly coffee mug   |
    | Air conditioner | 28800     | pc       | Reliable AC            |
    
    
    
