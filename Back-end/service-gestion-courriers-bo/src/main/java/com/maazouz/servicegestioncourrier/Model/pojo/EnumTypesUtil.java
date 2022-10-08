package com.maazouz.servicegestioncourrier.Model.pojo;

public class EnumTypesUtil {

    public enum ModeRecepetion {
        Email,mainPropre,Post,Fax;
    }

    public enum TypeCourrier {
        Entrant,Sortant;
    }

    public enum PrioriteCourrier {
        haute, moyenne, faible;
    }

    public enum EtatCourrier {
        ENVOYER,REFUSER,TRAITER,CLOTURER
        ,ENTRAITEMNT
    }

    public enum CourrierOperation {
        CREATE
    }


    public enum Poste {
        DIRECTEUR,AGENT
    }


    public enum CourrierExpRequest {
        MORAL, EXIST,PHYSIQUE
    }
}
