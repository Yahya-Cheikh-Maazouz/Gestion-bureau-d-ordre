package com.maazouz.authentification_service.Service;

import com.maazouz.authentification_service.Core.Dtos.UserDto;
import com.maazouz.authentification_service.Core.Dtos.UserRequestDto;
import com.maazouz.authentification_service.Model.AppUser;

public interface AppUserService {
     AppUser loadUserByMail(String mail);
     void addRoleToUser(AppUser appUser, String rolename);
     String  signup(AppUser appUser, String role, String tenantId);
     UserRequestDto getUserInformation(String email);
     Long getUserId(String email);
     void updateAccount(UserDto userdto , String password);
     void enableAppUser(String email);
     String getUserTenantId(String email);
}
