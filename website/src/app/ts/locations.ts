import { Component } from '@angular/core';
import { DataService } from './dataService';

@Component({
  selector: 'locations',
  templateUrl: '../html/locations.html',
  styleUrls: ['../css/locations.css']
})
export class Locations {

  public locations;
  public isEditMode = false;
  public isAddMode = false;

  constructor(private dao: DataService){
    this.dao.allLocations().toPromise().then(res => this.locations  = res);
  }

  addLocation(e, address, description, latitude, longitude): void{
    e.preventDefault();
    latitude = parseFloat(latitude);
    longitude = parseFloat(longitude);
    if(latitude < -90 || latitude > 90 || isNaN(latitude)){
      alert("Latitude must be between -90 & 90.");
      return;
    }
    if(longitude < -180 || longitude > 180 || isNaN(longitude)){
      alert("Longitude must be between -180 & 180.");
      return;
    }
    let object = {address: address, description: description, latitude: latitude, longitude: longitude};
    this.dao.addLocation(object).toPromise().then(res => this.locations.push(res));
    this.isAddMode = false;
  }

  deleteLocation(id): void{
    if(confirm("Are you sure?")){
      this.dao.deleteLocation(id);
      this.locations = this.locations.filter(item => item.locationId !== id);
    }
  }

}
