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

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ServerStatusComponent,
    ManualComponent
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
