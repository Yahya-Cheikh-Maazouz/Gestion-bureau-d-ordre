package com.maazouz.servicegestioncourrier.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor
@NoArgsConstructor
@Inheritance (strategy=InheritanceType.TABLE_PER_CLASS)
public  class Expediteur {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long   id;
    private String tel;
    private String adresse;
    private String email;
    @OneToOne
    @JoinColumn (name="expediteur")
    private Courrier courrier;


    /*
    //A COMPLETER APRES
    @Column(name = "DELETED")
    private boolean deleted = false;
    @OneToMany(mappedBy = "expediteur", cascade = {CascadeType.MERGE}, targetEntity = CourrierEntrant.class)
    private List<CourrierEntrant> courrierEntrants;
    @OneToMany(mappedBy = "expediteur", cascade = {CascadeType.MERGE}, targetEntity = CourrierSortant.class)
    private List<CourrierSortant> courrierSortants;
    @OneToMany(mappedBy = "expediteur", cascade = {CascadeType.MERGE}, targetEntity = HistoriqueCourrierE.class)
    private List<HistoriqueCourrierE> historiqueCourrierEs;
    @OneToMany(mappedBy = "expediteur", cascade = {CascadeType.MERGE}, targetEntity = HistoriqueCourrierS.class)
    private List<HistoriqueCourrierS> historiqueCourrierSs;
    @Column(name="DTYPE",insertable = false, updatable = false)
    private String DTYPE;*/

}
