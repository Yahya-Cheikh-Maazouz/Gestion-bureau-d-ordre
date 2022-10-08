package com.maazouz.authentification_service.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maazouz.authentification_service.Core.Enum.Poste;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
@Builder
public  class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppRole> roles=new ArrayList<>();
    private boolean enabled= false;
    @Enumerated(EnumType.STRING)
    private Poste poste;
    @OneToOne
    private Tenant tenant;


    public AppUser(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public void setRole(AppRole role) {
        this.roles.add(role);
    }
}
