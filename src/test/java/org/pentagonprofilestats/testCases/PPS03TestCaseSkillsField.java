package org.pentagonprofilestats.testCases;

import org.pentagonprofilestats.pageObjects.PPSHomePageObjects;
import org.pentagonprofilestats.testBase.BaseTestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class PPS03TestCaseSkillsField extends BaseTestCase {
    PPSHomePageObjects homeObj;
    @BeforeClass(groups = {"smoke", "functional", "positive", "negative", "name"})
    public void initializeObject(){
        homeObj = getPPPHomePageObjects();
    }

    @Test(priority = 1 ,groups = {"functional", "positive", "skills"} ,dataProvider = "skillsDataProvider", dataProviderClass = org.pentagonprofilestats.utilities.DataProviders.class)
    public void validateSkillsPopulatedPositive(String startingCharacter, String skillsWithCommaSeparated){
        homeObj.enterStartingCharacterofSkillInSkillsField(startingCharacter);
        List<String> skillsPopulated = homeObj.getSkillsPopulated();
        String[] actualSkillsNeedToPopulate = skillsWithCommaSeparated.split(",");
        boolean flag = true;
        for(String skill : actualSkillsNeedToPopulate){
            if(!skillsWithCommaSeparated.contains(skill)){
                flag = false;
                break;
            }
        }
        Assert.assertTrue(flag);
    }

    @Test(priority = 2 ,groups = {"functional", "negative", "skills"} ,dataProvider = "skillsNegativeDataProvider", dataProviderClass = org.pentagonprofilestats.utilities.DataProviders.class)
    public void validateSkillsPopulatedNegative(String startingCharacter, String skillsWithCommaSeparated){
        homeObj.enterStartingCharacterofSkillInSkillsField(startingCharacter);
        List<String> skillsPopulated = homeObj.getSkillsPopulated();
        String[] actualSkillsNeedToPopulate = skillsWithCommaSeparated.split(",");
        Assert.assertTrue(skillsPopulated.isEmpty());
    }


}
