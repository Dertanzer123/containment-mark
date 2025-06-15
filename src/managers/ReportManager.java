package managers;

import core.SystemRoot;
import types.Report;

import java.util.HashMap;

public class ReportManager extends BaseManager {
    HashMap<String, Report> reports = new HashMap<>();//
    // TODO: Add a database or file portal to store reports create a hashmap atleast

    public ReportManager(SystemRoot root) {
        super(root);
    }

    public void addReport(Report r) {
        reports.put(r.id(), r);
    }

    public Report getReport(String id) {
        if (!reports.containsKey(id)) {
            return null;
        }
        return reports.get(id);
    }
}
