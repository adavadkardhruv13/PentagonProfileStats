package org.pentagonprofilestats.testCases;

import org.pentagonprofilestats.pageObjects.PPSHomePageObjects;
import org.pentagonprofilestats.pageObjects.PPSResultDisplayPageObjects;
import org.pentagonprofilestats.testBase.BaseTestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PPS07TestCaseProfileAnalysis extends BaseTestCase {

    PPSHomePageObjects homeObj;
    PPSResultDisplayPageObjects resultObj;
    @BeforeClass(groups = {"smoke", "functional", "positive", "negative", "name"})
    public void initializeObject(){
        homeObj = getPPPHomePageObjects();
        resultObj = getPPSResultDisplayPageObjects();
    }



    @Test(groups = {"e2e", "functional", "smoke", "profile", "resultcard"}, dataProvider = "profileAnalysisDataProvider",
            dataProviderClass = org.pentagonprofilestats.utilities.DataProviders.class)
    public void profileAnalysis_validatesResultCard(String name, String yearsOfExperience, String skills, String websitesDeveloped, String appsMade) {
        logger.info("Test Class : PPS07TestCaseProfileAnalysis | Test Started : profileAnalysis | Data Used : user name : '{}' - Years of Experience : '{}' - Skills : '{}' - Websites Developed : '{}' - Apps Made : '{}'",name,yearsOfExperience,skills,websitesDeveloped,appsMade);

        homeObj.setUserName(name)
                .setYearsOfExperience(yearsOfExperience)
                .setSkills(skills)
                .setWebsitesDeveloped(websitesDeveloped)
                .setAppsMade(appsMade)
                .clickTrack();

        logger.info("Clicked on Track Button");
        logger.info("waiting for result card Visibility");

        resultObj.waitForResultCardVisible();

        Assert.assertEquals(resultObj.getDisplayedName(), name);
        Assert.assertTrue(resultObj.isCupIconDisplayed());
        Assert.assertTrue(resultObj.isCatergoryDisplayed());
        Assert.assertTrue(resultObj.isStarsDisplayed());
        Assert.assertTrue(resultObj.isCaptionDisplayed());
        resultObj.clickCloseResultCard();
    }


}
