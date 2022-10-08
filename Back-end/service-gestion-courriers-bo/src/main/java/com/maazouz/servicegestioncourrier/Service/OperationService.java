package com.maazouz.servicegestioncourrier.Service;
import com.maazouz.servicegestioncourrier.Model.Courrier;
import com.maazouz.servicegestioncourrier.Model.CourrierOperation;

public interface OperationService {
    void createOperation(String operationType,Long idEmployer,Long idCourrier);
    void deleteAllOperation(Courrier Courrier);
}
