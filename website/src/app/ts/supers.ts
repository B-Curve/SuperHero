import { Component } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { DataService } from './dataService';

@Component({
  selector: 'supers',
  templateUrl: '../html/supers.html',
  styleUrls: ['../css/supers.css'],
  exportAs: 'clicked'
})
export class Supers {

  public supers;
  public powers;
  public sightings;
  public organizations;
  public addSuper = false;
  public createdSuper = {
    superId: undefined,
    name: undefined,
    description: "",
    type: undefined,
    sightings: [],
    organizations: [],
    powers: []
  };
  title = "Search";
  subtext = "Be careful what you wish for...";

  setup(): void{
    this.dao.allPowers().toPromise().then(res => this.powers = res);
    this.dao.allSightings().toPromise().then(res => this.sightings = res);
    this.dao.allOrganizations().toPromise().then(res => this.organizations = res);
    if(window.location.pathname.includes("search/")){
      this.doSearch();
      return;
    }
    let sort = window.location.pathname.split("supers/")[1];
    if(typeof sort === 'undefined') sort = "all";
    switch(sort){
      case "all":
        this.title = "All Supers";
        this.subtext = "Individuals with superior abilities...";
        break;
      case "heroes":
        this.title = "Super Heroes";
        this.subtext = "Individuals that use their advantages for good...";
        break;
      case "villains":
        this.title = "Super Villains";
        this.subtext = "Individuals that use their advantages for evil...";
        break;
    }
    this.dao.sortedSupers(sort).toPromise().then(res => this.supers = res);
  }

  constructor(private dao: DataService, private r: Router, private activeRoute: ActivatedRoute){
    this.activeRoute.params.subscribe(() => {
      this.setup();
    });
  }

  doSearch(): void{
    let value = window.location.pathname.split("search/")[1];
    this.dao.searchSuper(value).toPromise().then(res => this.supers = res);
  }

  search(value): void{
    if(value === '') return;
    this.r.navigate(['/supers/search/'+value]);
  }

  deleteSuper(Super): void{
    if(confirm("Are you sure you want to remove " + Super.name + "?")){
      this.dao.deleteSuper(Super.superId);
      this.supers = this.supers.filter(item => item.superId !== Super.superId);
    }
  }

  notIncludedPowers(superpowers){
    let list = this.powers;
    for(var key in superpowers){
      list = list.filter(item => item.superPowerId !== superpowers[key].superPowerId);
    }
    return list;
  }

  notIncludedSightings(supersightings){
    let list = this.sightings;
    for(var key in supersightings){
      list = list.filter(item => item.sightingId !== supersightings[key].sightingId);
    }
    return list;
  }

  notIncludedOrganizations(superorganizations){
    let list = this.organizations;
    for(var key in superorganizations){
      list = list.filter(item => item.organizationId !== superorganizations[key].organizationId);
    }
    return list;
  }

  onPowerSelect(e, Super): void{
    let addedPower;
    addedPower = this.powers.filter(item => item.superPowerId == e.target.value);
    this.dao.addPowerToSuper(Super.superId, parseInt(e.target.value));
    Super.powers.push(addedPower[0]);
  }

  onSightingsSelect(e, Super): void{
    let addedSighting;
    addedSighting = this.sightings.filter(item => item.sightingId == e.target.value);
    this.dao.addSightingToSuper(Super.superId, parseInt(e.target.value));
    Super.sightings.push(addedSighting[0]);
  }

  onOrganizationsSelect(e, Super): void{
    let addedOrganization;
    addedOrganization = this.organizations.filter(item => item.organizationId == e.target.value);
    this.dao.addOrganizationToSuper(Super.superId, parseInt(e.target.value));
    Super.organizations.push(addedOrganization[0]);
  }

  click(clicked): boolean{
    if(typeof clicked === 'undefined')  return true;
    return !clicked;
  }

  deletePower(id, Super): void{
    this.dao.deletePowerFromSuper(Super.superId, id).toPromise().then(res => Super.powers = res);
  }

  deleteSighting(id, Super): void{
    this.dao.deleteSightingFromSuper(Super.superId, id).toPromise().then(res => Super.sightings = res);
  }

  deleteOrganization(id, Super): void{
    this.dao.deleteOrgFromSuper(Super.superId, id).toPromise().then(res => Super.organizations = res);
  }

  newAddPower = (value) => {
    let getPower = this.powers.filter(item => item.superPowerId == value)[0];
    this.createdSuper.powers.push(getPower);
  }
  newAddSighting = (value) => {
    let getSighting = this.sightings.filter(item => item.sightingId == value)[0];
    this.createdSuper.sightings.push(getSighting);
  }
  newAddOrganization = (value) => {
    let getOrg = this.organizations.filter(item => item.organizationId == value)[0];
    this.createdSuper.organizations.push(getOrg);
  }
  deletePowerFromNew = (value) => this.createdSuper.powers = this.createdSuper.powers.filter(item => item.superPowerId !== value);
  deleteSightingFromNew = (value) => this.createdSuper.sightings = this.createdSuper.sightings.filter(item => item.sightingId !== value);
  deleteOrgFromNew = (v) => this.createdSuper.organizations = this.createdSuper.organizations.filter(item => item.organizationId !== v);
  getReady(): string{
    if(typeof this.createdSuper.name !== 'undefined' && typeof this.createdSuper.type !== 'undefined'){
      if(this.createdSuper.name.length > 0){
        return "green";
      }
    }
    return "maroon";
  }
  submitNewSuper = () => {
    if(typeof this.createdSuper.name === 'undefined' || typeof this.createdSuper.type === 'undefined') return;
    if(this.createdSuper.name.length < 1) return;
    if(this.createdSuper.name.length > 32){
      alert("Name must be 32 characters or less!");
      return;
    }
    if(this.createdSuper.description.length > 800){
      alert("Description must be 800 characters or less!");
      return;
    }
    if(this.createdSuper.sightings.length > 0){
      for(var key in this.createdSuper.sightings){
        this.createdSuper.sightings[key].date = this.createdSuper.sightings[key].date.toString();
      }
    }
    this.dao.addSuper(this.createdSuper).toPromise().then(res => {
      this.r.navigate(['/supers/search/'+res['name']]);
    });
  }

}
