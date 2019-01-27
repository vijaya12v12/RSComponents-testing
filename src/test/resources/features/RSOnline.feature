Feature: Automation Tests for RS Online

Scenario: End-End Tests for RS Online - From Product Dropdown
Given I am on RS Online Home Page
When I select product 'Glass Battery Hydrometer' using All Products Dropdown
And add item to the basket
And Go to View Basket
Then I will see product 'Glass Battery Hydrometer' in Basket Summary Page
And I will see checkout securely button


Scenario: End-End Tests for RS Online - From Brands Dropdown
Given I am on RS Online Home Page
When I select product 'Glass Battery Hydrometer' using Our Brands Dropdown
And add item to the basket
And Go to View Basket
Then I will see product 'Glass Battery Hydrometer' in Basket Summary Page
And I will see checkout securely button


Scenario Outline: Search for RS Online - Using Filters
Given I am on RS Online Home Page
When I Search Item using <SearchValue>
And I Filter the results using <Filter> and <FilterValue>
Then Item <SearchValue> is listed in the search results

Examples:
| SearchBy | SearchValue                                                                            | Filter        | FilterValue |
| Keyword  | Siemens S7-1200 PLC CPU, Ethernet Networking Profinet Interface, 75 kB Program Capacity| OutputType    | Transistor  |
| RSStockNo| 862-4471                                                                               |               |             |
| MfrPartNo| 6ES7214                                                                                | OutputCurrent | 500 mA       |