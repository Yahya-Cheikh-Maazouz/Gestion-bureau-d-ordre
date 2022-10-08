package com.maazouz.servicegestioncourrier.Repository;
import com.maazouz.servicegestioncourrier.Model.ExpediteurPhysique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;



@CrossOrigin
public interface ExpediteurPhysiqueRepository extends JpaRepository<ExpediteurPhysique,Long> {
}
