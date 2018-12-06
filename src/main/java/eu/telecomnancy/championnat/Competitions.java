package eu.telecomnancy.championnat;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.ArrayList;


@Entity
public class Competitions {
    private @Id
    @GeneratedValue
    Long id;
    public ListEquipes equipes_participantes;
    public String name_compet;

    public Competitions(String name_compet, ListEquipes equipes_participantes){
        this.equipes_participantes=equipes_participantes;
        this.name_compet=name_compet;
    }
}
