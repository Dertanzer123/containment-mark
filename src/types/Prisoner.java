package types;

import java.util.ArrayList;

public class Prisoner {
    private final String id;
    private  Section homeSection;
    private  ArrayList<Visit> visits = new ArrayList<>();




    public Prisoner(String id, Section homeSection) {
        this.id = id;
        this.homeSection = homeSection;
    }

    public ArrayList<Visit> getVisits() {
        return visits;
    }

    public void addVisit(Visit visit) {
        visits.add(visit);
    }

    public void removeVisit(Visit visit) {
        visits.remove(visit);
    }


    public String getId() {
        return id;
    }

    public void setVisits(ArrayList<Visit> visits) {
        this.visits = visits;
    }

    public Section getHomeSection() {
        return homeSection;
    }

    public void setHomeSection(Section homeSection) {
        this.homeSection = homeSection;
    }

    @Override
    public boolean equals(Object o)
    {
       if(o instanceof Prisoner)
       {
           Prisoner p = (Prisoner)o;
           return p.id.equals(id) ;
       }
       return false;
    }


}
