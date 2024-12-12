package com.sminfinitetech.thrivesonke.user.controller;

import com.sminfinitetech.thrivesonke.helper.ApiResponse;
import com.sminfinitetech.thrivesonke.user.model.Tenant;
import com.sminfinitetech.thrivesonke.user.service.TenantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
    public ResponseEntity<ApiResponse<?>> registerTenant(@Valid @RequestBody Tenant tenant) {
        try {
            Tenant registeredTenant = tenantService.createTenant(tenant);
            ApiResponse<Tenant> response = new ApiResponse<>(true, HttpStatus.CREATED.value(), registeredTenant);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiResponse<String> response = new ApiResponse<>(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to register tenant: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/tenants")
    public ResponseEntity<ApiResponse<?>> getTenants() {
        try {
            List<Tenant> allTenants = tenantService.getAllTenants();
            ApiResponse<List<Tenant>> response = new ApiResponse<>(true, HttpStatus.OK.value(), allTenants);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<String> response = new ApiResponse<>(false, HttpStatus.INTERNAL_SERVER_ERROR.value(),  "Failed to retrieve tenants: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<?>> getTenant(@RequestParam long id) {
        try {
            Tenant tenant = tenantService.getTenantById(id);
            ApiResponse<Tenant> response = new ApiResponse<>(true, HttpStatus.OK.value(), tenant);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<String> response = new ApiResponse<>(false, HttpStatus.NOT_FOUND.value(),  e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
