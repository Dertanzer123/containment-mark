package types;

import java.util.ArrayList;

public class Prisoner {
    private final String id;
    private final Section homeSection;
    private final ArrayList<Visit> visits = new ArrayList<>();

    public Prisoner(String id, Section homeSection) {
        this.id = id;
        this.homeSection = homeSection;
    }

    public ArrayList<Visit> getVisits() {
        return visits;
    }
}
