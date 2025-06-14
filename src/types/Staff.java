package types;

import java.util.ArrayList;

public class Staff {
    private final String id;
    private final ArrayList<Section> sections = new ArrayList<>();

    public Staff(String id) {
        this.id = id;
    }
    @Override
    public boolean equals(Object o) {
        if (o instanceof Staff s) {
            return s.id.equals(id);
        }
        return false;
    }

    public void addSection(Section section) {
        sections.add(section);
    }
}
