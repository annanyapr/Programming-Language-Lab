package Q3_b;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class guiInterface {

    // GUI elements created by intellij swing designer

    private JButton a4;
    private JButton a7;
    private JButton a0;
    private JButton a2;
    private JButton a5;
    private JButton a8;
    private JButton subtractButton;
    private JButton a3;
    private JButton a6;
    private JButton a9;
    private JButton spaceButton;
    private JButton addButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton enterButton;
    private JTextArea displayArea;
    private JButton a1;
    private JButton calculateButton;
    private JButton clearButton;
    public JPanel rootPanel;
    private JScrollPane scroll;
    private int flag = 0;

    private JButton[] numbers = new JButton[10];

    private JButton[] functions = new JButton[6];

    public JButton[] getFunctions() {  // this function sends function buttons to function highlight thread
        functions[0] = addButton;
        functions[1] = subtractButton;
        functions[2] = multiplyButton;
        functions[3] = divideButton;
        functions[4] = clearButton;
        functions[5] = calculateButton;
        return functions;
    }

    public JButton[] getNumbers() {// this function sends number buttons to number highlight thread
        numbers[0] = a0;
        numbers[1] = a1;
        numbers[2] = a2;
        numbers[3] = a3;
        numbers[4] = a4;
        numbers[5] = a5;
        numbers[6] = a6;
        numbers[7] = a7;
        numbers[8] = a8;
        numbers[9] = a9;
        return numbers;
    }


    public guiInterface() {


        displayArea.addKeyListener(new KeyAdapter() {  // create a listener for enter and space keys
            @Override
            public void keyPressed(KeyEvent e) {

                numbers[0] = a0;
                numbers[1] = a1;
                numbers[2] = a2;
                numbers[3] = a3;
                numbers[4] = a4;
                numbers[5] = a5;
                numbers[6] = a6;
                numbers[7] = a7;
                numbers[8] = a8;
                numbers[9] = a9;
                functions[0] = addButton;
                functions[1] = subtractButton;
                functions[2] = multiplyButton;
                functions[3] = divideButton;
                functions[4] = clearButton;
                functions[5] = calculateButton;


                int key = e.getKeyCode();
                if (flag == 0) {


                    if (key == KeyEvent.VK_ENTER) {     // if space was pressed and button highlighted was number


                        for (int i = 0; i < 10; i++) {
                            int red = numbers[i].getBackground().getRed();
                            int blue = numbers[i].getBackground().getBlue();
                            int green = numbers[i].getBackground().getGreen();
                            if (red == 255 && green == 212 && blue == 255) {
                                displayArea.append(String.valueOf(i));
                            }
                        }

                    }
                    if (key == KeyEvent.VK_SPACE) {   // if space was pressed and button highlighted was(+, -, *, /)


                        for (int i = 0; i < 4; i++) {
                            int red = functions[i].getBackground().getRed();
                            int blue = functions[i].getBackground().getBlue();
                            int green = functions[i].getBackground().getGreen();
                            if (red == 255 && green == 212 && blue == 255) {
                                displayArea.append(functions[i].getText());
                            }
                        }


                    }

                    if (key == KeyEvent.VK_SPACE) {  // if SPACE was pressed and button highlighted was CALCULATE


                        int calred = functions[5].getBackground().getRed();
                        int calblue = functions[5].getBackground().getBlue();
                        int calgreen = functions[5].getBackground().getGreen();

                        if (calred == 255 && calgreen == 212 && calblue == 255) {
                            if (flag == 0) {
                                String input = displayArea.getText();
                                if (input.length() == 0) {
                                    flag = 0;
                                } else {
                                    String ans = Q3_b.evaluate.eval(input);  // send the input to evaluate function
                                    displayArea.setText(ans);
                                    flag = 1;
                                }
                            }
                            displayArea.grabFocus();
                        }

                    }


                }

                if (key == KeyEvent.VK_SPACE) { // if SPACE was pressed and button highlighted was CLEAR

                    int clred = functions[4].getBackground().getRed();
                    int clblue = functions[4].getBackground().getBlue();
                    int clgreen = functions[4].getBackground().getGreen();

                    if (clred == 255 && clgreen == 212 && clblue == 255) {
                        displayArea.setText("");
                        flag = 0;

                        displayArea.grabFocus();
                    }
                }


                displayArea.grabFocus();


            }
        });


        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                displayArea.grabFocus();
            }
        };
        clearButton.addMouseListener(listener);
        calculateButton.addMouseListener(listener);

        a4.addMouseListener(listener);
        a7.addMouseListener(listener);
        a2.addMouseListener(listener);
        a5.addMouseListener(listener);
        a8.addMouseListener(listener);
        a3.addMouseListener(listener);
        a6.addMouseListener(listener);
        a9.addMouseListener(listener);
        a0.addMouseListener(listener);
        multiplyButton.addMouseListener(listener);
        addButton.addMouseListener(listener);
        subtractButton.addMouseListener(listener);
        divideButton.addMouseListener(listener);
        a1.addMouseListener(listener);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(8, 4, new Insets(0, 0, 0, 0), -1, -1));
        a4 = new JButton();
        a4.setBackground(new Color(-7756085));
        a4.setEnabled(true);
        Font a4Font = this.$$$getFont$$$("Fira Code", -1, 14, a4.getFont());
        if (a4Font != null) a4.setFont(a4Font);
        a4.setText("4");
        rootPanel.add(a4, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a7 = new JButton();
        a7.setBackground(new Color(-7756085));
        a7.setEnabled(true);
        Font a7Font = this.$$$getFont$$$("Fira Code", -1, 14, a7.getFont());
        if (a7Font != null) a7.setFont(a7Font);
        a7.setText("7");
        rootPanel.add(a7, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a2 = new JButton();
        a2.setBackground(new Color(-7756085));
        a2.setEnabled(true);
        Font a2Font = this.$$$getFont$$$("Fira Code", -1, 14, a2.getFont());
        if (a2Font != null) a2.setFont(a2Font);
        a2.setSelected(false);
        a2.setText("2");
        rootPanel.add(a2, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a5 = new JButton();
        a5.setBackground(new Color(-7756085));
        a5.setEnabled(true);
        Font a5Font = this.$$$getFont$$$("Fira Code", -1, 14, a5.getFont());
        if (a5Font != null) a5.setFont(a5Font);
        a5.setText("5");
        rootPanel.add(a5, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a8 = new JButton();
        a8.setBackground(new Color(-7756085));
        a8.setEnabled(true);
        Font a8Font = this.$$$getFont$$$("Fira Code", -1, 14, a8.getFont());
        if (a8Font != null) a8.setFont(a8Font);
        a8.setText("8");
        rootPanel.add(a8, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a3 = new JButton();
        a3.setBackground(new Color(-7756085));
        a3.setEnabled(true);
        Font a3Font = this.$$$getFont$$$("Fira Code", -1, 14, a3.getFont());
        if (a3Font != null) a3.setFont(a3Font);
        a3.setText("3");
        rootPanel.add(a3, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a6 = new JButton();
        a6.setBackground(new Color(-7756085));
        a6.setEnabled(true);
        Font a6Font = this.$$$getFont$$$("Fira Code", -1, 14, a6.getFont());
        if (a6Font != null) a6.setFont(a6Font);
        a6.setText("6");
        rootPanel.add(a6, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a9 = new JButton();
        a9.setBackground(new Color(-7756085));
        a9.setEnabled(true);
        Font a9Font = this.$$$getFont$$$("Fira Code", -1, 14, a9.getFont());
        if (a9Font != null) a9.setFont(a9Font);
        a9.setText("9");
        rootPanel.add(a9, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a0 = new JButton();
        a0.setBackground(new Color(-7756085));
        a0.setEnabled(true);
        Font a0Font = this.$$$getFont$$$("Fira Code", -1, 14, a0.getFont());
        if (a0Font != null) a0.setFont(a0Font);
        a0.setText("0");
        rootPanel.add(a0, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        multiplyButton = new JButton();
        multiplyButton.setBackground(new Color(-7756085));
        multiplyButton.setEnabled(true);
        multiplyButton.setText("*");
        rootPanel.add(multiplyButton, new com.intellij.uiDesigner.core.GridConstraints(5, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addButton = new JButton();
        addButton.setBackground(new Color(-7756085));
        addButton.setEnabled(true);
        addButton.setText("+");
        rootPanel.add(addButton, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        subtractButton = new JButton();
        subtractButton.setBackground(new Color(-7756085));
        subtractButton.setEnabled(true);
        subtractButton.setText("-");
        rootPanel.add(subtractButton, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        divideButton = new JButton();
        divideButton.setBackground(new Color(-7756085));
        divideButton.setEnabled(true);
        divideButton.setText("/");
        rootPanel.add(divideButton, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setLineWrap(true);
        displayArea.setRows(10);
        rootPanel.add(displayArea, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        a1 = new JButton();
        a1.setAutoscrolls(false);
        a1.setBackground(new Color(-7756085));
        Font a1Font = this.$$$getFont$$$("Fira Code", -1, 14, a1.getFont());
        if (a1Font != null) a1.setFont(a1Font);
        a1.setRequestFocusEnabled(true);
        a1.setSelected(false);
        a1.setText("1");
        rootPanel.add(a1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        calculateButton = new JButton();
        calculateButton.setBackground(new Color(-7756085));
        Font calculateButtonFont = this.$$$getFont$$$("Fira Code", -1, 14, calculateButton.getFont());
        if (calculateButtonFont != null) calculateButton.setFont(calculateButtonFont);
        calculateButton.setText("Calculate");
        rootPanel.add(calculateButton, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        clearButton = new JButton();
        clearButton.setBackground(new Color(-7756085));
        Font clearButtonFont = this.$$$getFont$$$("Fira Code", -1, 14, clearButton.getFont());
        if (clearButtonFont != null) clearButton.setFont(clearButtonFont);
        clearButton.setText("Clear");
        rootPanel.add(clearButton, new com.intellij.uiDesigner.core.GridConstraints(7, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
