package managers;

import core.SystemRoot;
import types.Section;
import types.Signal;

public class SectionManager extends BaseManager {
    Section rootSection;
    // TODO: Add a database or file portal to store sections tree;
    // TODO: Add methods to add section tree;

    public SectionManager(SystemRoot root) {
        super(root);
    }


    public Section getfreeCellSection()
    {
        //todo implement a tree search algorithm to find a free cell
        return rootSection;
    }
}
