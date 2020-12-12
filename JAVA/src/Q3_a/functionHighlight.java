package Q3_a;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.locks.ReentrantLock;
// This thread highlights the function pad
public class functionHighlight extends Thread{

    private JButton[] function_keys;



    public functionHighlight(JButton[] function_keys){
        this.function_keys = function_keys;
    }


    @Override
    public void run() {
        int number = 0;


        while(true){
            if(Main.highlight == 0) // If number highlight is working, wait for it to notify
            {
                synchronized (Main.FunctionPadLock)
                {
                    try {
                        Main.FunctionPadLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            number++;
            number = number % 6;
            function_keys[number].setBackground(new Color(255, 212, 255));  // change colour

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            function_keys[number].setBackground(new Color(137, 166, 203));  // original colour

        }
    }
}
