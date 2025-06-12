package managers;

import core.SystemRoot;
import types.Prisoner;
import types.Section;
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

        // TODO: emit signal
    }

    @Override
    public void absorbSignal(String signal, String signalOrigin) {
        // TODO: absorb signal
    }

    @Override
    public String getSignalBuffer() {
        return signalBuffer;
        // TODO: absorb signal
    }

    private void addVisit(Prisoner p, String name, Date date, String reason)
    {
        if(!prisoners.contains(p))
        {
            System.out.println("prisoner not found");
            return;
        }
        Visit v = new Visit(p, name, date, reason);
        visits.add(v);
        p.addVisit(v);

    }
    private void deleteVisit(Visit v)
    {
        if(prisoners.contains(v.getVisited()))
        {
            v.getVisited().removeVisit(v);

        }
        else
        {
            System.out.println("prisoner not found");
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

    private void AddPrisoner(String id, Section homeSection)
    {
        Prisoner p = new Prisoner(id, homeSection);
        if(prisoners.contains(p))
        {
            System.out.println("prisoner already exists with same id");
            return;
        }
        prisoners.add(p);
    }
    private void AddPrisoner(String id)
    {

        if(prisoners.contains(new Prisoner(id, null)))
        {
            System.out.println("prisoner already exists with same id");
            return;
        }
        Section homeSection = null;//todo: add find empty section signal to here
        prisoners.add(new Prisoner(id, homeSection));

    }
  






}
