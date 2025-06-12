package managers;

import core.SystemRoot;
import types.Signal;

public abstract class BaseManager {
    Signal signalBuffer;
    String id;
    SystemRoot root;

    public BaseManager(String id, SystemRoot root) {
        this.id = id;
        this.root = root;
    }
    /**
     * Emits a signal to the root
     */
    public abstract void emitSignal(String signalDestination);

    /**
     * Absorbs a signal from the root
     */
    public abstract void absorbSignal(Signal signal, String signalOrigin);

    public abstract Signal getSignalBuffer();

}
