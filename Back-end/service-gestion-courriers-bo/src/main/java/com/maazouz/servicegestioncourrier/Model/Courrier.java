package com.maazouz.servicegestioncourrier.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maazouz.servicegestioncourrier.Model.pojo.EnumTypesUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Courrier{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private Date dateReception;
    private String reference;
    private String destinataire;
    private String modeReception;
    private String commentaire;
    @Enumerated(javax.persistence.EnumType.STRING)
    private EnumTypesUtil.TypeCourrier typeCourrier;
    @Enumerated(javax.persistence.EnumType.STRING)
    private EnumTypesUtil.EtatCourrier etatCourrier;
    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH})
    private DocumentFile doc ;
    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH})
    private Expediteur expediteur ;
}
