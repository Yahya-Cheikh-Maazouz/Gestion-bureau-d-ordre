package com.maazouz.servicegestioncourrier.Repository;
import com.maazouz.servicegestioncourrier.Model.ExpediteurMoral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface ExpediteurMoralRepository extends JpaRepository<ExpediteurMoral,Long> {
}
