import {Component, OnInit} from '@angular/core';
import {RestApiService} from "../shared/rest-api.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CurrentWeather} from "../shared/current-weather";

@Component({
  selector: 'app-manual',
  templateUrl: './manual.component.html',
  styleUrls: ['./manual.component.sass']
})
export class ManualComponent implements OnInit {
  manualUpdate : FormGroup;
  currentWeather : CurrentWeather;

  constructor(private restApiService : RestApiService, private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.reactiveForm();
  }

  reactiveForm() {
    this.manualUpdate =this.formBuilder.group({
      city: ['', [Validators.required]],
    })
  }

  submitManualUpdate() {
    let city : string = this.manualUpdate.get("city").value;
    console.log(city);
    this.restApiService.getCurrentWeather(city).subscribe(result => {
      console.log(result);
      this.currentWeather = result;
    });
  }

}
