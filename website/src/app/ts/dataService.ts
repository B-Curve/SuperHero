import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class DataService {

  constructor(private http: HttpClient){}

  /*
  *************
    SIGHTINGS
  *************
  */

  allSightings(){
    return this.http.get("http://localhost:9000/sightings/");
  }

  updateSighting(sighting){
    return this.http.put("http://localhost:9000/sightings/sighting", sighting);
  }

  addSighting(sighting){
    return this.http.post("http://localhost:9000/sightings/sighting", sighting);
  }

  deleteSighting(id){
    this.http.delete("http://localhost:9000/sightings/sighting/"+id).subscribe();
  }

  deleteSuperSighting(sighting){
    return this.http.put("http://localhost:9000/sightings/sighting", sighting);
  }

  /*
  *************
    LOCATIONS
  *************
  */

  allLocations(){
    return this.http.get("http://localhost:9000/locations/");
  }

  addLocation(loc){
    return this.http.post("http://localhost:9000/locations/location/", loc)
  }

  deleteLocation(id){
    this.http.delete("http://localhost:9000/locations/location/"+id).subscribe();
  }

  /*
  *************
     SUPERS
  *************
  */

  allSupers(){
    return this.http.get("http://localhost:9000/supers/supers/all");
  }

  sortedSupers(sort){
    return this.http.get("http://localhost:9000/supers/supers/"+sort);
  }

  searchSuper(search){
    return this.http.get("http://localhost:9000/supers/super/"+search);
  }

  addSuper(Super){
    return this.http.post("http://localhost:9000/supers/super", Super);
  }

  deleteSuper(id){
    this.http.delete("http://localhost:9000/supers/super/"+id).subscribe();
  }

  deletePowerFromSuper(s_id, p_id){
    return this.http.delete("http://localhost:9000/supers/super/"+s_id+"/power/"+p_id);
  }

  deleteSightingFromSuper(s_id, si_id){
    return this.http.delete("http://localhost:9000/supers/super/"+s_id+"/sighting/"+si_id);
  }

  deleteOrgFromSuper(s_id, o_id){
    return this.http.delete("http://localhost:9000/supers/super/"+s_id+"/organization/"+o_id);
  }

  addPowerToSuper(s_id, p_id){
    this.http.post("http://localhost:9000/supers/super/power", [s_id, p_id]).subscribe();
  }

  addSightingToSuper(s_id, si_id){
    this.http.post("http://localhost:9000/supers/super/sighting", [s_id, si_id]).subscribe();
  }

  addOrganizationToSuper(s_id, o_id){
    this.http.post("http://localhost:9000/supers/super/organization", [s_id, o_id]).subscribe();
  }

  /*
  *************
     POWERS
  *************
  */

  allPowers(){
    return this.http.get("http://localhost:9000/powers/");
  }

  deletePower(id){
    this.http.delete("http://localhost:9000/powers/power/"+id).subscribe();
  }

  addPower(power){
    return this.http.post("http://localhost:9000/powers/power", power);
  }

  /*
  *****************
    ORGANIZATIONS
  *****************
  */

  allOrganizations(){
    return this.http.get("http://localhost:9000/supers/organizations/all");
  }

  addOrganization(organization){
    return this.http.post("http://localhost:9000/organizations/org", organization);
  }

  deleteOrganization(id){
    this.http.delete("http://localhost:9000/organizations/org/"+id).subscribe();
  }

}
