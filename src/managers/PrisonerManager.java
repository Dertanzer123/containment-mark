package managers;

import core.SystemRoot;
import types.Prisoner;
import types.Section;
import types.Signal;
import types.Visit;

import java.util.ArrayList;
import java.util.Date;

public class PrisonerManager extends BaseManager {

    ArrayList<Prisoner> prisoners = new ArrayList<>();
    ArrayList<Visit> visits = new ArrayList<>();






    public PrisonerManager(SystemRoot root) {
        super("PrisonerBaseManager", root);
    }

    @Override
    public void emitSignal(String signalDestination) {


           root.bridgeSignals(id, signalDestination);


    }

    @Override
    public void absorbSignal(Signal signal, String signalOrigin) {

        switch (signal.signalcode)
        {//todo fill the switch cases
            case "reportAdded"://this is a feedback signal from report manager
                break;
            case "updateDate"://this is a signal for updateing time of the manager time is updated daily
                updateVisits(new Date());
                break;
            case "addVisit":
                Visit v = (Visit)signal.signaldata;
                if(addVisit(v.getVisited(), v.getName(), v.getDate(), v.getReason()))
                {
                    signalBuffer = new Signal("visitAdded", null);
                }
                else
                {
                    signalBuffer = new Signal("error", "prisoner not found");
                }
                emitSignal(signalOrigin);

                break;
            case "deleteVisit":
                break;
            case "addPrisoner":
                String id = (String)signal.signaldata;
                if(AddPrisoner(id))
                {
                    signalBuffer = new Signal("prisonerAdded", null);
                }
                else
                {
                    signalBuffer = new Signal("error", "prisoner already exists with same id");
                }
                emitSignal(signalOrigin);

                break;
            case "updatePrisonerData":
                Prisoner p = (Prisoner)signal.signaldata;
                if(updatePrisonerData(p.getId(), p.getHomeSection()))
                {
                    signalBuffer = new Signal("prisonerUpdated", null);
                }
                else
                {
                    signalBuffer = new Signal("error", "prisoner already exists with same id");
                }
                emitSignal(signalOrigin);

                break;
            case "deletePrisoner":
                if(deletePrisoner((String)signal.signaldata))
                {
                    signalBuffer = new Signal("prisonerDeleted", null);
                }
                else
                {
                    signalBuffer = new Signal("error", "prisoner not found");
                }
                emitSignal(signalOrigin);
                break;

        }






    }

    @Override
    public Signal getSignalBuffer() {
        return signalBuffer;

    }

    private boolean addVisit(Prisoner p, String name, Date date, String reason)
    {
        if(!prisoners.contains(p))
        {
           // System.out.println("prisoner not found");//todo: add error handling
            return false;
        }
        Visit v = new Visit(p, name, date, reason);
        visits.add(v);
        p.addVisit(v);
        return true;

    }
    private void deleteVisit(Visit v)
    {
        if(prisoners.contains(v.getVisited()))
        {
            v.getVisited().removeVisit(v);

        }
        else
        {
            System.out.println("prisoner not found");//todo: add error handling
        }
        visits.remove(v);


    }
    private void updateVisits(Date currentDate)
    {


        for(Visit v:visits)//TODO: add create report signal to updatevisits
        {
            if(v.getDate().before(currentDate))
            {
                deleteVisit(v);
            }
        }

    }

//    private boolean AddPrisoner(String id, Section homeSection)
//    {
//        Prisoner p = new Prisoner(id, homeSection);
//        if(prisoners.contains(p))
//        {
//            //System.out.println("prisoner already exists with same id");//todo: add error handling
//            return false;
//        }
//        prisoners.add(p);
//        return true;
//    }
    private boolean AddPrisoner(String id)
    {

        if(prisoners.contains(new Prisoner(id, null)))
        {

            return false;
        }
        Section homeSection = null;//todo: add find empty section signal to here
        prisoners.add(new Prisoner(id, homeSection));
        return true;
    }
    private boolean updatePrisonerData(String idkey,Section homeSection)
    {
        if(!prisoners.contains(new Prisoner(idkey, homeSection)))
        {
            ;//todo: add error handling
            return false;
        }
        prisoners.get(prisoners.indexOf(new Prisoner(idkey, homeSection))).setHomeSection(homeSection);

        return true;

    }
    private boolean deletePrisoner(String idkey)
    {
        if(!prisoners.contains(new Prisoner(idkey, null)))
        {
            ;//todo: add error handling
            return false;
        }
        prisoners.remove(prisoners.indexOf(new Prisoner(idkey, null)));
        return true;
    }







}
