package managers;

import core.SystemRoot;
import types.Staff;

import java.util.ArrayList;

public class StaffManager extends BaseManager {
    ArrayList<Staff> staffs = new ArrayList<>();
    // TODO: Add a database or file portal to store staff

    public StaffManager(SystemRoot root) {
        super(root);
    }

    public boolean addStaff(Staff s) {
        if (staffs.contains(s)) {
            return false;
        }
        staffs.add(s);
        // TODO: Add report to signal buffer
        return true;
    }

    public boolean updateStaffData(Staff s) {
        if (!staffs.contains(s)) {
            return false;
        }
        staffs.remove(s);
        staffs.add(s);
        // TODO: Add report to signal buffer
        return true;
    }

    public boolean deleteStaff(Staff s) {
        if (!staffs.contains(s)) {
            return false;
        }
        staffs.remove(s);
        // TODO: Add report to signal buffer
        return true;
    }

}
