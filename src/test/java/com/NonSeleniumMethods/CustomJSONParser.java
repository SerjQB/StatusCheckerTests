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

    private String currentDomain = BasePage.currentDomain;

    public JSONObject getLatestMetricsObject(String applicationName) {
        JSONObject obj = null;
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(currentDomain + "/instance/" + applicationName + "/latest.json");
            HttpResponse result = httpClient.execute(request);

            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            try {
                JSONParser parser = new JSONParser();
                Object resultObject = parser.parse(json);
                JSONObject jsonObject = (JSONObject) resultObject;
                JSONArray jsonArray = (JSONArray) jsonObject.get("metrics");
                obj = (JSONObject) jsonArray.get(0);
                obj = (JSONObject) obj.get("metrics");

            } catch (Exception e) {
            }

        } catch (IOException ex) {
        }

        return obj;
    }
}
