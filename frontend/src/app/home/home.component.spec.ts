import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {HomeComponent} from './home.component';
import {ServerStatusComponent} from "../server-status/server-status.component";
import {ManualComponent} from "../manual/manual.component";
import {AutomaticComponent} from "../automatic/automatic.component";
import {AngularMaterialModule} from "../angular-material.module";
import {MatTabsModule} from "@angular/material";
import {ReactiveFormsModule} from "@angular/forms";
import {CurrentWeatherViewComponent} from "../shared/current-weather-view/current-weather-view.component";
import {Observable, of} from "rxjs";
import {CurrentWeather} from "../shared/current-weather";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {RestApiService} from "../shared/rest-api.service";
import {ServerStatus} from "../shared/server-status";

class MockRestApiService {
  getCurrentWeather(city : string) : Observable<CurrentWeather> {
    return null;
  }
  getServerStatus() : Observable<ServerStatus> {
    let result : ServerStatus = {
      session:  "Test",
      status: "Test"
    };
    return of(result)
  }
}

describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomeComponent, ServerStatusComponent, ManualComponent, AutomaticComponent, CurrentWeatherViewComponent ],
      imports: [AngularMaterialModule, MatTabsModule, ReactiveFormsModule, BrowserAnimationsModule],
      providers: [
        {provide: RestApiService, useClass: MockRestApiService}
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
