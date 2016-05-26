package com.statuschecker.test;
import org.testng.Assert;
import org.testng.annotations.*;
import com.statuschecker.pages.*;


public class DisplayPageTests extends BaseTest {

    @Test
    public void checkNumberOfActiveInstances() {
        int numberOfEnabledInstances;

        ManageInstancesPage manageInstancesPage = new ManageInstancesPage(driver);
        manageInstancesPage.visit();
        numberOfEnabledInstances = manageInstancesPage.getNumberOfEnabledInstances();
        DisplayPage displayPage = manageInstancesPage.openDisplayPage();
        displayPage.visit();
        displayPage.waitForAllInstances(numberOfEnabledInstances);
        Assert.assertEquals(displayPage.getNumberInCounter(), numberOfEnabledInstances);
        Assert.assertEquals(displayPage.getNumberOfGraphs(), numberOfEnabledInstances);
    }


}
