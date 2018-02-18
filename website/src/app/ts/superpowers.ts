import { Component } from '@angular/core';
import { DataService } from './dataService';

@Component({
  selector: 'superpowers',
  templateUrl: '../html/superpowers.html',
  styleUrls: ['../css/superpowers.css']
})
export class SuperPowers {

  public powers;
  public isAddMode = false;
  public isEditMode = false;

  constructor(private dao: DataService){
    this.dao.allPowers().toPromise().then(item => this.powers = item);
  }

  deletePower(id): void{
    if(confirm("Are you sure?")){
      this.dao.deletePower(id);
      this.powers = this.powers.filter(item => item.superPowerId !== id);
    }
  }

  addPower(title, description): void{
    let object = {title: title, description: description};
    this.dao.addPower(object).toPromise().then(item => this.powers.push(item));
    this.isAddMode = false;
  }

}
