package managers;

import core.SystemRoot;
import types.Signal;
import types.Staff;

import java.util.ArrayList;

public class StaffManager extends BaseManager {
    ArrayList<Staff> staffs = new ArrayList<>();
    // TODO: Add a database or file portal to store staff

    public StaffManager(SystemRoot root) {
        super(root);
    }

    public boolean addStaff(Staff s)
    {
        if(staffs.contains(s))
        {
            return false;
        }
        staffs.add(s);
        //todo add report to signal buffer
        return true;
    }
    public boolean updateStaffData(Staff s)
    {
        if(!staffs.contains(s))
        {
            return false;
        }
        staffs.remove(s);
        staffs.add(s);
        //todo add report to signal buffer
        return true;
    }
    public boolean deleteStaff(Staff s)
    {
        if(!staffs.contains(s))
        {
            return false;
        }
        staffs.remove(s);
        //todo add report to signal buffer
        return true;
    }

}
