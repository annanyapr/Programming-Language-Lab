package Q2;

import java.util.concurrent.Semaphore;


// stores information for each row provided in the stud_info.txt. Each row in file is stored in the form of studentRecord object in the Hash Map
public class studentRecord {
    private String roll_no;  // roll number of record
    private String name;  // name in record
    private String mail_id; // mail_id in record
    private Integer marks;  // marks in record
    private String teacher_code;  // teacher who last updated that record
    private Semaphore lock ;   // semaphore to modify this record

    //constructors
    public studentRecord(String roll_no, String name, String mail_id, Integer marks, String teacher_code) {
        this.roll_no = roll_no;
        this.name = name;
        this.mail_id = mail_id;
        this.marks = marks;
        this.teacher_code = teacher_code;
        this.lock = new Semaphore(1);
    }
    public studentRecord() {
        this.lock = new Semaphore(1);
    }

    // acquire semaphore
    public void acquire() throws InterruptedException {
        lock.acquire();
    }
    // release semaphore
    public void release() {
        lock.release();
    }


    // getters and setters
    public String getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(String roll_no) {
        this.roll_no = roll_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail_id() {
        return mail_id;
    }

    public void setMail_id(String mail_id) {
        this.mail_id = mail_id;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public String getTeacher_code() {
        return teacher_code;
    }

    public void setTeacher_code(String teacher_code) {
        this.teacher_code = teacher_code;
    }

    public String fileString() {
        return roll_no + "," + name + "," + mail_id + "," + marks.toString() + "," + teacher_code;
    }
}
