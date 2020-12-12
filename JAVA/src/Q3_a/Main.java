// TODO make the first calculator
// TODO Add scroll to text area


package Q3_a;

import javax.swing.*;

// In this part "a" we have used two threads, function highlight and Number Highlight. These threads switch between each other when enter is pressed
// Main class contains main function and few static objects which are used in synchronization
public class Main {

    // tells which thread(number or function) is currently running
    public static int highlight;

    // objects to synchronise upon
    public static final Object NumPadLock = new Object();
    public static final Object FunctionPadLock = new Object();

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        highlight=0;

        // Set UI elements
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
        // Create JFrame and initialise it with gui interface
        JFrame frame = new JFrame("Calculator");
        guiInterface calculator = new guiInterface();
        frame.setContentPane(calculator.rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 350);
        frame.setResizable(false);
        frame.setVisible(true);

        // now start the two threads(NUmber highlight and function highlight)
        numberHighlight number_highlights = new numberHighlight(calculator.getNumbers());
        functionHighlight function_highlight = new functionHighlight(calculator.getFunctions());

        number_highlights.start();
        function_highlight.start();

    }


}
