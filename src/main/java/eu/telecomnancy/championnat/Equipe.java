package eu.telecomnancy.championnat;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
@Data
@Entity
public class Equipe implements Serializable {

    private @Id @GeneratedValue Long id;
    private String name;

    //public List <Match> matches;

    public Equipe(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //public void addMatches(Match match){ this.matches.add(match); }

    //public List<Match> getMatches() {return matches;}
}

