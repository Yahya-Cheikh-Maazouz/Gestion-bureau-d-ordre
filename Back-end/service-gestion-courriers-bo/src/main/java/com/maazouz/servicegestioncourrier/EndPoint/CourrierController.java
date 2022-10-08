package com.maazouz.servicegestioncourrier.EndPoint;
import com.maazouz.servicegestioncourrier.Core.Dtos.UpdateCourrierRequest;
import com.maazouz.servicegestioncourrier.Model.Courrier;
import com.maazouz.servicegestioncourrier.Model.Expediteur;
import com.maazouz.servicegestioncourrier.Model.ExpediteurPhysique;
import com.maazouz.servicegestioncourrier.Service.ExpediteurService;
import com.maazouz.servicegestioncourrier.Service.Impl.CourrierServiceImpl;
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
public class CourrierController {

    private final CourrierServiceImpl courrierService;
    private final OperationService operationService;
    private final ExpediteurService expediteurService;


    @PostMapping( path ="/add/courrier")
    public void saveCourrier(@RequestParam("files") MultipartFile[] documents, @RequestParam("datas") String courrier) throws IOException {
        this.courrierService.SaveCourrier(courrier,documents);
    }


    @PutMapping( path ="/update/courrier/{id}")
    public ResponseEntity<Courrier> UpdateCourrier(@PathVariable Long id ,@RequestBody UpdateCourrierRequest courrier)
    {
        return courrierService.UpdateCourrier(id,courrier);
    }

    @PutMapping( path ="/update/courrier/state/{id}")
    public void ChangeStatusCourrier(@PathVariable Long id ,@RequestBody String state)
    {
        courrierService.ChangeStatusCourrier(id,state);
    }

    @DeleteMapping ( path ="/delete/courrier/{id}")
    public void DeleteCourrier(@PathVariable Long id)
    {
        courrierService.DeleteCourrier(id);
    }

    @GetMapping( path ="/courriers/{type}")
    public ResponseEntity<List<Courrier>> ListCourrierByType(@PathVariable String type)
    {
        if (type.equals("entrant"))
            return courrierService.CourriersEntrant();
        else if (type.equals("sortant"))
            return courrierService.CourriersSortant();
        else throw new RuntimeException("not found");
    }

    @PostMapping( path ="/operation/{type}/{idEmp}/{idCour}")
    public void operation(@PathVariable String type,
                          @PathVariable Long idEmp,
                          @PathVariable Long idCour) {

        operationService.createOperation(type,idEmp,idCour);
    }


    @GetMapping( path ="/courriers")
    public ResponseEntity<List<Courrier>> ListCourrier()
    {
        return courrierService.Courriers();
    }

    @DeleteMapping ( path ="/delete/exp/{id}")
    public void DeleteExpediteur(@PathVariable Long id)
    {
        expediteurService.DeleteExpediteur(id);
    }

}
