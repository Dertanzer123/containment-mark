package managers;

import core.SystemRoot;
import types.Signal;
import types.feedBackCodes;
import types.errorcode;
import types.inputcode;

import java.util.Date;

public class UIManager extends BaseManager {
    public UIManager(SystemRoot root) {
        super(root);
    }
    public Date lastUpdateDate ;

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
        inputcode userInput =null;
        //todo impplement taking input from user if necessary update the signal buffer and return the user input ,also implement date based updating and store the last update date so it can update if it was more than one day even if the system not opened
        return userInput;
    }
    public void writeSignalBuffer(Signal signal)
    {
        signalBuffer=signal;
    }
}
