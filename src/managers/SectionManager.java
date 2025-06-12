package managers;

import core.SystemRoot;
import types.Section;
import types.Signal;
import types.SignalLocation;

public class SectionManager extends BaseManager {
    public SectionManager(SystemRoot root) {
        super(SignalLocation.SectionManager, root);
    }

    Section RootSection;

    // TODO: Add a database or file portal to store sections tree;
    // TODO: Add methods to add section tree;


    @Override
    public void emitSignal(SignalLocation signalDestination) {
        root.bridgeSignals(id, signalDestination);
    }

    @Override
    public void absorbSignal(Signal signal, SignalLocation signalOrigin) {
        // TODO: absorb signal
    }
}
