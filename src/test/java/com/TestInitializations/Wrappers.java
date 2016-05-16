package com.TestInitializations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Alert;


public abstract class Wrappers {

    public abstract WebDriver getWebDriver();


    protected By findByCss(String cssSelector){
        return By.cssSelector(cssSelector);
    }

    protected By findById(String id){
        return By.id(id);
    }

    protected By findByXPath(String xpath){
        return By.xpath(xpath);
    }

    protected void waitUntilElement(By locator){
        WebDriverWait wait = new WebDriverWait(getWebDriver(), 5);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void waitUntilElementNotPresented(By locator){
        new WebDriverWait(getWebDriver(), 60).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected void waitUntilElementVisible(By locator){
        new WebDriverWait(getWebDriver(), 60).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected int getCountOfCssElements(String locator){return getWebDriver().findElements(By.cssSelector(locator)).size();}

    protected void type(By field, String value){
        getWebDriver().findElement(field).sendKeys(value);
    }

    protected void clearAndType(By field, String value){
        getWebDriver().findElement(field).clear();
        getWebDriver().findElement(field).sendKeys(value);
    }

    protected void click(By element){
        getWebDriver().findElement(element).click();
        pause(100);
    }

    protected void acceptAlert(){
        Alert alert = getWebDriver().switchTo().alert();
        alert.accept();
    }


    protected String getText(By element){
        return getWebDriver().findElement(element).getText();
    }

    protected String getValue(By element){
        return getWebDriver().findElement(element).getAttribute("value");
    }

    protected void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    protected Boolean isElementExist( By element){return (getWebDriver().findElements(element).size() != 0);
    }

    protected Boolean isElementPresented(By element){
        Boolean elementCondition = false;
        try{
            waitUntilElement(element);
        }
        catch (Exception e){
            return false;
        }

        if(isElementExist(element))
            elementCondition = getWebDriver().findElement(element).isDisplayed();

        return  elementCondition;
    }


    protected void open(String url){
        getWebDriver().get(url);
    }

}
