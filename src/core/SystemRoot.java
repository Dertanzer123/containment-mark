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
        errorcode ec =null;
        feedBackCodes sc=null;


        //todo add loading data from database/file to the prisoner,staff and section managers before system loop





        boolean exit=false;
        while (!exit) {

            try {
                Thread.sleep(systemLoopWaitTime);

                inputcode ic=uiManager.getUserInput(sc,ec);
                ec=null;
                sc=null;
                signal= uiManager.getSignalBuffer();

                switch (ic)
                {
                    case inputcode.UpdateDate://todo implement the update date
                        signal = uiManager.getSignalBuffer();//get the date from the uimanager (not user)
                        prisonerManager.updateVisits((Date) signal.signalData);
                        sc=feedBackCodes.UpdateDate;
                        break;
                    case inputcode.exit:
                        exit=true;
                        break;
                    case inputcode.AddReport:
                        signal = uiManager.getSignalBuffer();//get the report from the uimanager (user)
                        reportManager.addReport((Report) signal.signalData);
                        sc=feedBackCodes.ReportAdded;
                        break;
                    case inputcode.GetReport:
                        signal = uiManager.getSignalBuffer();//get the id from the uimanager (user)
                        Report r=reportManager.getReport((String) signal.signalData);
                        uiManager.writeSignalBuffer(new Signal(r));//todo get the report from the buffer when report returned
                        sc=feedBackCodes.ReportReturn;
                        break;
                    case inputcode.AddPrisoner:

                        Section freeCell=sectionManager.getfreeCellSection();
                        if(prisonerManager.addPrisoner((String) signal.signalData,freeCell))
                        {
                            signal = prisonerManager.getSignalBuffer();
                            reportManager.addReport((Report)signal.signalData);
                            sc= feedBackCodes.PrisonerAdded;
                        }
                        else
                        {
                            ec=errorcode.SameIdPrisoner;
                        }

                        break;
                    case inputcode.UpdatePrisonerData:
                        if(prisonerManager.updatePrisonerData( (String) signal.signalData,sectionManager.getfreeCellSection()))//todo get the cell from the user
                        {
                            signal = prisonerManager.getSignalBuffer();
                            reportManager.addReport((Report)signal.signalData);
                            sc= feedBackCodes.PrisonerUpdated;
                        }
                        {
                            ec=errorcode.NoPrisonerFound;
                        }
                        break;
                    case inputcode.DeletePrisoner:
                        if(prisonerManager.deletePrisoner((String) signal.signalData))
                        {
                            signal = prisonerManager.getSignalBuffer();
                            reportManager.addReport((Report)signal.signalData);
                            sc= feedBackCodes.PrisonerDeleted;
                        }
                        {
                            ec=errorcode.NoPrisonerFound;
                        }
                        break;
                    case inputcode.AddVisit:
                        if(prisonerManager.addVisit((Visit) signal.signalData))
                        {
                            signal = prisonerManager.getSignalBuffer();
                            reportManager.addReport((Report)signal.signalData);
                            sc= feedBackCodes.VisitAdded;
                        }
                        else
                        {
                            ec=errorcode.NoPrisonerFound;
                        }
                        break;
                    case inputcode.DeleteVisit:
                        if(prisonerManager.deleteVisit((Visit) signal.signalData))
                        {
                            signal = prisonerManager.getSignalBuffer();
                            reportManager.addReport((Report)signal.signalData);
                            sc= feedBackCodes.VisitDeleted;
                        }
                        else
                        {
                            ec=errorcode.NoVisitFound;
                        }
                        break;//todo add getvisit and implement is on prisoner manager which will return all the visits of the prisoner
                    case inputcode.AddStaff:
                        if(staffManager.addStaff((Staff) signal.signalData))
                        {
                            signal = staffManager.getSignalBuffer();
                            reportManager.addReport((Report)signal.signalData);
                            sc= feedBackCodes.StaffAdded;
                        }
                        else {
                            ec=errorcode.sameIdStaff;
                        }
                        break;
                    case inputcode.UpdateStaffData:
                        if(staffManager.updateStaffData((Staff) signal.signalData))
                        {
                            signal = staffManager.getSignalBuffer();
                            reportManager.addReport((Report)signal.signalData);
                            sc= feedBackCodes.StaffUpdated;
                        }
                        else {
                            ec=errorcode.noStaffFound;
                        }
                        break;
                    case inputcode.DeleteStaff:
                        if(staffManager.deleteStaff((Staff) signal.signalData))
                        {
                            signal = staffManager.getSignalBuffer();
                            reportManager.addReport((Report)signal.signalData);
                            sc= feedBackCodes.StaffDeleted;
                        }
                        else {
                            ec=errorcode.noStaffFound;
                        }
                        break;
                    case inputcode.GetPrisoner://todo implement getters and return the data to the uimanager through the signal buffer maybe add getters for all the list of data so it can be displayed
                        break;
                    case inputcode.GetVisit:
                        break;
                    case inputcode.GetStaff:
                        break;

                }
            } catch (InterruptedException e) {
                e.printStackTrace();// todo Handle the exception
            }
        }
}








    /**
     * Takes the signal from origin and sends it to destination
     *
     * @param signalOrigin      the origin of the signal
     * @param signalDestination the destination of the signal
     */

}
