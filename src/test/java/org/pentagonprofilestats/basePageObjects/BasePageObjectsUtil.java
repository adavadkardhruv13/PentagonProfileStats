package org.pentagonprofilestats.basePageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePageObjectsUtil {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected BasePageObjectsUtil(WebDriver driver, long timeouts){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
        PageFactory.initElements(driver,this);
    }

    protected WebElement waitVisible(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitClickable(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitClickable(By locator){
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitVisible(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}
