package managers;

import core.SystemRoot;
import types.Signal;
import types.Staff;

import java.util.ArrayList;

public class StaffManager extends BaseManager {
    ArrayList<Staff> staffs = new ArrayList<>();
    // TODO: Add a database or file portal to store staff

    public StaffManager(SystemRoot root) {
        super(root);
    }

    @Override
    public void emitSignal(BaseManager signalDestination) {
        root.bridgeSignals(this, signalDestination);
    }

    @Override
    public void absorbSignal(Signal signal, BaseManager signalOrigin) {
        // TODO: absorb signal
    }
}
