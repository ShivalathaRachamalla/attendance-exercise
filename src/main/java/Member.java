/*
 * This class holds the details of the members
 */
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Member {
    String name;
    String id;
    HashMap<LocalDate, Boolean> attendance = new HashMap<LocalDate, Boolean>();

    /*
     * Constructor of the class
     */
    Member(String name, String id) {
        this.name = name;
        this.id = id;
    }

    /*
     * sets the attendance for member on given date
     */
    void setAttendance(LocalDate localDate, Boolean b) {
        this.attendance.put(localDate, b);
    }

    /*
     * returns name
     */
    String getName() {
        return this.name;
    }

    /*
     * returns id
     */
    String getId() {
        return this.id;
    }

    public String toString() {
        String result = null;
        result = "-------  "+this.id+"  ---------\n";
        result = result + "id: "+this.id +"\n";
        result = result + "name: "+this.name +"\n";
        result = result + "Attendance: \n";

        Iterator itr = attendance.entrySet().iterator();
        while(itr.hasNext()) {
            Map.Entry me = (Map.Entry) itr.next();
            Boolean b = (Boolean)me.getValue();
            String b1 = (b.booleanValue()) ? "PRESENT": "ABSENT";
            result = result + me.getKey().toString() +"      "+ b1 + "\n";
        }
        return result;
    }
}
