package managers;

import core.SystemRoot;
import types.Signal;
import types.SignalLocation;

public abstract class BaseManager {
    Signal signalBuffer;
    SignalLocation id;
    SystemRoot root;

    public BaseManager(SignalLocation id, SystemRoot root) {
        this.id = id;
        this.root = root;
    }

    /**
     * Emits a signal to the root
     */
    public abstract void emitSignal(SignalLocation signalDestination);

    /**
     * Absorbs a signal from the root
     */
    public abstract void absorbSignal(Signal signal, SignalLocation signalOrigin);

    public Signal getSignalBuffer() {
        return signalBuffer;
    }
}
