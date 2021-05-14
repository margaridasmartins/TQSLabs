package tqs.assignment.cleanair.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * AirMetric
 */

public class AirMetric {

    private double co;
    private double no;
    private double no2;
    private double o3;
    private double so2;
    private double pm2_5;
    private double pm10;
    private double nh3;
    private int aqi;
    private LocalDateTime mesureDate;

    public AirMetric(double co, double no, double no2, double o3, double so2,double pm2_5,double pm10, double nh3,int aqi, LocalDateTime mesureDate){
        this.co=co;
        this.no=no;
        this.o3=o3;
        this.so2=so2;
        this.pm2_5=pm2_5;
        this.nh3=nh3;
        this.pm10 = pm10;
        this.aqi=aqi;
        this.mesureDate=mesureDate;
    }
    public AirMetric(){

    }

    public double getCo(){
        return this.co;
    }

    public double getNo(){
        return this.no;
    }

    public double getNo2(){
        return this.no2;
    }

    public double getO3(){
        return this.o3;
    }

    public double getSo2(){
        return this.so2;
    }

    public double getPm2_5(){
        return this.pm2_5;
    }

    public double getPm10(){
        return this.pm10;
    }

    public double getNh3(){
        return this.nh3;
    }

    public int getAqi(){
        return this.aqi;
    }

    public LocalDateTime getMesureDate(){
        return this.mesureDate;
    }

    @Override
    public boolean equals(Object o) {
  
        // If the object is compared with itself then return true  
        if (o == this) {
            return true;
        }
  
        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof AirMetric)) {
            return false;
        }
          
        // typecast o to Complex so that we can compare data members 
        AirMetric aMetric = (AirMetric) o;
          
        // Compare the data members and return accordingly 
        return aMetric.getAqi()==this.aqi && aMetric.getCo()==this.co && aMetric.getNo()==this.no && aMetric.getNo2()==this.no2 
                && aMetric.getNh3()==this.nh3 && aMetric.getO3()==this.o3 && aMetric.getPm10()==this.pm10
                && aMetric.getSo2()==this.so2 && aMetric.getPm2_5()==this.pm2_5 && aMetric.getMesureDate()==this.mesureDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(co, no, no2, o3, so2, pm2_5, pm10, nh3, aqi, mesureDate);
    }

}