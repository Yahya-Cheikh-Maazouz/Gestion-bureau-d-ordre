package com.maazouz.authentification_service.EndPoint;

import com.maazouz.authentification_service.Core.Dtos.RegistrationRequest;
import com.maazouz.authentification_service.Core.Dtos.UserDto;
import com.maazouz.authentification_service.Model.AppUser;
import com.maazouz.authentification_service.Repository.UserRepository;
import com.maazouz.authentification_service.Service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping(path = "/api/v1/us")
public class UserController {
    private final UserRepository userRepository;
    private final RegistrationService authentificationService;


    @GetMapping(path = "/users/{tenant}")
    public List users(@PathVariable String tenant) {
        UserDto dto = new UserDto();
        List<AppUser> userstemp = this.userRepository.getAppUsersByTenant(tenant);
        List<UserDto> users = userstemp.stream().map(dto::UserToSession).collect(Collectors.toList());
        return users;
    }


    @PostMapping(path = "/register/{tenantId}")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest registrationRequest,@PathVariable String tenantId){
        return authentificationService.register(registrationRequest,"USER",tenantId);
    }

}
