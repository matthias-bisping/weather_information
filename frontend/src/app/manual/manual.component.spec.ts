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
  let compiled: any;

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
    compiled = fixture.debugElement.nativeElement;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should contain update button', () => {
    expect(compiled.querySelector('button').textContent).toContain('Update');
  });

  it('should have invalid form on empty input fields', () => {
    expect(component.manualUpdate.valid).toBeFalsy();
  });

  it('should have valid form on filled input fields', () => {
    component.manualUpdate.controls["city"].setValue('Oelde');
    expect(component.manualUpdate.valid).toBeTruthy();
  });

  it('should set current weather on submit form', () => {
    component.submitManualUpdate();
    expect(component.currentWeather).not.toBeNull();
  });
});
