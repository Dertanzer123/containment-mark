package types;

public enum SignalCode {
    // Report codes
    AddReport,
    GetReport,
    ReportAdded,
    ReportReturn,

    // Date codes
    UpdateDate,

    // Visit codes
    AddVisit,
    DeleteVisit,
    VisitAdded,

    // Prisoner codes
    AddPrisoner,
    UpdatePrisonerData,
    DeletePrisoner,
    PrisonerAdded,
    PrisonerUpdated,
    PrisonerDeleted,

    // Error codes
    Error
}
