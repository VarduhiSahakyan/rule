import {Component, OnInit} from '@angular/core';
import {DeleteService} from "./delete.service";
import {ActivatedRoute} from "@angular/router";
import {saveAs} from "file-saver";
import {FormBuilder, Validators} from "@angular/forms";

@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.css']
})
export class DeletesubissuerComponent implements OnInit {

  code: any;
  subIssuer: any;
  filename: string = "";
  subIssuerForm: any;

  constructor(
    private service: DeleteService,
    private router: ActivatedRoute,
    private formBuilder: FormBuilder
  ) {
  }

  ngOnInit(): void {
    this.code = this.router.snapshot.paramMap.get("code");
    this.initializeForm();
    this.getByCode(this.code);
  }

  delete() {
    console.log(this.subIssuerForm);
    let subIssuerData = this.subIssuerForm.value;
    this.service.deleteSubissuer(subIssuerData, this.code).subscribe(response => {
      this.filename = response;
    });
  }

  downloadFile() {
    this.service.downloadSqlFile(this.filename).subscribe(file => saveAs(file, this.filename));
  }


  getByCode(code: any) {
    this.service.getByCodeSubissuer(code).subscribe(resposne => {
      this.subIssuer = resposne;
    });
  }

  initializeForm() {
    this.subIssuerForm = this.formBuilder.group({
      name: ['', Validators.required],
      label: ['', Validators.required],
      code: ['', Validators.required]
    });
  }
}

