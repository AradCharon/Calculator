import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JButton addButton, subButton, mulButton, divButton;
    private JButton decButton, equButton, delButton, clrButton, negButton, sqrtButton, powButton, factButton;

    private double num1 = 0, num2 = 0, result = 0;
    private char operator;
    private boolean isNewCalculation = true;

    public Calculator() {
        setTitle("Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(new Color(240, 240, 240));

        // Display
        display = new JTextField();
        display.setBounds(30, 30, 340, 70);
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBackground(new Color(255, 255, 255));
        display.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));
        add(display);

        // Number buttons (0-9)
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 18));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setBackground(new Color(245, 245, 245));
            numberButtons[i].setBorder(BorderFactory.createRaisedBevelBorder());
        }

        // Operation buttons
        addButton = createOperationButton("+", new Color(100, 150, 255));
        subButton = createOperationButton("-", new Color(100, 150, 255));
        mulButton = createOperationButton("×", new Color(100, 150, 255));
        divButton = createOperationButton("÷", new Color(100, 150, 255));
        decButton = createOperationButton(".", new Color(200, 200, 200));
        equButton = createOperationButton("=", new Color(255, 150, 50));
        delButton = createOperationButton("DEL", new Color(255, 100, 100));
        clrButton = createOperationButton("AC", new Color(255, 100, 100));
        negButton = createOperationButton("±", new Color(200, 200, 200));
        sqrtButton = createOperationButton("√", new Color(150, 200, 150));
        powButton = createOperationButton("x^y", new Color(150, 200, 150));
        factButton = createOperationButton("n!", new Color(150, 200, 150));

        functionButtons = new JButton[]{
                addButton, subButton, mulButton, divButton,
                decButton, equButton, delButton, clrButton,
                negButton, sqrtButton, powButton, factButton
        };

        // Button layout
        negButton.setBounds(30, 120, 70, 50);
        sqrtButton.setBounds(110, 120, 70, 50);
        powButton.setBounds(190, 120, 70, 50);
        factButton.setBounds(270, 120, 70, 50);

        clrButton.setBounds(30, 180, 70, 50);
        delButton.setBounds(110, 180, 70, 50);
        divButton.setBounds(190, 180, 70, 50);
        mulButton.setBounds(270, 180, 70, 50);

        numberButtons[7].setBounds(30, 240, 70, 50);
        numberButtons[8].setBounds(110, 240, 70, 50);
        numberButtons[9].setBounds(190, 240, 70, 50);
        subButton.setBounds(270, 240, 70, 50);

        numberButtons[4].setBounds(30, 300, 70, 50);
        numberButtons[5].setBounds(110, 300, 70, 50);
        numberButtons[6].setBounds(190, 300, 70, 50);
        addButton.setBounds(270, 300, 70, 50);

        numberButtons[1].setBounds(30, 360, 70, 50);
        numberButtons[2].setBounds(110, 360, 70, 50);
        numberButtons[3].setBounds(190, 360, 70, 50);
        equButton.setBounds(270, 360, 70, 110);

        numberButtons[0].setBounds(30, 420, 150, 50);
        decButton.setBounds(190, 420, 70, 50);

        // Add components to frame
        add(negButton);
        add(sqrtButton);
        add(powButton);
        add(factButton);
        add(delButton);
        add(clrButton);
        add(divButton);
        add(mulButton);
        add(subButton);
        add(addButton);
        add(equButton);
        add(decButton);

        for (int i = 0; i < 10; i++) {
            add(numberButtons[i]);
        }

        setVisible(true);
    }

    private JButton createOperationButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.addActionListener(this);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setFocusPainted(false);
        return button;
    }

    private long factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                if (isNewCalculation) {
                    display.setText("");
                    isNewCalculation = false;
                }
                display.setText(display.getText() + i);
            }
        }

        if (e.getSource() == decButton) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        }

        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '+';
            display.setText("");
        }

        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '-';
            display.setText("");
        }

        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '*';
            display.setText("");
        }

        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '/';
            display.setText("");
        }

        if (e.getSource() == powButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '^';
            display.setText("");
        }

        if (e.getSource() == factButton) {
            try {
                int num = Integer.parseInt(display.getText());
                if (num >= 0 && num <= 20) { // Limit to prevent overflow
                    result = factorial(num);
                    display.setText(String.valueOf(result));
                    isNewCalculation = true;
                } else {
                    display.setText("Error: 0-20");
                }
            } catch (NumberFormatException ex) {
                display.setText("Error");
            }
        }

        if (e.getSource() == equButton) {
            try {
                num2 = Double.parseDouble(display.getText());

                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            display.setText("Error: Div/0");
                            return;
                        }
                        break;
                    case '^':
                        result = Math.pow(num1, num2);
                        break;
                }

                // Format the result to remove trailing .0 if it's an integer
                if (result == (long) result) {
                    display.setText(String.format("%d", (long) result));
                } else {
                    display.setText(String.valueOf(result));
                }
                isNewCalculation = true;
            } catch (NumberFormatException ex) {
                display.setText("Error");
            }
        }

        if (e.getSource() == clrButton) {
            display.setText("");
            num1 = 0;
            num2 = 0;
            result = 0;
            isNewCalculation = true;
        }

        if (e.getSource() == delButton) {
            String currentText = display.getText();
            if (!currentText.isEmpty()) {
                display.setText(currentText.substring(0, currentText.length() - 1));
            }
        }

        if (e.getSource() == negButton) {
            if (!display.getText().isEmpty()) {
                double temp = Double.parseDouble(display.getText());
                temp *= -1;
                display.setText(String.valueOf(temp));
            }
        }

        if (e.getSource() == sqrtButton) {
            if (!display.getText().isEmpty()) {
                double num = Double.parseDouble(display.getText());
                if (num >= 0) {
                    result = Math.sqrt(num);
                    // Format the result
                    if (result == (long) result) {
                        display.setText(String.format("%d", (long) result));
                    } else {
                        display.setText(String.valueOf(result));
                    }
                    isNewCalculation = true;
                } else {
                    display.setText("Error: √-num");
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new Calculator();
        });
    }
}