package types;

public enum errorcode {
    Error,
    SameIdPrisoner,//todo add error codes to corresponding errors and send error codes to the ui manager
    NoPrisonerFound,
    NoFreeCell,

    NoVisitFound,

    sameIdStaff,
    noStaffFound,

    NoReportFound,
}
