package Q2;


// Holds information regarding a single scheduled update
public class update {
    private String roll_no;  // roll number to be updated
    private Integer change_in_marks;  // change in marks
    private String teacher_code;  // teacher who is making the update


    // Constructors
    public update(String roll_no, Integer change_in_marks, String teacher_code) {
        this.roll_no = roll_no;
        this.change_in_marks = change_in_marks;
        this.teacher_code = teacher_code;
    }

    public update() {
    }


    // Getters and Setters
    public String getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(String roll_no) {
        this.roll_no = roll_no;
    }

    public Integer getChange_in_marks() {
        return change_in_marks;
    }

    public void setChange_in_marks(Integer change_in_marks) {
        this.change_in_marks = change_in_marks;
    }

    public String getTeacher_code() {
        return teacher_code;
    }

    public void setTeacher_code(String teacher_code) {
        this.teacher_code = teacher_code;
    }
}
