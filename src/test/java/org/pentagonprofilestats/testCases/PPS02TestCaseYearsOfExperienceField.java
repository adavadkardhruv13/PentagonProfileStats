package org.pentagonprofilestats.testCases;

import org.pentagonprofilestats.pageObjects.PPSHomePageObjects;
import org.pentagonprofilestats.testBase.BaseTestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class PPS02TestCaseYearsOfExperienceField extends BaseTestCase {
    PPSHomePageObjects homeObj;

    @BeforeClass(groups = {"smoke", "functional", "positive", "negative", "name"})
    public void initializeObject(){
        homeObj = getPPPHomePageObjects();
    }


    @Test(priority = 1,groups = {"functional", "ui", "dropdown", "experience"})
    public void yearsOfExperienceFieldValidation(){
        List<String> actualYearsOfExperienceOptions = homeObj.getActualExperienceDropDownList();
        List<String> yearsOfExperienceCurrently = homeObj.getExperienceDropDownList();
        int i = 0;
        boolean flag = true;
        homeObj.clickYearsOfExperience();
        for(String actual : actualYearsOfExperienceOptions){
            if(i < actualYearsOfExperienceOptions.size()){
//                System.out.println(actual + " " + yearsOfExperienceCurrently.get(i));
                if(!actual.equalsIgnoreCase(yearsOfExperienceCurrently.get(i))){
//                    System.out.println(actual + " " + yearsOfExperienceCurrently.get(i));
                    flag = !flag;
                    break;
                }
                i++;
            }
        }
        Assert.assertTrue(flag);
    }

    @Test(priority = 2 ,groups = {"functional", "positive", "experience"} ,dataProvider = "yearsOfExperienceDataProvider", dataProviderClass = org.pentagonprofilestats.utilities.DataProviders.class)
    public void inputYearsOfExperience(String yearsOfExperience){
        homeObj.setYearsOfExperience(yearsOfExperience);
        String experienceEntered = homeObj.getYearsOfExperience();
        Assert.assertEquals(yearsOfExperience, experienceEntered);
    }

}
