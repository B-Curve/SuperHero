import { Component } from '@angular/core';
import { DataService } from './dataService';

@Component({
  selector: 'organizations',
  templateUrl: '../html/organizations.html',
  styleUrls: ['../css/organizations.css']
})
export class Organizations {

  public organizations;
  public isAddMode = false;
  public isEditMode = false;

  constructor(private ds: DataService){
    this.ds.allOrganizations().toPromise().then(res => {
      this.organizations = res;
      console.log(res);
    });
  }

  deleteOrganization(id): void{
    if(confirm("Are you sure?")){
      this.ds.deleteOrganization(id);
      this.isEditMode = false;
      this.organizations = this.organizations.filter(item => item.organizationId !== id);
    }
  }

  addOrganization(name, description, address, phone): void{
    let object = {name: name, description: description, address: address, phone: phone};
    this.isAddMode = false;
    this.ds.addOrganization(object).toPromise().then(res => this.organizations.unshift(res));
  }

}
