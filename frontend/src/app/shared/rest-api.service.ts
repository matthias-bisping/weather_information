import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {ServerStatus} from "./server-status";
import {catchError, map, retry} from "rxjs/operators";

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

  static handleError(error) {
    let errorMessage = '';
    if(error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  }
}
