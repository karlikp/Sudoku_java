package Model;

import java.util.Arrays;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
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

    private final Map<Integer, JButton> buttonMap = new HashMap<>();
    private final Map<Integer, Integer> valueMap = new HashMap<>();
    
    // Table that stores the initial layout of the board
    private final int[][] currentGrid = new int[9][9];


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
        
        System.out.println("Initial grid state:");
        for (int[] row : currentGrid) {
         System.out.println(Arrays.toString(row));
        }
     
        if (value < 0 || value > 9) {
            throw new IllegalArgumentException("Value must be between 1 and 9.");
        }
        
        int row = id / 9;
        int col = id % 9;

        // Validate the value
        if (value != 0 && !isValueValid(row, col, value)) {
            throw new IllegalArgumentException("Value conflicts with Sudoku rules.");
        }
        valueMap.put(id, value);
        currentGrid[row][col] = value;

        // Aktualizacja tekstu na przycisku
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
    private boolean isValueValid(int row, int col, int value) {
        
        
        
        //Avoid if is the same value
        if (currentGrid[row][col] == value) {
            return true;
            }
        
         System.out.println("Setting value: " + value + " at row: " + row + ", col: " + col);
        // Check the row
        boolean isRowValid = IntStream.range(0, 9)
                .noneMatch(c -> currentGrid[row][c] == value);

        // Check the column
        boolean isColValid = IntStream.range(0, 9)
                .noneMatch(r -> currentGrid[r][col] == value);

        // Check the 3x3 block
        int blockRowStart = (row / 3) * 3;
        int blockColStart = (col / 3) * 3;
        boolean isBlockValid = IntStream.range(0, 9)
                .noneMatch(i -> currentGrid[blockRowStart + i / 3][blockColStart + i % 3] == value);

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

    
    int[][] initialGrid = {
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
    public void initCurrentGrid() {
        int rowIndex = 0;
        for (int[] row : initialGrid) {
            int colIndex = 0;
            for (int value : row) {
                currentGrid[rowIndex][colIndex] = value;
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
    public int[][] getCurrentGrid() {
        return currentGrid;
    }

 /**
     * Resets the grid values to 0, preserving the initial layout.
     */
    public void resetGrid() {
        IntStream.range(0, 9).forEach(row ->
                IntStream.range(0, 9).forEach(col -> {
                    int id = row * 9 + col;
                    if (initialGrid[row][col] == 0) {
                        valueMap.put(id, 0);
                        currentGrid[row][col] = 0;
                        JButton button = buttonMap.get(id);
                        if (button != null) {
                            button.setText(""); // Reset the button's text if it's not a fixed value
                        }
                    }
                })
        );
    }
}

