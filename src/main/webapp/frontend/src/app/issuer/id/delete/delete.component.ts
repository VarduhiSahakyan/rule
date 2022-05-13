import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {DeleteService} from "./delete.service";
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.css']
})
export class DeleteComponent implements OnInit {

  id: any;

  constructor(
    private deleteService: DeleteService,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
  }

  onDelete(){
    this.deleteService.delete(this.id).subscribe(resposne => {console.log(resposne)});
  }

}
