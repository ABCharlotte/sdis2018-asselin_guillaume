package eu.telecomnancy.championnat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Equipes {

    private @Id
    @GeneratedValue
    Long id;
    private String name;

    Equipes(String name) {
        this.name = name;
    }
}
