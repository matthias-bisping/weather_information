import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CurrentWeather} from "../shared/current-weather";
import {RestApiService} from "../shared/rest-api.service";
import {ReplaySubject, timer} from 'rxjs';
import {takeUntil} from "rxjs/operators";

@Component({
  selector: 'app-automatic',
  templateUrl: './automatic.component.html',
  styleUrls: ['./automatic.component.sass']
})
export class AutomaticComponent implements OnInit, OnDestroy {
  automaticUpdate : FormGroup;
  currentWeather : CurrentWeather;
  updateInProgress : boolean;

  private stop = new ReplaySubject<boolean>();

  constructor(private restApiService : RestApiService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.reactiveForm();
  }

  ngOnDestroy(): void {
    this.stopUpdate();
  }

  reactiveForm() {
    this.automaticUpdate =this.formBuilder.group({
      city: ['', [Validators.required]],
      interval: [0, [Validators.required]]
    })
  }

  submitAutomaticUpdate() {
    let city : string = this.automaticUpdate.get("city").value;
    let interval : number = this.automaticUpdate.get("interval").value;

    console.log("Start Update");
    this.stop = new ReplaySubject<boolean>();
    this.updateInProgress = true;
    timer(0,interval * 1000)
      .pipe(
        takeUntil(this.stop)
      ).subscribe(() => {
      console.log("Update");
      this.restApiService.getCurrentWeather(city).subscribe(result => {
        this.currentWeather = result;
      });
    });
  }

  stopUpdate() {
    console.log("Stop Update");
    this.updateInProgress = false;
    this.stop.next(true);
    this.stop.complete();
  }
}
