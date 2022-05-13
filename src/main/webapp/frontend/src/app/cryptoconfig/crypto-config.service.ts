import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CryptoConfigService {

  private baseUrl = 'http://localhost:8181/crypto';

  constructor(private http: HttpClient) {
  }

  getCryptoConfig(id: number) {

    return this.http.get(this.baseUrl + '/' + `${id}`)
  };

  getCryptoConfigList() {

    return this.http.get(this.baseUrl);
  }

  deleteById(id: number) {

    return this.http.delete(this.baseUrl + '/' + `${id}`, {responseType: 'text'});
  }


  addCryptoConfig(cryptoConfigData: any) {

    return this.http.post(this.baseUrl, cryptoConfigData, {responseType: 'text'});
  }

  updateCryptoConfig(cryptoConfigData: any) {

    return this.http.put(this.baseUrl, cryptoConfigData, {responseType: 'text'});
  }

  downloadFile(filename: String) {

    return this.http.get(this.baseUrl + '/download/' + filename, {responseType: 'blob'});
  }
}
