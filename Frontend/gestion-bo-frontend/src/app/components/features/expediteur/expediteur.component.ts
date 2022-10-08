import {Component, OnInit, ViewChild} from '@angular/core';
import {ExpediteurService} from "../../../core/services/expediteur.service";
import {Expediteur} from "../../../core/models/Expediteur";
import {MatPaginator} from "@angular/material/paginator";
import {MatTableDataSource} from "@angular/material/table";


@Component({
  selector: 'app-expediteur',
  templateUrl: './expediteur.component.html',
  styleUrls: ['./expediteur.component.scss']
})
export class ExpediteurComponent implements OnInit {
  displayedColumns: string[] = ['raisonSocial', 'adresse', 'email', 'telephone','Action'];
  displayedColumns2: string[] = ['nom','prenom','cin', 'adresse', 'email', 'telephone','Action'];
  dataSource :any
  displayed :boolean=false
  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;


  private ListExpediteur = new Array<Expediteur>();
  IsMoral:boolean=true ;
  dataRow: any
  hide:boolean=false
  constructor(private api : ExpediteurService) { }

  ngOnInit(): void {
    this.api.Expediteurs().subscribe((res)=>{
      if(this.IsMoral)
        this.ListExpediteur=res.expediteurMorals
      else
        this.ListExpediteur=res.expediteurPhysiques
      this.dataSource = new MatTableDataSource<Expediteur>(this.ListExpediteur);
      this.displayed = true
      this.dataSource.paginator = this.paginator;
    })

  }



  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }



  deleteExpditeur(row:any) {
    console.log(row)
    this.api.deleteExpediteur(row.id).subscribe(res=>{
      alert("has been removed")
      this.ngOnInit()
    })
    this.ngOnInit()
  }

  chooseExpType(type: string) {
    if(type==="p")
    {
      this.IsMoral=false
    }
    else if(type==="m"){
      this.IsMoral=true
    }

    this.api.Expediteurs().subscribe((res)=>{
      if(this.IsMoral)
        this.ListExpediteur=res.expediteurMorals
      else
        this.ListExpediteur=res.expediteurPhysiques
      this.dataSource = new MatTableDataSource<Expediteur>(this.ListExpediteur);
      this.displayed = true
      this.dataSource.paginator = this.paginator;
    })
  }

  getRow(row:any) {
    this.dataRow=row
  }
}


