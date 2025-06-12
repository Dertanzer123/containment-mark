package managers;

import core.SystemRoot;
import types.Signal;

public class UIManager extends BaseManager {
    public UIManager(SystemRoot root) {
        super( "UIManager", root);
    }

    //Todo: add methods to interact with system the only entry point to system is from this manager
    //todo: add ui methods to modify signal buffer and send signals to other managers
    @Override
    public void emitSignal(String signalDestination) {
        root.bridgeSignals(id, signalDestination);
    }

    @Override
    public void absorbSignal(Signal signal, String signalOrigin) {
        //todo: absorb signal
        switch (signal.signalcode)
        {
            case "error":
                System.out.println(signal.signaldata);//show error message on uı
                break;
        }
    }

    @Override
    public Signal getSignalBuffer() {
        return signalBuffer;
    }
}
