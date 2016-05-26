package com.statuschecker.test;
import com.statuschecker.utils.CustomJSONParser;
import com.statuschecker.utils.CustomDateOperators;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import com.statuschecker.pages.*;


public class StatusInstancesTests extends BaseTest {

    private static final String cpuUsageStat = "CPUUsage";
    private static final String messagesNumberPerSecondStat = "MessagesNumberPerSec";
    private static final String presencesNumberPerSecStat = "PresencesNumberPerSec";
    private static final String queueSizeStat = "QueueSize";
    private static final String connectionsNumberStat = "ConnectionsNumber";

    private String applicationName;

    @Parameters({"appName"})
    @BeforeMethod
    public void setApplicationName(String appName){
        applicationName = appName;
    }

    @Test
    public void checkInstanceMetricsCount() {
        EditInstancePage editInstancePage = new EditInstancePage(driver);
        editInstancePage.visit(applicationName);
        String[] enabledMetrics = editInstancePage.getMetricsNameArray();

        ApplicationStatusPage applicationStatusPage = editInstancePage.openApplicationStatusPage(applicationName);

        Assert.assertEquals(CustomJSONParser.getCountOfMetrics(applicationName), enabledMetrics.length);
        Assert.assertEquals(applicationStatusPage.getCountOfMetrics(), enabledMetrics.length);
    }

    @Test
    public void checkInstanceLatestMetricsDisplaying() {
        int loopOperator = 0;
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
    public void checkInstanceMonthlyMetricsDisplaying() {
        int loopOperator = 0;
        int monthDecrement = 1;//choose value from 0 to 5 for selecting how long from current month will be the month
        //in the test. 0 - current month, 5 - five monthes ago.
        String operableMonth;
        String jsonMonthName;
        EditInstancePage editInstancePage = new EditInstancePage(driver);
        editInstancePage.visit(applicationName);
        String[] enabledMetrics = editInstancePage.getMetricsNameArray();

        ApplicationStatusPage applicationStatusPage = editInstancePage.openApplicationStatusPage(applicationName);
        operableMonth = CustomDateOperators.getMonthName(monthDecrement);
        jsonMonthName = CustomDateOperators.getMonthJsonFileName(monthDecrement);
        applicationStatusPage.openCalendar();
        applicationStatusPage.clickOnMonthByName(operableMonth);
        applicationStatusPage.waitUntilLoad();
        JSONObject monthlyMetrics = CustomJSONParser.getCalendarMetricsObject(applicationName, jsonMonthName);

        for(String s : enabledMetrics){
            Assert.assertEquals(applicationStatusPage.getValueOfMetric(enabledMetrics[loopOperator]),
                    monthlyMetrics.get(enabledMetrics[loopOperator]).toString());
            loopOperator++;
        }
    }

    @Test
    public void checkInstancePeriodMetricsDisplaying() {
        int loopOperator = 0;
        String fromDate[] = {"04", "14", "2016"};//corresponding month, day and year values
        String toDate[] = {"05", "07", "2016"};//corresponding month, day and year values

        String jsonPeriodName;

        EditInstancePage editInstancePage = new EditInstancePage(driver);
        editInstancePage.visit(applicationName);
        String[] enabledMetrics = editInstancePage.getMetricsNameArray();

        ApplicationStatusPage applicationStatusPage = editInstancePage.openApplicationStatusPage(applicationName);
        jsonPeriodName = CustomDateOperators.getPeriodJsonFileName(fromDate, toDate);
        applicationStatusPage.openCalendar();
        applicationStatusPage.fillCalendarWithCorrectData(fromDate, toDate);
        applicationStatusPage.clickOnApplyButton();
        applicationStatusPage.waitUntilLoad();
        JSONObject monthlyMetrics = CustomJSONParser.getCalendarMetricsObject(applicationName, jsonPeriodName);

        for(String s : enabledMetrics){
            Assert.assertEquals(applicationStatusPage.getValueOfMetric(enabledMetrics[loopOperator]),
                    monthlyMetrics.get(enabledMetrics[loopOperator]).toString());
            loopOperator++;
        }
    }

    @Test
    public void checkInstanceLatestHealthCheckDisplaying() {

        ApplicationStatusPage applicationStatusPage = new ApplicationStatusPage(driver);
        applicationStatusPage.visit(applicationName);

        Assert.assertEquals(applicationStatusPage.getHealthCheckValue(),
                CustomJSONParser.getHealthCheckValue(applicationName));
    }

    @Test
    public void checkInstanceMonthlyHealthCheckDisplaying() {
        int monthDecrement = 0;//choose value from 0 to 5 for selecting how long from current month will be the month
        //in the test. 0 - current month, 5 - five monthes ago.
        String operableMonth;
        String jsonMonthName;

        ApplicationStatusPage applicationStatusPage = new ApplicationStatusPage(driver);
        applicationStatusPage.visit(applicationName);

        operableMonth = CustomDateOperators.getMonthName(monthDecrement);
        jsonMonthName = CustomDateOperators.getMonthJsonFileName(monthDecrement);
        applicationStatusPage.openCalendar();
        applicationStatusPage.clickOnMonthByName(operableMonth);
        applicationStatusPage.waitUntilLoad();

        Assert.assertEquals(applicationStatusPage.getHealthCheckValue(),
                CustomJSONParser.getCalendarHealthCheckValue(applicationName, jsonMonthName));
    }

    @Test
    public void checkInstancePeriodHealthCheckDisplaying() {
        String fromDate[] = {"05", "01", "2016"};//corresponding month, day and year values
        String toDate[] = {"05", "10", "2016"};//corresponding month, day and year values

        String jsonPeriodName;

        ApplicationStatusPage applicationStatusPage = new ApplicationStatusPage(driver);
        applicationStatusPage.visit(applicationName);

        applicationStatusPage.openCalendar();
        applicationStatusPage.fillCalendarWithCorrectData(fromDate, toDate);
        applicationStatusPage.clickOnApplyButton();
        applicationStatusPage.waitUntilLoad();
        jsonPeriodName = CustomDateOperators.getPeriodJsonFileName(fromDate, toDate);

        Assert.assertEquals(applicationStatusPage.getHealthCheckValue(),
                CustomJSONParser.getCalendarHealthCheckValue(applicationName, jsonPeriodName));
    }

    @Test
    public void checkLatestAdminStatsDisplaying() {

        ProdStatusPage prodStatusPage = new ProdStatusPage(driver);
        prodStatusPage.visit();

        JSONObject adminStatsObject = CustomJSONParser.getAdminStatisticsObject();

        Assert.assertEquals(prodStatusPage.getAdminStat(cpuUsageStat),
                CustomJSONParser.getAdminStatisticsMetric(adminStatsObject, cpuUsageStat));
        Assert.assertEquals(prodStatusPage.getAdminStat(messagesNumberPerSecondStat),
                CustomJSONParser.getAdminStatisticsMetric(adminStatsObject, messagesNumberPerSecondStat));
        Assert.assertEquals(prodStatusPage.getAdminStat(presencesNumberPerSecStat),
                CustomJSONParser.getAdminStatisticsMetric(adminStatsObject, presencesNumberPerSecStat));
        Assert.assertEquals(prodStatusPage.getAdminStat(queueSizeStat),
                CustomJSONParser.getAdminStatisticsMetric(adminStatsObject, queueSizeStat));
        Assert.assertEquals(prodStatusPage.getAdminStat(connectionsNumberStat),
                CustomJSONParser.getAdminStatisticsMetric(adminStatsObject, connectionsNumberStat));
    }

    @Test
    public void checkMonthlyAdminStatsDisplaying() {
        int monthDecrement = 1;//choose value from 0 to 5 for selecting how long from current month will be the month
        //in the test. 0 - current month, 5 - five monthes ago.
        String operableMonth;
        String jsonMonthName;
        ProdStatusPage prodStatusPage = new ProdStatusPage(driver);
        prodStatusPage.visit();

        operableMonth = CustomDateOperators.getMonthName(monthDecrement);
        jsonMonthName = CustomDateOperators.getMonthJsonFileName(monthDecrement);
        prodStatusPage.openCalendar();
        prodStatusPage.clickOnMonthByName(operableMonth);
        prodStatusPage.waitUntilLoad();
        JSONObject adminStatsObject = CustomJSONParser.getCalendarAdminStatisticsObject(jsonMonthName);

        Assert.assertEquals(prodStatusPage.getAdminStat(cpuUsageStat),
                CustomJSONParser.getAdminStatisticsMetric(adminStatsObject, cpuUsageStat));
        Assert.assertEquals(prodStatusPage.getAdminStat(messagesNumberPerSecondStat),
                CustomJSONParser.getAdminStatisticsMetric(adminStatsObject, messagesNumberPerSecondStat));
        Assert.assertEquals(prodStatusPage.getAdminStat(presencesNumberPerSecStat),
                CustomJSONParser.getAdminStatisticsMetric(adminStatsObject, presencesNumberPerSecStat));
        Assert.assertEquals(prodStatusPage.getAdminStat(queueSizeStat),
                CustomJSONParser.getAdminStatisticsMetric(adminStatsObject, queueSizeStat));
        Assert.assertEquals(prodStatusPage.getAdminStat(connectionsNumberStat),
                CustomJSONParser.getAdminStatisticsMetric(adminStatsObject, connectionsNumberStat));
    }

    @Test
    public void checkPeriodAdminStatsDisplaying() {
        String fromDate[] = {"05", "01", "2016"};//corresponding month, day and year values
        String toDate[] = {"05", "10", "2016"};//corresponding month, day and year values

        String jsonPeriodName;

        ProdStatusPage prodStatusPage = new ProdStatusPage(driver);
        prodStatusPage.visit();

        prodStatusPage.openCalendar();
        prodStatusPage.fillCalendarWithCorrectData(fromDate, toDate);
        prodStatusPage.clickOnApplyButton();
        prodStatusPage.waitUntilLoad();
        jsonPeriodName = CustomDateOperators.getPeriodJsonFileName(fromDate, toDate);
        JSONObject adminStatsObject = CustomJSONParser.getCalendarAdminStatisticsObject(jsonPeriodName);

        Assert.assertEquals(prodStatusPage.getAdminStat(cpuUsageStat),
                CustomJSONParser.getAdminStatisticsMetric(adminStatsObject, cpuUsageStat));
        Assert.assertEquals(prodStatusPage.getAdminStat(messagesNumberPerSecondStat),
                CustomJSONParser.getAdminStatisticsMetric(adminStatsObject, messagesNumberPerSecondStat));
        Assert.assertEquals(prodStatusPage.getAdminStat(presencesNumberPerSecStat),
                CustomJSONParser.getAdminStatisticsMetric(adminStatsObject, presencesNumberPerSecStat));
        Assert.assertEquals(prodStatusPage.getAdminStat(queueSizeStat),
                CustomJSONParser.getAdminStatisticsMetric(adminStatsObject, queueSizeStat));
        Assert.assertEquals(prodStatusPage.getAdminStat(connectionsNumberStat),
                CustomJSONParser.getAdminStatisticsMetric(adminStatsObject, connectionsNumberStat));
    }

    @Test
    public void fullScreenWithHiddenGraphs() {
        double compressedWidth;
        double fullScreenWidth;

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

        ApplicationStatusPage applicationStatusPage = new ApplicationStatusPage(driver);
        applicationStatusPage.visit(applicationName);

        compressedWidth = applicationStatusPage.getContentWidth();
        graphsNumber = applicationStatusPage.getCountOfAllMetrics();
        applicationStatusPage.clickOnExpandAllButton();
        applicationStatusPage.waitForExpandedGraphs();
        applicationStatusPage.clickOnFullScreenButton();
        applicationStatusPage.waitForFullScreen();
        pause(1000);
        applicationStatusPage.waitForExpandedChangeScreen();
        fullScreenWidth = applicationStatusPage.getContentWidth();
        Assert.assertTrue(compressedWidth * 1.5 < fullScreenWidth);
        Assert.assertEquals(applicationStatusPage.getCountOfFullScreenExpandedGraphs(), graphsNumber);

        applicationStatusPage.clickOnFullScreenButton();
        applicationStatusPage.waitForExpandedChangeScreen();
        pause(1000);
        Assert.assertEquals(applicationStatusPage.getCountOfFullScreenExpandedGraphs(), graphsNumber);
        Assert.assertTrue(fullScreenWidth > applicationStatusPage.getContentWidth() * 1.5);
    }

    @Test
    public void openAllGraphsManually() {
        int graphsNumber;

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
