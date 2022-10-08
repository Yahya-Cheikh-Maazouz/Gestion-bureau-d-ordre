import {Injectable} from "@angular/core";
import {CourrierService} from "../../core/services/courrier.service";
import {Courrier} from "../../core/models/Courrier";
import {Observable, Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class sharedData{
  public datas$: any;
  constructor(private api : CourrierService) {
    this.datas$=this.api.courriers();
  }
  private courriersList: Subject<Courrier[]> = new Subject<Courrier[]>();
  ngOnInit(): void {

  }

  public loadData(): Observable<[Courrier]>{
    this.datas$=this.api.courriers();
  //  alert("je suis exectuer ")
    return this.datas$
  }

}
