package managers;

import core.SystemRoot;

public abstract class BaseManager {
    String signalBuffer;
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
    public abstract void absorbSignal( String signal,String signalOrigin);

    public abstract String getSignalBuffer();

}
