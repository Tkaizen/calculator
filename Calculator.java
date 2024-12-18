import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator {
    // Create frame
    private JFrame frame;
    
    // Create text field to display results
    private JTextField textField;
    
    // Store the operator and operands
    private String operator;
    private double num1, num2, result;
    
    // Constructor
    public Calculator() {
        // Create frame
        frame = new JFrame("Calculator");
        
        // Create text field to display results
        textField = new JTextField();
        textField.setEditable(false);
        
        // Initialize operator
        operator = "";
        
        // Set frame layout
        frame.setLayout(new BorderLayout());
        
        // Add text field to the frame
        frame.add(textField, BorderLayout.NORTH);
        
        // Create the panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4)); // 4x4 grid layout
        
        // Button labels
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };
        
        // Create buttons and add to panel
        for (String label : buttons) {
            JButton button = new JButton(label);
            panel.add(button);
            button.addActionListener(new ButtonClickListener());
        }
        
        // Add the panel with buttons to the frame
        frame.add(panel, BorderLayout.CENTER);
        
        // Set default frame settings
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
    
    // Action listener for button clicks
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            
            if (command.charAt(0) == '=') {
                // Perform calculation
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            textField.setText("Error");
                            return;
                        }
                        break;
                }
                textField.setText(String.valueOf(result));
                operator = "";  // Reset operator
            } else if ("0123456789.".contains(command)) {
                // Append digit to the text field
                textField.setText(textField.getText() + command);
            } else {
                // Handle operator
                if (!operator.isEmpty()) {
                    num1 = Double.parseDouble(textField.getText());
                    textField.setText("");
                }
                operator = command;
            }
        }
    }
    
    // Main method to start the program
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create and display the calculator
                Calculator calc = new Calculator();
                calc.frame.setVisible(true);
            }
        });
    }
}
