package com.maazouz.servicegestioncourrier.Core.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ExpediteurPhysiqueDto {
    private String  nom;
    private String  prenom;
    private String  cin;
    private String  adresse;
    private String  tel;
    private String  email;
}
