package managers;

import core.SystemRoot;
import types.report;
import types.Signal;


import java.util.ArrayList;

public class ReportManager extends BaseManager {
    public ReportManager(SystemRoot root) {
        super("ReportManager", root);
    }

    ArrayList<report> reports = new ArrayList<report>();
    //todo: add a database or file portal to store reports


    @Override
    public void emitSignal(String signalDestination) {
        root.bridgeSignals(id, signalDestination);


    }
    @Override
    public void absorbSignal(Signal signal, String signalOrigin) {
        // TODO: absorb signal

        switch (signal.signalcode)
        {
            case "AddReport":
                report r = new report((report)signal.signaldata);
                addReport(r);
                signalBuffer = new Signal("reportAdded", null);
                emitSignal(signalOrigin);
                break;
            case "getReport":
                int index = (int)signal.signaldata;
                report r1 = getReport(index);
                signalBuffer = new Signal("reportReturn", r1);
                emitSignal(signalOrigin);
                break;

        }




    }
    @Override
    public Signal getSignalBuffer() {
        return signalBuffer;

    }

    private void addReport(report r)
    {
        reports.add(r);
    }
    private report getReport(int index)
    {
        return reports.get(index);
    }







}
