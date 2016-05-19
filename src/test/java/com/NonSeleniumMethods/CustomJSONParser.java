package com.NonSeleniumMethods;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.StatusCheckerPages.BasePage;

import java.io.IOException;


public class CustomJSONParser {

    private static String currentDomain = BasePage.currentDomain;

    public static JSONObject getLatestMetricsObject(String applicationName) {
        JSONObject obj = null;
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(currentDomain + "/instance/" + applicationName + "/latest.json");
            HttpResponse result = httpClient.execute(request);

            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            try {
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(json);
                JSONArray jsonArray = (JSONArray) jsonObject.get("metrics");
                obj = (JSONObject) jsonArray.get(0);
                obj = (JSONObject) obj.get("metrics");

            } catch (Exception e) {
            }

        } catch (IOException ex) {
        }

        return obj;
    }

    public static String  getHealthCheckValue(String applicationName) {
        String healthcheck = "";
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(currentDomain + "/instance/" + applicationName + "/latest.json");
            HttpResponse result = httpClient.execute(request);

            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            try {
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(json);
                JSONArray jsonArray = (JSONArray) jsonObject.get("healthcheck");
                jsonObject = (JSONObject) jsonArray.get(0);
                healthcheck = jsonObject.get("time").toString();

            } catch (Exception e) {
            }

        } catch (IOException ex) {
        }

        return healthcheck;
    }

    public static String getAdminStatisticsMetric(String metric) {
        String statValue = "";
        String metricLocate = "";
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(currentDomain + "/instance/prod" + "/latest.json");
            HttpResponse result = httpClient.execute(request);

            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            try {
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(json);
                JSONArray jsonArray = (JSONArray) jsonObject.get("metrics");
                jsonObject = (JSONObject) jsonArray.get(0);
                jsonObject = (JSONObject) jsonObject.get("stats");
                if(metric.equals("CPUUsage")){
                    metricLocate = "chatc";
                }

                else if(metric.equals("MessagesNumberPerSec")){
                    metricLocate = "chatm";
                }

                else if(metric.equals("PresencesNumberPerSec")){
                    metricLocate = "chatp";
                }

                else if(metric.equals("QueueSize")){
                    metricLocate = "chatq";
                }

                else if(metric.equals("ConnectionsNumber")){
                    metricLocate = "chatu";
                }
                jsonArray = (JSONArray) jsonObject.get(metricLocate);
                statValue = jsonArray.get(0).toString();
            } catch (Exception e) {
            }

        } catch (IOException ex) {
        }

        return statValue;
    }

    public static int getCountOfMetrics(String applicationName){
        return getLatestMetricsObject(applicationName).size();
    }
}
