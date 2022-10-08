package com.maazouz.servicegestioncourrier.Core.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ExpediteurMoralDto {
    private String  raisonSocial;
    private String  adresse;
    private String  tel;
    private String  email;
}
