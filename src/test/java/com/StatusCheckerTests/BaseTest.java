package com.StatusCheckerTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.Point;
import org.openqa.selenium.Dimension;
import com.TestInitializations.Wrappers;


import java.util.Random;
import java.util.UUID;
import java.nio.file.*;
import java.awt.Toolkit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseTest extends Wrappers {

    private static String currentBrowser = "Chrome" ;
    private static String currentDomain = "https://status.quickblox.com";

    protected WebDriver driver;
//    Setting chromedriver for Linux and Windows. Remember that names of executable drivers files are different
//    protected  WebDriver setChromeDriver(){
//        String pathToChromeDriver = Paths.get("./src/test/resources/ChromeDriver/chromedriver_linux").toAbsolutePath().toString();
//        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("start-maximized");
//        WebDriver chromeDriver = new ChromeDriver(options);
//        return chromeDriver;
//    }

//    Setting chromedriver for Mac
    protected  WebDriver setChromeDriver(){
        String pathToChromeDriver = Paths.get("./src/test/resources/ChromeDriver/chromedriver_mac").toAbsolutePath().toString();
        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
        WebDriver chromeDriver = new ChromeDriver();

        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point position = new Point(0, 0);
        chromeDriver.manage().window().setPosition(position);
        Dimension maximizedScreenSize =
                new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
        chromeDriver.manage().window().setSize(maximizedScreenSize);
        return chromeDriver;
    }


    protected  WebDriver setFirefoxDriver(){

        FirefoxDriver firefoxDriver = new FirefoxDriver();
        firefoxDriver.manage().window().maximize();

        return firefoxDriver;
    }



    // Before use Safari driver - you should download the latest version from here - http://www.seleniumhq.org/download/
    // and add him to your Safari browser as browser extension
    protected  WebDriver setSafariDriver(){

        SafariDriver safariDriver = new SafariDriver();
        safariDriver.manage().window().maximize();

        return safariDriver;
    }


    static public String getUniqueValue(){
        return UUID.randomUUID().toString().substring(1, 7);
    }

    static public String getUniqueId(){
        int var  = new Random(System.currentTimeMillis()).nextInt(9999999 - 111);
        return String.valueOf(var);
    }

    static public String getCurrentBrowser(){
        return currentBrowser;
    }

    static public String getCurrentDomain(){
        return currentDomain;
    }

    @Override
    public WebDriver getWebDriver() {
        return driver;
    }

    @Parameters({"browser", "domain"})
    @BeforeTest
    public void setUpBrowserAndDomain(String browser, String domain)
    {

        this.currentBrowser = browser;
        this.currentDomain = domain;
    }

    @BeforeMethod
    public void setUp()
    {
        if(currentBrowser.equals("Chrome"))
            driver = setChromeDriver();

        else if(currentBrowser.equals("Firefox"))
            driver = setFirefoxDriver();

        else if(currentBrowser.equals("Safari"))
            driver = setSafariDriver();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
