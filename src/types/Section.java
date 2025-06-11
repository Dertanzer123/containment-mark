package types;

import java.util.ArrayList;

public class Section {
    private final String id;
    private Section parent;

    private final ArrayList<Section> children = new ArrayList<>();
    private final ArrayList<Prisoner> prisoners = new ArrayList<>();
    private final ArrayList<Staff> staff = new ArrayList<>();

    public Section(String id, Section parent) {
        this.id = id;
        this.parent = parent;
    }

    public void addChild(Section child) {
        children.add(child);
        child.parent = this;
    }
}
