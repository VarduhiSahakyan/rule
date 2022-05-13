import { Component, OnInit } from '@angular/core';
import { ProfileService } from './profile.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  profiles:any = [];
  profileTerm!:string;
  showOnchange: boolean = false;

  constructor(
    private service: ProfileService
  ) { }

  ngOnInit(): void {
    this.index();
  }

  index(){
    this.service.getAllProfiles().subscribe(response => {this.profiles = response});
  }

  changeFunction(){
     this.showOnchange = true; 
  }

}
