import {Component, Injectable, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";
import {Courrier} from "../../../../core/models/Courrier";
import {CourrierService} from "../../../../core/services/courrier.service";
import {MatTableDataSource} from "@angular/material/table";
import {sharedData} from "../../../shared/sharedData.service";
import {map} from "rxjs";
import {DomSanitizer, SafeResourceUrl} from "@angular/platform-browser";
import {AuthService} from "../../../../core/services/auth.service";

@Component({
  selector: 'app-courriers',
  templateUrl: './courriers.component.html',
  styleUrls: ['./courriers.component.scss']
})
@Injectable({
  providedIn: 'root',
})
export class CourriersComponent implements OnInit {

  isAdmin:boolean=false
  displayedColumns: string[] = ['dateReception', 'reference', 'destinataire','etatCourrier','commentaire','Action'];
  dataSource :any
  displayed :boolean=false
  obj:any
  courrierState:any=["ENVOYER","REFUSER","TRAITER","CLOTURER","ENTRAITEMNT"]
  defaultState:any=null
  selectindex:any=null

  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;

  private ListCourrier = new Array<Courrier>();
  public fileURL!: any;
  constructor(private api : CourrierService,private sanitizer: DomSanitizer
  , private authapi :AuthService ) {

  }


  ngOnInit(): void {
    if(localStorage.getItem('role')==="ADMIN")
    this.isAdmin=true
    else this.isAdmin=false
    this.api.courriers().subscribe((res: Courrier[])=>{
      this.ListCourrier=res
      this.dataSource = new MatTableDataSource<Courrier>(this.ListCourrier);
      this.dataSource.paginator = this.paginator;
    })

  }

  editerCourrier(row:any) {
    this.obj=row
  }

  deleteCourrier(row:any) {
    console.log(row)
    this.api.deleteCourrier(row.id).subscribe(res=>{
      alert("has been removed")
      this.ngOnInit()
    })
    this.ngOnInit()
  }




  pdf(idDoc:any) {
    this.api.getPdf(idDoc).subscribe((response:any) => {
      let file = new Blob([response], {type: 'application/pdf'});
     this.fileURL = this.sanitizer.bypassSecurityTrustResourceUrl(URL.createObjectURL(file));
    })
  }




  download(idDoc:any) {
    this.api.getPdf(idDoc).subscribe((response:any) => {
      let file = new Blob([response], {type: 'application/pdf'});
      this.fileURL = this.sanitizer.bypassSecurityTrustResourceUrl(URL.createObjectURL(file));
      window.open(this.fileURL.changingThisBreaksApplicationSecurity, '_blank');

    })
  }

  changeStatusCourrier(id: any, $event: Event) {
    this.selectindex=id
    if (($event.target as HTMLInputElement)?.value) {
      this.defaultState = ($event.target as HTMLInputElement).value;
    }
  }

  submitStatusCourrier(){
    console.log("id : "+this.selectindex+" value : "+this.defaultState)
    this.api.changeStatusCourrier(this.selectindex,this.defaultState).subscribe(
      res=>{alert("bien mise a jour")}
    )
    this.defaultState=null
  }



  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  getCourrierEntrants() {
    this.api.courriersByType("entrant").subscribe((res: Courrier[])=>{
      this.ListCourrier=res
      this.dataSource = new MatTableDataSource<Courrier>(this.ListCourrier);
      this.dataSource.paginator = this.paginator;
    })
  }

  getCourrierSortants() {
    this.api.courriersByType("sortant").subscribe((res: Courrier[])=>{
      this.ListCourrier=res
      this.dataSource = new MatTableDataSource<Courrier>(this.ListCourrier);
      this.dataSource.paginator = this.paginator;
    })
  }

  getCourriers() {
    this.api.courriers().subscribe((res: Courrier[])=>{
      this.ListCourrier=res
      this.dataSource = new MatTableDataSource<Courrier>(this.ListCourrier);
      this.dataSource.paginator = this.paginator;
    })
  }

}
