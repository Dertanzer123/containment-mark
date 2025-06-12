package core;

import managers.*;
import types.Signal;

import java.util.Objects;

/// SystemRoot bridges the managers.
/// For example, one manager emits a signal with a destination root sends this signal to destination manager the destination manager absorbs and do what it need to do
public class SystemRoot {
    private final ReportManager reportManager;
    private final SectionManager sectionManager;
    private final PrisonerManager prisonerManager;
    private final StaffManager staffManager;
    private final UIManager uiManager;

    public SystemRoot() {
        this.uiManager = new UIManager( this);
        this.reportManager = new ReportManager(this);
        this.sectionManager = new SectionManager(this);
        this.prisonerManager = new PrisonerManager(this);
        this.staffManager = new StaffManager(this);

    }
    public void bridgeSignals(String signalOrigin, String signalDestination) {//this takes signal from origin and sends it to destination
        Signal signalBuffer = null;//todo: this kind of signal transmitting can get stack overflow error improve it by returns
        if (Objects.equals(signalOrigin, signalDestination)) {
            return;
        }
        switch (signalOrigin) {
            case "ReportManager":
                signalBuffer = reportManager.getSignalBuffer();
                break;
            case "SectionManager":
                signalBuffer = sectionManager.getSignalBuffer();
                break;
            case "PrisonerManager":
                signalBuffer = prisonerManager.getSignalBuffer();
                break;
            case "StaffManager":
                signalBuffer = staffManager.getSignalBuffer();
                break;
            case "UIManager":
            signalBuffer = uiManager.getSignalBuffer();
                break;
        }
        if(signalBuffer == null || signalBuffer.signalcode == null){
            return;//todo: add error handling
        }

        switch (signalDestination) {
            case "ReportManager":
                reportManager.absorbSignal(signalBuffer,signalOrigin);
                break;
            case "SectionManager":
                sectionManager.absorbSignal(signalBuffer,signalOrigin);
                break;
            case "PrisonerManager":
                prisonerManager.absorbSignal(signalBuffer,signalOrigin);
                break;
            case "StaffManager":
                staffManager.absorbSignal(signalBuffer,signalOrigin);
                break;
            case "UIManager":
            uiManager.absorbSignal(signalBuffer,signalOrigin);
                break;
        }
    }
}
