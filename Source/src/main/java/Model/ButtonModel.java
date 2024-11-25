package Model;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * SudokuButtonModel class manages the collection of buttons in the Sudoku grid.
 * It provides methods to interact with the buttons in a type-safe manner.
 * 
 * @author Karol
 */
public class ButtonModel {

    private final Map<Integer, JButton> buttonMap;

    /**
     * Initializes the SudokuButtonModel.
     */
    public ButtonModel() {
        this.buttonMap = new HashMap<>();
    }

    /**
     * Adds a button to the model.
     * 
     * @param id     the unique ID of the button
     * @param button the JButton to add
     */
    public void addButton(int id, JButton button) {
        buttonMap.put(id, button);
    }

    /**
     * Retrieves a button by its unique ID.
     * 
     * @param id the unique ID of the button
     * @return the JButton corresponding to the given ID
     */
    public JButton getButton(int id) {
        return buttonMap.get(id);
    }

    /**
     * Returns all buttons in the model.
     * 
     * @return a collection of all buttons
     */
    public Map<Integer, JButton> getAllButtons() {
        return buttonMap;
    }
}

