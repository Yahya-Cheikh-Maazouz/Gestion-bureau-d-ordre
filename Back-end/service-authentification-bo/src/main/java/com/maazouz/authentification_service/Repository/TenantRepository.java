package com.maazouz.authentification_service.Repository;

import com.maazouz.authentification_service.Model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<Tenant,Long> {
    Tenant findTenantByName(String name);
}
