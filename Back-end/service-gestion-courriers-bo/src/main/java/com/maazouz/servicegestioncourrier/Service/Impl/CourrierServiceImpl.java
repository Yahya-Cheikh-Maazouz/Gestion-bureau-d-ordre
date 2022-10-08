package com.maazouz.servicegestioncourrier.Service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maazouz.servicegestioncourrier.Core.Dtos.CourrierExpExistSaveRequst;
import com.maazouz.servicegestioncourrier.Core.Dtos.UpdateCourrierRequest;
import com.maazouz.servicegestioncourrier.Core.Mappers.CourrierMappers;
import com.maazouz.servicegestioncourrier.Model.Courrier;
import com.maazouz.servicegestioncourrier.Model.DocumentFile;
import com.maazouz.servicegestioncourrier.Model.Expediteur;
import com.maazouz.servicegestioncourrier.Model.pojo.EnumTypesUtil;
import com.maazouz.servicegestioncourrier.Repository.CourrierRepository;
import com.maazouz.servicegestioncourrier.Repository.DocRepository;
import com.maazouz.servicegestioncourrier.Repository.ExpediteurRepository;
import com.maazouz.servicegestioncourrier.Service.CourrierService;
import com.maazouz.servicegestioncourrier.Service.DocStorageService;
import com.maazouz.servicegestioncourrier.Service.OperationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class CourrierServiceImpl implements CourrierService {
    private final CourrierRepository courrierRepository;
    private final DocStorageService docStorageService;
    private final ExpediteurRepository expediteurRepository;
    private final OperationService operationService;
    private final DocRepository docRepository;
    ObjectMapper om=new ObjectMapper();


    public ResponseEntity<Courrier> SaveCourrier(String courrierRequest, MultipartFile[] documents) throws IOException {

        /*
         *  SPLIT DATA BEFORE DESERIALIZATION  DON'T CHANGE THIS !!!!!!!!!!!
         *  INDEX [0] CONTAIN TYPE OF COURRIER INDEX [1] JSON OBJECT OF COURRIER
         */

        List<String> data =  List.of(courrierRequest.split(",#/"));
        String saveExpediteurRequest = data.get(0);
        String courrierJson = data.get(1);
        Long idUser= Long.valueOf(data.get(2));

        /* GET DOCUMENT IMAGE */
        List<DocumentFile> Documentfiles=new ArrayList<DocumentFile>();
        for (MultipartFile file: documents) {
            String docname = file.getOriginalFilename();
            DocumentFile doc = new DocumentFile(null,docname,file.getContentType(),file.getBytes());
            Documentfiles.add(doc);
        }

        /* IF EXPEDITEUR ALREADY EXIST  */
         if (saveExpediteurRequest.toString().equals(EnumTypesUtil.CourrierExpRequest.EXIST.toString())){
            CourrierMappers mapper = new CourrierMappers();
            CourrierExpExistSaveRequst courrierDto = mapper.CourrierExpExistSaveRequst(courrierJson);
            /* MAP EXPEDITEUR */
            Expediteur expediteur = this.expediteurRepository.findById(courrierDto.getExpediteur()).get();
             System.out.println("i am here");
            /* MAP COURRIER REQUEST TO TO COURRIER */
            ModelMapper modelMapper = new ModelMapper();
            Courrier courrier = modelMapper.map(courrierDto, Courrier.class);
            courrier.setExpediteur(expediteur);
            courrier.setEtatCourrier(EnumTypesUtil.EtatCourrier.ENTRAITEMNT);
            this.docRepository.saveAll(Documentfiles);
            courrier.setDoc(Documentfiles.get(0));
            Courrier tt = this.courrierRepository.save(courrier);
             if(tt!=null)
                 this.operationService.createOperation("CREATE",idUser,tt.getId());
        }
        else if(saveExpediteurRequest.toString().equals(EnumTypesUtil.CourrierExpRequest.MORAL.toString()))
        {
            CourrierMappers mapper = new CourrierMappers();
            Courrier courrier = mapper.CourrierExpMoralNotExistRequestDeserializeToCourrier(courrierJson);
            this.expediteurRepository.save(courrier.getExpediteur());
            courrier.setEtatCourrier(EnumTypesUtil.EtatCourrier.ENTRAITEMNT);
            this.docRepository.saveAll(Documentfiles);
            courrier.setDoc(Documentfiles.get(0));
            Courrier tt = this.courrierRepository.save(courrier);
            this.courrierRepository.save(courrier);
        }
        else if(saveExpediteurRequest.toString().equals(EnumTypesUtil.CourrierExpRequest.PHYSIQUE.toString()))
        {
            CourrierMappers mapper = new CourrierMappers();
            Courrier courrier = mapper.CourrierExpPhysiqueNotExistRequestDeserializeToCourrier(courrierJson);
            this.expediteurRepository.save(courrier.getExpediteur());
            courrier.setEtatCourrier(EnumTypesUtil.EtatCourrier.ENTRAITEMNT);
            this.docRepository.saveAll(Documentfiles);
            courrier.setDoc(Documentfiles.get(0));
            Courrier tt = this.courrierRepository.save(courrier);
            this.courrierRepository.save(courrier);
        }

        return null;
    }

    @Override
    public ResponseEntity<Courrier> UpdateCourrier(Long id,UpdateCourrierRequest courrier) {
        Courrier courrierTemp= this.courrierRepository.findById(id).get();
        courrierTemp.setCommentaire(courrier.getCommentaire());
        courrierTemp.setDateReception(courrier.getDate());
        courrierTemp.setReference(courrier.getReference());
        courrierTemp.setDestinataire(courrier.getDestinataire());
        courrierTemp.setModeReception(courrier.getModeReception());
        return ResponseEntity.ok(this.courrierRepository.save(courrierTemp));
    }

    @Override
    public ResponseEntity<Courrier> FindCourrier(Long idCourrier) {
        Courrier courrierTemp= this.courrierRepository.findById(idCourrier).get();
            return ResponseEntity.ok(courrierTemp);
    }

    @Override
    public void DeleteCourrier(Long idCourrier) {
        Courrier courrierTemp= this.courrierRepository.findById(idCourrier).get();
        this.operationService.deleteAllOperation(courrierTemp);
        this.courrierRepository.delete(courrierTemp);
    }

    @Override
    public ResponseEntity<List<Courrier>> CourriersEntrant() {
        return ResponseEntity.ok( this.courrierRepository.findByTypeCourrier(EnumTypesUtil.TypeCourrier.Entrant));
    }

    @Override
    public ResponseEntity<List<Courrier>> CourriersSortant() {
        return ResponseEntity.ok( this.courrierRepository.findByTypeCourrier(EnumTypesUtil.TypeCourrier.Sortant));
    }

    @Override
    public ResponseEntity<List<Courrier>> Courriers() {
        return ResponseEntity.ok( this.courrierRepository.findAll());
    }

    @Override
    public void ChangeStatusCourrier(Long id, String State) {
        Courrier courrierTemp= this.courrierRepository.findById(id).get();
        courrierTemp.setEtatCourrier(EnumTypesUtil.EtatCourrier.valueOf(State));
        this.courrierRepository.save(courrierTemp);
    }
}
