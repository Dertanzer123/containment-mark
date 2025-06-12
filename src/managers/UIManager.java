package managers;

import core.SystemRoot;
import types.Signal;
import types.SignalCode;
import types.SignalLocation;

public class UIManager extends BaseManager {
    public UIManager(SystemRoot root) {
        super(SignalLocation.UIManager, root);
    }

    // TODO: Add methods to interact with system the only entry point to system is from this manager
    // TODO: Add ui methods to modify signal buffer and send signals to other managers
    @Override
    public void emitSignal(SignalLocation signalDestination) {
        root.bridgeSignals(id, signalDestination);
    }

    @Override
    public void absorbSignal(Signal signal, SignalLocation signalOrigin) {
        // TODO: Absorb signal
        switch (signal.signalCode) {
            case SignalCode.Error:
                System.out.println(signal.signalData);//show error message on uı
                break;
        }
    }
}
