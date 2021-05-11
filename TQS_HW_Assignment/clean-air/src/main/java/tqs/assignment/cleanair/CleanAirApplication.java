package tqs.assignment.cleanair;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;  

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CleanAirApplication {

	public static void main(String[] args) {
		SpringApplication.run(CleanAirApplication.class, args);
	}

	// Load File with Portuguese cities 
	@Bean
	public 	Map<String,Map<String,String[]>> city_map() throws Exception{
		try {
			String line="";
			Map<String,Map<String,String[]>> cityMap = new HashMap();
			BufferedReader br = new BufferedReader(new FileReader("../pt.csv"));
			while ((line = br.readLine()) != null) {
				String[] city = line.split(",");
				Map<String,String[]> locationMap;
				if (!cityMap.containsKey(city[5])){
					locationMap = new HashMap();
					cityMap.put(city[5],locationMap);
				}
				else{
					locationMap = cityMap.get(city[5]);
					String[] coord = new String[]{city[1],city[2]} ;
					locationMap.put(city[0], coord);
				}

			}
			return cityMap;
		} catch (Exception e) {
			throw e;
		}
		
	}
}
