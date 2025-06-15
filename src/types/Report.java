package types;

public record Report(String id, types.Report.Origin reportOrigin, types.Report.ReportLevel alertLevel,
                     String reportType, String reportContent) {
    public enum Origin {
        PRISONER,
        STAFF,
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

    @Override
    public boolean equals(Object o) {
        if (o instanceof Report r) {
            return (r.id).equals(this.id);
        }
        return false;
    }
}
