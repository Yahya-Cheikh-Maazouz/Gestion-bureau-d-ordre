package com.maazouz.servicegestioncourrier.Core.Analytics;
import com.maazouz.servicegestioncourrier.Model.pojo.EnumTypesUtil;
import com.maazouz.servicegestioncourrier.Repository.CourrierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StatisticsImpl implements Statistics {

    private final CourrierRepository courrierRepository;

    @Override
    public List courriers(String type) {
       // return courrierRepository.courriers(EnumTypesUtil.TypeCourrier.valueOf(type));
        return null;
    }

    @Override
    public StatisticsDtos.dtoCountCourrier nombreDeCourriersByType(String type) {
        StatisticsDtos.dtoCountCourrier obj =new StatisticsDtos.dtoCountCourrier();
        obj.setStatisticValue(courrierRepository.courriers(EnumTypesUtil.TypeCourrier.valueOf(type)));
        obj.setLabel("nomre de courriers "+type+"s");

        return obj;
    }

    @Override
    public StatisticsDtos.dtoCountCourrier nombreDeCourriers() {
        StatisticsDtos.dtoCountCourrier obj =new StatisticsDtos.dtoCountCourrier();
        obj.setStatisticValue(courrierRepository.allCourriers());
        obj.setLabel("nombre totales des courriers ");

        return obj;
    }

    @Override
    public StatisticsDtos.dtoPourcentageList pourcentageCourrier() {
        ArrayList<String> type = new ArrayList<String>();
        StatisticsDtos.dtoPourcentageList dto= new StatisticsDtos.dtoPourcentageList();
        type.add("Entrant");
        type.add("Sortant");

        for (String t : type) {
            dto.data.add(nombreDeCourriersByType(t));
        }

        for (int i = 0; i < dto.data.size(); i++) {
            dto.data.get(i).setLabel(type.get(i));
        }

        return dto;

    }

    @Override
    public StatisticsDtos.dtoCountCourrier nombreDeCourriersByEtat(EnumTypesUtil.EtatCourrier state) {
        StatisticsDtos.dtoCountCourrier obj =new StatisticsDtos.dtoCountCourrier();
        obj.setLabel("nombre totales des courriers "+state);
        try{
            obj.setStatisticValue(courrierRepository.allCourriers1(state));
            return obj;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return obj;
    }

    @Override
    public StatisticsDtos.dtoCountCourrierList nombreTotalDeCourriersByState() {
        ArrayList<String> state = new ArrayList<String>();
        StatisticsDtos.dtoCountCourrierList dto= new StatisticsDtos.dtoCountCourrierList();
        state.add("ENVOYER");
        state.add("REFUSER");
        state.add("TRAITER");
        state.add("CLOTURER");
        state.add("ENTRAITEMNT");

        for (String s : state) {
            dto.data.add(nombreDeCourriersByEtat(EnumTypesUtil.EtatCourrier.valueOf(s)));
        }

        for (int i = 0; i < dto.data.size(); i++) {
           dto.data.get(i).setLabel(state.get(i));
        }

        return dto;
    }

    @Override
    public StatisticsDtos.dtoNombre Total() {
        return null;
    }


}
