export class AirMetrics{
    co: number;
    no: number;
    o3: number;
    so2: number;
    pm2_5: number;
    nh3: number;
    pm10: number;
    aqi: number;
    mesureDate: string;

    constructor(co: number, no: number, o3: number, so2: number, pm2_5: number, nh3: number, pm10: number, 
        aqi: number, mesureDate: string) {
        this.co=co;
        this.no=no;
        this.o3=o3;
        this.so2=so2;
        this.pm2_5=pm2_5;
        this.nh3=nh3;
        this.pm10=pm10;
        this.aqi=aqi;
        this.mesureDate=mesureDate;
    }

}