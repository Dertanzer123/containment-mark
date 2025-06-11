package managers;

import core.SystemRoot;

public class PrisonerManager extends BaseManager {
    public PrisonerManager(SystemRoot root) {
        super("PrisonerBaseManager", root);
    }

    @Override
    public void emitSignal() {
        // TODO: emit signal
    }

    @Override
    public void absorbSignal() {
        // TODO: absorb signal
    }
}
