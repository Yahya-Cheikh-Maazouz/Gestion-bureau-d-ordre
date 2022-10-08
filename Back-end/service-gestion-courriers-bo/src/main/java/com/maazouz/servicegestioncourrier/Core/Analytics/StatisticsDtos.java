package com.maazouz.servicegestioncourrier.Core.Analytics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class StatisticsDtos {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class dtoCountCourrier{
        String label;
        Long statisticValue;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class dtoCountCourrierList{
        List<dtoCountCourrier> data=new ArrayList<dtoCountCourrier> ();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class dtoPourcentageList{
        List<dtoCountCourrier> data=new ArrayList<dtoCountCourrier> ();
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class dtoNombre{
        List<dtoCountCourrier> data=new ArrayList<dtoCountCourrier> ();
    }
}
