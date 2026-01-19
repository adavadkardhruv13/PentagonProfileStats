package org.pentagonprofilestats.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pentagonprofilestats.basePageObjects.BasePageObjectsUtil;


public class PPSResultDisplayPageObjects extends BasePageObjectsUtil {

    public PPSResultDisplayPageObjects(WebDriver driver, long timeouts){
        super(driver,timeouts);
    }

    @FindBy(tagName = "h2")
    WebElement displayedName;

    @FindBy(xpath = "//img[@class = 'cup-img']")
    WebElement cupIcon;

    @FindBy(xpath = "//div[@id='result']/h3")
    WebElement catergory;

    @FindBy(xpath = "//div[@class='stars']")
    WebElement stars;

    @FindBy(xpath = "//p[@class='caption']")
    WebElement caption;

    @FindBy(id = "cross")
    WebElement closeResultCard;

    public String getDisplayedName(){
        return waitVisible(displayedName).getText();
    }

    public boolean isNameDisplayed(){
        return waitVisible(displayedName).isDisplayed();
    }

    public boolean isCupIconDisplayed(){
        return waitVisible(cupIcon).isDisplayed();
    }

    public boolean isCatergoryDisplayed(){
        return waitVisible(catergory).isDisplayed();
    }

    public boolean isStarsDisplayed(){
        WebElement starsElement =  stars;
        return starsElement.getText().equalsIgnoreCase("") || starsElement.isDisplayed();
    }

    public boolean isCaptionDisplayed(){
        return waitVisible(caption).isDisplayed();
    }

    public void clickCloseResultCard(){
        waitClickable(closeResultCard).click();
    }


    public void waitForResultCardVisible() {
        By cardRoot = By.id("result");
        waitVisible(cardRoot);
    }



}
