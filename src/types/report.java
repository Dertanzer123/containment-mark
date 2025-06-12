package types;
public class report {

    private final Origin reportorigin;
    private final ReportLevel alertlevel;
    private final String reporttype;
    private final String reportcontent;

    public report(Origin reportorigin, ReportLevel alertlevel, String reporttype, String reportcontent) {
        this.reportorigin = reportorigin;
        this.alertlevel = alertlevel;
        this.reporttype = reporttype;
        this.reportcontent = reportcontent;
    }//todo fix the enum errors

    public report(report r) {
        this.reportorigin = r.reportorigin;
        this.alertlevel = r.alertlevel;
        this.reporttype = r.reporttype;
        this.reportcontent = r.reportcontent;
    }


    public enum Origin {
    PRISONER,
    STAFF
}
public enum ReportLevel {//more important reports have higher level,two prisoner deletion reports reason of one is escape and has high level,one is parole and has low level
    HIGH,
    MEDIUM,
    LOW
}


}
