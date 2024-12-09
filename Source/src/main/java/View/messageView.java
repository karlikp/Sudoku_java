package View;

import javax.swing.JOptionPane;

/**
 * The messageView class is responsible for displaying messages to the user, 
 * including error messages, using dialog boxes.
 * 
 * @author Karol Pitera
 */
public class messageView {
  
    /**
     * Displays an error message in a dialog box with an "Error" title.
     * The dialog box will show the provided message to the user.
     * 
     * @param message the error message to be displayed
     */
    public void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
