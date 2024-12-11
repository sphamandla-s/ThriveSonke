package com.sminfinitetech.thrivesonke.user.repository;

import com.sminfinitetech.thrivesonke.user.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {
     Optional<Tenant> findByName(String name);

}
