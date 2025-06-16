package types;

import java.util.ArrayList;

public class Section {
    private final String id;
    private Section parent;
    public boolean isCell;
    private int capacity;

    private final ArrayList<Section> children = new ArrayList<>();
    private final ArrayList<Prisoner> prisoners = new ArrayList<>();
    private final ArrayList<Staff> staff = new ArrayList<>();

    public Section(String id, Section parent) {
        this.id = id;
        this.parent = parent;
    }

    public String getId() {
        return id;
    }

    public void addChild(Section child) {
        children.add(child);
        child.parent = this;
    }

    public boolean isFree() {
        return capacity > prisoners.size();
    }

    public boolean addPrisoner(Prisoner p) {
        if (isFree()) {
            prisoners.add(p);
            return true;
        }

        return false;
    }

}
