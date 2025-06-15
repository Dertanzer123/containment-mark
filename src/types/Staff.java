package types;

import java.time.LocalDate;
import java.util.ArrayList;

public class Staff {
    private final String id;
    private final String name;
    private final String gender;
    private final LocalDate birthDate;
    private final ArrayList<Section> sections = new ArrayList<>();

    public Staff(String id, String name, String gender, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
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
