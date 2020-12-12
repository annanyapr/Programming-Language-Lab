package Q1;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


// implements robot arm thread
public class robotArm extends Thread {

    // manager buffer shared with matching robot. Arms will put socks into this and matching robot will remove socks from this
    private ConcurrentLinkedQueue<sock> manager_buffer;

    // list of socks and locks shared among all the arm robots
    private List<pair> socks;
    private List<ReentrantLock> locks;

    // id of robot arm
    private int robot_arm_id;
    // number of socks
    private int size_of_heap;

    private static AtomicInteger signal; // atomic integer signal which tells the robot arms that they have exhausted the list of socks. It is shared among all the arms

    // constructor
    public robotArm(ConcurrentLinkedQueue<sock> manager_buffer, int robot_arm_id, List<pair> socks, List<ReentrantLock> locks) {
        super();
        this.manager_buffer = manager_buffer;
        this.robot_arm_id = robot_arm_id;
        this.socks = socks;
        this.locks = locks;
        this.size_of_heap = socks.size();  // number of socks
    }

    public static void setSignal(int signal) {
        robotArm.signal = new AtomicInteger(signal);
    }  // setting signal. This is set to the number of socks initially

    public void print_sock_picked(sock picked){  // when robot arm picks sock, print
        synchronized (System.out){
            System.out.println(String.format("Robot Arm Number: %d picked the sock number: %d of colour: %s", robot_arm_id, picked.getId(), picked.getColour()));
            System.out.flush();
        }
    }



    @Override
    public void run(){

        while(true){
            if (signal.get() == 0){  // all socks have been removed, terminate thread
                break;
            }
            int i = ThreadLocalRandom.current().nextInt(0, this.size_of_heap);  // select a sock to take out from the heap of socks
            ReentrantLock temp_lock = locks.get(i);  // try getting lock of the sock
            boolean successful = temp_lock.tryLock();

            if(successful == false){

                continue;
            }
            else{
                // now locked by the thread
                if(socks.get(i).getIs_taken() == 0){   // no one has taken the sock yet
                    // pass the sock to the matching robot
                    sock to_give = socks.get(i).getSock();
                    print_sock_picked(to_give);
                    manager_buffer.add(to_give);

                    // Print the sock picked by the robot arm
                    socks.get(i).setIs_taken(1);
                    signal.decrementAndGet();
                }
                else{
                    // sock already taken by someone else
                }

                temp_lock.unlock();
            }
        }

    }





}
