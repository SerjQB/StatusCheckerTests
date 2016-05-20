package com.StatusCheckerTests;
import com.NonSeleniumMethods.CustomJSONParser;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import com.StatusCheckerPages.*;


public class StatusInstancesTests extends BaseTest {

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

    @Test
    public void fullScreenWithHiddenGraphs() {
        double compressedWidth;
        double fullScreenWidth;
        String applicationName = "novochatqa";

        ApplicationStatusPage applicationStatusPage = new ApplicationStatusPage(driver);
        applicationStatusPage.visit(applicationName);

        compressedWidth = applicationStatusPage.getContentWidth();
        applicationStatusPage.clickOnFullScreenButton();
        applicationStatusPage.waitForFullScreen();
        pause(1000);
        fullScreenWidth = applicationStatusPage.getContentWidth();
        Assert.assertTrue(compressedWidth * 1.5 < fullScreenWidth);

        applicationStatusPage.clickOnFullScreenButton();
        applicationStatusPage.waitForCompressedScreen();
        pause(1000);
        Assert.assertTrue(fullScreenWidth > applicationStatusPage.getContentWidth() * 1.5);
    }

    @Test
    public void expandAllGraphs() {
        int graphsNumber;
        String applicationName = "novochatqa";

        ApplicationStatusPage applicationStatusPage = new ApplicationStatusPage(driver);
        applicationStatusPage.visit(applicationName);

        graphsNumber = applicationStatusPage.getCountOfAllMetrics();
        applicationStatusPage.clickOnExpandAllButton();
        applicationStatusPage.waitForExpandedGraphs();
        pause(1000);
        Assert.assertEquals(applicationStatusPage.getCountOfExpandedGraphs(), graphsNumber);

        applicationStatusPage.clickOnExpandAllButton();
        applicationStatusPage.waitForHiddenGraphs();
        pause(1000);
        Assert.assertEquals(applicationStatusPage.getCountOfExpandedGraphs(), 0);
    }

    @Test
    public void fullScreenWithExpandedGraphs() {
        double compressedWidth;
        double fullScreenWidth;
        int graphsNumber;
        String applicationName = "novochatqa";

        ApplicationStatusPage applicationStatusPage = new ApplicationStatusPage(driver);
        applicationStatusPage.visit(applicationName);

        compressedWidth = applicationStatusPage.getContentWidth();
        graphsNumber = applicationStatusPage.getCountOfAllMetrics();
        applicationStatusPage.clickOnExpandAllButton();
        applicationStatusPage.waitForExpandedGraphs();
        applicationStatusPage.clickOnFullScreenButton();
        applicationStatusPage.waitForFullScreen();
        applicationStatusPage.waitForExpandedChangeScreen();
        pause(1000);
        fullScreenWidth = applicationStatusPage.getContentWidth();
        Assert.assertTrue(compressedWidth * 1.5 < fullScreenWidth);
        Assert.assertEquals(applicationStatusPage.getCountOfExpandedGraphs(), graphsNumber);

        applicationStatusPage.clickOnFullScreenButton();
        applicationStatusPage.waitForExpandedChangeScreen();
        pause(1000);
        Assert.assertTrue(fullScreenWidth > applicationStatusPage.getContentWidth() * 1.5);
    }

    @Test
    public void openAllGraphsManually() {
        int graphsNumber;
        String applicationName = "novochatqa";

        ApplicationStatusPage applicationStatusPage = new ApplicationStatusPage(driver);
        applicationStatusPage.visit(applicationName);

        graphsNumber = applicationStatusPage.getCountOfAllMetrics();
        for(int i = 1; i <= graphsNumber; i++){
            applicationStatusPage.clickMetricIndex(i);
            Assert.assertTrue(applicationStatusPage.isIndexGraphPresented(i));
        }
        Assert.assertEquals(applicationStatusPage.getCountOfExpandedGraphs(), graphsNumber);
    }

    @Test
    public void hideAllGraphsManually() {
        int graphsNumber;
        String applicationName = "novochatqa";

        ApplicationStatusPage applicationStatusPage = new ApplicationStatusPage(driver);
        applicationStatusPage.visit(applicationName);
        graphsNumber = applicationStatusPage.getCountOfAllMetrics();
        applicationStatusPage.clickOnExpandAllButton();
        applicationStatusPage.waitForExpandedGraphs();
        pause(1000);

        for(int i = 1; i <= graphsNumber; i++){
            applicationStatusPage.clickMetricIndex(i);
            Assert.assertTrue(applicationStatusPage.isIndexGraphHidden(i));
        }
        Assert.assertEquals(applicationStatusPage.getCountOfHiddenGraphs(), graphsNumber);
    }
}
