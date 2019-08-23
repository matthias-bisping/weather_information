import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CurrentWeatherViewComponent} from './current-weather-view.component';
import {AngularMaterialModule} from "../../angular-material.module";
import {TestData} from "../app.testdata";

describe('CurrentWeatherViewComponent', () => {
  let component: CurrentWeatherViewComponent;
  let fixture: ComponentFixture<CurrentWeatherViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CurrentWeatherViewComponent ],
      imports: [AngularMaterialModule]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrentWeatherViewComponent);
    component = fixture.componentInstance;
    component.currentWeather = TestData.currentWeather();

    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
