package types;

public class Report {
    public enum Origin {
        PRISONERM,
        STAFFM,
        USER
    }

    // Report level is used to indicate the severity of the report -
    // for example, a HIGH report may indicate a security breach
    // while a LOW report may indicate parole of a prisoner
    public enum ReportLevel {
        HIGH,
        MEDIUM,
        LOW
    }

    public final String id;
    public final Origin reportOrigin;
    public final ReportLevel alertLevel;
    public final String reportType;
    public final String reportContent;

    public Report(String id, Origin reportOrigin, ReportLevel alertLevel, String reportType, String reportContent) {
        this.id = id;
        this.reportOrigin = reportOrigin;
        this.alertLevel = alertLevel;
        this.reportType = reportType;
        this.reportContent = reportContent;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Report r) {
            return (r.id).equals(this.id);
        }
        return false;
    }


}
