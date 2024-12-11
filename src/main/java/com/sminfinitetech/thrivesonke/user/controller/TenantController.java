package com.sminfinitetech.thrivesonke.user.controller;

import com.sminfinitetech.thrivesonke.helper.ApiResponse;
import com.sminfinitetech.thrivesonke.user.model.Tenant;
import com.sminfinitetech.thrivesonke.user.service.TenantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tenant")
@Tag(name = "Tenant API", description = "APIs for managing tenants")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Tenant>>  registerTenant(@RequestBody Tenant tenant) {
        Tenant registeredTenant = tenantService.createTenant(tenant);
        ApiResponse<Tenant> response = new ApiResponse<>(true, HttpStatus.OK.value(), registeredTenant);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/tenants")
    public ResponseEntity<ApiResponse<List<Tenant>>> getTenants() {
        List<Tenant> allTenants = tenantService.getAllTenants();
        ApiResponse<List<Tenant>> response = new ApiResponse<>(true, HttpStatus.OK.value(), allTenants);
        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<Tenant>> getTenant(@RequestParam long id) {
        Tenant tenant = tenantService.getTenantById(id);
        ApiResponse<Tenant> response = new ApiResponse<>(true, HttpStatus.OK.value(), tenant);
        return ResponseEntity.ok(response);
    }
}
