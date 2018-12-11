package eu.telecomnancy.championnat;

import java.util.ArrayList;

public class ListEquipes extends ArrayList<Equipe> {
    public ListEquipes(){
        super();
    }


    public ListEquipes put(Equipe new_equipe){
        this.add(new_equipe);
        return this;
    }

    public Equipe getEquipe(int i){
        return this.get(i);
    };


    public ArrayList<Long> getIDs(){
        ArrayList<Long> ids = new ArrayList<Long>();
        for (int i=0 ; i<this.size() ; i++) {
            ids.add(this.getEquipe(i).getId());
        }
        return ids;
    }


}
