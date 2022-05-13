import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {EditService} from "./edit.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {

  code:any;
  // @ts-ignore
  issuerEditForm: FormGroup;

  issuer: any;

  constructor(
    private service: EditService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.code = this.route.snapshot.paramMap.get("code");
    this.initializeForm();
    this.find(this.code);
  }

  initializeForm(){
    this.issuerEditForm = new FormGroup({
      code: new FormControl(''),
      createdBy: new FormControl(''),
      creationDate:new FormControl(''),
      name: new FormControl(''),
      updateState:new FormControl(''),
      label:new FormControl(''),
      availaibleAuthentMeans:new FormControl('')
    });
  }

  find(id:any){
     this.service.findById(id).subscribe(resposne => {this.issuer = resposne});
  }

  onEdit(issuerEditForm:any){
     this.service.edit(issuerEditForm.value,this.code).subscribe(response => {console.log(response)});
  }

}
