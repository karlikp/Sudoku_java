package Model;

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

    @NoArgsConstructor // Lombok: adding default constructor
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
    
  /**
     * The current state of the Sudoku grid, represented as a list of lists (9x9 grid).
     */
    private final List<List<Integer>> currentGrid = new ArrayList<>(9);


    /**
     * Adds a button to the model.
     * 
     * @param id     the unique ID of the button
     * @param button the JButton to add
     */
    public void addButton(int id, JButton button) {
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
        valueMap.put(id, value);
        currentGrid.get(row).set(col, value);

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

//    /**
//     * Resets the grid values to 0, preserving the initial layout.
//     * 
//     * <p>
//     * This method clears any user-entered values while keeping
//     * the initial Sudoku puzzle unchanged.
//     * </p>
//     */
//    public void resetGrid() {
//        IntStream.range(0, 9).forEach(row ->           // IntStream to ineration  through each cell
//                IntStream.range(0, 9).forEach(col -> { //for every number in the scope will execute operation in {}
//                    int id = row * 9 + col;
//                     if (initialGrid.get(row).get(col) == 0) {
//                        valueMap.put(id, 0);
//                        currentGrid.get(row).set(col, 0);
//                        JButton button = buttonMap.get(id);
//                        if (button != null) {
//                            button.setText(""); // Reset the button's text if it's not a fixed value
//                        }
//                    }
//                })
//       );
//    }

    public void setJOptionPane(JOptionPane jOptionPane) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

