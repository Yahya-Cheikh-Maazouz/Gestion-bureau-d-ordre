import { Component, OnInit } from '@angular/core';
import {ExpediteurService} from "../../../../core/services/expediteur.service";
import {Expediteur} from "../../../../core/models/Expediteur";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {map} from "rxjs";
import {CourrierExpTypeRequestModel} from "../../../../core/models/courrierExpTypeRequest.model";

@Component({
  selector: 'app-save-expediteur-modal',
  templateUrl: './save-expediteur-modal.component.html',
  styleUrls: ['./save-expediteur-modal.component.scss']
})
export class SaveExpediteurModalComponent implements OnInit {

  private expediteur =<Expediteur>{};
  public isMoral:boolean=true

  constructor(private api : ExpediteurService) { }
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
  }


  TypeExpediteur($event: Event) {
    if (($event.target as HTMLInputElement)?.value) {
      this.isMoral = ($event.target as HTMLInputElement).value == "moral";
    }
  }

  public saveExpediteur() : void {
    if(this.isMoral)
    this.api.saveExpediteurMoral(this.getFormData()).subscribe((res=>{
      alert("has been inserted")
    }))
    else
      this.api.saveExpediteurPhysiques(this.getFormData()).subscribe((res=>{
        alert("has been inserted")
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
