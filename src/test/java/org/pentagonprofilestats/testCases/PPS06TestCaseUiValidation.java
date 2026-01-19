package org.pentagonprofilestats.testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pentagonprofilestats.testBase.BaseTestCase;
import org.testng.Assert;
import org.testng.annotations.*;

import org.pentagonprofilestats.pageObjects.PPSHomePageObjects;

public class PPS06TestCaseUiValidation extends BaseTestCase {

    private WebDriver driver;
    private PPSHomePageObjects page;

    @BeforeClass(groups = {"smoke", "functional", "positive", "negative", "name"})
    public void initializeObject(){
        page = getPPPHomePageObjects();
    }

    // -------- Header ----------
    @Test(priority = 1,groups = {"smoke", "ui", "header"} , description = "Verify header title is displayed")
    public void verifyHeaderIsDisplayed() {
        Assert.assertTrue(page.isHeaderDisplayed(), "Header title is not displayed.");
    }

    @Test(priority = 2,groups = {"ui", "header"} , description = "Verify header title text matches expected")
    public void verifyHeaderText() {
        Assert.assertEquals(page.getHeaderText(), "💎 Pentagon Profile Stats", "Header text mismatch.");
    }

    // -------- Labels: one method per label ----------
    @Test(priority = 3, groups = {"ui", "labels", "name"})
    public void verifyNameLabel() {
        Assert.assertTrue(page.isNameLabelDisplayed(), "Name label not displayed.");
        Assert.assertEquals(page.getNameLabelText(), "Name:", "Name label text mismatch.");
    }

    @Test(priority = 4, groups = {"ui", "labels", "experience"} )
    public void verifyYearsOfExperienceLabel() {
        Assert.assertTrue(page.isYearsOfExperienceLabelDisplayed(), "Years of Experience label not displayed.");
        Assert.assertEquals(page.getYearsOfExperienceLabelText(), "Years of Experience:", "Experience label text mismatch.");
    }

    @Test(priority = 5, groups = {"ui", "labels", "skills"} )
    public void verifySkillsLabel() {
        Assert.assertTrue(page.isSkillsLabelDisplayed(), "Skills label not displayed.");
        Assert.assertEquals(page.getSkillsLabelText(), "Skills:", "Skills label text mismatch.");
    }

    @Test(priority = 6, groups = {"ui", "labels", "websites"} )
    public void verifyWebsitesDevelopedLabel() {
        Assert.assertTrue(page.isWebsitesDevelopedLabelDisplayed(), "Websites Developed label not displayed.");
        Assert.assertEquals(page.getWebsitesDevelopedLabelText(), "Websites Developed:", "Websites label text mismatch.");
    }

    @Test(priority = 7, groups = {"ui", "labels", "apps"} )
    public void verifyAppsMadeLabel() {
        Assert.assertTrue(page.isAppsMadeLabelDisplayed(), "Apps Made label not displayed.");
        Assert.assertEquals(page.getAppsMadeLabelText(), "Apps Made:", "Apps Made label text mismatch.");
    }

    // -------- Inputs: one method per control ----------
    @Test(priority = 8, groups = {"ui", "inputs", "name"} )
    public void verifyUserNameInputDisplayed() {
        Assert.assertTrue(page.isUserNameInputDisplayed(), "User Name input not displayed.");
    }

    @Test(priority = 9, groups = {"ui", "inputs", "experience"} )
    public void verifyYearsOfExperienceSelectDisplayed() {
        Assert.assertTrue(page.isYearsOfExperienceSelectDisplayed(), "Years of Experience select not displayed.");
    }

    @Test(priority = 10, groups = {"ui", "inputs", "skills"} )
    public void verifySkillsInputDisplayed() {
        Assert.assertTrue(page.isSkillsInputDisplayed(), "Skills input not displayed.");
    }

    @Test(priority = 11, groups = {"ui", "inputs", "websites"} )
    public void verifyWebsitesDevelopedInputDisplayed() {
        Assert.assertTrue(page.isWebsitesDevelopedInputDisplayed(), "Websites Developed input not displayed.");
    }

    @Test(priority = 12, groups = {"ui", "inputs", "apps"} )
    public void verifyAppsMadeInputDisplayed() {
        Assert.assertTrue(page.isAppsMadeInputDisplayed(), "Apps Made input not displayed.");
    }

    @Test(priority = 13, groups = {"ui", "buttons", "smoke"} )
    public void verifyTrackButtonDisplayed() {
        Assert.assertTrue(page.isTrackButtonDisplayed(), "Track button not displayed.");
    }

    // -------- Optional: Verify Experience dropdown values ----------
    @Test(priority = 14, groups = {"ui", "dropdown", "experience", "functional"} , description = "Verify experience dropdown options")
    public void verifyExperienceDropdownValues() {
        page.clickYearsOfExperience();
        Assert.assertEquals(
                page.getExperienceDropDownList(),
                page.getActualExperienceDropDownList(),
                "Experience dropdown values mismatch."
        );
    }


}