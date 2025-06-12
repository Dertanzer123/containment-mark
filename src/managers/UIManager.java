package managers;

import core.SystemRoot;

public class UIManager extends BaseManager {
    public UIManager(SystemRoot root) {
        super( "UIManager", root);
    }

    @Override
    public void emitSignal(String signalDestination) {

    }

    @Override
    public void absorbSignal(String signal, String signalOrigin) {

    }

    @Override
    public String getSignalBuffer() {
        return signalBuffer;
    }
}
