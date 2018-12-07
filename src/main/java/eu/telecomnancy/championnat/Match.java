package eu.telecomnancy.championnat;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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


    private int scoreDom;
    private int scoreGuest;

    Match(Equipe domicile, Equipe exterieur, Status state, String date) {
        this.domicile=domicile;
        this.exterieur=exterieur;
        this.scoreDom=0;
        this.scoreGuest=0;
        this.status=state;
        this.date=date;
    }

    Match(Equipe domicile, Equipe exterieur) {
        new Match(domicile,exterieur,Status.PREVU,"jj/MM/AA, hh:mm");//formatDate.format(new Date()));
    }
    Match(){
        new Match(null, null);
    }



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


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
