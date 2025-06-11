package types;

import java.util.ArrayList;

public class Staff {
    private final String id;
    private final ArrayList<Section> sections = new ArrayList<>();

    public Staff(String id) {
        this.id = id;
    }

    public void addSection(Section section) {
        sections.add(section);
    }
}
