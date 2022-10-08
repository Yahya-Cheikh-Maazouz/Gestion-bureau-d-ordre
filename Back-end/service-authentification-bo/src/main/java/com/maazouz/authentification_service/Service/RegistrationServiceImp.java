package com.maazouz.authentification_service.Service;

import com.maazouz.authentification_service.Core.Dtos.RegistrationRequest;
import com.maazouz.authentification_service.Core.Email.EmailSender;
import com.maazouz.authentification_service.Model.AppUser;
import com.maazouz.authentification_service.Model.Tenant;
import com.maazouz.authentification_service.Repository.TenantRepository;
import com.maazouz.authentification_service.Repository.UserRepository;
import com.maazouz.authentification_service.Security.AES.securityAESEncoder;
import com.maazouz.authentification_service.Security.EmailValidator;
import com.maazouz.authentification_service.Security.token.ConfirmationToken;
import com.maazouz.authentification_service.Security.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.maazouz.authentification_service.Core.Email.BuildMail.buildEmail;

@Component
@AllArgsConstructor
public class RegistrationServiceImp implements RegistrationService {


    @Autowired
    private Environment env;
    private static Environment environment;

    @PostConstruct
    public void init(){
        environment = env;
    }


    private final AppUserService userService;
    private final UserRepository userRepository;
    private final EmailValidator emailValidator;
    private final EmailSender emailSender;
    private final TenantRepository tenantRepository;
    private final ConfirmationTokenService confirmTokenService;


    @Override
    public ResponseEntity<String> register(RegistrationRequest request, String role, String tenantId) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (isValidEmail) {

            String tokenForNewUser = userService.signup(new AppUser(request.getFirstName(),
                    request.getLastName(),
                    request.getEmail(),
                    request.getPassword()
            ) {
            },role,tenantId);
            //Since, we are running the spring boot application in localhost, we are hardcoding the
            //url of the server. We are creating a POST request with token param
            String link = "http://localhost:5050/api/v1/registration/confirm?token=" + tokenForNewUser;
            emailSender.sendEmail(request.getEmail(), buildEmail(request.getFirstName(), link));
            return ResponseEntity.ok(tokenForNewUser);
        } else {
            throw new IllegalStateException(String.format("Email %s, not valid", request.getEmail()));
        }
    }





    @Transactional
    public String confirmToken(String token) {
        String keyEncoder = environment.getProperty("encoder.key");
        Optional<ConfirmationToken> confirmToken = confirmTokenService.getToken(token);

        if (confirmToken.isEmpty()) {
            throw new IllegalStateException("Token not found!");
        }

        if (confirmToken.get().getConfirmedAt() != null) {
            throw new IllegalStateException("Email is already confirmed");
        }

        LocalDateTime expiresAt = confirmToken.get().getExpiresAt();

        if (expiresAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token is already expired!");
        }

        confirmTokenService.setConfirmedAt(token);
        AppUser appuser = userService.loadUserByMail(confirmToken.get().getAppUser().getEmail());
        Tenant t = Tenant.builder()
                .name(UUID.randomUUID().toString())
                .username(securityAESEncoder.encrypt("root",keyEncoder))
                .password(securityAESEncoder.encrypt("root",keyEncoder))
                .driverClassName(securityAESEncoder.encrypt("com.mysql.cj.jdbc.Driver",keyEncoder))
                .initialize(false)
                .url(securityAESEncoder.encrypt("jdbc:mysql://localhost:8889",keyEncoder))
                .build();
        if(!appuser.getRoles().iterator().next().getRoleName().equals("USER"))
        {
            tenantRepository.save(t);
            appuser.setTenant(t);
        }
        userRepository.save(appuser);
        userService.enableAppUser(appuser.getEmail());
        //auto ddl all services
        String url = "http://localhost:6060/actuator/restart";
        RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        final HttpEntity<String> entity = new HttpEntity<String>(headers);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("XTenantID",t.getName());
        try {
            restTemplate.exchange(url, HttpMethod.POST, entity,String.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //Returning confirmation message if the token matches
        return "Your email is confirmed. Thank you for using our service!";
    }






}
