package com.maazouz.servicegestioncourrier.Core.Analytics;
import com.maazouz.servicegestioncourrier.Model.pojo.EnumTypesUtil;

import java.util.List;

public interface Statistics {
    List courriers(String type);
    StatisticsDtos.dtoCountCourrier nombreDeCourriersByType(String type);
    StatisticsDtos.dtoCountCourrier nombreDeCourriers();
    StatisticsDtos.dtoPourcentageList pourcentageCourrier();
    StatisticsDtos.dtoCountCourrier nombreDeCourriersByEtat(EnumTypesUtil.EtatCourrier state);
    StatisticsDtos.dtoCountCourrierList nombreTotalDeCourriersByState();
    StatisticsDtos.dtoNombre Total();


}
