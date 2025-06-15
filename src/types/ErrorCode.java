package types;

public enum ErrorCode {
    Error,
    SameIdPrisoner,// TODO: Add error codes to corresponding errors and send error codes to the ui manager
    NoPrisonerFound,
    NoFreeCell,

    NoVisitFound,

    SameIdStaff,
    NoStaffFound,

    NoReportFound,
}
