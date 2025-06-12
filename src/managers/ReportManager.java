package managers;

import core.SystemRoot;
import types.Report;
import types.Signal;
import types.SignalCode;
import types.SignalLocation;

import java.util.ArrayList;

public class ReportManager extends BaseManager {
    public ReportManager(SystemRoot root) {
        super(SignalLocation.ReportManager, root);
    }

    ArrayList<Report> Reports = new ArrayList<>();
    // TODO: Add a database or file portal to store reports

    @Override
    public void emitSignal(SignalLocation signalDestination) {
        root.bridgeSignals(id, signalDestination);
    }

    @Override
    public void absorbSignal(Signal signal, SignalLocation signalOrigin) {
        switch (signal.signalCode) {
            case AddReport:
                Report r = new Report((Report) signal.signalData);
                addReport(r);
                signalBuffer = new Signal(SignalCode.ReportAdded, null);
                emitSignal(signalOrigin);
                break;
            case GetReport:
                int index = (int) signal.signalData;
                Report r1 = getReport(index);
                signalBuffer = new Signal(SignalCode.ReportReturn, r1);
                emitSignal(signalOrigin);
                break;

        }
    }

    private void addReport(Report r) {
        Reports.add(r);
    }

    private Report getReport(int index) {
        return Reports.get(index);
    }


}
