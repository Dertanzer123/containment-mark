package core;

import managers.PrisonerManager;
import managers.ReportManager;
import managers.SectionManager;
import managers.StaffManager;

/// SystemRoot bridges the managers.
/// For example, one manager emits a signal with a destination root sends this signal to destination manager the destination manager absorbs and do what it need to do
public class SystemRoot {
    private final ReportManager reportManager;
    private final SectionManager sectionManager;
    private final PrisonerManager prisonerManager;
    private final StaffManager staffManager;

    public SystemRoot() {
        this.reportManager = new ReportManager(this);
        this.sectionManager = new SectionManager(this);
        this.prisonerManager = new PrisonerManager(this);
        this.staffManager = new StaffManager(this);
    }
}
