package managers;

import core.SystemRoot;
import types.Signal;
import types.Staff;

import java.util.ArrayList;

public class StaffManager extends BaseManager {
    public StaffManager(SystemRoot root) {
        super("StaffManager", root);
    }

    //todo: add a database or file portal to store staff

    ArrayList<Staff> staffs = new ArrayList<Staff>();


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
