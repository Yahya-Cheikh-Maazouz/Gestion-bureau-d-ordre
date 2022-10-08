import {Component, OnInit, ViewChild} from '@angular/core';
import {Expediteur} from "../../../../core/models/Expediteur";
import {ExpediteurService} from "../../../../core/services/expediteur.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Courrier} from "../../../../core/models/Courrier";
import {CourrierService} from "../../../../core/services/courrier.service";
import {sharedData} from "../../sharedData.service";
import {CourrierExpTypeRequestModel} from "../../../../core/models/courrierExpTypeRequest.model";
import {AuthService} from "../../../../core/services/auth.service";

@Component({
  selector: 'app-save-courrier-modal',
  templateUrl: './save-courrier-modal.component.html',
  styleUrls: ['./save-courrier-modal.component.scss']
})
export class SaveCourrierModalComponent implements OnInit {
  public ListExpediteur = new Array<any>();
  private courrier =<Courrier>{};
  @ViewChild('checkbox') checkbox: any;
  public expediteurExist:boolean=true
  public isMoral:boolean=true
  public ExpediteurType: boolean=true;
  public formIsValided: boolean=false;
  public files: any[] | undefined=[]



  CourrierForm = new FormGroup({
    dateReception: new FormControl(this.courrier.dateReception, Validators.compose([
      Validators.required, Validators.minLength(1)])),
    reference: new FormControl(this.courrier.reference, Validators.compose([
      Validators.required, Validators.minLength(1)])),
    destinataire: new FormControl(this.courrier.destinataire, Validators.compose([
      Validators.required, Validators.minLength(1)])),
    modeReception: new FormControl(this.courrier.modeReception, Validators.compose([
      Validators.required,
      Validators.minLength(1)])),
    commentaire: new FormControl(this.courrier.commentaire, Validators.compose([
      Validators.required,
      Validators.minLength(1)
    ])),
    typeCourrier: new FormControl(this.courrier.typeCourrier, Validators.compose([
      Validators.required,
      Validators.minLength(1)
    ])),
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
    expediteur: new FormControl( Validators.compose([
      Validators.required
    ]))
  });

  constructor(private apiShared : sharedData,private apiExp : ExpediteurService,private api :CourrierService
  , private autthapi:AuthService) { }

  ngOnInit(): void {
    this.apiExp.Expediteurs().subscribe(res=>{
      if (this.isMoral)
      this.ListExpediteur=res.expediteurMorals
      else
        this.ListExpediteur=res.expediteurPhysiques
    })
  }

  public saveCourrier() : void {
    if(this.expediteurExist){
      this.api.saveCourrier(this.GetFormsDataFrom(),
        CourrierExpTypeRequestModel[CourrierExpTypeRequestModel.EXIST],
        this.autthapi.getIduser(),this.files).subscribe((res=>{
        alert("has been inserted")
      }))
    }
    else if(this.isMoral){
      this.api.saveCourrier(this.GetFormsDataFrom(),
        CourrierExpTypeRequestModel[CourrierExpTypeRequestModel.MORAL],
        this.autthapi.getIduser(),this.files).subscribe((res=>{
        alert("has been inserted")
      }))
    }
    else {
      this.api.saveCourrier(this.GetFormsDataFrom(),
        CourrierExpTypeRequestModel[CourrierExpTypeRequestModel.PHYSIQUE],
        this.autthapi.getIduser(),this.files).subscribe((res=>{
        alert("has been inserted")
      }))
    }

    this.formIsValided=false
  }


  onFileChange($event: any) {
    this.files = $event.target.files;
  }

  checkCheckBoxvalue(){
    this.expediteurExist=!this.expediteurExist
  }

  TypeExpediteur($event: Event) {
    if (($event.target as HTMLInputElement)?.value) {
      this.isMoral = ($event.target as HTMLInputElement).value == "moral";
    }
  }


  chooseExpediteurType($event: Event){
    if (($event.target as HTMLInputElement)?.value) {
     // console.log(($event.target as HTMLInputElement).value)
      this.ExpediteurType = ($event.target as HTMLInputElement).value == "moral";
    }
    if(this.ExpediteurType)
      this.apiExp.Expediteurs().subscribe((res)=>{
        this.ListExpediteur=res.expediteurMorals
      })
    else
      this.apiExp.Expediteurs().subscribe((res)=>{
        this.ListExpediteur=res.expediteurPhysiques
      })
  }



  GetFormsDataFrom():any{
    if(this.expediteurExist){
      return {
          "reference": this.CourrierForm.get("reference")?.value,
          "dateReception": this.CourrierForm.get("dateReception")?.value,
          "typeCourrier": this.CourrierForm.get("typeCourrier")?.value,
          "destinataire": this.CourrierForm.get("destinataire")?.value,
          "commentaire": this.CourrierForm.get("commentaire")?.value,
          "modeReception": this.CourrierForm.get("modeReception")?.value,
          "expediteur": this.CourrierForm.get("expediteur")?.value,
        };
    }
    else{
        if(this.isMoral){
          return {
            "reference": this.CourrierForm.get("reference")?.value,
            "dateReception": this.CourrierForm.get("dateReception")?.value,
            "typeCourrier": this.CourrierForm.get("typeCourrier")?.value,
            "destinataire": this.CourrierForm.get("destinataire")?.value,
            "commentaire": this.CourrierForm.get("commentaire")?.value,
            "modeReception": this.CourrierForm.get("modeReception")?.value,
            "expediteur": {
              "raisonSocial":this.CourrierForm.get("raisonSocial")?.value ,
              "adresse":this.CourrierForm.get("adresse")?.value,
              "tel":this.CourrierForm.get("telephone")?.value,
              "email":this.CourrierForm.get("email")?.value,
            }
          };
        }
        else {
          return {
            "reference": this.CourrierForm.get("reference")?.value,
            "dateReception": this.CourrierForm.get("dateReception")?.value,
            "typeCourrier": this.CourrierForm.get("typeCourrier")?.value,
            "destinataire": this.CourrierForm.get("destinataire")?.value,
            "commentaire": this.CourrierForm.get("commentaire")?.value,
            "modeReception": this.CourrierForm.get("modeReception")?.value,
            "expediteur": {
              "nom":this.CourrierForm.get("nom")?.value ,
              "prenom":this.CourrierForm.get("prenom")?.value ,
              "cin":this.CourrierForm.get("cin")?.value ,
              "adresse":this.CourrierForm.get("adresse")?.value,
              "tel":this.CourrierForm.get("telephone")?.value,
              "email":this.CourrierForm.get("email")?.value,
            }
          };
        }
    }
  }




}
