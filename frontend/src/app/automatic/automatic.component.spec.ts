import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AutomaticComponent} from './automatic.component';
import {ReactiveFormsModule} from "@angular/forms";
import {AngularMaterialModule} from "../angular-material.module";
import {CurrentWeatherViewComponent} from "../shared/current-weather-view/current-weather-view.component";
import {RestApiService} from "../shared/rest-api.service";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MockRestApiService} from "../shared/app.testdata";

describe('AutomaticComponent', () => {
  let component: AutomaticComponent;
  let fixture: ComponentFixture<AutomaticComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AutomaticComponent, CurrentWeatherViewComponent ],
      imports: [ReactiveFormsModule, AngularMaterialModule, BrowserAnimationsModule],
      providers: [
        {provide: RestApiService, useClass: MockRestApiService}
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AutomaticComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
