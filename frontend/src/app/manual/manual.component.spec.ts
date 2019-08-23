import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ManualComponent} from './manual.component';
import {ReactiveFormsModule} from "@angular/forms";
import {AngularMaterialModule} from "../angular-material.module";
import {CurrentWeatherViewComponent} from "../shared/current-weather-view/current-weather-view.component";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {RestApiService} from "../shared/rest-api.service";
import {MockRestApiService} from "../shared/app.testdata";

describe('ManualComponent', () => {
  let component: ManualComponent;
  let fixture: ComponentFixture<ManualComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManualComponent, CurrentWeatherViewComponent ],
      imports: [ReactiveFormsModule, AngularMaterialModule, BrowserAnimationsModule],
      providers: [
        {provide: RestApiService, useClass: MockRestApiService}
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManualComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
