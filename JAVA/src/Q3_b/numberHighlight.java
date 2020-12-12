package Q3_b;

import javax.swing.*;
import java.awt.*;

// This thread is responsible for lighting the number pad
public class numberHighlight extends Thread{

    private JButton[] number_keys;

    public numberHighlight(JButton[] number_keys){
        this.number_keys = number_keys;
    }

    @Override
    public void run() {
        int number =0;
        while(true){
            number++;
            number  = number % 10;

            number_keys[number].setBackground(new Color(255, 212, 255));   //change colour

            try { // wait for 2 seconds to jump to next function
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            number_keys[number].setBackground(new Color(137, 166, 203));   // original colour

        }

    }
}
