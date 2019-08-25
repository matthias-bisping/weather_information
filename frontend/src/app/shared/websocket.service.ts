import {Injectable} from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import {Subject} from "rxjs";
import {CurrentWeather} from "./current-weather";

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {
  private stompClient;

  currentWeather :CurrentWeather;
  currentWeatherChange = new Subject<CurrentWeather>();

  constructor() { }

  init() {
    let ws = new SockJS("/weather/websocket");
    this.stompClient =Stomp.over(ws);

    let instance = this;
    this.stompClient.connect({}, function (frame) {
        instance.stompClient.subscribe("/topic",
          (message) => {
            console.log("Incomming message");
            let current = new CurrentWeather(JSON.parse(message.body));
            instance.currentWeather = current;
            instance.currentWeatherChange.next(current);
        });
      }
    );
  }

}
