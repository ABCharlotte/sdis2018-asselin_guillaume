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
    /*private Equipe domicile;
    private Equipe exterieur;*/
    private String equipeDomicileName;
    private Long equipeDomicileId;
    private String equipeInviteName;
    private Long equipeInviteId;

    private Status status;
    //public final SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yy, h:mm");
    private String date;

    private  Long competitionId;


    private int equipeDomicileScore;
    private int equipeInviteScore;

    Match(Equipe domicile, Equipe exterieur, Status state, String date, Long competId/*Competition compet*/) {
        /*this.domicile=domicile;
        this.exterieur=exterieur;*/
        this.equipeDomicileId=domicile.getId();
        this.equipeDomicileName=domicile.getName();
        this.equipeInviteId=exterieur.getId();
        this.equipeInviteName=exterieur.getName();
        this.equipeDomicileScore=0;
        this.equipeInviteScore=0;
        this.status=state;
        this.date=date;
        this.competitionId=competId;//compet.getId();
    }

    Match(Equipe domicile, Equipe exterieur, Status state, String date) {
        /*this.domicile=domicile;
        this.exterieur=exterieur;*/
        this.equipeDomicileId=domicile.getId();
        this.equipeDomicileName=domicile.getName();
        this.equipeInviteId=exterieur.getId();
        this.equipeInviteName=exterieur.getName();
        this.equipeDomicileScore=0;
        this.equipeInviteScore=0;
        this.status=state;
        this.date=date;
        this.competitionId=null;
    }

    Match(Equipe domicile, Equipe exterieur) {
        /*this.domicile=domicile;
        this.exterieur=exterieur;*/
        this.equipeDomicileId=domicile.getId();
        this.equipeDomicileName=domicile.getName();
        this.equipeInviteId=exterieur.getId();
        this.equipeInviteName=exterieur.getName();
        this.equipeDomicileScore=0;
        this.equipeInviteScore=0;
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

    public int getEquipeDomicileScore() {
        return equipeDomicileScore;
    }

    public void setEquipeDomicileScore(int scoreDom) {
        this.equipeDomicileScore = scoreDom;
    }

    public int getEquipeInviteScore() {
        return equipeInviteScore;
    }

    public void setEquipeInviteScore(int scoreGuest) {
        this.equipeInviteScore = scoreGuest;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setEquipeDom(Equipe domicile){
        this.equipeDomicileId=domicile.getId();
        this.equipeDomicileName=domicile.getName();
    }

    public void setEquipeGuest(Equipe exterieur) {
        this.equipeInviteId = exterieur.getId();
        this.equipeInviteName = exterieur.getName();
    }

    public Long getId() {
        return id;
    }

    public String getEquipeDomicileName() {
        return equipeDomicileName;
    }

    public Long getEquipeDomicileId() {
        return equipeDomicileId;
    }

    public String getEquipeInviteName() {
        return equipeInviteName;
    }

    public Long getEquipeInviteId() {
        return equipeInviteId;
    }

    public Long getCompetitionId() {
        return competitionId;
    }
}
