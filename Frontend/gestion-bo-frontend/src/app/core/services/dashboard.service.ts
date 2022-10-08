import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Expediteur} from "../models/Expediteur";
import {Courrier} from "../models/Courrier";

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  constructor(private http : HttpClient) { }

  public getCourrierBystate()
  {
    return this.http.get("http://localhost:6060/stat/courriers/states");
  }

  public getCourrierPourcentage()
  {
    return this.http.get("http://localhost:6060/stat/courriers/pourcentage");
  }

  public getNombreCourrier(type:any)
  {
    return this.http.get("http://localhost:6060/stat/courriers/"+type);
  }
  //

  public getNombreTotalCourrier()
  {
    return this.http.get("http://localhost:6060/stat/courriers");
  }

}
