package org.pentagonprofilestats.testCases;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.pentagonprofilestats.pageObjects.PPSHomePageObjects;
import org.pentagonprofilestats.testBase.BaseTestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PPS05TestCaseAppsMade extends BaseTestCase {

    PPSHomePageObjects homeObj;

    @BeforeClass(groups = {"smoke", "functional", "positive", "negative", "name"})
    public void initialize(){
        homeObj = getPPPHomePageObjects();
    }

    @Test(priority = 1 ,groups = {"functional", "positive", "apps"} ,dataProvider = "appsMadeDataProvider", dataProviderClass = org.pentagonprofilestats.utilities.DataProviders.class)
    public void validateAppsMade(String noOfAppsMade){
        homeObj.setAppsMade(noOfAppsMade);
        Assert.assertEquals(homeObj.getAppsMade(), noOfAppsMade);
    }

    @Test(priority = 2 ,groups = {"functional", "negative", "apps"} ,dataProvider = "appsMadeNegativeTestCaseDataProvider", dataProviderClass = org.pentagonprofilestats.utilities.DataProviders.class)
    @Description("Negative Numerics are not allowed but it is accepting")
    @Severity(SeverityLevel.CRITICAL)
    public void validateNegativeTestCasesOfAppsMadeField(String noOfAppsMade){
        homeObj.setAppsMade(noOfAppsMade);
        takeScreenshot("AppsMadeNegativeValueAccepted");
        Assert.assertEquals(homeObj.getAppsMade(), "", "Should only accept positive numeric value");
    }

}
