package com.maazouz.authentification_service.Security.spring_security;


import com.maazouz.authentification_service.Model.AppUser;
import com.maazouz.authentification_service.Service.AppUserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AppUserServiceImpl appUserService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser=appUserService.loadUserByMail(email);
        if(appUser==null) throw new UsernameNotFoundException("invalid user");
        if(!appUser.isEnabled()) throw new UsernameNotFoundException("please confirm your email");
        Collection<GrantedAuthority> authorities=new ArrayList<>();
        appUser.getRoles().forEach(r->{
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
        });
        return new User(appUser.getEmail(),appUser.getPassword(),authorities);
    }
}
