package eu.telecomnancy.championnat;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
@Data
@Entity
public class Equipes {

    private @Id
    @GeneratedValue
    Long id;
    private String name;

    public List <Matches> matches;

    Equipes(String name) {
        this.name = name;
    }

    public void addMatches(Matches match){
        this.matches.add(match);
    }

    public List<Matches> getMatches() {
        return matches;
    }

}
