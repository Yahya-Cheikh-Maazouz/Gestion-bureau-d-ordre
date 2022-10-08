package com.maazouz.servicegestioncourrier.Service;
import com.maazouz.servicegestioncourrier.Core.Dtos.UpdateCourrierRequest;
import com.maazouz.servicegestioncourrier.Model.Courrier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CourrierService {
    ResponseEntity<Courrier> SaveCourrier(String courrierRequest, MultipartFile[] documents) throws IOException;
    ResponseEntity<Courrier> UpdateCourrier(Long id,UpdateCourrierRequest courrier);
    void DeleteCourrier(Long idCourrier);
    ResponseEntity<Courrier> FindCourrier(Long idCourrier);
    ResponseEntity<List<Courrier>> CourriersEntrant();
    ResponseEntity<List<Courrier>> CourriersSortant();
    ResponseEntity<List<Courrier>> Courriers();
    void ChangeStatusCourrier(Long id,String State);
}
