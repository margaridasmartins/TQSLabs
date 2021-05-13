import { Component, OnInit } from '@angular/core';
import { AirMetrics } from './airMetric/airMetric';
import {HttpClient} from '@angular/common/http';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'Clean Air';

  private baseURL = 'http://127.0.0.1/api/v1/';

  distritos_concelhos!:  Map<string, string[]>;

  distritos!: string[];
  distrito!: string;

  concelhos!: string[];
  concelho!: string;

  airMetric_atual!: AirMetrics;

  constructor(private http: HttpClient) { }
  
  ngOnInit(): void {
    this.getDistritos();
  }

  getDistritos(): void{
    this.http.get<Map<string, string[]>>(this.baseURL+"cities").subscribe(data => {
        this.distritos_concelhos = data;
        for( let d in this.distritos_concelhos){
            this.distritos.push(d);
        }
        this.distrito=this.distritos[0];
        this.getConselhos();
    })
  }
  getConselhos(): void{
    this.concelhos= this.distritos_concelhos.get(this.distrito)!;
    this.concelho=this.concelhos[0];
  }
}
