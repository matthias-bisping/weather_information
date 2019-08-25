import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {RestApiService} from "../shared/rest-api.service";
import {Properties} from "../shared/properties";

@Component({
  selector: 'app-properties',
  templateUrl: './properties.component.html',
  styleUrls: ['./properties.component.sass']
})
export class PropertiesComponent implements OnInit {
  propertiesUpdate : FormGroup;

  constructor(private restApiService : RestApiService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.reactiveForm();
  }

  reactiveForm() {
    this.propertiesUpdate =this.formBuilder.group({
      city: ['', [Validators.required]],
      interval: [0, [Validators.required]]
    })
  }

  submitPropertiesUpdate() {
    let city: string = this.propertiesUpdate.get("city").value;
    let interval: number = this.propertiesUpdate.get("interval").value;

    console.log("Update propeties to: " + city + ", " + interval);
    this.restApiService.updateProperties(new Properties(city, interval)).subscribe(result => {
      alert("Updated to: " + result.city + ", " + result.interval)
    });
  }
}
