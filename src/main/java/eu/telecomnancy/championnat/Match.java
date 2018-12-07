package eu.telecomnancy.championnat;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Match {
    private @Id
    @GeneratedValue
    Long id;
    private Equipe domicile;
    private Equipe exterieur;

    private Status status;
    protected enum Status {
        PREVU,
        EN_COURS,
        PAUSE,FINI,
        REPORTE,
        ANNULE;
    }

    private int scoreDom;
    private int scoreGuest;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getScoreDom() {
        return scoreDom;
    }

    public void setScoreDom(int scoreDom) {
        this.scoreDom = scoreDom;
    }

    public int getScoreGuest() {
        return scoreGuest;
    }

    public void setScoreGuest(int scoreGuest) {
        this.scoreGuest = scoreGuest;
    }

    Match(Equipe domicile, Equipe exterieur, Status state) {
        this.domicile=domicile;
        this.exterieur=exterieur;
        this.scoreDom=0;
        this.scoreGuest=0;
        this.status=state;
    }

    Match(Equipe domicile, Equipe exterieur) {
        new Match(domicile,exterieur,Status.PREVU);
    }

}
