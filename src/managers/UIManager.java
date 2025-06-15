package managers;

import com.sun.net.httpserver.HttpServer;
import core.SystemRoot;
import helper.WebServer;
import types.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class UIManager extends BaseManager {
    public Date lastUpdateDate;
    private final BlockingQueue<UIInput> inputQueue = new LinkedBlockingQueue<>();
    private HttpServer server;

    public UIManager(SystemRoot root) throws IOException {
        super(root);

        WebServer.start(this);

        openHTML("src/UI.html");
    }

    public void setServer(HttpServer server) {
        this.server = server;
    }

    public static void openHTML(String filepath) {
        try {
            File htmlFile = new File(filepath);
            if (htmlFile.exists() && Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                    desktop.browse(htmlFile.toURI());
                } else {
                    System.err.println("BROWSE action not supported!");
                }
            } else {
                System.out.println("Cannot open browser.");
            }
        } catch (IOException e) {
            System.err.println("Error opening HTML file: " + e.getMessage());
        }
    }

    public void receiveInput(UIInput input) {
        inputQueue.offer(input);
    }

    public UIInput waitForInput() {
        try {
            return inputQueue.take(); // This waits until UI sends input
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

    public InputCode getUserInput(FeedbackCodes feedbackCode, ErrorCode errorCode) {
        // SignalCode might not be necessary depending on the next developments
        // TODO: Implement user interface and return user input to the system root

        if (errorCode != null) {
            // TODO: Handle and show error code
            System.err.println("Error code: " + errorCode);
            switch (errorCode) {
                case Error:
                    System.err.println("Error");
                    break;
                case SameIdPrisoner:
                    System.err.println("Same id prisoner");
                    break;
                case NoPrisonerFound:
                    System.err.println("No prisoner found");
                    break;
                case NoFreeCell:
                    System.err.println("No free cell");
                    break;
                case NoVisitFound:
                    System.err.println("No visit found");
                    break;
                case SameIdStaff:
                    System.err.println("Same id staff");
                    break;
                case NoStaffFound:
                    System.err.println("No staff found");
                    break;
            }
        } else if (feedbackCode != null) {
            // TODO: Handle and show feedback code
            System.out.println("Feedback code: " + feedbackCode);
            switch (feedbackCode) {
                case ReportAdded:
                    System.out.println("Report added");
                    break;
                case ReportReturn:
                    System.out.println("Report returned");// TODO: Get the report from the signal buffer and display it
                    break;
                case UpdateDate:
                    System.out.println("Date updated");
                    break;
                case VisitAdded:
                    System.out.println("Visit added");
                    break;
                case VisitDeleted:
                    System.out.println("Visit deleted");
                    break;
                case PrisonerAdded:
                    System.out.println("Prisoner added");
                    break;
                case PrisonerUpdated:
                    System.out.println("Prisoner updated");
                    break;
                case PrisonerDeleted:
                    System.out.println("Prisoner deleted");
                    break;
                case StaffAdded:
                    System.out.println("Staff added");
                    break;
                case StaffUpdated:
                    System.out.println("Staff updated");
                    break;
                case StaffDeleted:
                    System.out.println("Staff deleted");
                    break;
            }
        }

        // TODO: Implement taking input from user if necessary,
        //  update the signal buffer and return the user input,
        //  also implement date based updating and store the last update date,
        //  so it can update if it was more than one day even if the system not opened
        UIInput input = waitForInput();
        String inputCode = input.inputCode;
        InputCode userInput;
        try {
            userInput = InputCode.valueOf(inputCode);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input code: " + inputCode);
            server.stop(0);
            return null;
        }

        Map<String, String> parameters = input.parameters;
        System.out.println("Code: " + input.inputCode);
        System.out.println("Params: " + input.parameters);
        switch (userInput) {
            case AddReport:
                Report.ReportLevel reportLevel = switch (parameters.get("report-level")) {
                    case "high" -> Report.ReportLevel.HIGH;
                    case "medium" -> Report.ReportLevel.MEDIUM;
                    case "low" -> Report.ReportLevel.LOW;
                    default -> Report.ReportLevel.LOW;
                };

                String reportId = parameters.get("report-id");
                String reportType = parameters.get("report-type");
                String reportContent = parameters.get("report-content");

                signalBuffer = new Signal(new Report(reportId, Report.Origin.USER, reportLevel, reportType, reportContent));
                break;
            case GetReport:
                signalBuffer = new Signal(parameters.get("get-report-id"));
                break;
            case UpdateDate:
                break;
            case AddVisit: {
                String prisonerId = parameters.get("add-visit-prisoner-id");
                String visitName = parameters.get("add-visit-name");
                String visitDate = parameters.get("add-visit-date");
                LocalDate date = LocalDate.parse(visitDate);
                String visitReason = parameters.get("add-visit-reason");

                signalBuffer = new Signal(new Visit(prisonerId, visitName, date, visitReason));
                break;
            }
            case DeleteVisit: {
                String prisonerId = parameters.get("delete-visit-prisoner-id");
                String visitName = parameters.get("delete-visit-name");
                String visitDate = parameters.get("delete-visit-date");
                LocalDate date = LocalDate.parse(visitDate);
                String visitReason = parameters.get("delete-visit-reason");

                signalBuffer = new Signal(new Visit(prisonerId, visitName, date, visitReason));
                break;
            }
            case GetVisit:
                signalBuffer = new Signal(parameters.get("get-visit-prisoner-id"));
                break;
            case AddPrisoner: {
                String prisonerId = parameters.get("add-prisoner-id");
                String prisonName = parameters.get("add-prison-name");
                String prisonGender = parameters.get("add-prison-gender");
                String prisonBirthDate = parameters.get("add-prison-birthdate");
                LocalDate birthDate = LocalDate.parse(prisonBirthDate);

                // TODO: Somehow get a section for the new prisoner
//                Section freeCell = sectionManager.getFreeCellSection();
                Section freeCell = new Section("TODO", null);
                signalBuffer = new Signal(new Prisoner(prisonerId, prisonName, prisonGender, birthDate, freeCell));
                break;
            }
            case UpdatePrisonerData: {
                String prisonerId = parameters.get("update-prisoner-id");
                String prisonName = parameters.get("update-prison-name");
                String prisonGender = parameters.get("update-prison-gender");
                String prisonBirthDate = parameters.get("update-prison-birthdate");
                LocalDate birthDate = LocalDate.parse(prisonBirthDate);

                // TODO: Somehow get a section for the prisoner
//                Section freeCell = sectionManager.getFreeCellSection();
                Section freeCell = new Section("TODO", null);

                // TODO: As far as I understand, send the prisoner as a signal so it can be updated
                Prisoner prisoner = new Prisoner(prisonerId, prisonName, prisonGender, birthDate, freeCell);
                signalBuffer = new Signal(prisoner);
                break;
            }
            case DeletePrisoner:
                signalBuffer = new Signal(parameters.get("delete-prisoner-id"));
                break;
            case GetPrisoner:
                signalBuffer = new Signal(parameters.get("get-prisoner-id"));
                break;
            case AddStaff: {
                String staffId = parameters.get("add-staff-id");
                String staffName = parameters.get("add-staff-name");
                String staffGender = parameters.get("add-staff-gender");
                String staffBirthDate = parameters.get("add-staff-birthdate");
                LocalDate birthDate = LocalDate.parse(staffBirthDate);

                // TODO: Find a way to add fields to staff with signal buffer
                signalBuffer = new Signal(new Staff(staffId, staffName, staffGender, birthDate));
                break;
            }
            case UpdateStaffData: {
                String staffId = parameters.get("update-staff-id");
                String staffName = parameters.get("update-staff-name");
                String staffGender = parameters.get("update-staff-gender");
                String staffBirthDate = parameters.get("update-staff-birthdate");
                LocalDate birthDate = LocalDate.parse(staffBirthDate);

                // TODO: As far as I understand, send the staff as a signal so it can be updated
                Staff staff = new Staff(staffId, staffName, staffGender, birthDate);
                signalBuffer = new Signal(staff);
                break;
            }
            case DeleteStaff:
                signalBuffer = new Signal(parameters.get("delete-staff-id"));
                break;
            case GetStaff:
                signalBuffer = new Signal(parameters.get("get-staff-id"));
                break;
            case Exit:
                server.stop(0);
                break;
        }

        return userInput;
    }

    public void writeSignalBuffer(Signal signal) {
        signalBuffer = signal;
    }
}
