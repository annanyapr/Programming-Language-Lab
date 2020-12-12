// TODO make the first calculator
// TODO Add scroll to text area


package Q3_b;

import javax.swing.*;


// In Part b we have used two thread which run concurrently and highlight both number and functions
// Main Class contains main function
public class Main {


    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        // Style setting of the window
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
        // Create the JFrame using guiInterface
        JFrame frame = new JFrame("Calculator");
        guiInterface calculator = new guiInterface();
        frame.setContentPane(calculator.rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 350);
        frame.setResizable(false);
        frame.setVisible(true);

        // run the function highlight and number highlight threads
        numberHighlight number_highlights = new numberHighlight(calculator.getNumbers());
        functionHighlight function_highlight = new functionHighlight(calculator.getFunctions());

        number_highlights.start();
        function_highlight.start();
;
    }


}
