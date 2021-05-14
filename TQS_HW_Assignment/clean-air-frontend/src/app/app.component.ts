import { Component, OnInit } from '@angular/core';
import { AirMetrics } from './airMetric/airMetric';
import {HttpClient,HttpHeaders} from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({ 
    'Access-Control-Allow-Origin':'*',
  })
};

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'Clean Air';

  private baseURL = 'http://127.0.0.1:8081/api/v1/';

  pesquisa: boolean = false;

  distritos_concelhos!:  Map<string, string[]>;

  distritos!: string[];
  distrito!: string;

  concelhos!: string[];
  concelho!: string;

  airMetric_atual!: AirMetrics;
  airMetics!: AirMetrics[];

  current_day: number =0;
  current_date: string="";

  constructor(private http: HttpClient) { 
    this.distritos=[];
    this.concelhos=[];
    this.distritos_concelhos= new Map<string, string[]>();
    this.airMetics=[];
  }
  
  ngOnInit(): void {
    this.getDistritos();
  }

  getDistritos(): void{
    this.http.get<Map<string, string[]>>(this.baseURL+"cities",httpOptions).subscribe(data => {
      this.distritos_concelhos = new Map<string, string[]>(Object.entries(data));
        for( let d in data){
          this.distritos.push(d);
        }
        this.distrito=this.distritos[0];
        this.getConcelhos();
    })
  }
  getConcelhos(): void{
    this.concelhos= this.distritos_concelhos.get(this.distrito)!;
    this.concelho=this.concelhos[0];
  }

  getData(): void{
    this.current_day=0;
    this.http.get<AirMetrics[]>(this.baseURL+"air/now/" + this.concelho,httpOptions).subscribe(data => {
      this.airMetric_atual= new AirMetrics(data[0].co,data[0].no,data[0].o3,data[0].so2,
        data[0].pm2_5,data[0].nh3,data[0].pm10,data[0].aqi,data[0].mesureDate);
        this.pesquisa=true;
    })
    this.getDailyData();
  }

  getDailyData(): void{
    this.http.get<AirMetrics[]>(this.baseURL+"air/period/day/" + this.concelho +"?start="+this.current_day
    + "&end=" + this.current_day ,httpOptions).subscribe(data => {
      this.airMetics=[];
      for( let d of data){
        let m: AirMetrics  = new AirMetrics(d.co,d.no,d.o3,d.so2,d.pm2_5,d.nh3,d.pm10,d.aqi,d.mesureDate);
        this.airMetics.push(m);

      }
      let m_date: string =this.airMetics[0].mesureDate+' ';
      this.current_date=m_date.split(",")[2]+ "/" + m_date.split(",")[1];
    })
  }

  getDateFormat(m: AirMetrics): string{
    let m_date: string =m.mesureDate+' ';
    return m_date.split(",")[3];
  }

  previousDay(): void{
    this.current_day--;
    this.getDailyData();
  }

  nextDay():void{
    this.current_day++;
    this.getDailyData();
  }
}
