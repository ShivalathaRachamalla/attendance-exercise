/* This is the main class of Attendance system */

public class Attendance {

    static String memberInfoFilePath = "src/main/resources/members-list.json";

    public  static void main(String args[]) {
        System.out.println("Welcome to Attendance system");
        UserInterface ui = new UserInterface();
        ui.displayOptions();
    }
}
