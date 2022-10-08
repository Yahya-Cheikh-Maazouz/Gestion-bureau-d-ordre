package com.maazouz.servicegestioncourrier.Service.Impl;

import com.maazouz.servicegestioncourrier.Model.Courrier;
import com.maazouz.servicegestioncourrier.Model.CourrierOperation;
import com.maazouz.servicegestioncourrier.Model.pojo.EnumTypesUtil;
import com.maazouz.servicegestioncourrier.Model.pojo.Employer;
import com.maazouz.servicegestioncourrier.MultiTenant.Interceptor.TenantContext;
import com.maazouz.servicegestioncourrier.Remote.IUserRemote;
import com.maazouz.servicegestioncourrier.Repository.CourrierRepository;
import com.maazouz.servicegestioncourrier.Repository.OperationRepository;
import com.maazouz.servicegestioncourrier.Service.OperationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class OperationServiceImpl implements OperationService {
    private final OperationRepository operationRepository;
    private final CourrierRepository courrierRepository;
    private final IUserRemote iUserRemote;


    @Override
    public void createOperation(String operationType,Long idEmployer,Long idCourrier) {

        HttpHeaders headers = new HttpHeaders();

        headers.add("XTenantID", TenantContext.getCurrentTenant());
        Courrier courrier=courrierRepository.findById(idCourrier).get();
        Employer employer=iUserRemote.findEmployer(headers,idEmployer);

        System.out.println(courrier);
        System.out.println(employer);

        switch (operationType){
            case "CREATE" :{
                this.operationRepository.save(
                        CourrierOperation.builder()
                                .courrier(courrier)
                                .operationType(EnumTypesUtil.CourrierOperation.valueOf(operationType))
                                .date(new Date())
                                .employer(employer)
                                .details(createDetailsMessage(courrier,employer,operationType))
                                .idEmplyer(idEmployer)
                                .build()
                );
                break;
            }
            case "Other" :{
                break;
            }
            default: throw new RuntimeException("operation not exist");
        }


    }

    @Override
    public void deleteAllOperation(Courrier Courrier) {
        List<CourrierOperation> listeOperation = this.operationRepository.findAllByCourrier(Courrier);
        this.operationRepository.deleteAll(listeOperation);
    }


    public String createDetailsMessage(Courrier c,Employer e,String oper){
        return e.getFirstName()+" "+e.getLastName()
                +" a effectué "+oper+" "+new Date();
    }

}
