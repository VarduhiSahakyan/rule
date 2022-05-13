import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AuthentmeansService {

  private baseUrl = 'http://localhost:8181/authent';

  constructor(private http: HttpClient) {
  }

  getAuthentmeans(id: number) {

    return this.http.get(this.baseUrl + '/' + `${id}`)
  };

  getAuthentmeansList() {

    return this.http.get(this.baseUrl);
  }

  deleteAuthentmeansById(id: number) {

    return this.http.delete(this.baseUrl + '/' + `${id}`, {responseType: 'text'});
  }

  sendAuthentmeansData(authentMeansData: any) {

    return this.http.post(this.baseUrl, authentMeansData, {responseType: 'text'});
  }

  updateAuthentmeans(authentMeansData: any) {

    return this.http.put(this.baseUrl, authentMeansData, {responseType: 'text'});
  }

  downloadFile(filename: String) {

    return this.http.get(this.baseUrl + '/download/' + filename, {responseType: 'blob'});
  }
}
