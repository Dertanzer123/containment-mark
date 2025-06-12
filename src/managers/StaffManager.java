package managers;

import core.SystemRoot;
import types.Signal;
import types.SignalLocation;
import types.Staff;

import java.util.ArrayList;

public class StaffManager extends BaseManager {
    public StaffManager(SystemRoot root) {
        super(SignalLocation.StaffManager, root);
    }

    // TODO: Add a database or file portal to store staff

    ArrayList<Staff> staffs = new ArrayList<>();

    @Override
    public void emitSignal(SignalLocation signalDestination) {
        root.bridgeSignals(id, signalDestination);
    }

    @Override
    public void absorbSignal(Signal signal, SignalLocation signalOrigin) {
        // TODO: absorb signal
    }
}
