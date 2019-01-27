
package pageObjects;

import Utilities.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage extends Util {

    @FindBy(how = How.XPATH, using = ".//button[contains(@class,'addToBasketBtn')]")
    public WebElement addButton;

    @FindBy(how = How.XPATH, using = ".//div[contains(text(),'View Basket')]")
    public WebElement viewBasket;

    @FindBy(how = How.XPATH, using = ".//a[contains(text(),'Output Type')]")
    public WebElement outputtype;

    @FindBy(how = How.XPATH, using = ".//span[contains(text(),'Transistor')]/../..")
    public WebElement transistorType;

    @FindBy(how = How.XPATH, using = ".//div[@class='popover-content']//button[text()='Apply filters']")
    public WebElement applyFilters;

    @FindBy(how = How.XPATH, using = ".//a[contains(text(),'Output Current')]")
    public WebElement outputcurrent;

    @FindBy(how = How.XPATH, using = ".//span[contains(text(),'500 mA')]/../..")
    public WebElement fiveHundredMA;

    public SearchResultPage()
    {
        PageFactory.initElements(driver,this);
    }


    public void AddItemToBasket()
    {
        click(addButton);
        WaitForPageRefresh();
    }


    public void GoToBasket()
    {
        click(viewBasket);
        WaitForPageRefresh();
    }

    public void AddOutputTypeFilter(String filterValue)
    {
        click(outputtype);
        click(ElementByXPath(".//span[contains(text(),'"+ filterValue +"')]/../.."));
        click(applyFilters);
        WaitForPageRefresh();
    }

    public void AddOutputCurrentFilter(String filterValue)
    {
        click(outputcurrent);
        click(ElementByXPath(".//span[contains(text(),'"+ filterValue +"')]/../.."));
        click(applyFilters);
        WaitForPageRefresh();
    }

    public void VerifyItemDisplayed(String item)
    {
        WebElement element=null;
        try {
            element = ElementByXPath(".//span[contains(text(),'" + item + "')]");
        }catch (Exception e){}
        if(element != null)
            AssertDisplayed(element);
        else
            AssertDisplayed(ElementByXPath(".//a[contains(text(),'"+ item +"')]"));
    }



}