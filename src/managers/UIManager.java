package managers;

import com.sun.net.httpserver.HttpServer;
import core.SystemRoot;
import helper.WebServer;
import types.*;

import java.net.InetSocketAddress;
import java.util.Date;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.*;
import java.awt.Desktop;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

public class UIManager extends BaseManager {


    public Date lastUpdateDate ;
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
            File htmlFile = new File(filepath); // path to your file
            if (htmlFile.exists() && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(htmlFile.toURI());
            } else {
                System.out.println("Cannot open browser.");
            }
        } catch (IOException e) {
            e.printStackTrace();
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


    public inputcode getUserInput(feedBackCodes feedbackcode, errorcode errorcode) {//signalcode might not be nececery depending on the next developments
        //todo implement user interface and return user input to the system root
        if(errorcode!=null)
        {
            //todo handle and show error code
            System.err.println("Error code: "+errorcode);
            switch (errorcode)
            {
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
                case sameIdStaff:
                    System.err.println("Same id staff");
                    break;
                case noStaffFound:
                    System.err.println("No staff found");
                    break;
            }
        }
        else if(feedbackcode!=null)
        {
            //todo handle and show feedback code
            System.out.println("Feedback code: "+feedbackcode);
            switch (feedbackcode)
            {
                case ReportAdded:
                    System.out.println("Report added");
                    break;
                case ReportReturn:
                    System.out.println("Report returned");//todo get the report from the signal buffer and display it
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
        inputcode userInput = null;
        //todo impplement taking input from user if necessary update the signal buffer and return the user input ,also implement date based updating and store the last update date so it can update if it was more than one day even if the system not opened
       UIInput input = waitForInput();
        System.out.println("Code: " + input.inputcode);
        System.out.println("Params: " + input.parameters);
        switch (input.inputcode) {
            case "AddReport":
                userInput = inputcode.AddReport;
                Report.ReportLevel reportLevel;
                switch (input.parameters.get("report-level")) {
                    case "high":
                        reportLevel = Report.ReportLevel.HIGH;
                        break;
                    case "medium":
                        reportLevel = Report.ReportLevel.MEDIUM;
                        break;
                    case "low":
                        reportLevel = Report.ReportLevel.LOW;
                        break;
                    default:
                        reportLevel = Report.ReportLevel.LOW;
                        break;
                }

                signalBuffer = new Signal(new Report(input.parameters.get("report-id"), Report.Origin.USER, reportLevel, input.parameters.get("report-type"), input.parameters.get("report-content")));
                break;
            case "GetReport":
                userInput = inputcode.GetReport;
                signalBuffer =new Signal((String) input.parameters.get("get-report-id"));
                break;
            case "UpdateDate":
                userInput = inputcode.UpdateDate;
                break;
            case "AddVisit":
                userInput = inputcode.AddVisit;//todo control and bugfix switches from now down
                signalBuffer = new Signal(new Visit(new Prisoner(input.parameters.get("add-visit-prisoner-id"),null),input.parameters.get("add-visit-name"),new Date(Long.parseLong(input.parameters.get("add-visit-date"))),input.parameters.get("add-visit-reason")));//todo parse date correctly
                break;
            case "DeleteVisit":
                userInput = inputcode.DeleteVisit;
                signalBuffer = new Signal(new Visit(new Prisoner(input.parameters.get("delete-visit-prisoner-id"),null),input.parameters.get("delete-visit-name"),new Date(Long.parseLong(input.parameters.get("delete-visit-date"))),input.parameters.get("delete-visit-reason")));//todo parse date correctly
                break;
            case "GetVisit":
                userInput = inputcode.GetVisit;
                signalBuffer =new Signal((String) input.parameters.get("get-visit-prisoner-id"));
                break;
            case "AddPrisoner":
                userInput = inputcode.AddPrisoner;
                //todo add date parsing from string and find a way to add fields to prisoner with signal buffer
                signalBuffer =new Signal((String) input.parameters.get("add-prisoner-id"));

                break;
            case "UpdatePrisonerData":
                userInput = inputcode.UpdatePrisonerData;//todo same as add prison but with update
                signalBuffer =new Signal((String) input.parameters.get("update-prisoner-id"));
                break;
            case "DeletePrisoner":
                userInput = inputcode.DeletePrisoner;
                signalBuffer =new Signal((String) input.parameters.get("delete-prisoner-id"));
                break;
            case "GetPrisoner":
                userInput = inputcode.GetPrisoner;
                signalBuffer =new Signal((String) input.parameters.get("get-prisoner-id"));
                break;
            case "AddStaff":
                userInput = inputcode.AddStaff;
                signalBuffer =new Signal((String) input.parameters.get("add-staff-id"));//todo find a way to add fields to staff with signal buffer
                break;
            case "UpdateStaffData":
                userInput = inputcode.UpdateStaffData;
                signalBuffer=new Signal((String) input.parameters.get("update-staff-id"));

                break;
            case "DeleteStaff":
                userInput = inputcode.DeleteStaff;
                signalBuffer =new Signal((String) input.parameters.get("delete-staff-id"));
                break;
            case "GetStaff":
                userInput = inputcode.GetStaff;
                signalBuffer =new Signal((String) input.parameters.get("get-staff-id"));
                break;
            case "exit":
                userInput = inputcode.exit;

                break;


        }



        if(userInput.equals(inputcode.exit))
        {
            server.stop(0);
        }
        return userInput;
    }
    public void writeSignalBuffer(Signal signal)
    {
        signalBuffer=signal;
    }

}
