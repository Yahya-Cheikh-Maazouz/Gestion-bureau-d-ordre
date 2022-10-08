import {Component, Input, OnInit} from '@angular/core';
import {Courrier} from "../../../../core/models/Courrier";
import {CourrierService} from "../../../../core/services/courrier.service";

@Component({
  selector: 'app-update-courrier-modal',
  templateUrl: './update-courrier-modal.component.html',
  styleUrls: ['./update-courrier-modal.component.scss']
})
export class UpdateCourrierModalComponent implements OnInit {

  @Input("courriertemp")
  courriertemp!:Courrier
  formData!:any
  constructor(private api :CourrierService) {

  }

  ngOnInit(): void {
    this.formData={
      date:null,
      reference:null,
      destinataire:null,
      modeReception:null,
      commentaire:null
    }
  }

  updateCourrier() {

    this.formData.date=this.courriertemp.dateReception
    this.formData.reference=this.courriertemp.reference
    this.formData.destinataire=this.courriertemp.destinataire
    this.formData.modeReception=this.courriertemp.modeReception
    this.formData.commentaire=this.courriertemp.commentaire

    this.api.updateCourrier(this.courriertemp.id,this.formData).subscribe(res=>{
      alert("bien modifier")
    })
    //console.log(this.formData)
  }
}
