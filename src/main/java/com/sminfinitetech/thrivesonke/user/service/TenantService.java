package com.sminfinitetech.thrivesonke.user.service;

import com.sminfinitetech.thrivesonke.exception.TenantNotFoundException;
import com.sminfinitetech.thrivesonke.user.model.Tenant;
import com.sminfinitetech.thrivesonke.user.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TenantService {
    private final TenantRepository tenantRepository;
    private final UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Tenant createTenant(Tenant tenant) {
//        if (tenantRepository.findByName(tenant.getName())) {
//            throw new IllegalArgumentException("Tenant with the same name already exists.");
//        }

        // Generate a unique API key
        String apiKey = UUID.randomUUID().toString();
        tenant.setApiKey(apiKey);
        tenant.setAdminPassword(passwordEncoder.encode(tenant.getAdminPassword()));

        Tenant savedTenant = tenantRepository.save(tenant);
        userService.saveTenantAdminUser(tenant);

        return savedTenant;
    }

    public Tenant getTenantById(Long id) {
        return tenantRepository.findById(id)
                .orElseThrow(() -> new TenantNotFoundException("Tenant with ID " + id + " not found"));
    }

    public List<Tenant> getAllTenants() {
        List<Tenant> tenants = tenantRepository.findAll();
        if (tenants.isEmpty()) {
            throw new TenantNotFoundException("No tenants found.");
        }
        return tenants;
    }
}
