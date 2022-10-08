package com.maazouz.authentification_service.Service;

import com.maazouz.authentification_service.Core.Dtos.UserDto;
import com.maazouz.authentification_service.Core.Dtos.UserRequestDto;
import com.maazouz.authentification_service.Model.AppRole;
import com.maazouz.authentification_service.Model.AppUser;
import com.maazouz.authentification_service.Model.Tenant;
import com.maazouz.authentification_service.Repository.RoleRepository;
import com.maazouz.authentification_service.Repository.TenantRepository;
import com.maazouz.authentification_service.Repository.UserRepository;
import com.maazouz.authentification_service.Security.token.ConfirmationToken;
import com.maazouz.authentification_service.Security.token.ConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AppUserServiceImpl implements AppUserService{

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final TenantRepository tenantRepository;
    private final ConfirmationTokenService confirmationTokenService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;







    @Override
    public AppUser loadUserByMail(String mail) {
        return userRepository.findAppUserByEmail(mail);
    }

    @Override
    public void addRoleToUser(AppUser appUser, String rolename) {
        AppRole role = roleRepository.findByRoleName(rolename);
        if(role==null){
            AppRole roletempo = new AppRole(rolename);
            roleRepository.save(roletempo);
            appUser.setRole(roletempo);
        }
        else
        appUser.setRole(role);
    }

    @Override
    public String  signup(AppUser appUser, String role, String tenantId){
        AppUser  user=userRepository.findAppUserByEmail(appUser.getEmail());
        if(user!=null){
            boolean isEnabled = user.isEnabled();
            if (!isEnabled) {
                String token = UUID.randomUUID().toString();
                //A method to save user and token in this class
                saveConfirmationToken(user,token);
                return token;

            }
            throw new IllegalStateException(String.format("User with email %s already exists!", appUser.getEmail()));
        }

        String password=appUser.getPassword();
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        addRoleToUser(appUser,role);
        AppUser appuser = AppUser.builder()
                .firstName(appUser.getFirstName())
                .email(appUser.getEmail())
                .lastName(appUser.getLastName())
                .roles(appUser.getRoles())
                .password(appUser.getPassword())
                .build();
        //Saving the user after encoding the password
        if(Objects.equals(role, "USER")){
            Tenant t = this.tenantRepository.findTenantByName(tenantId);
            appuser.setTenant(t);
        }
        userRepository.save(appuser);
        //Creating a token from UUID
        String token = UUID.randomUUID().toString();
        //Getting the confirmation token and then saving it
        saveConfirmationToken(appUser, token);
        //Returning token
        return token;
    }

    @Override
    public UserRequestDto getUserInformation(String email) {
        UserRequestDto dto=new UserRequestDto();
        return dto.UserToSession(this.userRepository.findAppUserByEmail(email));
    }

    @Override
    public Long getUserId(String email) {
        return this.userRepository.findAppUserByEmail(email).getId();
    }

    @Override
    public void updateAccount(UserDto userdto, String password) {

    }


    private void saveConfirmationToken(AppUser appUser, String token) {
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15), appUser);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
    }

    public void enableAppUser(String email) {
        userRepository.enableAppUser(email);
    }

    @Override
    public String getUserTenantId(String email) {
        return this.userRepository.getUserTenantId(email);
    }
}
