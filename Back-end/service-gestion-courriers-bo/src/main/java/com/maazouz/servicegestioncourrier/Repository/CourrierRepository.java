package com.maazouz.servicegestioncourrier.Repository;
import com.maazouz.servicegestioncourrier.Model.Courrier;
import com.maazouz.servicegestioncourrier.Model.Expediteur;
import com.maazouz.servicegestioncourrier.Model.pojo.EnumTypesUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CourrierRepository extends JpaRepository<Courrier,Long> {

    List<Courrier> findByTypeCourrier(EnumTypesUtil.TypeCourrier typecourrier);
    List<Courrier> findByExpediteur(Expediteur exp);

    /*Nombre des Courriers By Type*/
    @Query(value = "SELECT COUNT(co) as count FROM Courrier  co" +
            " WHERE co.typeCourrier=:type  ")
    Long courriers(EnumTypesUtil.TypeCourrier type);

    /*Nombre des Courriers*/
    @Query(value = "SELECT COUNT(co) as count FROM Courrier  co")
    Long allCourriers();

    /*Nombre des Courriers*/
    @Query(value = "SELECT COUNT(co) as count FROM Courrier  co " +
            " WHERE co.etatCourrier=:state ")
    Long allCourriers1(EnumTypesUtil.EtatCourrier state);


    /*Nombre des Courriers*/
    @Query(value = "SELECT COUNT(co) as count FROM Courrier  co" +
            " WHERE co.typeCourrier=:type  ")
    List courriersByDate(EnumTypesUtil.TypeCourrier type);
}
