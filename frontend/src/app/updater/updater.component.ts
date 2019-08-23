import {Component, OnInit} from '@angular/core';
import {WebsocketService} from "../shared/websocket.service";
import {CurrentWeather} from "../shared/current-weather";

@Component({
  selector: 'app-updater',
  templateUrl: './updater.component.html',
  styleUrls: ['./updater.component.sass']
})
export class UpdaterComponent implements OnInit {
  currentWeather : CurrentWeather;

  constructor(private websocket : WebsocketService) { }

  ngOnInit() {
    this.websocket.init();
    this.websocket.currentWeatherChange.subscribe(
      data => {
        console.log("Component receive new weather data");
        this.currentWeather = data;
      }
    );
  }

}
