package com.maazouz.servicegestioncourrier.Core.Dtos;

import com.maazouz.servicegestioncourrier.Model.pojo.EnumTypesUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourrierExpExistSaveRequst {
    private String commentaire;
    private Date dateReception;
    private String destinataire ;
    private String modeReception ;
    private String reference ;
    private EnumTypesUtil.TypeCourrier typeCourrier;
    private Long expediteur;

}
