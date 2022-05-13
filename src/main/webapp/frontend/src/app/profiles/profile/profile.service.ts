import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { distinct } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http: HttpClient) { }

  getAllProfiles(){
    return this.http.get("/profiles");
  }

}
