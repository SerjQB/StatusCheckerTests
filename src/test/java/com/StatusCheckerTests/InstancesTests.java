package com.StatusCheckerTests;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import com.StatusCheckerPages.*;
import com.NonSeleniumMethods.CustomJSONParser;


public class InstancesTests extends BaseTest {
    private static final String cpuUsageStat = "CPUUsage";
    private static final String messagesNumberPerSecondStat = "MessagesNumberPerSec";
    private static final String presencesNumberPerSecStat = "PresencesNumberPerSec";
    private static final String queueSizeStat = "QueueSize";
    private static final String connectionsNumberStat = "ConnectionsNumber";

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

    @Test
    public void checkAdminStatsDisplaying() {

        ProdStatusPage prodStatusPage = new ProdStatusPage(driver);
        prodStatusPage.visit();

        Assert.assertEquals(prodStatusPage.getAdminStat(cpuUsageStat),
                CustomJSONParser.getAdminStatisticsMetric(cpuUsageStat));
        Assert.assertEquals(prodStatusPage.getAdminStat(messagesNumberPerSecondStat),
                CustomJSONParser.getAdminStatisticsMetric(messagesNumberPerSecondStat));
        Assert.assertEquals(prodStatusPage.getAdminStat(presencesNumberPerSecStat),
                CustomJSONParser.getAdminStatisticsMetric(presencesNumberPerSecStat));
        Assert.assertEquals(prodStatusPage.getAdminStat(queueSizeStat),
                CustomJSONParser.getAdminStatisticsMetric(queueSizeStat));
        Assert.assertEquals(prodStatusPage.getAdminStat(connectionsNumberStat),
                CustomJSONParser.getAdminStatisticsMetric(connectionsNumberStat));
    }

}
