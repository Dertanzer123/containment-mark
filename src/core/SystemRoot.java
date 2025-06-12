package core;

import managers.*;
import types.Signal;
import types.SignalCode;

/// SystemRoot bridges the managers.
/// For example, one manager emits a signal with a destination root sends this signal to destination manager the destination manager absorbs and do what it need to do
public class SystemRoot {
    private final ReportManager reportManager;
    private final SectionManager sectionManager;
    private final PrisonerManager prisonerManager;
    private final StaffManager staffManager;
    private final UIManager uiManager;

    public SystemRoot() {
        this.reportManager = new ReportManager(this);
        this.sectionManager = new SectionManager(this);
        this.prisonerManager = new PrisonerManager(this);
        this.staffManager = new StaffManager(this);
        this.uiManager = new UIManager(this);
    }

    /**
     * Takes the signal from origin and sends it to destination
     *
     * @param signalOrigin      the origin of the signal
     * @param signalDestination the destination of the signal
     */
    public void bridgeSignals(BaseManager signalOrigin, BaseManager signalDestination) {
        // If the signal origin and destination are the same, then there is no need to bridge the signal
        if (signalOrigin.equals(signalDestination)) {
            return;
        }

        // Get the signal buffer from the origin manager
        Signal signalBuffer = signalOrigin.getSignalBuffer();

        // If the received signal is Error, redirect to the UI manager
        if (signalBuffer.signalCode == SignalCode.Error) {
            this.uiManager.absorbSignal(signalBuffer, signalOrigin);
            return;
        }

        // If the received signal code is null, then do nothing
        if (signalBuffer.signalCode == null) {
            return;
        }

        // Now send the signal to the destination manager
        signalDestination.absorbSignal(signalBuffer, signalOrigin);
    }
}
