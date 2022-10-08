import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Expediteur} from "../models/Expediteur";

@Injectable({
  providedIn: 'root'
})
export class ExpediteurService {

  constructor(private http : HttpClient) { }

  public saveExpediteurPhysiques(expditeur :any ): Observable<any>{
    return this.http.post("http://localhost:6060/expediteurPhysiques",expditeur);
  }

  public saveExpediteurMoral(expditeur :any ): Observable<any>{
    return this.http.post("http://localhost:6060/expediteurMorals",expditeur);
  }



  public updateExpediteurMoral(id:any,expditeur :any ): Observable<any>{
    return this.http.put("http://localhost:6060/expediteurMorals/"+id,expditeur);
  }

  public updateExpediteurPhysiques(id:any,expditeur :any ): Observable<any>{
    return this.http.put("http://localhost:6060/expediteurPhysiques/"+id,expditeur);
  }

  public Expediteurs(): Observable<any>{
    return this.http.get("http://localhost:6060/expediteurs");
  }



  public deleteExpediteur(idExpditeur :any ): Observable<any>{
    return this.http.delete("http://localhost:6060/delete/exp/"+idExpditeur);
  }


}
