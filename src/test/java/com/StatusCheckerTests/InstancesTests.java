package com.StatusCheckerTests;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import com.StatusCheckerPages.*;
import com.NonSeleniumMethods.CustomJSONParser;


public class InstancesTests extends BaseTest {


    @Test
    public void checkInstanceDisplaying() {
        int loopOperator = 0;
        String applicationName = "novochatqa";
        EditInstancePage editInstancePage = new EditInstancePage(driver);
        editInstancePage.visit(applicationName);
        String[] enabledMetrics = editInstancePage.getMetricsNameArray();

        ApplicationStatusPage applicationStatusPage = editInstancePage.openApplicationStatusPage(applicationName);
        CustomJSONParser customJSONParser = new CustomJSONParser();
        JSONObject latestMetrics = customJSONParser.getLatestMetricsObject(applicationName);

        Assert.assertEquals(latestMetrics.size()-1, enabledMetrics.length);
        for(String s : enabledMetrics){
            Assert.assertEquals(applicationStatusPage.getValueOfMetric(enabledMetrics[loopOperator]), latestMetrics.get(enabledMetrics[loopOperator]).toString());
            loopOperator++;
        }
    }
}
