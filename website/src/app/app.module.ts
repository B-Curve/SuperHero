import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { DataService } from './ts/dataService';

import { AppComponent } from './app.component';
import { Home } from './ts/home';
import { Supers } from './ts/supers';
import { Sightings } from './ts/sightings';
import { Locations } from './ts/locations';
import { SuperPowers } from './ts/superpowers';
import { Organizations } from './ts/organizations';

const routes: Routes = [
  { path: '', component: Home },
  { path: 'supers', component: Supers },
  { path: 'supers/:anything', component: Supers },
  { path: 'supers/search/:anything', component: Supers },
  { path: 'sightings', component: Sightings },
  { path: 'locations', component: Locations },
  { path: 'powers', component: SuperPowers },
  { path: 'organizations', component: Organizations }
];

@NgModule({
  declarations: [
    Supers,
    Home,
    Sightings,
    Locations,
    SuperPowers,
    Organizations,
    AppComponent
  ],
  imports: [
    RouterModule.forRoot(
      routes
    ),
    HttpClientModule,
    BrowserModule
  ],
  providers: [DataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
