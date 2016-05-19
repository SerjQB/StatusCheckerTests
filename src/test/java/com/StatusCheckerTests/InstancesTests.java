package com.StatusCheckerTests;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import com.StatusCheckerPages.*;
import com.NonSeleniumMethods.CustomJSONParser;


public class InstancesTests extends BaseTest {


    @Test
    public void checkInstanceMetricsDisplaying() {
        int loopOperator = 0;
        String applicationName = "novochatqa";
        EditInstancePage editInstancePage = new EditInstancePage(driver);
        editInstancePage.visit(applicationName);
        String[] enabledMetrics = editInstancePage.getMetricsNameArray();

        ApplicationStatusPage applicationStatusPage = editInstancePage.openApplicationStatusPage(applicationName);
        JSONObject latestMetrics = CustomJSONParser.getLatestMetricsObject(applicationName);

        for(String s : enabledMetrics){
            Assert.assertEquals(applicationStatusPage.getValueOfMetric(enabledMetrics[loopOperator]),
                    latestMetrics.get(enabledMetrics[loopOperator]).toString());
            loopOperator++;
        }
    }

    @Test
    public void checkInstanceMetricsCount() {
        String applicationName = "novochatqa";
        EditInstancePage editInstancePage = new EditInstancePage(driver);
        editInstancePage.visit(applicationName);
        String[] enabledMetrics = editInstancePage.getMetricsNameArray();

        ApplicationStatusPage applicationStatusPage = editInstancePage.openApplicationStatusPage(applicationName);

        Assert.assertEquals(CustomJSONParser.getCountOfMetrics(applicationName), enabledMetrics.length);
        Assert.assertEquals(applicationStatusPage.getCountOfMetrics(), enabledMetrics.length);
    }

    @Test
    public void checkInstanceHealthCheckDisplaying() {
        String applicationName = "novochatqa";

        ApplicationStatusPage applicationStatusPage = new ApplicationStatusPage(driver);
        applicationStatusPage.visit(applicationName);

        Assert.assertEquals(applicationStatusPage.getHealthCheckValue(),
                CustomJSONParser.getHealthCheckValue(applicationName));
    }
}
