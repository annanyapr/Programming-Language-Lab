package Q1;

import java.util.concurrent.ConcurrentLinkedQueue;


// This thread implements the matching robot
public class matchingMachine extends Thread{

// these are the queues which will store the matched pairs of socks. These are shared with shelfmanager
    private ConcurrentLinkedQueue<sock> shelf_manager_white;
    private ConcurrentLinkedQueue<sock> shelf_manager_black;
    private ConcurrentLinkedQueue<sock> shelf_manager_blue;
    private ConcurrentLinkedQueue<sock> shelf_manager_grey;

// these variables are helpful in implementing our matching algorithm. The are null if all the respective coloured socks have been matched
// else they have the sock object which hasn't been matched and is waiting for a similar coloured sock to appear in matching buffer.
    private sock white_sock;
    private sock black_sock;
    private sock blue_sock;
    private sock grey_sock;

    private int robot_arm_done = 0;  // tells the matching machine that all the robots have completed their work


    // buffer in which multiple robot arms will put the socks in and from which the matching manager will take the socks out.
    // Shared with the robot arms
    private ConcurrentLinkedQueue<sock> manager_buffer;


    public ConcurrentLinkedQueue<sock> getManager_buffer() {
        return manager_buffer;
    }

    // constructor
    public matchingMachine(ConcurrentLinkedQueue<sock> shelf_manager_white, ConcurrentLinkedQueue<sock> shelf_manager_black, ConcurrentLinkedQueue<sock> shelf_manager_blue, ConcurrentLinkedQueue<sock> shelf_manager_grey){
        this.manager_buffer = new ConcurrentLinkedQueue<>();

        this.shelf_manager_white = shelf_manager_white;
        this.shelf_manager_black = shelf_manager_black;
        this.shelf_manager_blue = shelf_manager_blue;
        this.shelf_manager_grey = shelf_manager_grey;

        this.white_sock = null;
        this.black_sock = null;
        this.blue_sock = null;
        this.grey_sock = null;

    }

    private void print_matched_pair(sock first, sock second){  // prints whenever matching robot finds a pair
        synchronized(System.out){
            System.out.println(String.format("Matching Manager found a pair of %S colour with IDs %d and %d ", first.getColour(), first.getId(), second.getId()));
            System.out.flush();
        }

    }

    public void setRobot_arm_done(){
        robot_arm_done = 1;
    }



    @Override
    public void run() {  // runs the matching robot
        while(true){
            // remove socks from the buffer
            sock a = manager_buffer.poll();

            if (robot_arm_done == 1){  // if robots have completed then break
                if(a == null){
                    break;
                }
            }


            if(a != null){  // try matching if not null
                if(a.getColour() == "White"){
                    if(this.white_sock != null){
                        shelf_manager_white.add(this.white_sock);
                        shelf_manager_white.add(a);
                        print_matched_pair(this.white_sock, a);
                        this.white_sock = null;
                    }
                    else{
                        this.white_sock = a;
                    }
                }

                if(a.getColour() == "Black"){
                    if(this.black_sock != null){
                        shelf_manager_black.add(this.black_sock);
                        shelf_manager_black.add(a);
                        print_matched_pair(this.black_sock, a);
                        this.black_sock = null;
                    }
                    else{
                        this.black_sock = a;
                    }
                }

                if(a.getColour() == "Blue"){
                    if(this.blue_sock != null){
                        shelf_manager_blue.add(this.blue_sock);
                        shelf_manager_blue.add(a);
                        print_matched_pair(this.blue_sock, a);
                        this.blue_sock = null;
                    }
                    else{
                        this.blue_sock = a;
                    }
                }

                if(a.getColour() == "Grey"){
                    if(this.grey_sock != null){
                        shelf_manager_grey.add(this.grey_sock);
                        shelf_manager_grey.add(a);
                        print_matched_pair(this.grey_sock, a);
                        this.grey_sock = null;
                    }
                    else{
                        this.grey_sock = a;
                    }
                }
            }



        }
    }
}
