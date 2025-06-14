package managers;

import core.SystemRoot;
import types.Report;

import java.util.ArrayList;

public class ReportManager extends BaseManager {
    ArrayList<Report> Reports = new ArrayList<>();//
    // TODO: Add a database or file portal to store reports create a hashmap atleast

    public ReportManager(SystemRoot root) {
        super(root);
    }



    public void addReport(Report r) {
        Reports.add(r);
    }

    public Report getReport(String id) {
        return Reports.get(Reports.indexOf(new Report(id,null,null,null,null)));//todo change this with a hasmap get method
    }


}
