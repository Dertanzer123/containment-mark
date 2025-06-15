package core;

import managers.*;
import types.*;

import java.io.IOException;
import java.util.Date;

/// SystemRoot bridges the managers.
/// For example, one manager emits a signal with a destination root sends this signal to destination manager the destination manager absorbs and do what it need to do
public class SystemRoot {
    public final ReportManager reportManager;
    public final SectionManager sectionManager;
    public final PrisonerManager prisonerManager;
    public final StaffManager staffManager;
    public final UIManager uiManager;

    public SystemRoot() throws IOException {
        this.reportManager = new ReportManager(this);
        this.sectionManager = new SectionManager(this);
        this.prisonerManager = new PrisonerManager(this);
        this.staffManager = new StaffManager(this);
        this.uiManager = new UIManager(this);
        systemLoop();
    }

    private void systemLoop() {
        int systemLoopWaitTime = 1000;
        Signal signal;
        ErrorCode ec = null;
        FeedbackCodes sc = null;

        // TODO: : Add loading data from database/file to the prisoner,staff and section managers before system loop

        boolean exit = false;
        while (!exit) {
            try {
                Thread.sleep(systemLoopWaitTime);

                InputCode ic = uiManager.getUserInput(sc, ec);
                ec = null;
                sc = null;
                signal = uiManager.getSignalBuffer();

                switch (ic) {
                    case InputCode.UpdateDate: // TODO: : Implement the update date
                        signal = uiManager.getSignalBuffer(); //get the date from the uimanager (not user)
                        prisonerManager.updateVisits((Date) signal.signalData);
                        sc = FeedbackCodes.UpdateDate;
                        break;
                    case InputCode.Exit:
                        exit = true;
                        break;
                    case InputCode.AddReport:
                        signal = uiManager.getSignalBuffer();//get the report from the uimanager (user)
                        reportManager.addReport((Report) signal.signalData);
                        sc = FeedbackCodes.ReportAdded;
                        break;
                    case InputCode.GetReport:
                        signal = uiManager.getSignalBuffer();//get the id from the uimanager (user)
                        Report r = reportManager.getReport((String) signal.signalData);
                        if (r == null) {
                            ec = ErrorCode.NoReportFound;
                        } else {
                            // TODO: Get the report from the buffer when report returned
                            uiManager.writeSignalBuffer(new Signal(r));
                            sc = FeedbackCodes.ReportReturn;
                        }
                        break;
                    case InputCode.AddPrisoner:

                        Section freeCell = sectionManager.getfreeCellSection();
                        if (prisonerManager.addPrisoner((String) signal.signalData, freeCell)) {
                            signal = prisonerManager.getSignalBuffer();
                            reportManager.addReport((Report) signal.signalData);
                            sc = FeedbackCodes.PrisonerAdded;
                        } else {
                            ec = ErrorCode.SameIdPrisoner;
                        }

                        break;
                    case InputCode.UpdatePrisonerData: {
                        // TODO: Get the cell from the user
                        if (prisonerManager.updatePrisonerData((String) signal.signalData, sectionManager.getfreeCellSection())) {
                            signal = prisonerManager.getSignalBuffer();
                            reportManager.addReport((Report) signal.signalData);
                            sc = FeedbackCodes.PrisonerUpdated;
                        }
                        ec = ErrorCode.NoPrisonerFound;
                        break;
                    }
                    case InputCode.DeletePrisoner: {
                        if (prisonerManager.deletePrisoner((String) signal.signalData)) {
                            signal = prisonerManager.getSignalBuffer();
                            reportManager.addReport((Report) signal.signalData);
                            sc = FeedbackCodes.PrisonerDeleted;
                        }
                        ec = ErrorCode.NoPrisonerFound;
                        break;
                    }
                    case InputCode.AddVisit: {
                        if (prisonerManager.addVisit((Visit) signal.signalData)) {
                            signal = prisonerManager.getSignalBuffer();
                            reportManager.addReport((Report) signal.signalData);
                            sc = FeedbackCodes.VisitAdded;
                        } else {
                            ec = ErrorCode.NoPrisonerFound;
                        }
                        break;
                    }
                    case InputCode.DeleteVisit: {
                        if (prisonerManager.deleteVisit((Visit) signal.signalData)) {
                            signal = prisonerManager.getSignalBuffer();
                            reportManager.addReport((Report) signal.signalData);
                            sc = FeedbackCodes.VisitDeleted;
                        } else {
                            ec = ErrorCode.NoVisitFound;
                        }
                        // TODO: Add getVisit and implement is on prisoner manager which will return all the visits of the prisoner
                        break;
                    }
                    case InputCode.AddStaff: {
                        if (staffManager.addStaff((Staff) signal.signalData)) {
                            signal = staffManager.getSignalBuffer();
                            reportManager.addReport((Report) signal.signalData);
                            sc = FeedbackCodes.StaffAdded;
                        } else {
                            ec = ErrorCode.SameIdStaff;
                        }
                        break;
                    }
                    case InputCode.UpdateStaffData: {
                        if (staffManager.updateStaffData((Staff) signal.signalData)) {
                            signal = staffManager.getSignalBuffer();
                            reportManager.addReport((Report) signal.signalData);
                            sc = FeedbackCodes.StaffUpdated;
                        } else {
                            ec = ErrorCode.NoStaffFound;
                        }
                        break;
                    }
                    case InputCode.DeleteStaff: {
                        if (staffManager.deleteStaff((Staff) signal.signalData)) {
                            signal = staffManager.getSignalBuffer();
                            reportManager.addReport((Report) signal.signalData);
                            sc = FeedbackCodes.StaffDeleted;
                        } else {
                            ec = ErrorCode.NoStaffFound;
                        }
                        break;
                    }
                    case InputCode.GetPrisoner:// TODO: Implement getters and return the data to the uimanager through the signal buffer maybe add getters for all the list of data so it can be displayed
                        break;
                    case InputCode.GetVisit:
                        break;
                    case InputCode.GetStaff:
                        break;

                }
            } catch (InterruptedException e) {
                System.err.println("InterruptedException: " + e);
                System.exit(1);
            }
        }
    }
}
