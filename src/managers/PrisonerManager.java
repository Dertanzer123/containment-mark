package managers;

import core.SystemRoot;
import types.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class PrisonerManager extends BaseManager {
    HashMap<String, Prisoner> prisoners = new HashMap<>();
    ArrayList<Visit> visits = new ArrayList<>();

    public PrisonerManager(SystemRoot root) {
        super(root);
    }





    /**
     * Adds a visit to a prisoner
     *
     * @param prisoner the prisoner that is being visited
     * @param name     the name of the visitor
     * @param date     the date of the visit
     * @param reason   the reason for the visit
     * @return true if the visit was added, false otherwise
     */
    public boolean addVisit(Prisoner prisoner, String name, Date date, String reason) {
        String id = prisoner.getId();
        if (!prisoners.containsKey(id)) {

            return false;
        }

        Visit visit = new Visit(prisoner, name, date, reason);
        visits.add(visit);
        prisoner.addVisit(visit);

        return true;
    }
    public boolean addVisit(Visit visit)
    {
        if(addVisit(visit.visitedPrisoner(),visit.name(),visit.date(),visit.reason()))
        {
            signalBuffer = new Signal( visit);
            return true;
        }
        else return false;
    }

    /**
     * Deletes a visit from a prisoner
     *
     * @param visit the visit to delete
     * @return true if the visit was deleted, false otherwise
     */
    public boolean deleteVisit(Visit visit) {
        Prisoner prisoner = visit.visitedPrisoner();
        String id = prisoner.getId();

        if (!prisoners.containsKey(id)) {

            return false;
        }

        prisoner.removeVisit(visit);
        visits.remove(visit);

        return true;
    }

    /**
     * Updates the visits of the system
     *
     * @param currentDate the current date
     */
    public boolean updateVisits(Date currentDate) {
        for (Visit visit : visits) {
            // TODO: add create report signal to updateVisits
            if (visit.date().before(currentDate)) {
                deleteVisit(visit);
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new prisoner to the system
     *
     * @param id the id of the prisoner
     * @return true if the prisoner was added, false otherwise
     */
    public boolean addPrisoner(String id, Section homeSection) {
        if (prisoners.containsKey(id)) {
            return false;
        }

        prisoners.put(id, new Prisoner(id, homeSection));
        //todo add report to signal buffer
        return true;
    }

    /**
     * Updates the home section of a prisoner
     *
     * @param id          the id of the prisoner
     * @param homeSection the new home section of the prisoner
     * @return true if the update was successful, false otherwise
     */
    public boolean updatePrisonerData(String id, Section homeSection) {
        if (!prisoners.containsKey(id)) {
            System.err.println("The prisoner with id " + id + " was not found");
            return false;
        }

        prisoners.get(id).setHomeSection(homeSection);
        return true;
    }

    /**
     * Deletes a prisoner from the system
     *
     * @param id the id of the prisoner
     * @return true if the prisoner was deleted, false otherwise
     */
    public boolean deletePrisoner(String id) {
        if (!prisoners.containsKey(id)) {
            System.err.println("The prisoner with id " + id + " was not found");
            return false;
        }

        prisoners.remove(id);
        return true;
    }
}
