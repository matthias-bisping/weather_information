import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {ServerStatus} from "./server-status";
import {catchError, map, retry} from "rxjs/operators";
import {CurrentWeather} from "./current-weather";

@Injectable({
  providedIn: 'root'
})
export class RestApiService {

  constructor(private http: HttpClient) { }

  getServerStatus() : Observable<ServerStatus>{
    return this.http
      .get<ServerStatus>("rest/status")
      .pipe(
        retry(1),
        catchError(RestApiService.handleError),
        map(result => new ServerStatus(result))
      )
  }

  getCurrentWeather(city : string) : Observable<CurrentWeather> {
    let params = new HttpParams().set("city", city);

    return this.http
      .get<CurrentWeather>("rest/currentWeather", {params: params })
      .pipe(
        retry(1),
        catchError(RestApiService.handleError),
        map(result => new CurrentWeather(result))
      )
  }

  static handleError(error) {
    let errorMessage = '';
    if(error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(errorMessage);
    alert(errorMessage);
    return throwError(errorMessage);
  }
}
