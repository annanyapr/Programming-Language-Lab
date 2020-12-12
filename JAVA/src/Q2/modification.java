package Q2;

import java.io.*;
import java.util.*;


// Modification class hosts our record data. It has all the read/write functions which deals with to read/write to files. It has functions that lead to update of records
public class modification {
    // strings to file locations
    private static final String stud_info = "./resource/Q2/stud_info.txt";
    private static final String stud_info_roll_number_sorted = "./resource/Q2/stud_info_roll.txt";
    private static final String stud_info_name_sorted = "./resource/Q2/stud_info_name.txt";
    private final Scanner input_reader;

    private Map<String, studentRecord> records;   // Maps rollnumber to the corresponding student record. Acts as our database. We perform all our operations on this
    private List<update> list_of_updates;   // contains all the newly scheduled updates

    // Constructor
    public modification() {
        records = new HashMap<String, studentRecord>();
        input_reader = new Scanner(System.in);
        list_of_updates = new ArrayList<update>();
    }

    // Reads stud_info file.
    public void read_file() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(stud_info));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                studentRecord student = new studentRecord(data[0], data[1], data[2], Integer.parseInt(data[3].trim()), data[4]);
                records.put(student.getRoll_no(), student);
            }

        } catch (IOException e) {

            System.out.println("Couldn't read from file!! " + e.toString());

        }
    }


    // schedules update
    public void schedule_update() {

        update upd = new update();
        System.out.println("Enter Teacher's Code: ");   // enter the code of teacher making the update
        String code_of_teacher = input_reader.next();

        if (code_of_teacher.equals(teacher.CC) || code_of_teacher.equals(teacher.TA1) || code_of_teacher.equals(teacher.TA2)) { // correct teacher code
            upd.setTeacher_code(code_of_teacher);
        }
        else {
            System.out.println("Invalid teacher name. Couldn't schedule Update. Try Again!!");   // wrong teacher code
            return;
        }

        System.out.println("Enter Roll Number : ");  // Enter the roll number to be updated
        upd.setRoll_no(input_reader.next());
        System.out.println("Chose how to update Marks \n" + "Type 1 to  Increase marks \n" + "Type 2 to  Decrease marks ");  // Enter how much marks do you want to change

        int choice = input_reader.nextInt();

        if (choice == 1) {
            System.out.println("Increase the marks by: ");
            int change = input_reader.nextInt();
            upd.setChange_in_marks(change);
        } else if (choice == 2) {
            System.out.println("Decrease the marks by : ");
            int change = input_reader.nextInt();
            upd.setChange_in_marks(-1 * change);
        } else {
            System.out.println("Invalid Option given. Couldn't schedule Update. Try Again!!");
            return;
        }

        list_of_updates.add(upd);
        System.out.println("Update Scheduled Successfully!");
    }

    public void execute_updates() {  // executes updates
        // Make threads for teachers
        teacher CC = new teacher(this, teacher.CC);
        teacher TA1 = new teacher(this, teacher.TA1);
        teacher TA2 = new teacher(this, teacher.TA2);

        for (Q2.update update : list_of_updates) {   // sort all the updates according to their respective teacher code and pass these updates to their teacher thread
            switch (update.getTeacher_code()) {
                case teacher.TA1:
                    TA1.addUpdate(update);
                    break;
                case teacher.TA2:
                    TA2.addUpdate(update);
                    break;
                case teacher.CC:
                    CC.addUpdate(update);
                    break;
            }
        }
        list_of_updates.clear();

        try {    // Start the threads
            CC.start();
            CC.join();  // First execute CC thread(highest priority)
            TA1.start();// Than execute TA1 and TA2
            TA2.start();
            TA1.join();
            TA2.join();
        } catch (InterruptedException e) {
            System.out.println("Execution interrupted !!" + e.toString());
        }
        write_to_file();
    }

    public void update_record(update update) throws InterruptedException {  // Update records given an update. This is called by a thread
        String rollNo = update.getRoll_no();

        if (records.get(rollNo) == null) {  // if update is invalid
            System.out.println("The student with Roll No " + rollNo + " doesn't exist.+" +
                    " Data for the student not updated!");
        }
        else {
            records.get(rollNo).acquire();   // acquire semaphore for a record

            if (records.get(rollNo).getTeacher_code().equals(teacher.CC) && !update.getTeacher_code().equals(teacher.CC)) {  // if update's teacher doesn't have the permission to make the update
                System.out.println(update.getTeacher_code() + " tried to update marks Roll No " + rollNo +
                        ", but lacks permission! Data for the student not updated!");

            } else {  // successful update
                Integer marks = records.get(rollNo).getMarks() + update.getChange_in_marks();
                records.get(rollNo).setMarks(marks);
                records.get(rollNo).setTeacher_code(update.getTeacher_code());
                System.out.println("Roll No " + rollNo + " successfully updated by " + update.getTeacher_code() +
                        " with a change in marks of " + update.getChange_in_marks() + " marks.");
            }
            records.get(rollNo).release();  // semaphore release
        }
    }

    private void write_to_file() {  // After all the updates are done, write them to file
        try (BufferedWriter originalWriter = new BufferedWriter(new FileWriter(stud_info))) {  // write to stud_info with updated records

            for (studentRecord student : records.values()) {
                originalWriter.append(student.fileString());
                originalWriter.append('\n');
            }
        } catch (IOException e) {
            System.out.println("Error in writing to Original File !!" + e.toString());
        }

        try (BufferedWriter rollSortedWriter = new BufferedWriter(new FileWriter(stud_info_roll_number_sorted))) {  // write to stud_info_roll with sorted roll numbers

            List<String> sortedKeys = new ArrayList<String>(records.keySet());
            Collections.sort(sortedKeys);

            for (String key : sortedKeys) {
                rollSortedWriter.append(records.get(key).fileString());
                rollSortedWriter.append('\n');
            }

        } catch (IOException e) {
            System.out.println("Error in writing to Roll Sorted File !!" + e.toString());
        }

        try (BufferedWriter nameSortedWriter = new BufferedWriter(new FileWriter(stud_info_name_sorted))) {   // write to stud_info_name with sorted names
            List<studentRecord> sortedNames = new ArrayList<studentRecord>(records.values());
            sortedNames.sort(Comparator.comparing(studentRecord::getName));

            for (studentRecord student : sortedNames) {
                nameSortedWriter.append(student.fileString());
                nameSortedWriter.append('\n');
            }

        } catch (IOException e) {
            System.out.println("Error in writing to Name Sorted File !!" + e.toString());
        }
    }

}
