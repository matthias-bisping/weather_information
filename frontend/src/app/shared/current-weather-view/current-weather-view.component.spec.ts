import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CurrentWeatherViewComponent} from './current-weather-view.component';
import {AngularMaterialModule} from "../../angular-material.module";

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
    component.currentWeather = {
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

    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
