package Q2;

import java.util.ArrayList;
import java.util.List;


// Teacher class implements the thread which makes update to records
public class teacher extends Thread {

    // constant string names
    public static final String CC = "CC";
    public static final String TA1 = "TA1";
    public static final String TA2 = "TA2";

    private modification modification;  // modification object, used to access update_record function of modification class
    private List<update> updates;   // list of all updates to be made
    private String type;   // type of teacher(CC or TA1 or TA2)

    // Constructor
    public teacher(modification modification, String type) {
        this.modification = modification;
        this.updates = new ArrayList<update>();
        this.type = type;
        setName(type);
    }

    @Override
    public void run() {  // run the thread
        try {
            for (Q2.update update : updates) {  // for all updates update records
                modification.update_record(update);
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + "'s execution interrupted !!" + e.toString());
        }
        updates.clear();

    }

    public void addUpdate(update update) {
        updates.add(update);
    }  // fills updates list for thread

}
