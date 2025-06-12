package managers;

import core.SystemRoot;
import types.Report;
import types.Signal;
import types.SignalCode;

import java.util.ArrayList;

public class ReportManager extends BaseManager {
    ArrayList<Report> Reports = new ArrayList<>();
    // TODO: Add a database or file portal to store reports

    public ReportManager(SystemRoot root) {
        super(root);
    }

    @Override
    public void emitSignal(BaseManager signalDestination) {
        root.bridgeSignals(this, signalDestination);
    }

    @Override
    public void absorbSignal(Signal signal, BaseManager signalOrigin) {
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
