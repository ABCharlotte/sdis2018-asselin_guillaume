package eu.telecomnancy.championnat;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Match {
    private @Id
    @GeneratedValue
    Long id;
    private Equipe domicile;
    private Equipe exterieur;

    private String status;//PREVU|EN COURS|PAUSE|FINI|REPORTE|ANNULE

    private int scoreA;
    private int scoreB;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getScoreA() {
        return scoreA;
    }

    public void setScoreA(int scoreA) {
        this.scoreA = scoreA;
    }

    public int getScoreB() {
        return scoreB;
    }

    public void setScoreB(int scoreB) {
        this.scoreB = scoreB;
    }

    Match(Equipe domicile, Equipe exterieur) {
        this.domicile=domicile;
        this.exterieur=exterieur;
        this.scoreA=0;
        this.scoreB=0;
    }

}
