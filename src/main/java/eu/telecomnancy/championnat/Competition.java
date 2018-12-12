package eu.telecomnancy.championnat;

import lombok.Data;
import org.springframework.data.util.Pair;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;

@Data
@Entity
//@XmlRootElement //to RESTful
public class Competition {
    private @Id @GeneratedValue Long id;
    /*public ListEquipes equipes_participantes;*/
    public ArrayList<Long> equipesIds;
    public String name;
    //public HashMap<Long, Integer> classement;   //<id de l'équipe, nb de victoires>
    public ArrayList<Integer> classement;
    
    public Competition(String name_compet){
        this.name=name_compet;
        /*this.equipes_participantes= new ListEquipes();*/
        this.equipesIds= new ArrayList<Long>();
        //this.classement= new HashMap<Long, Integer>();
        this.classement=new ArrayList<Integer>();
    }

    public Competition(){
        new Competition("NoName");
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

    public ArrayList<Long> getEquipesIds(){
        return equipesIds;
    }
    
    public void setEquipesIds(ArrayList<Long> listIdsEquipes){
        this.equipesIds = listIdsEquipes;
    }
    /*public ListEquipes getEquipes() {
        return equipes_participantes.getEquipes();
        //return equipes_participantes;
    }

     public void setEquipes(ListEquipes equipes_participantes) {
        this.equipes_participantes = equipes_participantes;
    }*/

    public ArrayList<Integer> getClassement(){
    //public HashMap<Long,Integer> getClassement() {
        return classement;
    }

    public void addEquipe(Equipe newEquipe) {
        /*this.equipes_participantes.put(newEquipe);*/
        this.equipesIds.add(newEquipe.getId());
        //this.classement.putIfAbsent(newEquipe.getId(),0);
        this.classement.add(0);
    }

    public void majClassement(Equipe gagant, Equipe perdant, Boolean egalite){
        Long gagantId = gagant.getId();
        Long perdantId = perdant.getId();
        /*if (! classement.containsKey(gagantId)){
            this.addEquipe(gagant);
        }
        if (! classement.containsKey(perdantId)){
            this.addEquipe(perdant);
        }
        classement.put(gagantId, classement.get(gagantId)+2);
        classement.put(perdantId, classement.get(perdantId)-2);
        if(egalite){
            classement.put(gagantId, classement.get(gagantId)-1);
            classement.put(perdantId, classement.get(perdantId)+3);
        }*/
        for (int i=0; i<equipesIds.size();i++){
            if (gagantId == equipesIds.get(i)){
                if (egalite){
                    classement.set(i, classement.get(i)+1);
                }
                else{
                    classement.set(i, classement.get(i)+2);
                }
            }
            if (perdantId == equipesIds.get(i)){
                if (egalite){
                    classement.set(i, classement.get(i)+1);
                }
                else{
                    classement.set(i, classement.get(i)-2);
                }
            }
        }
    }
    /*public void setClassement(ArrayList<Equipe> classement) {
        this.classement = classement;
    }*/
    
}