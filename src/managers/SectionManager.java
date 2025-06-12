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

    @Override
    public void emitSignal(BaseManager signalDestination) {
        root.bridgeSignals(this, signalDestination);
    }

    @Override
    public void absorbSignal(Signal signal, BaseManager signalOrigin) {
        // TODO: absorb signal
        switch (signal.signalCode) {
            default: {
                System.err.println("Invalid signal received: " + signal.signalData);
                System.exit(1);
            }
        }
    }
}
