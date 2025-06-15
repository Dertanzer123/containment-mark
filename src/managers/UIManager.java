package managers;

import com.sun.net.httpserver.HttpServer;
import core.SystemRoot;
import helper.WebServer;
import types.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;
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

    public InputCode getUserInput(FeedBackCodes feedbackCode, ErrorCode errorCode) {
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

        InputCode userInput = null;
        // TODO: Implement taking input from user if necessary,
        //  update the signal buffer and return the user input,
        //  also implement date based updating and store the last update date,
        //  so it can update if it was more than one day even if the system not opened
        UIInput input = waitForInput();
        System.out.println("Code: " + input.inputCode);
        System.out.println("Params: " + input.parameters);
        switch (input.inputCode) {
            case "AddReport":
                userInput = InputCode.AddReport;
                Report.ReportLevel reportLevel = switch (input.parameters.get("report-level")) {
                    case "high" -> Report.ReportLevel.HIGH;
                    case "medium" -> Report.ReportLevel.MEDIUM;
                    case "low" -> Report.ReportLevel.LOW;
                    default -> Report.ReportLevel.LOW;
                };

                signalBuffer = new Signal(new Report(input.parameters.get("report-id"), Report.Origin.USER, reportLevel, input.parameters.get("report-type"), input.parameters.get("report-content")));
                break;
            case "GetReport":
                userInput = InputCode.GetReport;
                signalBuffer = new Signal(input.parameters.get("get-report-id"));
                break;
            case "UpdateDate":
                userInput = InputCode.UpdateDate;
                break;
            case "AddVisit":
                // TODO: Control and bugfix switches from now down
                userInput = InputCode.AddVisit;
                signalBuffer = new Signal(new Visit(new Prisoner(input.parameters.get("add-visit-prisoner-id"), null), input.parameters.get("add-visit-name"), new Date(Long.parseLong(input.parameters.get("add-visit-date"))), input.parameters.get("add-visit-reason")));//todo parse date correctly
                break;
            case "DeleteVisit":
                userInput = InputCode.DeleteVisit;
                signalBuffer = new Signal(new Visit(new Prisoner(input.parameters.get("delete-visit-prisoner-id"), null), input.parameters.get("delete-visit-name"), new Date(Long.parseLong(input.parameters.get("delete-visit-date"))), input.parameters.get("delete-visit-reason")));//todo parse date correctly
                break;
            case "GetVisit":
                userInput = InputCode.GetVisit;
                signalBuffer = new Signal(input.parameters.get("get-visit-prisoner-id"));
                break;
            case "AddPrisoner":
                userInput = InputCode.AddPrisoner;
                // TODO: Add date parsing from string and find a way to add fields to prisoner with signal buffer
                signalBuffer = new Signal(input.parameters.get("add-prisoner-id"));
                break;
            case "UpdatePrisonerData":
                userInput = InputCode.UpdatePrisonerData;
                // TODO: Same as add prison but with update
                signalBuffer = new Signal(input.parameters.get("update-prisoner-id"));
                break;
            case "DeletePrisoner":
                userInput = InputCode.DeletePrisoner;
                signalBuffer = new Signal(input.parameters.get("delete-prisoner-id"));
                break;
            case "GetPrisoner":
                userInput = InputCode.GetPrisoner;
                signalBuffer = new Signal(input.parameters.get("get-prisoner-id"));
                break;
            case "AddStaff":
                userInput = InputCode.AddStaff;
                // TODO: Find a way to add fields to staff with signal buffer
                signalBuffer = new Signal(input.parameters.get("add-staff-id"));
                break;
            case "UpdateStaffData":
                userInput = InputCode.UpdateStaffData;
                signalBuffer = new Signal(input.parameters.get("update-staff-id"));
                break;
            case "DeleteStaff":
                userInput = InputCode.DeleteStaff;
                signalBuffer = new Signal(input.parameters.get("delete-staff-id"));
                break;
            case "GetStaff":
                userInput = InputCode.GetStaff;
                signalBuffer = new Signal(input.parameters.get("get-staff-id"));
                break;
            case "Exit":
                userInput = InputCode.Exit;
                break;
        }

        if (userInput == null || userInput.equals(InputCode.Exit)) {
            server.stop(0);
        }
        return userInput;
    }

    public void writeSignalBuffer(Signal signal) {
        signalBuffer = signal;
    }
}
