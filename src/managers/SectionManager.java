package managers;

import core.SystemRoot;
import types.Section;

public class SectionManager extends BaseManager {
    Section rootSection;
    // TODO: Add a database or file portal to store sections tree;
    // TODO: Add methods to add section tree;

    public SectionManager(SystemRoot root) {
        super(root);
        rootSection = new Section("root", null);
    }

    public Section getfreeCellSection() {
        // TODO: Implement a tree search algorithm to find a free cell
        return rootSection;
    }
}
