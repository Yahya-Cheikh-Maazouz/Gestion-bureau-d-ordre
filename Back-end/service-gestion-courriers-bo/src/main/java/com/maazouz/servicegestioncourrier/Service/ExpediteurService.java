package com.maazouz.servicegestioncourrier.Service;

import com.maazouz.servicegestioncourrier.Core.Dtos.UpdateCourrierRequest;
import com.maazouz.servicegestioncourrier.Model.Courrier;
import com.maazouz.servicegestioncourrier.Model.Expediteur;
import com.maazouz.servicegestioncourrier.Model.ExpediteurMoral;
import com.maazouz.servicegestioncourrier.Model.ExpediteurPhysique;
import com.maazouz.servicegestioncourrier.Service.Impl.ExpediteurServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ExpediteurService {

    void DeleteExpediteur(Long id);
    ResponseEntity<List<ExpediteurPhysique>> ExpediteursPhysiques();
    ResponseEntity<List<ExpediteurMoral>> ExpediteursMoral();
    ResponseEntity<ExpediteurServiceImpl.dtoExpediteur> Expediteurs();
    ResponseEntity<ExpediteurMoral> SaveExpediteurMoral(ExpediteurMoral exp) ;
    ResponseEntity<ExpediteurPhysique> SaveExpediteurPhysique(ExpediteurPhysique exp) ;
    ResponseEntity<ExpediteurMoral> UpdateExpediteurMoral(Long id, ExpediteurMoral exp);
    ResponseEntity<ExpediteurPhysique> UpdateExpediteurPhysique(Long id, ExpediteurPhysique exp);

}
