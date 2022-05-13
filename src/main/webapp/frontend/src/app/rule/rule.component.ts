import { Component, OnInit } from '@angular/core';
import {RuleService} from "./rule.service";
import {FormBuilder, Validators} from "@angular/forms";
import {saveAs} from "file-saver";

@Component({
  selector: 'app-rule',
  templateUrl: './rule.component.html',
  styleUrls: ['./rule.component.css']
})
export class RuleComponent implements OnInit {

  rules: any;
  ruleForm: any;
  filename: string = "";

  constructor(private ruleService: RuleService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.initializeForm();
    this.getAllRules();
  }

  sendRuleData() {
    console.log(this.ruleForm);
    let ruleData = this.ruleForm.value;
    this.ruleService.sendData(ruleData).subscribe(response => {
      this.filename = response;
    })
  }


  initializeForm() {
    this.ruleForm = this.formBuilder.group({
      id: [0, Validators.required],
      createdBy: ['', Validators.required],
      description: ['', Validators.required],
      lastUpdateBy: ['', Validators.required],
      name: ['', Validators.required],
      updateState: ['', Validators.required],
      orderRule: ['', Validators.required]
    });
  }

  getAllRules() {
    this.ruleService.getAllRules().subscribe(rule => {
      this.rules = rule;
    });
  }


  downloadFile() {
    this.ruleService.downloadSqlFile(this.filename).subscribe(file => saveAs(file, this.filename));
  }

}
