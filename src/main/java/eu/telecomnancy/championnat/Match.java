package eu.telecomnancy.championnat;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static eu.telecomnancy.championnat.Competition.*;

enum Status {
    PREVU,
    EN_COURS,
    PAUSE,
    FINI,
    REPORTE,
    ANNULE;
}

@Entity
@Data
@Table(name = "MATCHES")
public class Match {
    private @Id @GeneratedValue Long id;
    private Equipe domicile;
    private Equipe exterieur;

    private Status status;
    //public final SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yy, h:mm");
    private String date;

    private  Long competitionId;


    private int scoreDom;
    private int scoreGuest;

    Match(Equipe domicile, Equipe exterieur, Status state, Competition compet) {
        this.domicile=domicile;
        this.exterieur=exterieur;
        this.scoreDom=0;
        this.scoreGuest=0;
        this.status=state;
        this.date=date;
        this.competitionId=compet.getId();
    }

    Match(Equipe domicile, Equipe exterieur, Status state, String date) {
        this.domicile=domicile;
        this.exterieur=exterieur;
        this.scoreDom=0;
        this.scoreGuest=0;
        this.status=state;
        this.date=date;
        this.competitionId=null;
    }

    Match(Equipe domicile, Equipe exterieur) {
        this.domicile=domicile;
        this.exterieur=exterieur;
        this.scoreDom=0;
        this.scoreGuest=0;
        this.status=Status.PREVU;
        this.date="jj/MM/AA, hh:mm";
        this.competitionId=null;
    }
    Match(){
        Equipe anonymous = new Equipe("??");
        new Match(anonymous, anonymous);
    }



    public Status getStatus() {
        return status;
    }

    public void setStatus(Status newStatus) {

        this.status = newStatus;
        /*if (competitionId != null) {
            Competition compet = findById(competitionId);
            if (newStatus == Status.FINI) {
                if (scoreDom > scoreGuest) {

                    compet.majClassement(this.domicile, this.exterieur, Boolean.FALSE);
                }else{
                    compet.majClassement(this.exterieur, this.domicile, scoreDom==scoreGuest);
                }
            }
        }*/
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


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
