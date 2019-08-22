import {Component, Input, OnInit} from '@angular/core';
import {CurrentWeather} from "../current-weather";

@Component({
  selector: 'app-current-weather-view',
  templateUrl: './current-weather-view.component.html',
  styleUrls: ['./current-weather-view.component.sass']
})
export class CurrentWeatherViewComponent implements OnInit {
  @Input()
  currentWeather : CurrentWeather;

  constructor() { }

  ngOnInit() {
  }

}
