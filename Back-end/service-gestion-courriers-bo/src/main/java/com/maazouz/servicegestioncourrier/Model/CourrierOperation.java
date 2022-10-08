package com.maazouz.servicegestioncourrier.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.maazouz.servicegestioncourrier.Model.pojo.Employer;
import com.maazouz.servicegestioncourrier.Model.pojo.EnumTypesUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CourrierOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idEmplyer;
    @Transient
    private Employer employer;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;
    @Lob
    private String details;
    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH})
    private Courrier courrier;
    @Enumerated(EnumType.STRING)
    private EnumTypesUtil.CourrierOperation operationType;
}
