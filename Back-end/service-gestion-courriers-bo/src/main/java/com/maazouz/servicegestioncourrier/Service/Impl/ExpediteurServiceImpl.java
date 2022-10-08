package com.maazouz.servicegestioncourrier.Service.Impl;

import com.maazouz.servicegestioncourrier.Model.Courrier;
import com.maazouz.servicegestioncourrier.Model.Expediteur;
import com.maazouz.servicegestioncourrier.Model.ExpediteurMoral;
import com.maazouz.servicegestioncourrier.Model.ExpediteurPhysique;
import com.maazouz.servicegestioncourrier.Repository.CourrierRepository;
import com.maazouz.servicegestioncourrier.Repository.ExpediteurMoralRepository;
import com.maazouz.servicegestioncourrier.Repository.ExpediteurPhysiqueRepository;
import com.maazouz.servicegestioncourrier.Repository.ExpediteurRepository;
import com.maazouz.servicegestioncourrier.Service.CourrierService;
import com.maazouz.servicegestioncourrier.Service.ExpediteurService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
@CrossOrigin
public class ExpediteurServiceImpl implements ExpediteurService {
    private final ExpediteurRepository expediteurRepository;
    private final CourrierRepository courrierRepository;
    private final CourrierService courrierService;
    private final ExpediteurPhysiqueRepository physiqueRepository;
    private final ExpediteurMoralRepository moralRepository;



    @Override
    public void DeleteExpediteur(Long id) {
        Expediteur exp = this.expediteurRepository.findById(id).get();
        List<Courrier> courrierTemp = courrierRepository.findByExpediteur(exp);
        courrierTemp.forEach(res->{
            this.courrierService.DeleteCourrier(res.getId());
        });
        this.expediteurRepository.delete(exp);
    }

    @Override
    public ResponseEntity<List<ExpediteurPhysique>> ExpediteursPhysiques() {
        return ResponseEntity.ok(this.physiqueRepository.findAll());
    }

    @Override
    public ResponseEntity<List<ExpediteurMoral>> ExpediteursMoral() {
        return ResponseEntity.ok(this.moralRepository.findAll());
    }

    @Override
    public ResponseEntity<dtoExpediteur> Expediteurs() {

        return ResponseEntity.ok( dtoExpediteur.builder()
                .expediteurMorals(this.moralRepository.findAll())
                .expediteurPhysiques(this.physiqueRepository.findAll())
                .build());

    }

    @Override
    public ResponseEntity<ExpediteurMoral> SaveExpediteurMoral(ExpediteurMoral exp) {
        return ResponseEntity.ok(this.moralRepository.save(exp));
    }

    @Override
    public ResponseEntity<ExpediteurPhysique> SaveExpediteurPhysique(ExpediteurPhysique exp) {
        return ResponseEntity.ok(this.physiqueRepository.save(exp));
    }

    @Override
    public ResponseEntity<ExpediteurMoral> UpdateExpediteurMoral(Long id, ExpediteurMoral exp) {
        ExpediteurMoral expTemp = this.moralRepository.findById(id).get();
        if(exp.getAdresse()!=null)
        expTemp.setAdresse(exp.getAdresse());
        if(exp.getEmail()!=null)
        expTemp.setEmail(exp.getEmail());
        if(exp.getTel()!=null)
        expTemp.setTel(exp.getTel());
        if(exp.getRaisonSocial()!=null)
        expTemp.setRaisonSocial(exp.getRaisonSocial());
        return ResponseEntity.ok(this.moralRepository.save(expTemp));
    }

    @Override
    public ResponseEntity<ExpediteurPhysique> UpdateExpediteurPhysique(Long id, ExpediteurPhysique exp) {
        ExpediteurPhysique expTemp = this.physiqueRepository.findById(id).get();
        if(exp.getAdresse()!=null)
            expTemp.setAdresse(exp.getAdresse());
        if(exp.getEmail()!=null)
            expTemp.setEmail(exp.getEmail());
        if(exp.getTel()!=null)
            expTemp.setTel(exp.getTel());
        if(exp.getCin()!=null)
            expTemp.setCin(exp.getCin());
        if(exp.getNom()!=null)
            expTemp.setNom(exp.getNom());
        if(exp.getPrenom()!=null)
            expTemp.setPrenom(exp.getPrenom());
        return ResponseEntity.ok(this.physiqueRepository.save(expTemp));
    }

    @Data @Builder
    @AllArgsConstructor @NoArgsConstructor
    public static class dtoExpediteur {
        List<ExpediteurMoral>expediteurMorals;
        List<ExpediteurPhysique>expediteurPhysiques;
    }

}
