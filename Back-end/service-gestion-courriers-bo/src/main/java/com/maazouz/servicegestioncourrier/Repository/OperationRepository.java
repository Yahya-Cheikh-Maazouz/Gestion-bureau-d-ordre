package com.maazouz.servicegestioncourrier.Repository;

import com.maazouz.servicegestioncourrier.Model.Courrier;
import com.maazouz.servicegestioncourrier.Model.CourrierOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<CourrierOperation,Long> {
    List<CourrierOperation> findAllByCourrier(Courrier courrier);
}
