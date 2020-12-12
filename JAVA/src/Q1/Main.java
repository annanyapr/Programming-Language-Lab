package Q1;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

// Contains the main function. All threads are initiated here and executed here. It also contains list of socks(&their locks) which is shared with robot arms
public class Main {
    final private static String INPUT_FILE = "./resource/Q1/input.txt";  // input file

    private List<pair> socks; // list of socks(with is_taken) for each robot
    private List<ReentrantLock> locks;  // list of locks for each sock

    private int number_of_robots ;

    private int number_of_white ;

    private int number_of_black ;

    private int number_of_blue ;

    private int number_of_grey ;

    private List<robotArm> robot_arms;   // robot arms

    private shelfManager shelf;    // shelf
    private matchingMachine matching_robot;   // matching robot


    private void run_machine() throws InterruptedException {  // start execution of all robot_arms, matching manager and shelves
        matching_robot.start();  // start execution of matching robot
        for(robotArm robot: robot_arms){  // start execution of all robots
            robot.start();
        }



        for(robotArm robot: robot_arms){  // robot arms have completed their work
            robot.join();
        }

        matching_robot.setRobot_arm_done();
        matching_robot.join();    // matching manager has completed its work
        shelf.print_shelves();  // print all the shelves
    }



    private void initialiseSocksAndLocks(List<Integer>input_socks){  // initialises socks list and their corresponding locks list.
        socks = new ArrayList<>();
        locks = new ArrayList<>();


        for (int id = 0; id < input_socks.size(); id++){   // All socks are given an id between 0 and number_of_socks-1
            int input_sock = input_socks.get(id);
            if(input_sock == 1){
                socks.add(new pair(new sock("White", id) , 0));
                locks.add(new ReentrantLock());
            }
            if(input_sock == 2){
                socks.add(new pair(new sock("Black", id) , 0));
                locks.add(new ReentrantLock());
            }
            if(input_sock == 3){
                socks.add(new pair(new sock("Blue", id) , 0));
                locks.add(new ReentrantLock());
            }
            if(input_sock == 4){
                socks.add(new pair(new sock("Grey", id) , 0));
                locks.add(new ReentrantLock());
            }
        }
    }

    private void initialiseRobotArms(int number_of_robots, matchingMachine matching_robot){  // initialises robot arms. Creates thread object for each robot arm
        robot_arms = new ArrayList<>();
        robotArm.setSignal(socks.size());
        for(int i = 0; i < number_of_robots; i++){
            robot_arms.add(new robotArm(matching_robot.getManager_buffer(), i, socks, locks));
        }
    }


    // Constructor of Main class
    public Main(int number_of_robots, int number_of_white, int number_of_black, int number_of_blue, int number_of_grey, List<Integer>input_socks){
        this.number_of_black = number_of_black;
        this.number_of_blue = number_of_blue;
        this.number_of_grey = number_of_grey;
        this.number_of_white = number_of_white;
        this.number_of_robots = number_of_robots;
        initialiseSocksAndLocks(input_socks);  // initialise socks list and locks list from input

        shelf = new shelfManager();   // makes shelf
        matching_robot = new matchingMachine(shelf.getShelf_white(), shelf.getShelf_black(), shelf.getShelf_blue(), shelf.getShelf_grey());// initialises matching robot
        initialiseRobotArms(number_of_robots, matching_robot);// intialises robot arms

    }


// Main function
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Hi Welcome to the Sock Matcher. ");
        System.out.println("Reading input from " + INPUT_FILE);

        File input_file = new File(INPUT_FILE);  // reading from input file


        int number_of_white = 0;
        int number_of_black = 0;
        int number_of_blue = 0;
        int number_of_grey = 0;
        int number_of_robots = 0;

        List<Integer> input_socks = new ArrayList<>();  // will store input information regarding socks
        Scanner input_reader = null;
        try {                                                     // scanning input file
            input_reader = new Scanner(input_file);
            number_of_robots = input_reader.nextInt();
            int id=0;
            while(input_reader.hasNextInt()){
                int input_sock = input_reader.nextInt();
                if(input_sock < 1  || input_sock > 4){
                    // skip input
                    System.out.println("Invalid sock colour detected: " + String.valueOf(input_sock) +" Skipped this sock");
                }
                else{
                    if(input_sock == 1){
                        number_of_white += 1;
                    }
                    if(input_sock == 2){
                        number_of_black += 1;
                    }
                    if(input_sock == 3){
                        number_of_blue += 1;
                    }
                    if(input_sock == 4){
                        number_of_grey += 1;
                    }
                    input_socks.add(input_sock);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        if ((number_of_robots < 1)){
            System.out.println("No robots");
            return;
        }
        System.out.println("");

        Main main = new Main(number_of_robots, number_of_white, number_of_black, number_of_blue, number_of_grey, input_socks);  // cosntruct object of Main class

        main.run_machine();  // Start execution of everything

    }

}
