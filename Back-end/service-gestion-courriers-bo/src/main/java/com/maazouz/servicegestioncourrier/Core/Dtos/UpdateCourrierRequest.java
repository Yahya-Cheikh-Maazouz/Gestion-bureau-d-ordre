package com.maazouz.servicegestioncourrier.Core.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @NoArgsConstructor
@Builder @AllArgsConstructor

public class UpdateCourrierRequest {
    private Date date;
    private String reference;
    private String destinataire;
    private String modeReception;
    private String commentaire;
}
