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

    @Override
    public void emitSignal(BaseManager signalDestination) {
        root.bridgeSignals(this, signalDestination);
    }

    @Override
    public void absorbSignal(Signal signal, BaseManager signalOrigin) {
        // TODO: fill the switch cases
        switch (signal.signalCode) {
            case ReportAdded: {
                // This is a feedback signal from the report manager, no need to do anything
                break;
            }
            case UpdateDate: {
                // This is a signal for updating time of the manager. Time is updated daily
                updateVisits(new Date());
                break;
            }
            case AddVisit: {
                Visit visit = (Visit) signal.signalData;
                if (addVisit(visit.visitedPrisoner(), visit.name(), visit.date(), visit.reason())) {
                    signalBuffer = new Signal(SignalCode.VisitAdded, null);
                } else {
                    signalBuffer = new Signal(SignalCode.Error, "Prisoner not found");
                }
                emitSignal(signalOrigin);
                break;
            }
            case DeleteVisit: {
                Visit visit = (Visit) signal.signalData;
                if (deleteVisit(visit)) {
                    signalBuffer = new Signal(SignalCode.VisitDeleted, null);
                } else {
                    signalBuffer = new Signal(SignalCode.Error, "Visit not found");
                }
                emitSignal(signalOrigin);
                break;
            }
            case AddPrisoner: {
                String id = (String) signal.signalData;
                if (addPrisoner(id)) {
                    signalBuffer = new Signal(SignalCode.PrisonerAdded, null);
                } else {
                    signalBuffer = new Signal(SignalCode.Error, "Prisoner with id " + id + " already exists");
                }
                emitSignal(signalOrigin);
                break;
            }
            case UpdatePrisonerData: {
                Prisoner prisoner = (Prisoner) signal.signalData;
                if (updatePrisonerData(prisoner.getId(), prisoner.getHomeSection())) {
                    signalBuffer = new Signal(SignalCode.PrisonerUpdated, null);
                } else {
                    signalBuffer = new Signal(SignalCode.Error, "Prisoner with id " + prisoner.getId() + " not found");
                }
                emitSignal(signalOrigin);
                break;
            }
            case DeletePrisoner: {
                String id = (String) signal.signalData;
                if (deletePrisoner(id)) {
                    signalBuffer = new Signal(SignalCode.PrisonerDeleted, null);
                } else {
                    signalBuffer = new Signal(SignalCode.Error, "Prisoner not found");
                }
                emitSignal(signalOrigin);
                break;
            }
            default: {
                System.err.println("Invalid signal received: " + signal.signalData);
                System.exit(1);
            }
        }
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
    private boolean addVisit(Prisoner prisoner, String name, Date date, String reason) {
        String id = prisoner.getId();
        if (!prisoners.containsKey(id)) {
            System.err.println("The prisoner with id " + id + " was not found");
            return false;
        }

        Visit visit = new Visit(prisoner, name, date, reason);
        visits.add(visit);
        prisoner.addVisit(visit);

        return true;
    }

    /**
     * Deletes a visit from a prisoner
     *
     * @param visit the visit to delete
     * @return true if the visit was deleted, false otherwise
     */
    private boolean deleteVisit(Visit visit) {
        Prisoner prisoner = visit.visitedPrisoner();
        String id = prisoner.getId();

        if (!prisoners.containsKey(id)) {
            System.err.println("The prisoner with id " + id + " was not found");
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
    private void updateVisits(Date currentDate) {
        for (Visit visit : visits) {
            // TODO: add create report signal to updateVisits
            if (visit.date().before(currentDate)) {
                deleteVisit(visit);
            }
        }
    }

    /**
     * Adds a new prisoner to the system
     *
     * @param id the id of the prisoner
     * @return true if the prisoner was added, false otherwise
     */
    private boolean addPrisoner(String id) {
        if (prisoners.containsKey(id)) {
            return false;
        }

        // TODO: Add find empty section signal to here
        Section homeSection = null;
        prisoners.put(id, new Prisoner(id, homeSection));
        return true;
    }

    /**
     * Updates the home section of a prisoner
     *
     * @param id          the id of the prisoner
     * @param homeSection the new home section of the prisoner
     * @return true if the update was successful, false otherwise
     */
    private boolean updatePrisonerData(String id, Section homeSection) {
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
    private boolean deletePrisoner(String id) {
        if (!prisoners.containsKey(id)) {
            System.err.println("The prisoner with id " + id + " was not found");
            return false;
        }

        prisoners.remove(id);
        return true;
    }
}
