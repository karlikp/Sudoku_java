package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MessageFrame displays a custom message in a new window.
 * This is used for showing notifications or errors to the user.
 * 
 * @author Karol
 * @version 1.0
 */
public class MessageFrame extends JDialog {

    /**
     * Constructs a MessageFrame with the specified message.
     * 
     * @param message the message to display in the frame
     */
    public MessageFrame(String message) {
        // Set up the dialog
        setTitle("Message");
        setSize(500, 150);
        setModal(true); // Block user interaction with other windows
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen

        // Create the layout for the dialog
        setLayout(new BorderLayout());

        // Create and add message label to the center of the dialog
        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        add(messageLabel, BorderLayout.CENTER);

        // Create and add OK button to the bottom of the dialog
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dialog when OK is pressed
            }
        });

        // Add the button to a panel and place it at the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Display the dialog
        setVisible(true);
    }
}

