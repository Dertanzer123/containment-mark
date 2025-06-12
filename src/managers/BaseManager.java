package managers;

import core.SystemRoot;
import types.Signal;

public abstract class BaseManager {
    Signal signalBuffer;
    SystemRoot root;

    public BaseManager(SystemRoot root) {
        this.root = root;
    }

    /**
     * Emits a signal to the root
     */
    public abstract void emitSignal(BaseManager signalDestination);

    /**
     * Absorbs a signal from the root
     */
    public abstract void absorbSignal(Signal signal, BaseManager signalOrigin);

    public Signal getSignalBuffer() {
        return signalBuffer;
    }
}
