package com.maazouz.servicegestioncourrier.EndPoint;
import com.maazouz.servicegestioncourrier.Core.Dtos.UpdateCourrierRequest;
import com.maazouz.servicegestioncourrier.Model.Courrier;
import com.maazouz.servicegestioncourrier.Model.ExpediteurMoral;
import com.maazouz.servicegestioncourrier.Model.ExpediteurPhysique;
import com.maazouz.servicegestioncourrier.Service.ExpediteurService;
import com.maazouz.servicegestioncourrier.Service.Impl.CourrierServiceImpl;
import com.maazouz.servicegestioncourrier.Service.Impl.ExpediteurServiceImpl;
import com.maazouz.servicegestioncourrier.Service.OperationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@Transactional
@CrossOrigin
public class ExpediteurController {
    private final ExpediteurService expediteurService;


    @PostMapping( path ="/expediteurMorals")
    public ResponseEntity<ExpediteurMoral>  saveExpediteurMoral(@RequestBody ExpediteurMoral exp){
        return this.expediteurService.SaveExpediteurMoral(exp);
    }

    @PostMapping( path ="/expediteurPhysiques")
    public ResponseEntity<ExpediteurPhysique>  saveExpediteurPhysiques(@RequestBody ExpediteurPhysique exp){
        return this.expediteurService.SaveExpediteurPhysique(exp);
    }


    @PutMapping( path ="/expediteurMorals/{id}")
    public ResponseEntity<ExpediteurMoral> UpdateExpediteurMoral(@PathVariable Long id ,@RequestBody ExpediteurMoral exp)
    {
        return expediteurService.UpdateExpediteurMoral(id,exp);
    }

    @PutMapping( path ="/expediteurPhysiques/{id}")
    public ResponseEntity<ExpediteurPhysique> UpdateExpediteurPhysique(@PathVariable Long id ,@RequestBody ExpediteurPhysique exp)
    {
        return expediteurService.UpdateExpediteurPhysique(id,exp);
    }



    @GetMapping( path ="/expediteurs")
    public ResponseEntity<ExpediteurServiceImpl.dtoExpediteur> Expediteurs()
    {
        return expediteurService.Expediteurs();
    }

    @GetMapping( path ="/expediteurPhysiques")
    public ResponseEntity<List<ExpediteurPhysique>> ExpediteurPhysique()
    {
        return expediteurService.ExpediteursPhysiques();
    }

    @GetMapping( path ="/expediteurMorals")
    public ResponseEntity<List<ExpediteurMoral>> ListExpediteur()
    {
        return expediteurService.ExpediteursMoral();
    }
}
