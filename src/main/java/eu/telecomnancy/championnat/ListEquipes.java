package eu.telecomnancy.championnat;

import java.util.ArrayList;

public class ListEquipes extends ArrayList<Equipe> {
    public ListEquipes(){
        super();
    }


    public ListEquipes put (Equipe new_equipe){
        this.add(new_equipe);
        return this;
    }


}
