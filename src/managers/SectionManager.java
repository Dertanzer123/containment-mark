package managers;

import core.SystemRoot;
import types.Section;
import types.Signal;

public class SectionManager extends BaseManager {
    public SectionManager(SystemRoot root) {
        super("SectionManager", root);
    }

    Section RootSection;

    //todo: add a database or file portal to store sections tree;
    //todo: add methods to add section tree;


    @Override
    public void emitSignal(String signalDestination) {


        root.bridgeSignals(id, signalDestination);


    }

    @Override
    public void absorbSignal(Signal signal, String signalOrigin) {
        // TODO: absorb signal
    }





    @Override
    public Signal getSignalBuffer() {
        return signalBuffer;

    }
}
