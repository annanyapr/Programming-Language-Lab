package Q2;

import java.util.Scanner;


// Main function, takes action to be performed from user
public class Main {
    private final static Scanner input_reader = new Scanner(System.in);

    public static void main(String[] args) {
        // create an object of modification class
        modification modif = new modification();
        modif.read_file();   // read stud_info
        boolean quit = false;
        while (!quit) {
            int choice;
            System.out.println("Choose an operation to perform: \n" + "Type 1 to Schedule an Update\n" +  "Type 2 to Execute all scheduled updates\n" + "Type 3 to Exit");
            choice = input_reader.nextInt();   // choice of the user
            switch (choice) {
                case 1:
                    modif.schedule_update();  // schedule update
                    break;
                case 2:
                    modif.execute_updates();  // execute an update
                    break;
                case 3:
                    quit=true;
                    break;
                default:
                    System.out.println("Invalid Option!");
                    break;
            }
        }


    }

}
