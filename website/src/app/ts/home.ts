import { Component } from '@angular/core';
import { DataService } from './dataService';

@Component({
  selector: 'home',
  templateUrl: '../html/home.html',
  styleUrls: ['../css/home.css']
})
export class Home {

  public sightings;

  constructor(private ds: DataService){
    this.ds.allSightings().toPromise().then(res => this.sightings = res);
  }

}
