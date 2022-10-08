package com.maazouz.authentification_service.Service;

import com.maazouz.authentification_service.Core.Dtos.UserDto;
import com.maazouz.authentification_service.Model.AppUser;
import com.maazouz.authentification_service.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class test {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

   Authentication getAuth(String email, String password){
        System.out.println(authenticationManager);
       return  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

    }



    public void updateAccount(UserDto userdto, String password) {
        AppUser user = userRepository.findById(userdto.getIdUser()).get();
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), password));
            if(auth.isAuthenticated())
            {
                if(userdto.getFirstName()!=null&& !userdto.getFirstName().isEmpty())
                    user.setFirstName(userdto.getFirstName());
                if(userdto.getLastName()!=null&& !userdto.getLastName().isEmpty())
                    user.setLastName(userdto.getLastName());
                if(userdto.getEmail()!=null&& !userdto.getEmail().isEmpty())
                    user.setEmail(userdto.getEmail());
                if(userdto.getNewpassword()!=null && !userdto.getNewpassword().isEmpty()){
                    user.setPassword(bCryptPasswordEncoder.encode(userdto.getNewpassword()));
                }
                this.userRepository.save(user);
            }
        }
        catch (Exception exception) {
            throw new RuntimeException("you are not authorized");
        }

        /*if(auth.isAuthenticated())
        {
            if(userdto.getFirstName()!=null&& !userdto.getFirstName().isEmpty())
            user.setFirstName(userdto.getFirstName());
            if(userdto.getLastName()!=null&& !userdto.getLastName().isEmpty())
            user.setLastName(userdto.getLastName());
            if(userdto.getEmail()!=null&& !userdto.getEmail().isEmpty())
            user.setEmail(userdto.getEmail());
            if(userdto.getNewpassword()!=null && !userdto.getNewpassword().isEmpty()){
                user.setPassword(bCryptPasswordEncoder.encode(userdto.getNewpassword()));
            }
            this.userRepository.save(user);
        }
        else
            throw new RuntimeException("you are not authorized");*/
    }
}
