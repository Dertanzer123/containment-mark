package managers;

import core.SystemRoot;

public class StaffManager extends BaseManager {
    public StaffManager(SystemRoot root) {
        super("StaffManager", root);
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
