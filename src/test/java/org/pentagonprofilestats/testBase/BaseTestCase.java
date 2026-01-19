package org.pentagonprofilestats.testBase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.pentagonprofilestats.pageObjects.PPSHomePageObjects;
import org.pentagonprofilestats.pageObjects.PPSResultDisplayPageObjects;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;


public class BaseTestCase {
    public WebDriver driver;
    public Logger logger;
    public ExtentReports extent;
    public ExtentTest test;
    Properties properties;

    @BeforeClass(groups = {"smoke", "functional", "positive", "negative", "name"})
    @Parameters({"os","browser"})
    public void driverSetup(String os, String browser) throws IOException {

        FileReader file = new FileReader("src/test/resources/config.properties");
        properties = new Properties();
        properties.load(file);

        logger = LogManager.getLogger(this.getClass());

        if(properties.getProperty("execution_env").equalsIgnoreCase("remote")){
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            if(os.equalsIgnoreCase("windows")){
                desiredCapabilities.setPlatform(Platform.WIN11);
                logger.info("Class : BaseTestCase | Method : driverSetup | In Remote Execution (Grid) | Selected OS Platform : '{}'", os);
            }else if(os.equalsIgnoreCase("mac")){
                desiredCapabilities.setPlatform(Platform.MAC);
                logger.info("Class : BaseTestCase | Method : driverSetup | In Remote Execution (Grid) | Selected OS Platform : '{}'", os);
            }else{
                logger.error("Class : BaseTestCase | Method : driverSetup | In Remote Execution (Grid) | ******** No Matching Operating System Found ********");
                return;
            }

            switch (browser.toLowerCase()){
                case "chrome" :
                    desiredCapabilities.setBrowserName("chrome");
                    logger.info("Class : BaseTestCase | Method : driverSetup | In Remote Execution (Grid) | Selected Browser : '{}'", browser);
                    break;
                case "edge" :
                    desiredCapabilities.setBrowserName("MicrosoftEdge");
                    logger.info("Class : BaseTestCase | Method : driverSetup | In Remote Execution (Grid) | Selected Browser : '{}'", browser);
                    break;
                default:
                    logger.info("Class : BaseTestCase | Method : driverSetup | In Remote Execution (Grid) | ******** No Matching Browser Found ********");
                    return;

            }

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);

        }
        else if(properties.getProperty("execution_env").equalsIgnoreCase("local")){
            switch (browser.toLowerCase()){
                case "chrome" :
                    driver = new ChromeDriver();
                    logger.info("Class : BaseTestCase | Method : driverSetup | In Local Execution | Selected Browser : '{}'", browser);
                    break;
                case "edge" :
                    driver = new EdgeDriver();
                    logger.info("Class : BaseTestCase | Method : driverSetup | In Local Execution | Selected Browser : '{}'", browser);
                    break;
                case "firefox" :
                    driver = new FirefoxDriver();
                    logger.info("Class : BaseTestCase | Method : driverSetup | In Local Execution | Selected Browser : '{}'", browser);
                    break;
                default:
                    logger.info("Class : BaseTestCase | Method : driverSetup | In Local Execution | ******** No Matching Browser Found ********");
                    return;

            }
        }
        driver.get("https://kashishbarnwal2611.github.io/PentagonProfileStats/");
        driver.manage().window().maximize();
    }

    @BeforeMethod(groups = {"smoke", "functional", "positive", "negative", "name"})
    public void refresh(){
        driver.navigate().refresh();
    }


    public PPSHomePageObjects getPPPHomePageObjects(){
        return new PPSHomePageObjects(driver, 10);
    }

    public PPSResultDisplayPageObjects getPPSResultDisplayPageObjects(){
        return new PPSResultDisplayPageObjects(driver, 10);
    }


    @AfterClass(groups = {"smoke", "functional", "positive", "negative", "name"})
    public void close(){
        if(driver != null){
            driver.quit();
        }
        if(extent!=null){
            extent.flush();;
        }
    }



    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshotForAllure(){
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }



    public String takeScreenshot(String screenshotName){
        String dateName = new java.text.SimpleDateFormat("yyyyMMddhhmmss").format(new java.util.Date());
        TakesScreenshot ss = (TakesScreenshot) driver;
        File source = ss.getScreenshotAs(OutputType.FILE);

        String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + "_" + dateName + ".png";
        File finalDestination = new File(destination);

        try{
            FileUtils.copyFile(source, finalDestination);
//            System.out.println("ScreenShot saved: "+destination);
            if(test!=null){
                test.addScreenCaptureFromPath(destination);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return destination;
    }





}
