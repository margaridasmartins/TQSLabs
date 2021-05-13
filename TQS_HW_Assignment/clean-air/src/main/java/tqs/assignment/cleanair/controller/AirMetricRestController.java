package tqs.assignment.cleanair.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * AirMetricRestController
 */

@RestController
@RequestMapping("/api/v1")
public class AirMetricRestController {

    @Autowired
    public Map<String,String[]> cityMap;

    @GetMapping("/air")
    public @ResponseBody Map<String,String[]> teste(){
        return cityMap;
    }


}