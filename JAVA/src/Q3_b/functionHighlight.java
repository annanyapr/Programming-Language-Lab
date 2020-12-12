package Q3_b;

import javax.swing.*;
import java.awt.*;


// This thread is responsible for lighting the function pad
public class functionHighlight extends Thread{

    private JButton[] function_keys;

    public functionHighlight(JButton[] function_keys){
        this.function_keys = function_keys;
    }



    @Override
    public void run() {
        int number = 0;
        while(true){
            number++;
            number = number % 6;
            function_keys[number].setBackground(new Color(255, 212, 255));   // change colour

            try {   // wait for 2 seconds to jump to next function
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // now set it back to the original
            function_keys[number].setBackground(new Color(137, 166, 203));
        }
    }
}
