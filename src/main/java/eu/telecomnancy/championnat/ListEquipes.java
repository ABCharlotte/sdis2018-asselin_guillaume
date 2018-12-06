package eu.telecomnancy.championnat;

import java.util.ArrayList;

public class ListEquipes extends ArrayList<Equipes> {
    public ListEquipes(){
        super();
    }

    public ListEquipes put (Equipes new_equipe){
        this.add(new_equipe);
        return this;
    }

}
