package types;

public class Report {
    public enum Origin {
        PRISONER,
        STAFF
    }

    // Report level is used to indicate the severity of the report -
    // for example, a HIGH report may indicate a security breach
    // while a LOW report may indicate parole of a prisoner
    public enum ReportLevel {
        HIGH,
        MEDIUM,
        LOW
    }

    private final Origin reportOrigin;
    private final ReportLevel alertLevel;
    private final String reportType;
    private final String reportContent;

    public Report(Origin reportOrigin, ReportLevel alertLevel, String reportType, String reportContent) {
        this.reportOrigin = reportOrigin;
        this.alertLevel = alertLevel;
        this.reportType = reportType;
        this.reportContent = reportContent;
    }

    public Report(Report r) {
        this.reportOrigin = r.reportOrigin;
        this.alertLevel = r.alertLevel;
        this.reportType = r.reportType;
        this.reportContent = r.reportContent;
    }
}
