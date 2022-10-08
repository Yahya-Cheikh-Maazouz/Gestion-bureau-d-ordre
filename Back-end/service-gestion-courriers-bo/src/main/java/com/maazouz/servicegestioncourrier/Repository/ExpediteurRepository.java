package com.maazouz.servicegestioncourrier.Repository;
import com.maazouz.servicegestioncourrier.Model.Expediteur;
import com.maazouz.servicegestioncourrier.Model.ExpediteurMoral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;


@CrossOrigin
public interface ExpediteurRepository extends JpaRepository<Expediteur,Long> {
}
