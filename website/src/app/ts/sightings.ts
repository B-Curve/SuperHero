import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { DataService } from './dataService';

@Component({
  selector: 'sightings',
  templateUrl: '../html/sightings.html',
  styleUrls: ['../css/sightings.css']
})
export class Sightings {

  public sightings;
  public locations;
  public supers;
  public selectedSupers = [];
  public sighting = {
    location: {
      locationId: null
    },
    description: null,
    headline: null,
    date: null,
    supers: null
  };
  public isEditMode = false;
  public isAddMode = false;

  constructor(private dao: DataService){
    this.dao.allSightings().toPromise().then(res => this.sightings = res);
    this.dao.allLocations().toPromise().then(res => this.locations = res);
    this.dao.allSupers().toPromise().then(res => this.supers = res);
  }

  onSuperSelect(o): boolean{
    let val = parseInt(o.value);
    let index = this.supers.filter(item => item.superId === val)[0];
    if(this.selectedSupers.indexOf(index) > -1){
      this.selectedSupers = this.selectedSupers.filter(item => item.superId !== val);
      return o['ok'] = false;
    }else{
      let addedSuper = this.supers.filter(item => item.superId === val)[0];
      this.selectedSupers.push(addedSuper);
      return o['ok'] = true;
    }
  }

  deleteSighting(sighting): void{
    if(confirm("Are you sure?")){
      this.dao.deleteSighting(sighting.sightingId);
      this.sightings = this.sightings.filter(item => item.sightingId !== sighting.sightingId);
    }
  }

  removeSuperFromSighting(sighting, superId): void{
    sighting.supers = sighting.supers.filter(item => item.superId !== superId);
    this.dao.deleteSuperSighting(sighting).toPromise()
      .then(this.supers[sighting] = this.supers[sighting].filter(item => item.superId !== superId));
  }

  updateSighting(sighting): void{
    this.dao.updateSighting(sighting).toPromise()
      .then(res => this.supers[sighting] = res);
  }

  addSighting(): void{
    let addedSighting = this.sighting;
    console.log(addedSighting);
    if(this.selectedSupers.length < 1){
      alert("A sighting must have at least one Super!");
      return;
    }
    if(addedSighting.date === null || addedSighting.date === ""){
      alert("A sighting must have a date!");
      return;
    }
    if(addedSighting.headline === null || addedSighting.headline === ""){
      alert("A sighting must have a headline!");
      return;
    }
    if(addedSighting.location.locationId === null){
      alert("A sighting must have a location!");
      return;
    }
    addedSighting.supers = this.selectedSupers;
    addedSighting.location.locationId = parseInt(addedSighting.location.locationId);
    this.dao.addSighting(addedSighting).toPromise().then(res => this.sightings.unshift(res));
    this.isAddMode = false;
  }

}
