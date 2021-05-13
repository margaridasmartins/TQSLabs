import { Component } from '@angular/core';
import { airMetrics } from './airMetric/airMetric';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular-app';

  distritos: string[];
  conselhos: string[];

  airMetric_atual: airMetrics;
  

}
