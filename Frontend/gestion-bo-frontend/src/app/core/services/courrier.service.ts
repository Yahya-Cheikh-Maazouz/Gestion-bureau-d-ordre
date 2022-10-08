import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Expediteur} from "../models/Expediteur";
import {Courrier} from "../models/Courrier";

@Injectable({
  providedIn: 'root'
})
export class CourrierService {

  constructor(private http : HttpClient) { }

  public saveCourrier(courrier :any, type :string , idUser:any, doc:any): Observable<any>{
    let data:string=type+",#/"+JSON.stringify(courrier)+",#/"+idUser
    var files = new FormData();
    files.append("files",doc[0])
    files.append("datas",data)
    console.log(data)
    return this.http.post("http://localhost:6060/add/courrier",files);
  }


  public courriers(): Observable<any>{
    return this.http.get("http://localhost:6060/courriers");
  }
  public courriersByType(types:any): Observable<any>{
    return this.http.get("http://localhost:6060/courriers/"+types);
  }

  public deleteCourrier(id:number ):Observable<any>{
    return this.http.delete<any>("http://localhost:6060/delete/courrier/"+id);
  }

  public updateCourrier(id:any, courrier :any ):Observable<any>{
    return this.http.put("http://localhost:6060/update/courrier/"+id,courrier);
  }


  public changeStatusCourrier(id:any, state :any ):Observable<any>{
    return this.http.put("http://localhost:6060/update/courrier/state/"+id,state);
  }



  public getPdf(id:number)
  {
    return this.http.get("http://localhost:6060/downloadFile/"+id,{responseType: 'blob'});
  }

}
