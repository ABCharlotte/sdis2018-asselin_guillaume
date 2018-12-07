package eu.telecomnancy.championnat;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Competitions {
    private @Id @GeneratedValue Long id;
    public ListEquipes equipes_participantes;
    public String name_compet;

    /*public Competitions(String name_compet, ListEquipes equipes_participantes){
        this.equipes_participantes=equipes_participantes;*/
    /*public Competitions(String name_compet, Equipe dom, Equipe participant1){
        this.equipes_participantes.put(dom);
        this.equipes_participantes.put(participant1);*/
    public Competitions(String name_compet){
        this.name_compet=name_compet;
    }
}
