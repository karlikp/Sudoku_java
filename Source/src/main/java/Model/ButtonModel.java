package Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * SudokuButtonModel class manages the collection of buttons in the Sudoku grid.
 * It provides methods to interact with the buttons in a type-safe manner.
 * 
 * @author Karol Pitera
 */

@NoArgsConstructor // Lombok: adding default constructor
@Getter // Lombok: generate getters for `buttonMap`
public class ButtonModel {

    private final Map<Integer, JButton> buttonMap = new HashMap<>();
    private final Map<Integer, Integer> valueMap = new HashMap<>();
    
    // Table that stores the initial layout of the board
    private final int[][] initialGrid = new int[9][9];


    /**
     * Adds a button to the model.
     * 
     * @param id     the unique ID of the button
     * @param button the JButton to add
     */
    public void addButton(int id, JButton button) {
        buttonMap.put(id, button);
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
            throw new IllegalArgumentException("Value must be between 0 and 9.");
        }
        valueMap.put(id, value);

        // Aktualizacja tekstu na przycisku
        JButton button = buttonMap.get(id);
        if (button != null) {
            button.setText(value == 0 ? "" : String.valueOf(value));
        }
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

    
    int[][] grid = {
        {5, 3, 0, 0, 7, 0, 0, 0, 0},
        {6, 0, 0, 1, 9, 5, 0, 0, 0},
        {0, 9, 8, 0, 0, 0, 0, 6, 0},
        {8, 0, 0, 0, 6, 0, 0, 0, 3},
        {4, 0, 0, 8, 0, 3, 0, 0, 1},
        {7, 0, 0, 0, 2, 0, 0, 0, 6},
        {0, 6, 0, 0, 0, 0, 2, 8, 0},
        {0, 0, 0, 4, 1, 9, 0, 0, 5},
        {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };
    
    /**
     * Sets the initial grid, typically used to define the starting Sudoku puzzle.
     *
     * @param grid the initial Sudoku grid to be used
     */
    public void setInitialGrid() {
        int rowIndex = 0;
        for (int[] row : grid) {
            int colIndex = 0;
            for (int value : row) {
                initialGrid[rowIndex][colIndex] = value;
                colIndex++;
            }
            rowIndex++;
        }
    }

    /**
     * Gets the initial grid for the Sudoku puzzle.
     *
     * @return the initial Sudoku grid
     */
    public int[][] getInitialGrid() {
        return initialGrid;
    }

    /**
     * Resets the grid values to 0, preserving the initial layout.
     */
    public void resetGrid() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int id = row * 9 + col;
                if (initialGrid[row][col] == 0) {
                    valueMap.put(id, 0);
                    JButton button = buttonMap.get(id);
                    if (button != null) {
                        button.setText(""); // Reset the button's text if it's not a fixed value
                    }
                }
            }
        }
    }
}

