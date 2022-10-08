package com.maazouz.authentification_service.Core.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class updateAccountRequestDto {
   private  String password;
   private  UserDto user;
}
