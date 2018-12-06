package eu.telecomnancy.championnat;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Competitions {
    private @Id
    @GeneratedValue
    Long id;

    Competitions(){

    }
}
