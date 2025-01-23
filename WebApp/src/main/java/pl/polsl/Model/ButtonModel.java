package pl.polsl.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.swing.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * SudokuButtonModel class manages the collection of buttons in the Sudoku grid.
 * It provides methods to interact with the buttons in a type-safe manner.
 * 
 * @author Karol Pitera
 */

//@NoArgsConstructor // Lombok: adding default constructor
@Getter // Lombok: generate getters for `buttonMap`
public class ButtonModel {

    /**
     * A map linking button IDs to their corresponding JButton instances.
     */
    private final Map<Integer, JButton> buttonMap = new HashMap<>();
    
     /**
     * A map linking button IDs to their respective integer values.
     */
    private final Map<Integer, Integer> valueMap = new HashMap<>();
    
    /**A list to store the history of moves. */
    private final List<String> history = new ArrayList<>();
    
    
  /**
     * The current state of the Sudoku grid, represented as a list of lists (9x9 grid).
     */
    private final List<List<Integer>> currentGrid = new ArrayList<>(9);
 
    public ButtonModel() {
        setCurrentGrid(); // Initialize currentGrid with the initial grid
    }
    
    /**
     * Record a move in the history.
     * 
     * @param moveDescription a description of the move
     */
    public void recordMove(String moveDescription){
        history.add(moveDescription);
    }
    
    /**
     * Retrieves
     * 
     * @return a list of move descriptions
     */
    public List<String> getHistory(){
        return new ArrayList<>(history); //Return a copy to prevent modification
    }
     
    /**
     * Clears the history of moves
     */
    public void clearHistory(){
        history.clear();
    }
    /**
     * Adds a button to the model.
     * 
     * @param id     the unique ID of the button
     * @param button the JButton to add
     */
    public void setButton(int id, JButton button) {
        buttonMap.put(id, button); //example safe collection
        valueMap.put(id, 0); // Default empty cell
    }

    
        /**
     * Sets the value for a button by its ID.
     *
     * @param id    the unique ID of the button
     * @param value the value to set (1-9 or 0 for empty)
     */
    public void setValue(int id, int value) {

     
        if (value < 0 || value > 9) {
            
            JOptionPane.showMessageDialog(
            null,
            "Value must be between 1 and 9.",
            "Invalid Input",
            JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        int row = id / 9;
        int col = id % 9;
        
         // Ensure currentGrid is properly initialized
        if (currentGrid.isEmpty() || currentGrid.size() < 9 || currentGrid.get(row).size() < 9) {
            throw new IllegalStateException("Grid is not properly initialized.");
        }

        // Validate the value
        if (value != 0 && !isValueValid(row, col, value)) {
            JOptionPane.showMessageDialog(
            null,
            "Value conflicts with Sudoku rules.",
            "Invalid Move",
            JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        // Update valueMap and currentGrid
        valueMap.put(id, value);
        currentGrid.get(row).set(col, value);
        
        // Add move history
        if (value != 0) {
            recordMove("Set value " + value + " at (" + (row + 1) + ", " + (col + 1) + ")");
        }
        
        // Update the button text if the button exists
        JButton button = buttonMap.get(id);
        if (button != null) {
            button.setText(value == 0 ? "" : String.valueOf(value));
        }
    }
    
      /**
     * Validates if the given value can be placed at the specified row and column.
     *
     * @param row   the row index
     * @param col   the column index
     * @param value the value to validate
     * @return true if the value can be placed, false otherwise
     */
    public boolean isValueValid(int row, int col, int value) {
        
        // check correct of index
        if (row < 0 || row >= 9 || col < 0 || col >= 9) {
            return false; // incorrect localization
        }
        
        // Check if the value is within the valid range (1-9)
        if (value < 1 || value > 9) {
            return false; // Invalid value
        }
        
        // Avoid if it's the same value
        if (currentGrid.get(row).get(col) == value) {
            return true;
        }
        
        // IntStream use to validation
        // Check the row
        boolean isRowValid = IntStream.range(0, 9)
                .noneMatch(c -> currentGrid.get(row).get(c) == value);

        // Check the column
        boolean isColValid = IntStream.range(0, 9)
                .noneMatch(r -> currentGrid.get(r).get(col) == value);

        // Check the 3x3 block
        int blockRowStart = (row / 3) * 3;
        int blockColStart = (col / 3) * 3;
        boolean isBlockValid = IntStream.range(0, 9)
                .noneMatch(i -> currentGrid.get(blockRowStart + i / 3).get(blockColStart + i % 3) == value);

        return isRowValid && isColValid && isBlockValid;
    }
 
    /**
     * Retrieves the value for a button by its ID.
     *
     * @param id the unique ID of the button
     * @return the value corresponding to the button
     */
    public int getValue(int id) {
        return valueMap.getOrDefault(id, 0); // Default value 0
    }

     /**
     * The predefined initial Sudoku grid, represented as a list of lists (9x9 grid).
     */
    List<List<Integer>> initialGrid = Arrays.asList(
            Arrays.asList(5, 3, 0, 0, 7, 0, 0, 0, 0),
            Arrays.asList(6, 0, 0, 1, 9, 5, 0, 0, 0),
            Arrays.asList(0, 9, 8, 0, 0, 0, 0, 6, 0),
            Arrays.asList(8, 0, 0, 0, 6, 0, 0, 0, 3),
            Arrays.asList(4, 0, 0, 8, 0, 3, 0, 0, 1),
            Arrays.asList(7, 0, 0, 0, 2, 0, 0, 0, 6),
            Arrays.asList(0, 6, 0, 0, 0, 0, 2, 8, 0),
            Arrays.asList(0, 0, 0, 4, 1, 9, 0, 0, 5),
            Arrays.asList(0, 0, 0, 0, 8, 0, 0, 7, 9)
    );
    
   /**
     * Sets the initial grid, typically used to define the starting Sudoku puzzle.
     */
    public void setCurrentGrid() {
        currentGrid.clear();
        
        // Using the initialGrid data to populate the currentGrid list
        for (int row = 0; row < 9; row++) {
            currentGrid.add(new ArrayList<>(initialGrid.get(row)));
        }
    }

    /**
     * Gets the initial grid for the Sudoku puzzle.
     *
     * @return the initial Sudoku grid
     */
    public List<List<Integer>> getCurrentGrid() {
        return currentGrid;
    }
    
    public List<List<Integer>> getInitialGrid(){
        return initialGrid;
    }
    
    // Setter for valueMap (for unit testing)
    public void setValueMap(Map<Integer, Integer> valueMap) {
        this.valueMap.clear();
        this.valueMap.putAll(valueMap);
    }

    // Setter for buttonMap (for unit testing)
    public void setButtonMap(Map<Integer, JButton> buttonMap) {
        this.buttonMap.clear();
        this.buttonMap.putAll(buttonMap);
    }

    public void setJOptionPane(JOptionPane jOptionPane) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
//    /**
//     * Retrieves the history of operations performed on the Sudoku grid.
//     * 
//     * @return A list of strings describing the operations performed.
//     */
//    public List<String> getHistory() {
//        return history;
//    }
//
//    /**
//     * Clears the history of operations. Useful for resetting the game.
//     */
//    public void clearHistory() {
//        history.clear();
//    }

}

