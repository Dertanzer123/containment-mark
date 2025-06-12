package managers;

import core.SystemRoot;

public class SectionManager extends BaseManager {
    public SectionManager(SystemRoot root) {
        super("SectionManager", root);
    }

    @Override
    public void emitSignal(String signalDestination) {


        root.bridgeSignals(id, signalDestination);

        // TODO: emit signal
    }

    @Override
    public void absorbSignal(String signal, String signalOrigin) {
        // TODO: absorb signal
    }





    @Override
    public String getSignalBuffer() {
        return signalBuffer;
        // TODO: absorb signal
    }
}
