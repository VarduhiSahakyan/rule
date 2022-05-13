import DateTimeFormat = Intl.DateTimeFormat;
import {Data} from "@angular/router";

export interface Authentmeans {

  id: number;

  createdBy: String;

  creationDate: Data;

  description: String;

  lastUpdateBy: String;

  lastUpdateDate:DateTimeFormat;

  name: String;

  updateState: String;
}
