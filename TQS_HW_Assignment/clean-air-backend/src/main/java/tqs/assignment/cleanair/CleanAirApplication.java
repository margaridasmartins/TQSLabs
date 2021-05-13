package tqs.assignment.cleanair;

import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.beans.BeanProperty;
import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class CleanAirApplication {

	public static void main(String[] args) {
		SpringApplication.run(CleanAirApplication.class, args);
	}

	// Load File with Portuguese cities 
	@Bean
	public 	Map<String,String[]> city_map() throws Exception{
		try {
			String line="";
			Map<String,String[]> cityMap = new HashMap<String,String[]>();
			BufferedReader br = new BufferedReader(new FileReader("../pt.csv"));
			while ((line = br.readLine()) != null) {
				String[] city = line.split(",");
				String[] coord = new String[]{city[1],city[2]} ;
				cityMap.put(city[0], coord);
			}
			return cityMap;
		} catch (Exception e) {
			throw e;
		}
		
	}

	// Load File with Portuguese cities 
	@Bean
	public 	Map<String,List<String>> district_cities() throws Exception{
		try {
			String line="";
			Map<String,List<String>> districtMap = new TreeMap<String,List<String>>();
			BufferedReader br = new BufferedReader(new FileReader("../pt.csv"));
			while ((line = br.readLine()) != null) {
				String[] city = line.split(",");
				if (!districtMap.containsKey(city[5])){
					List<String> cities = new ArrayList<String>();
					cities.add(city[0]);
					districtMap.put(city[5],cities);
				}
				else{
					districtMap.get(city[5]).add(city[0]);
				}
			}
			for (String key : districtMap.keySet()){
				Collections.sort(districtMap.get(key));
			}

			return districtMap;
		} catch (Exception e) {
			throw e;
		}
		
	}

	@Bean
	public Docket projectApi() {
	   return new Docket(DocumentationType.SWAGGER_2).select()
		  .apis(RequestHandlerSelectors.basePackage("tqs.assignment.cleanair")).build();
	}
}