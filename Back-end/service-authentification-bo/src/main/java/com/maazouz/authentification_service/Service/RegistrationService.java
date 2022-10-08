package com.maazouz.authentification_service.Service;

import com.maazouz.authentification_service.Core.Dtos.RegistrationRequest;
import org.springframework.http.ResponseEntity;

public interface RegistrationService {
    ResponseEntity<String> register(RegistrationRequest request, String Role, String tenantId);
    String confirmToken(String token);
}
