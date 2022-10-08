package com.maazouz.authentification_service.EndPoint;

import com.maazouz.authentification_service.Core.Dtos.RegistrationRequest;
import com.maazouz.authentification_service.Core.Dtos.UserDto;
import com.maazouz.authentification_service.Core.Dtos.UserRequestDto;
import com.maazouz.authentification_service.Core.Dtos.updateAccountRequestDto;
import com.maazouz.authentification_service.Model.AppUser;
import com.maazouz.authentification_service.Repository.UserRepository;
import com.maazouz.authentification_service.Service.AppUserService;
import com.maazouz.authentification_service.Service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping(path = "/api/v1/registration")
public class Registration {

    private final RegistrationService authentificationService;
    private final AppUserService appUserService;
    private final UserRepository userRepository;
    private final com.maazouz.authentification_service.Service.test test;

    @PostMapping(path = "/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest registrationRequest){
        return authentificationService.register(registrationRequest, "ADMIN", null);
    }


    @PostMapping(path = "/user/info")
    public Long getId(@RequestBody String email){
        return this.userRepository.getUserId(email);
    }

    @GetMapping(path = "/user/{id}")
    public UserRequestDto userByEmail(@PathVariable Long id) {
        UserRequestDto u = new UserRequestDto();
        return u.UserToSession(this.userRepository.findById(id).get());
    }

    @GetMapping(path = "/users")
    public List users() {
        UserDto dto = new UserDto();
        List<AppUser> userstemp = this.userRepository.findAll();
        List<UserDto> users = userstemp.stream().map(dto::UserToSession).collect(Collectors.toList());
        return users;

    }


    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return authentificationService.confirmToken(token);
    }


    @PostMapping(path = "/account/update")
    public void update(@RequestBody updateAccountRequestDto dto) {
         this.test.updateAccount(dto.getUser(),dto.getPassword());
    }

    @PostMapping(path = "/get/tenant")
    public String getUserTenantId(@RequestBody String email){
        return this.userRepository.getUserTenantId(email);
    }


}
