package com.maazouz.authentification_service.Repository;

import com.maazouz.authentification_service.Model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<com.maazouz.authentification_service.Model.AppUser,Long> {
    AppUser findAppUserByEmail(String email);

    @Query("select a.id from AppUser a where a.email=:email")
    Long getUserId(String email);

    @Query("select a.tenant.name from AppUser a where a.email=:email")
    String getUserTenantId(String email);


    @Query("select a from AppUser a where a.tenant.name=:tenantid")
    List<AppUser> getAppUsersByTenant(String tenantid);




    @Transactional
    @Modifying
    @Query("UPDATE AppUser a SET a.enabled" +
            "=true WHERE a.email=?1")
    void enableAppUser(String email);
}
