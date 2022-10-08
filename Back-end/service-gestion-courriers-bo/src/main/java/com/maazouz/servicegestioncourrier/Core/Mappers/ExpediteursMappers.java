package com.maazouz.servicegestioncourrier.Core.Mappers;

import com.maazouz.servicegestioncourrier.Core.Dtos.ExpediteurMoralDto;
import com.maazouz.servicegestioncourrier.Core.Dtos.ExpediteurPhysiqueDto;
import com.maazouz.servicegestioncourrier.Model.ExpediteurMoral;
import com.maazouz.servicegestioncourrier.Model.ExpediteurPhysique;

public class ExpediteursMappers {


    public ExpediteurPhysique DtoToExpediteurPhysique(ExpediteurPhysiqueDto ExpDto){
        return ExpediteurPhysique.builder()
                .cin(ExpDto.getCin())
                .email(ExpDto.getEmail())
                .nom(ExpDto.getNom())
                .prenom(ExpDto.getPrenom())
                .tel(ExpDto.getTel())
                .adresse(ExpDto.getAdresse())
                .build();
    }


    public ExpediteurMoral DtoToExpediteurMoral(ExpediteurMoralDto ExpDto){
        return ExpediteurMoral.builder()
                .raisonSocial(ExpDto.getRaisonSocial())
                .email(ExpDto.getEmail())
                .tel(ExpDto.getTel())
                .adresse(ExpDto.getAdresse())
                .build();
    }
}
