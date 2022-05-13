import {Component, Input, OnInit} from '@angular/core';
import {NgbModal, NgbModalConfig} from "@ng-bootstrap/ng-bootstrap";
import {FormBuilder, Validators} from "@angular/forms";
import {saveAs} from "file-saver";
import {AuthentmeansService} from "./authentmeans.service";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-authentmeans',
  templateUrl: './authentmeans.component.html',
  styleUrls: ['./authentmeans.component.css'],
  providers: [NgbModalConfig, NgbModal, DatePipe]
})
export class AuthentmeansComponent implements OnInit {
  public authentMeanses: any;
  public authentMeans: any;
  public authentMeansAddForm: any;
  public authentMeansUpdateForm: any;
  public content: any;
  public filename = "";
  @Input() isChecked = false;


  constructor(private authentmeansService: AuthentmeansService, config: NgbModalConfig, private modalService: NgbModal,
              private formBuilder: FormBuilder, private datePipe: DatePipe) {
    config.backdrop = 'static';
    config.keyboard = false;
  }


  private initializeAddForm() {
    let currentDateTime = this.datePipe.transform(new Date(), 'MM/dd/yyyy hh:mm:ss');
    this.authentMeansAddForm = this.formBuilder.group({
      createdBy: ['', Validators.required],
      createdDate: [currentDateTime, Validators.required],
      description: ['', Validators.required],
      lastUpdateBy: ['',],
      name: ['', Validators.required],
      updateState: ['', Validators.required],
    });
  }

  private initializeUpdateForm() {
    this.authentMeansUpdateForm = this.formBuilder.group({
      id: ['', Validators.required],
      createdBy: ['', Validators.required],
      createdDate: ['',],
      description: ['', Validators.required],
      lastUpdateBy: ['',],
      name: ['', Validators.required],
      updateState: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.getAuthentmeansList();
    this.initializeAddForm();
    this.initializeUpdateForm();
  }

  updateCrypto({content}: { content: any }) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'});

  }

  addAuthentmeans({content}: { content: any }) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'});
    this.reloadData();
  }

  getAuthentmeansById(id: number) {
    this.authentmeansService.getAuthentmeans(id).subscribe(authentMeans => {
      this.authentMeans = authentMeans;
    });
  }

  reloadData() {
    this.authentMeanses = this.getAuthentmeansList();
  }


  getAuthentmeansList() {
    this.authentmeansService.getAuthentmeansList().subscribe(cryptoConfigs => {
      this.authentMeanses = cryptoConfigs;
    });
  }

  deleteAuthentmeansById(id: number) {
    this.authentmeansService.deleteAuthentmeansById(id).subscribe(response => {
      this.filename = response;
      this.reloadData();
    });
  }

  sendAuthentmeansData() {
    let authentMeansData = this.authentMeansAddForm.value;
    this.authentmeansService.sendAuthentmeansData(authentMeansData).subscribe(response => {
      this.filename = response;
      this.reloadData();
    });
  }

  updateAuthentmeans() {
    let authentMeansData = this.authentMeansUpdateForm.value;
    this.authentmeansService.updateAuthentmeans(authentMeansData).subscribe(response => {
      console.log(response);
      this.filename = response;
      this.reloadData();
    });
  }

  downloadFile() {
    this.authentmeansService.downloadFile(this.filename).subscribe(file => saveAs(file, this.filename));
  }
}
