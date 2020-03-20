/*
* This class reads the Json file and manages the Member class objects
*/
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonProcessor {
    private HashMap<String, Member> members = new HashMap<>();
    private static JsonProcessor jsonProcessor = null;

    /*
     * Returns singleton instance of Json processor class
     */
    static JsonProcessor getInstance() {
        if (jsonProcessor == null) {
            jsonProcessor = new JsonProcessor();
        }
        return jsonProcessor;
    }

    /*
     * Reads the json file and loads to attendance system
     */
    public void readJson(String memberInfoFilePath) {
        System.out.println("The file path is : "+memberInfoFilePath);

        // Read json file and create Object
        try {
            JSONParser jsonParser = new JSONParser();
            FileReader fileReader = new FileReader(memberInfoFilePath);
            JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);
            JSONArray members = (JSONArray) jsonObject.get("members");

            for (Object o : members) {
                JSONObject jo = (JSONObject) o;
                this.members.put(jo.get("id").toString(), new Member(jo.get("name").toString(), jo.get("id").toString()));
            }

        } catch (Exception e) {

        }
    }

    /*
     * returns boolean value for memberId existence
     */
    boolean isIdExist(String id) {
        return this.members.containsKey(id);
    }

    /*
     * add new member to Attendance system
     */
    void addRecord(String name, String id) {
        this.members.put(id, new Member(name, id));
    }

    /*
     * remove a member from attendance system
     */
    void removeRecord(String id) {
        this.members.remove(id);
    }

    /*
     * give attendance to member on given date
     */
    void markAttendance(String id, LocalDate localDate, boolean isPresent) {
        this.members.get(id).setAttendance(localDate, Boolean.valueOf(isPresent));
    }

    /*
     * Prints the list of members available in attendance system
     */
    void listMembers() {
        Iterator itr = members.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry me = (Map.Entry) itr.next();
            System.out.println(me.getValue());
        }
    }

    /*
     * Saving member data into the file
     */
    void loadIntoFile() throws Exception{
        JSONArray ja = new JSONArray();
        Iterator itr = members.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry me = (Map.Entry) itr.next();
            Member m = (Member) me.getValue();
            JSONObject jo = new JSONObject();
            jo.put("name", m.getName());
            jo.put("id", m.getId());
            ja.add(jo);
        }
        JSONObject jo1 = new JSONObject();
        jo1.put("members", ja);

        // Writing into file
        FileWriter fw = new FileWriter("my-members-list.json");
        fw.write(jo1.toJSONString());
        fw.flush();
        fw.close();
    }

}
