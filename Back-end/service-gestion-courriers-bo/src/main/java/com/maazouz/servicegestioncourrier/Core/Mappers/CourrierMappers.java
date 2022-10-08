package com.maazouz.servicegestioncourrier.Core.Mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maazouz.servicegestioncourrier.Core.Dtos.CourrierExpExistSaveRequst;
import com.maazouz.servicegestioncourrier.Core.Dtos.CourrierExpMoralNotExistSaveRequst;
import com.maazouz.servicegestioncourrier.Core.Dtos.CourrierExpPhysiqueNotExistSaveRequst;
import com.maazouz.servicegestioncourrier.Model.Courrier;
import com.maazouz.servicegestioncourrier.Model.ExpediteurMoral;
import com.maazouz.servicegestioncourrier.Model.ExpediteurPhysique;
import org.modelmapper.ModelMapper;

public class CourrierMappers {
    ObjectMapper om = new ObjectMapper();

    public CourrierExpExistSaveRequst CourrierExpExistSaveRequst(String courrierJson){
        System.out.println(courrierJson);
        CourrierExpExistSaveRequst courrierTemp=null;
        /* DESERIALIZE JSON TO DTO REQUEST */
        try {
            courrierTemp = om.readValue(courrierJson, CourrierExpExistSaveRequst.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assert courrierTemp != null;

        return courrierTemp;


    }

    public Courrier CourrierExpPhysiqueNotExistRequestDeserializeToCourrier(String courrierJson){
        CourrierExpPhysiqueNotExistSaveRequst courrierTemp=null;
        /* DESERIALIZE JSON TO DTO REQUEST */
        try {
            courrierTemp = om.readValue(courrierJson, CourrierExpPhysiqueNotExistSaveRequst.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assert courrierTemp != null;
        /* MAP EXPEDITEUR PHYSIQUE */
        ExpediteursMappers ExpMapper = new ExpediteursMappers();
        ExpediteurPhysique expediteur=ExpMapper.DtoToExpediteurPhysique(courrierTemp.getExpediteur());
        /* MAP COURRIER REQUEST TO TO COURRIER */
        ModelMapper modelMapper = new ModelMapper();
        Courrier courrier = modelMapper.map(courrierTemp, Courrier.class);
        courrier.setExpediteur(expediteur);
        return courrier;
    }





    public Courrier CourrierExpMoralNotExistRequestDeserializeToCourrier(String courrierJson){
        CourrierExpMoralNotExistSaveRequst courrierTemp=null;
        /* DESERIALIZE JSON TO DTO REQUEST */
        try {
            courrierTemp = om.readValue(courrierJson, CourrierExpMoralNotExistSaveRequst.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assert courrierTemp != null;
        /* MAP EXPEDITEUR PHYSIQUE */
        ExpediteursMappers ExpMapper = new ExpediteursMappers();
        ExpediteurMoral expediteur = ExpMapper.DtoToExpediteurMoral(courrierTemp.getExpediteur());
        /* MAP COURRIER REQUEST TO TO COURRIER */
        ModelMapper modelMapper = new ModelMapper();
        Courrier courrier = modelMapper.map(courrierTemp, Courrier.class);
        courrier.setExpediteur(expediteur);
        return courrier;
    }
}
