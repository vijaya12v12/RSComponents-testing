
package stepDefinitions;

import Utilities.Util;
import com.sun.org.apache.bcel.internal.generic.SWITCH;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;
import pageObjects.*;

import java.util.List;

public class RSOnlineSteps extends Util{

    @Given("^I am on RS Online Home Page$")
    public void I_am_on_RS_Online_Home_Page(){
        new HomePage().WaitForPageLoad();
    }

    @When("^I select product '(.*)' using All Products Dropdown$")
    public void I_select_product_using_All_Products_Dropdown(String product){
        new HomePage().SelectBatteryHydrometers_AllProducts(product);
    }


    @When("^add item to the basket$")
    public void add_item_to_the_basket(){
        new SearchResultPage().AddItemToBasket();
    }

    @When("^Go to View Basket$")
    public void Go_to_View_Basket(){
        new SearchResultPage().GoToBasket();
    }


    @Then("^I will see product '(.*)' in Basket Summary Page$")
    public void I_will_see_product_in_Basket_Summary_Page(String product){
        new BasketSummaryPage().VerifyItemDisplayed(product);
    }



    @Then("^I will see checkout securely button$")
    public void I_will_see_checkout_securely_button(){
        new BasketSummaryPage().VerifyCheckoutButtonDisplayed();
    }



    @When("^I select product '(.*)' using Our Brands Dropdown$")
    public void I_select_product_using_Our_Brands_Dropdown(String product){
        new HomePage().SelectBatteryHydrometers_OurBrands(product);
    }



    @When("^I Search Item using (.*)$")
    public void I_Search_Item_using(String product){
        new HomePage().SelectItem(product);
    }



    @When("^I Filter the results using (.*) and (.*)$")
    public void I_Filter_the_results_using(String filter, String filterValue){

        switch (filter) {
            case "OutputType":
                new SearchResultPage().AddOutputTypeFilter(filterValue);
                break;
            case "OutputCurrent":
                new SearchResultPage().AddOutputCurrentFilter(filterValue);
                break;
            default:
                break;
        }
    }



    @Then("^Item (.*) is listed in the search results$")
    public void Item_is_listed_in_the_search_results(String searchValue){
        new SearchResultPage().VerifyItemDisplayed(searchValue);
    }

}