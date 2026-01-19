package org.pentagonprofilestats.testCases;

import org.pentagonprofilestats.pageObjects.PPSResultDisplayPageObjects;
import org.pentagonprofilestats.testBase.BaseTestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PPS08TestCaseResultCard extends BaseTestCase {

    PPSResultDisplayPageObjects resultObj;
    @BeforeClass
    public void initializeObject(){
        resultObj = getPPSResultDisplayPageObjects();
    }


    @Test(dependsOnGroups = "profileFlow")
    public void verifyDisplayName(org.testng.ITestContext ctx) {
        resultObj.waitForResultCardVisible();
        String displayedName = resultObj.getDisplayedName();
        String actualName = (String) ctx.getAttribute("actualName");

        org.testng.asserts.SoftAssert soft = new org.testng.asserts.SoftAssert();
        soft.assertTrue(resultObj.isNameDisplayed(), "Name not displayed on Result card");
        soft.assertAll();

        Assert.assertEquals(displayedName, actualName, "Displayed Name is not same as Actual Name");
    }

    @Test(dependsOnGroups = "profileFlow")
    public void verifyCupIcon() {
        resultObj.waitForResultCardVisible();
        Assert.assertTrue(resultObj.isCupIconDisplayed(), "Cup Icon not displayed on Result Card");
    }

    @Test(dependsOnGroups = "profileFlow")
    public void verifyCategory() {
        resultObj.waitForResultCardVisible();
        Assert.assertTrue(resultObj.isCatergoryDisplayed(), "Category not displayed on Result Card");
    }

    @Test(dependsOnGroups = "profileFlow")
    public void verifyStar() {
        resultObj.waitForResultCardVisible();
        Assert.assertTrue(resultObj.isStarsDisplayed(), "Stars not displayed on Result Card");
    }

    @Test(dependsOnGroups = "profileFlow")
    public void verifyCaption() {
        resultObj.waitForResultCardVisible();
        Assert.assertTrue(resultObj.isCaptionDisplayed(), "Caption not displayed on Result Card");
    }

}
