package com.maazouz.servicegestioncourrier.Model;
import lombok.*;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
public final class ExpediteurMoral extends Expediteur {
    private String raisonSocial;


    @Builder
    public ExpediteurMoral(Long id, String tel, String adresse, String email, String raisonSocial) {
        super(id, tel, adresse, email,null);
        this.raisonSocial = raisonSocial;
    }
}
