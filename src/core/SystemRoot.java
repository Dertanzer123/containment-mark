package core;

import managers.*;
import types.Signal;
import types.SignalLocation;

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
    public void bridgeSignals(SignalLocation signalOrigin, SignalLocation signalDestination) {
        if (signalOrigin.equals(signalDestination)) {
            return;
        }

        // Get the signal buffer from the origin manager
        Signal signalBuffer = null;
        switch (signalOrigin) {
            case ReportManager:
                signalBuffer = reportManager.getSignalBuffer();
                break;
            case SectionManager:
                signalBuffer = sectionManager.getSignalBuffer();
                break;
            case PrisonerManager:
                signalBuffer = prisonerManager.getSignalBuffer();
                break;
            case StaffManager:
                signalBuffer = staffManager.getSignalBuffer();
                break;
            case UIManager:
                signalBuffer = uiManager.getSignalBuffer();
                break;
            default:
                System.err.println("Invalid signal origin");
                System.exit(1);
        }

        // If the received signal code is null, then the signal buffer is invalid
        if (signalBuffer.signalCode == null) {
            System.err.println(signalOrigin + " sent a signal with a null signal code");
            System.exit(1);
        }

        // Now send the signal to the destination manager
        switch (signalDestination) {
            case ReportManager:
                reportManager.absorbSignal(signalBuffer, signalOrigin);
                break;
            case SectionManager:
                sectionManager.absorbSignal(signalBuffer, signalOrigin);
                break;
            case PrisonerManager:
                prisonerManager.absorbSignal(signalBuffer, signalOrigin);
                break;
            case StaffManager:
                staffManager.absorbSignal(signalBuffer, signalOrigin);
                break;
            case UIManager:
                uiManager.absorbSignal(signalBuffer, signalOrigin);
                break;
        }
    }
}
