package managers;

import core.SystemRoot;
import types.Prisoner;
import types.Section;
import types.Signal;
import types.Visit;

import java.time.LocalDate;
import java.util.ArrayList;
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
     * @param prisonerID the prisoner that is being visited
     * @param name       the name of the visitor
     * @param date       the date of the visit
     * @param reason     the reason for the visit
     * @return true if the visit was added, false otherwise
     */
    public boolean addVisit(String prisonerID, String name, LocalDate date, String reason) {
        if (!prisoners.containsKey(prisonerID)) {
            return false;
        }
        Prisoner prisoner = prisoners.get(prisonerID);

        Visit visit = new Visit(prisonerID, name, date, reason);
        visits.add(visit);
        prisoner.addVisit(visit);

        return true;
    }

    public boolean addVisit(Visit visit) {
        if (addVisit(visit.prisonerID(), visit.name(), visit.date(), visit.reason())) {
            signalBuffer = new Signal(visit);
            return true;
        }

        return false;
    }

    /**
     * Deletes a visit from a prisoner
     *
     * @param visit the visit to delete
     * @return true if the visit was deleted, false otherwise
     */
    public boolean deleteVisit(Visit visit) {
        String id = visit.prisonerID();
        if (!prisoners.containsKey(id)) {
            return false;
        }

        Prisoner prisoner = prisoners.get(id);

        prisoner.removeVisit(visit);
        visits.remove(visit);

        return true;
    }

    /**
     * Updates the visits of the system
     *
     * @param currentDate the current date
     */
    public boolean updateVisits(LocalDate currentDate) {
        for (Visit visit : visits) {
            // TODO: add create report signal to updateVisits
            if (visit.date().isBefore(currentDate)) {
                deleteVisit(visit);
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new prisoner to the system
     *
     * @param prisoner the prisoner to add
     * @return true if the prisoner was added, false otherwise
     */
    public boolean addPrisoner(Prisoner prisoner, Section freeCell) {
        String id = prisoner.getId();
        if (prisoners.containsKey(id)) {
            return false;
        }
        prisoner.setHomeSection(freeCell);

        prisoners.put(id, prisoner);
        // TODO: Add report to signal buffer
        return true;
    }

    /**
     * Updates the home section of a prisoner
     *
     * @param prisoner    the prisoner to update
     * @param homeSection the new home section of the prisoner
     * @return true if the update was successful, false otherwise
     */
    public boolean updatePrisonerData(Prisoner prisoner, Section homeSection) {
        String id = prisoner.getId();
        if (!prisoners.containsKey(id)) {
            System.err.println("The prisoner with id " + id + " was not found");
            return false;
        }

        prisoner.setHomeSection(homeSection);
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
