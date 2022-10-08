import {Component, OnInit, ViewChild} from '@angular/core';
import {Courrier} from "../../../../core/models/Courrier";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {CourrierService} from "../../../../core/services/courrier.service";
import {DomSanitizer} from "@angular/platform-browser";
import {UserService} from "../../../../core/services/user.service";
import {UserModel} from "../../../../core/models/User.model";

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.scss']
})
export class AccountsComponent implements OnInit {

  displayedColumns: string[] = ['firstName', 'lastName','email','Action'];
  dataSource :any
  displayed :boolean=false
  constructor(private api: UserService) {

  }

  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;

  private ListCourrier = new Array<UserModel>();
  public fileURL!: any;


  ngOnInit(): void {
    this.api.users().subscribe((res: any)=>{
      this.ListCourrier=res
      console.log(res)
      this.dataSource = new MatTableDataSource<UserModel>(this.ListCourrier);
      this.dataSource.paginator = this.paginator;
    })

  }

  applyFilter($event: KeyboardEvent) {

  }
}
