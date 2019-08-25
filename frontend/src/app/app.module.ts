import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HomeComponent} from './home/home.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ServerStatusComponent} from './server-status/server-status.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ManualComponent} from './manual/manual.component';
import {AngularMaterialModule} from "./angular-material.module";
import {MatTabsModule} from "@angular/material";
import {AutomaticComponent} from './automatic/automatic.component';
import {CurrentWeatherViewComponent} from './shared/current-weather-view/current-weather-view.component';
import {UpdaterComponent} from './updater/updater.component';
import {PropertiesComponent} from './properties/properties.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ServerStatusComponent,
    ManualComponent,
    AutomaticComponent,
    CurrentWeatherViewComponent,
    UpdaterComponent,
    PropertiesComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    AngularMaterialModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatTabsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
