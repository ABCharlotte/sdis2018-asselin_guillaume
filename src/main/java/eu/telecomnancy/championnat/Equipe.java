package eu.telecomnancy.championnat;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
public class Equipe {

    private @Id
    @GeneratedValue
    Long id;
    private String name;

    //public List <Match> matches;

    public Equipe(String name) {
        this.name = name;
    }

    //public void addMatches(Match match){ this.matches.add(match); }

    //public List<Match> getMatches() {return matches;}
}

