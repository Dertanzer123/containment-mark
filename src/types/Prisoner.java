package types;

import java.util.ArrayList;

public class Prisoner {
    private final String id;
    private Section homeSection;
    private final ArrayList<Visit> visits = new ArrayList<>();

    public Prisoner(String id, Section homeSection) {
        this.id = id;
        this.homeSection = homeSection;
        homeSection.addPrisoner(this);
    }

    public String getId() {
        return id;
    }

    public Section getHomeSection() {
        return homeSection;
    }

    public void setHomeSection(Section homeSection) {
        this.homeSection = homeSection;
    }

    public void addVisit(Visit visit) {
        visits.add(visit);
    }

    public void removeVisit(Visit visit) {
        visits.remove(visit);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Prisoner p) {
            return p.id.equals(id);
        }
        return false;
    }


}
