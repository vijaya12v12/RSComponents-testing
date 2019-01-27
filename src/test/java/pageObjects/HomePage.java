
package pageObjects;

import Utilities.Environment_Config;
import Utilities.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Util {

    @FindBy(how = How.ID, using = "js-cookie-accept")
    public WebElement acceptCookies;

    @FindBy(how = How.ID, using = "searchTerm")
    public WebElement searchEdit;

    @FindBy(how = How.XPATH, using = ".//a[@title='All Products']")
    public WebElement allProducts;

    @FindBy(how = How.XPATH, using = ".//li[contains(@class,'verticalMenuOption')]/a[text()='Batteries']")
    public WebElement batteries;

    @FindBy(how = How.XPATH, using = ".//div[text()='Batteries']")
    public WebElement batteries1;

    @FindBy(how = How.XPATH, using = ".//a[text()='Battery Hydrometers']")
    public WebElement batteryHydrometers;

    @FindBy(how = How.XPATH, using = ".//a[@title='Our Brands']")
    public WebElement ourBrands;

    @FindBy(how = How.XPATH, using = ".//a[@title='RS PRO']")
    public WebElement RSPro;

    @FindBy(how = How.XPATH, using = ".//div[@class='shBasket js-basket']/a")
    public WebElement basket;

    @FindBy(how = How.XPATH, using = ".//button[text()='Add to basket']")
    public WebElement addToBasket;




    public HomePage()
    {
        PageFactory.initElements(driver,this);
    }


    public void WaitForPageLoad()
    {
        navigate(getURL());
        WaitForPageRefresh();
        if(isElementPresent(acceptCookies))
            click(acceptCookies);
        WaitForElement(addToBasket);
        WaitForPageRefresh();
    }

    public void SelectBatteryHydrometers_AllProducts(String product)
    {
        click(allProducts);
        click(batteries);
        click(batteryHydrometers);
        AssertDisplayed(ElementByXPath(".//a[text()='"+ product +"']"));
    }

    public void SelectBatteryHydrometers_OurBrands(String product)
    {
        click(ourBrands);
        click(RSPro);
        click(batteries1);
        click(batteryHydrometers);
        AssertDisplayed(ElementByXPath(".//a[text()='"+ product +"']"));
    }

    public void SelectItem(String product)
    {
        sendKeysEnter(searchEdit,product);
    }

}