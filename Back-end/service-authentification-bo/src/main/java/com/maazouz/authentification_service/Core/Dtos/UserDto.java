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
public class UserDto {
    private Long  idUser;
    private String firstName;
    private String lastName;
    private  String newpassword;
    private String email;

    public UserDto UserToSession(AppUser appUser)
    {
        return UserDto.builder()
                .idUser(appUser.getId())
                .firstName(appUser.getFirstName())
                .lastName(appUser.getLastName())
                .email(appUser.getEmail())
                .build();
    }
}
