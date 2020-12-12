package Q3_a;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.locks.ReentrantLock;


// This thread highlights the number pad
public class numberHighlight extends Thread{

    private JButton[] number_keys;



    public numberHighlight(JButton[] number_keys){
        this.number_keys = number_keys;

    }

    @Override
    public void run() {
        int number =0;
        while(true){

            if(Main.highlight == 1)   // If function highlight is working, wait for it to notify
            {
                synchronized (Main.NumPadLock)
                {
                    try {
                        Main.NumPadLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }


            number++;
            number  = number % 10;

            number_keys[number].setBackground(new Color(255, 212, 255));  // Change colour

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            number_keys[number].setBackground(new Color(137, 166, 203));  // Return back to original

        }

    }
}
