package tqs.assignment.cleanair.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;

import tqs.assignment.cleanair.model.AirMetric;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;
import java.time.Instant;

/**
 * ExternalAPI
 */
@Component
public class ExternalAPI {

    public List<AirMetric> getMetricsFromAPI(String API_URL) throws IOException, MalformedURLException{
        try {
            final URL url = new URL(API_URL);
            final URLConnection request = url.openConnection();
            request.connect();
    
            // Convert to a JSON object to print data
            JsonParser jp = new JsonParser(); //from gson
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
    
            JsonObject rootobj = root.getAsJsonObject(); 

            return this.getMetricsFromJson(rootobj); 
            
        } catch (Exception e) {
            throw e;
        }  
    }

    private List<AirMetric> getMetricsFromJson(JsonObject rootobj){
        List<AirMetric> airMetrics = new ArrayList<AirMetric>();

        JsonArray airMetricList = rootobj.getAsJsonArray("list");

        for(JsonElement airM : airMetricList){
            JsonObject airMetric = airM.getAsJsonObject();

            int aqi = Integer.parseInt(airMetric.get("main").getAsJsonObject().get("aqi").toString());
            JsonObject otherMetrics = airMetric.get("components").getAsJsonObject();
            double co = Double.parseDouble(otherMetrics.get("co").toString());
            double no = Double.parseDouble(otherMetrics.get("no").toString());
            double no2 = Double.parseDouble(otherMetrics.get("no2").toString());
            double o3 = Double.parseDouble(otherMetrics.get("o3").toString());
            double so2 = Double.parseDouble(otherMetrics.get("so2").toString());
            double pm2_5 = Double.parseDouble(otherMetrics.get("pm2_5").toString());
            double pm10 = Double.parseDouble(otherMetrics.get("pm10").toString());
            double nh3= Double.parseDouble(otherMetrics.get("nh3").toString());
            long unix_timestamp = Long.parseLong(airMetric.get("dt").toString());

            LocalDateTime measureDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(unix_timestamp), TimeZone.getTimeZone("GMT+1").toZoneId()); 
            airMetrics.add(new AirMetric(co,no,no2,o3,so2,pm2_5,pm10,nh3,aqi,measureDate));
        }
        return airMetrics;
    }
}