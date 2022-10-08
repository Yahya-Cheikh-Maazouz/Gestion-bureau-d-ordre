package com.maazouz.servicegestioncourrier.Model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
public  class ExpediteurPhysique extends Expediteur  {
    private String cin;
    private String prenom;
    private String nom;


    @Builder
    public ExpediteurPhysique(Long id, String tel, String adresse, String email, String cin, String prenom, String nom) {
        super(id, tel, adresse, email,null);
        this.cin = cin;
        this.prenom = prenom;
        this.nom = nom;
    }





}
