package com.maazouz.authentification_service.Core.Dtos;

import com.maazouz.authentification_service.Model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private Long  idUser;
    private String firstName;
    private String lastName;
    private String email;
    private String poste;

   public UserRequestDto UserToSession(AppUser appUser)
   {
       return UserRequestDto.builder()
               .idUser(appUser.getId())
               .firstName(appUser.getFirstName())
               .lastName(appUser.getLastName())
               .email(appUser.getEmail())
               .poste(String.valueOf(appUser.getPoste()))
               .build();
   }
}
