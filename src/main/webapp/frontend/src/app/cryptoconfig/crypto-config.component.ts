import {Component, Input, OnInit} from '@angular/core';
import {CryptoConfigService} from "./crypto-config.service";
import {NgbModal, NgbModalConfig} from "@ng-bootstrap/ng-bootstrap";
import {FormBuilder, Validators} from "@angular/forms";
import {saveAs} from "file-saver";

@Component({
  selector: 'app-crypto-config',
  templateUrl: './crypto-config.component.html',
  styleUrls: ['./crypto-config.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class CryptoConfigComponent implements OnInit {

  cryptoConfigs: any;
  cryptoConfig: any;
  cryptoConfigAddForm: any;
  cryptoConfigUpdateForm: any;
  content: any;
  filename = "";

  @Input() isChecked = false;

  constructor(private cryptoConfigService: CryptoConfigService, config: NgbModalConfig, private modalService: NgbModal,
              private formBuilder: FormBuilder) {
    config.backdrop = 'static';
    config.keyboard = false;
  }

  private initializeAddForm() {
    this.cryptoConfigAddForm = this.formBuilder.group({
      protocolOne: ['', Validators.required],
      protocolTwo: ['', Validators.required],
      description: ['', Validators.required]
    });
  }

  private initializeUpdateForm() {
    this.cryptoConfigUpdateForm = this.formBuilder.group({
      id: ['', Validators.required],
      protocolOne: ['', Validators.required],
      protocolTwo: ['', Validators.required],
      description: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.getCryptoConfigList();
    this.initializeAddForm();
    this.initializeUpdateForm();
  }

  updateCrypto({content}: { content: any }) {
    this.modalService.open(content, {size: 'xl', scrollable: true});

  }

  addCrypto({content}: { content: any }) {
    this.modalService.open(content);
    this.reloadData();
  }

  getCryptoConfigById(id: number) {
    this.cryptoConfigService.getCryptoConfig(id).subscribe(cryptoConfig => {
      this.cryptoConfig = cryptoConfig;
    });
  }

  reloadData() {
    this.cryptoConfigs = this.getCryptoConfigList();
  }


  getCryptoConfigList() {
    this.cryptoConfigService.getCryptoConfigList().subscribe(cryptoConfigs => {
      this.cryptoConfigs = cryptoConfigs;
    });
  }

  deleteById(id: number) {
    this.cryptoConfigService.deleteById(id).subscribe(response => {
      this.filename = response;
      this.reloadData();
    });
  }

  sendCryptoConfigData() {
    let cryptoConfigData = this.cryptoConfigAddForm.value;
    this.cryptoConfigService.addCryptoConfig(cryptoConfigData).subscribe(response => {
      this.filename = response;
      this.reloadData();
    });
  }

  updateCryptoConfig() {
    let cryptoConfigData = this.cryptoConfigUpdateForm.value;
    this.cryptoConfigService.updateCryptoConfig(cryptoConfigData).subscribe(response => {
      console.log(response);
      this.filename = response;
      this.reloadData();
    });
  }

  downloadFile() {
    this.cryptoConfigService.downloadFile(this.filename).subscribe(file => saveAs(file, this.filename));
  }
}
