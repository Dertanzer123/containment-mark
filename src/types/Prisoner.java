package types;

import java.time.LocalDate;
import java.util.ArrayList;

public class Prisoner {
    private final String id;
    private final String name;
    private final String gender;
    private final LocalDate birthDate;
    private Section homeSection;
    private final ArrayList<Visit> visits = new ArrayList<>();

    public Prisoner(String id, String name, String gender, LocalDate birthDate, Section homeSection) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
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
