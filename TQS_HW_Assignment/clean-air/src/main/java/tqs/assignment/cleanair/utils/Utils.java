package tqs.assignment.cleanair.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Utils
 */
public class Utils {

    public JsonObject requestAPI(String API_URL) throws IOException, MalformedURLException{
        try {
            final URL url = new URL(API_URL);
            final URLConnection request = url.openConnection();
            request.connect();
    
            // Convert to a JSON object to print data
            JsonParser jp = new JsonParser(); //from gson
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
    
            JsonObject rootobj = root.getAsJsonObject(); 
            //JsonElement code = rootobj.get("condition"); 
    
            //String condition_code = rootobj.get("condition").toString();
    
            return rootobj; 
        } catch (Exception e) {
            throw e;
        }

        
    }
}