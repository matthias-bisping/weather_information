import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CurrentWeatherViewComponent} from './current-weather-view.component';

describe('CurrentWeatherViewComponent', () => {
  let component: CurrentWeatherViewComponent;
  let fixture: ComponentFixture<CurrentWeatherViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CurrentWeatherViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrentWeatherViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
