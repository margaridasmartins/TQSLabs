package tqs.assignment.cleanair.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * AirMetricRestController
 */

@RestController("/api/v1/air")
public class AirMetricRestController {

    @Autowired
    public Map<String,Map<String,String[]>> cityMap;

    @GetMapping("/teste")
    @ResponseBody
    public Map<String,Map<String,String[]>> teste(){
        return cityMap;
    }
}