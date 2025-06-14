package managers;

import core.SystemRoot;
import types.Signal;

public abstract class BaseManager {
    Signal signalBuffer;
    SystemRoot root;

    public BaseManager(SystemRoot root) {
        this.root = root;
    }



    public Signal getSignalBuffer() {
        return signalBuffer;
    }
}
