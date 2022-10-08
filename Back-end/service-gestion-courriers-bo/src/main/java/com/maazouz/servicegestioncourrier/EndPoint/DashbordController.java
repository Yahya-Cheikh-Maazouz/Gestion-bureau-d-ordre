package com.maazouz.servicegestioncourrier.EndPoint;

import com.maazouz.servicegestioncourrier.Core.Analytics.Statistics;
import com.maazouz.servicegestioncourrier.Core.Analytics.StatisticsDtos;
import com.maazouz.servicegestioncourrier.Model.pojo.EnumTypesUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Transactional
@CrossOrigin
public class DashbordController {
    private final Statistics statistics;

    @GetMapping( path ="/stat/courriers/{type}")
    public ResponseEntity<StatisticsDtos.dtoCountCourrier> stat(@PathVariable String type)
    {
        return ResponseEntity.ok(statistics.nombreDeCourriersByType(type));
    }

    @GetMapping( path ="/stat/courriers")
    public ResponseEntity<StatisticsDtos.dtoCountCourrier> stats()
    {
        return ResponseEntity.ok(statistics.nombreDeCourriers());
    }

    @GetMapping( path ="/stat/courriers/state/{state}")
    public ResponseEntity<StatisticsDtos.dtoCountCourrier> stats3(@PathVariable String state)
    {
        return ResponseEntity.ok(statistics.nombreDeCourriersByEtat(EnumTypesUtil.EtatCourrier.valueOf(state)));
    }

    @GetMapping( path ="/stat/courriers/states")
    public ResponseEntity<StatisticsDtos.dtoCountCourrierList> stats4()
    {
        return ResponseEntity.ok(this.statistics.nombreTotalDeCourriersByState());
    }


    @GetMapping( path ="/stat/courriers/pourcentage")
    public ResponseEntity<StatisticsDtos.dtoPourcentageList> stats5()
    {
        return ResponseEntity.ok(this.statistics.pourcentageCourrier());
    }
}
