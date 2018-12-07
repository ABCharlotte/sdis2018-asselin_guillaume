package eu.telecomnancy.championnat;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Data
@Entity
public class Competition {
    private @Id @GeneratedValue Long id;
    public ListEquipes equipes_participantes;
    public String name;
    public ArrayList<Equipe> classement;

    /*public Competition(String name_compet, ListEquipes equipes_participantes){
        this.equipes_participantes=equipes_participantes;*/
    /*public Competition(String name_compet, Equipe dom, Equipe participant1){
        this.equipes_participantes.put(dom);
        this.equipes_participantes.put(participant1);*/
    public Competition(String name_compet){
        this.name=name_compet;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public ListEquipes getEquipes() {
        return equipes_participantes;
    }

    public ArrayList<Equipe> getClassement() {
        return classement;
    }

    public void setEquipes(ListEquipes equipes_participantes) {
        this.equipes_participantes = equipes_participantes;
    }
    public void addEquipe(Equipe newEquipe) {
        this.equipes_participantes.put(newEquipe);
    }

    public void setClassement(ArrayList<Equipe> classement) {
        this.classement = classement;
    }
}
