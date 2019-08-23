import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {HomeComponent} from './home.component';
import {ServerStatusComponent} from "../server-status/server-status.component";
import {ManualComponent} from "../manual/manual.component";
import {AutomaticComponent} from "../automatic/automatic.component";
import {AngularMaterialModule} from "../angular-material.module";
import {MatTabsModule} from "@angular/material";
import {ReactiveFormsModule} from "@angular/forms";
import {CurrentWeatherViewComponent} from "../shared/current-weather-view/current-weather-view.component";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {RestApiService} from "../shared/rest-api.service";
import {MockRestApiService} from "../shared/app.testdata";
import {UpdaterComponent} from "../updater/updater.component";

describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomeComponent, ServerStatusComponent, ManualComponent, AutomaticComponent, CurrentWeatherViewComponent, UpdaterComponent ],
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
