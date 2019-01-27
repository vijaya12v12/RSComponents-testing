
package pageObjects;

import Utilities.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class BasketSummaryPage extends Util {

    @FindBy(how = How.ID, using = "checkoutSecurelyAndPuchBtn")
    public WebElement checkoutButton;


    public BasketSummaryPage()
    {
        PageFactory.initElements(driver,this);
    }


    public void VerifyItemDisplayed(String item)
    {
        AssertDisplayed(ElementByXPath(".//a[text()='"+ item +"']"));
    }


    public void VerifyCheckoutButtonDisplayed()
    {
        AssertDisplayed(checkoutButton);
    }



}