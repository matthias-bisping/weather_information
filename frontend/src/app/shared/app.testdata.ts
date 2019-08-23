import {Observable, of} from "rxjs";
import {ServerStatus} from "./server-status";
import {CurrentWeather} from "./current-weather";

export class MockRestApiService {
  getCurrentWeather(city : string) : Observable<CurrentWeather> {
    return of(TestData.currentWeather());
  }
  getServerStatus() : Observable<ServerStatus> {
    let result : ServerStatus = {
      session:  "Test",
      status: "Test"
    };
    return of(result)
  }
}

export class TestData {
  static currentWeather() : CurrentWeather {
    return {
      messageId : "1",
      messageTimestamp : "2019/08/23",
      id : 2,
      name : "Oelde",
      main : "Clear Sky",
      description : "very clear sky",
      iconUrl : "http://test.url",
      temperature : 28.3,
      pressure : 10.5,
      humidity : 25.0,
      windSpeed : 3.5,
      windDegree : 220
    };
  }

}
