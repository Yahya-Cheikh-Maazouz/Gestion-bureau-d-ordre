import {Component, Input, OnInit} from '@angular/core';
import {Expediteur} from "../../../../core/models/Expediteur";
import {ExpediteurService} from "../../../../core/services/expediteur.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-update-expediteur-modal',
  templateUrl: './update-expediteur-modal.component.html',
  styleUrls: ['./update-expediteur-modal.component.scss']
})
export class UpdateExpediteurModalComponent implements OnInit {

  constructor(private api : ExpediteurService) { }

  @Input("isMoral")
  isMoral: boolean | undefined
  @Input("row")
  row: any
  private expediteur =<Expediteur>{};

  ExpedForm = new FormGroup({
    adresse: new FormControl("", Validators.compose([
      Validators.required
    ])),
    email: new FormControl("", Validators.compose([
      Validators.required
    ])),
    telephone: new FormControl("", Validators.compose([
      Validators.required
    ])),
    nom: new FormControl("", Validators.compose([
      Validators.required
    ])),
    prenom: new FormControl("", Validators.compose([
      Validators.required
    ])),
    cin: new FormControl("", Validators.compose([
      Validators.required
    ])),
    raisonSocial: new FormControl("", Validators.compose([
      Validators.required
    ])),
  });


  ngOnInit(): void {
    if(this.isMoral)
    {
      console.log("executed")
      console.log(this.row)
      //if(this.ExpedForm)
     // this.ExpedForm.get("raisonSocial")?.setValue(this.row.raisonSocial)

    }
    else {

    }
  }



  public saveExpediteur() : void {
    if(this.isMoral)
      this.api.updateExpediteurMoral(this.row.id,this.getFormData()).subscribe((res=>{
        alert("has been updated")
      }))
    else
      this.api.updateExpediteurPhysiques(this.row.id,this.getFormData()).subscribe((res=>{
        alert("has been updated")
      }))
  }



  getFormData(){
    if(this.isMoral){
      return {
        "raisonSocial":this.ExpedForm.get("raisonSocial")?.value ,
        "adresse":this.ExpedForm.get("adresse")?.value,
        "tel":this.ExpedForm.get("telephone")?.value,
        "email":this.ExpedForm.get("email")?.value,
      };
    }
    else {
      return {
        "nom":this.ExpedForm.get("nom")?.value ,
        "prenom":this.ExpedForm.get("prenom")?.value ,
        "cin":this.ExpedForm.get("cin")?.value ,
        "adresse":this.ExpedForm.get("adresse")?.value,
        "tel":this.ExpedForm.get("telephone")?.value,
        "email":this.ExpedForm.get("email")?.value,
      };
    }
  }


}
