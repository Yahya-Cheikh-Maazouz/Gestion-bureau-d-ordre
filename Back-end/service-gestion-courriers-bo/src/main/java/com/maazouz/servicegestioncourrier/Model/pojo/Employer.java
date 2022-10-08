package com.maazouz.servicegestioncourrier.Model.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employer {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
