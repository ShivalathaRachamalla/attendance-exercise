/* This class displays User options
and deligates requests to JSONProcessor class
 */

import java.time.LocalDate;
import java.util.Scanner;

public class UserInterface {
    static Scanner sc = new Scanner(System.in);
    void displayOptions() {

            while(true) {
                try {

                    this.printOptions();
                    int option = readInputInt();

                    switch (option) {
                        case 1:
                            System.out.println("Enter the path of the file");
                            String path = this.readInputString();
                            JsonProcessor.getInstance().readJson(path);
                            System.out.println("Members file is successfully loaded !!");
                            break;
                        case 2:
                            this.addMember();
                            System.out.println("Member added successfully !!");
                            break;
                        case 3:
                            this.removeMember();
                            System.out.println("Member removed successfully");
                            break;
                        case 4:
                            this.enterAttendance();
                            System.out.println("Attendance updated successfully");
                            break;
                        case 5:
                            this.listMembers();
                            break;
                        case 6:
                            this.loadIntoFile();
                            System.out.println("Loaded all members into file");
                            break;
                        case 7:
                            System.out.println("Exiting , Welcome back !!");
                            System.exit(0);
                        default:
                            System.out.println("Please enter correct input");
                            break;
                    }


                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
    }
    /*
    * Displaying options to the user
    * */
    void printOptions() {
        System.out.println("[1] Load the Members from file");
        System.out.println("[2] Add Member");
        System.out.println("[3] Remove Member");
        System.out.println("[4] Give Attendance");
        System.out.println("[5] List All Members");
        System.out.println("[6] Load members into file");
        System.out.println("[7] Exit");
        System.out.println("Please enter the option");
    }

    /*
     *  Read input from user as integer
     */
    int readInputInt() {
        int n = 0;
        try {
            n = Integer.parseInt(sc.next());
        } catch(Exception e) {
            System.out.print(e.toString());
            throw e;
        }
        return n;
    }

    /*
     * Read input from user as string
     */
    String readInputString() throws Exception{
        String path = null;
        Exception e1 = null;
        try {
            sc.nextLine(); // Consuming the \n character
            path = sc.nextLine();
            Integer.parseInt(path);
        } catch(Exception e) {
            e1 = e;
        } finally {
            if (e1 == null) {
                throw e1;
            }
        }
        return path;
    }


    /*
     * Adding member to the Attendance list
     */
    void addMember() throws Exception{
        System.out.println("Enter the member name");
        String name = this.readInputString();
        System.out.println("Enter member id");
        String id = this.readInputString();

        if (JsonProcessor.getInstance().isIdExist(id)) {
            System.out.println("Warning ! : The given member id already exists");
        } else {
            JsonProcessor.getInstance().addRecord(name, id);
        }
    }

    /*
     * Remove member from Attendance list
     */
    void removeMember() throws Exception {
        System.out.println("Enter member id to remove");
        String id = this.readInputString();
        if (!JsonProcessor.getInstance().isIdExist(id)) {
            System.out.println("Warning ! : The given member id does not exists");
        } else {
             JsonProcessor.getInstance().removeRecord(id);
        }
    }

    /*
     * Giving attendance to the member
     */
    void enterAttendance() throws Exception {
        System.out.println("Enter the member id");
        String id = this.readInputString();

        if (!JsonProcessor.getInstance().isIdExist(id)) {
            System.out.println("Warning ! : The given member id does not exists");
        } else {
            System.out.println("Enter the date in yyyy-MM-dd format");
            String date = this.readInputString();
            System.out.println("Enter option");
            System.out.println("[1] PRESENT");
            System.out.println("[2] ABSENT");
            int option = this.readInputInt();
            boolean isPresent;
            if (option == 1 ) {
                isPresent = true;
            } else if(option == 2) {
                isPresent = false;
            } else {
                 System.out.println("Option should be [1] PRESENT or 2] ABSENT ");
                 throw new Exception();
            }
            LocalDate localDate = LocalDate.parse(date);
            JsonProcessor.getInstance().markAttendance(id, localDate, isPresent);

        }

    }

    /*
     * List all members in attendance system
     */
    void listMembers() {
        JsonProcessor.getInstance().listMembers();
    }


    /*
      Writing members list into Json file 
     */
    void loadIntoFile() throws Exception{
        JsonProcessor.getInstance().loadIntoFile();
    }
}
