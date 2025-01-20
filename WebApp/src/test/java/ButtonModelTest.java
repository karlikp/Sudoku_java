import pl.polsl.Model.*;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import org.junit.jupiter.api.TestMethodOrder;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;



/**
 * Test class for the ButtonModel class, which handles operations on a Sudoku grid,
 * including value setting and validation logic.
 * 
 * This class provides parameterized tests to verify the behavior of key methods
 * in various scenarios (correct, incorrect, and boundary cases). Mocked button
 * maps are used to isolate and validate grid logic.
 * 
 * @author Karol Pitera
 */

@TestMethodOrder(OrderAnnotation.class)
public class ButtonModelTest {

    static private ButtonModel buttonModel;
    static private Map<Integer, JButton> buttonMapMock;
    static private Map<Integer, Integer> valueMapMock;

    public ButtonModelTest() {
    }

    /**
     * Sets up the test environment by initializing the ButtonModel object with
     * an empty 9x9 grid and empty maps for buttons and values. This ensures that
     * each test starts with a clean state.
     */
    @BeforeEach
    public void setUp() {
        buttonModel = new ButtonModel();

        // Initialize the grid with zeros
        List<List<Integer>> emptyGrid = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            List<Integer> row = new ArrayList<>(Collections.nCopies(9, 0));
            emptyGrid.add(row);
        }
        // Set the current grid to the empty grid
        buttonModel.setCurrentGrid(); // Ustawienie siatki na domyślną
        buttonModel.getCurrentGrid().clear();
        buttonModel.getCurrentGrid().addAll(emptyGrid);

        // Mocking the buttonMap and valueMap
        buttonMapMock = new HashMap<>();
        valueMapMock = new HashMap<>();
        buttonModel.setButtonMap(buttonMapMock);  // Assuming setter exists
        buttonModel.setValueMap(valueMapMock);   // Assuming setter exists
    }

     /**
     * Test method to verify that valid values are correctly set in the grid
     * and the button text is updated accordingly.
     * 
     * @param id    ID of the button being tested
     * @param value Value to be set
     */
    @ParameterizedTest
    @CsvSource({
        "0, 0", 
        "1, 2",
        "8, 9"
    })
    public void testSetValue_ValidValue(int id, int value) {
        // Create the button and add it to the buttonMapMock
        JButton button = new JButton();
        buttonMapMock.put(id, button);

        // Sync buttonMapMock with buttonMap in the ButtonModel
        buttonModel.setButtonMap(buttonMapMock);

        // Call the method to set the value
        buttonModel.setValue(id, value);

        // Check if the valueMap and currentGrid were updated correctly
        int row = id / 9;
        int col = id % 9;
        assertEquals(value, buttonModel.getCurrentGrid().get(row).get(col), "Check if the value is set correctly in the grid.");

        // Check if the button text is updated correctly
        assertEquals(value == 0 ? "" : String.valueOf(value), button.getText(), "Button text should reflect the value.");
    }
    
    /**
     * Test method to ensure that conflicting values are not set in the grid.
     * 
     * @param id1   ID of the first cell
     * @param value1 Value to set in the first cell
     * @param id2   ID of the second cell
     * @param value2 Conflicting value to set in the second cell
     */
    @ParameterizedTest
    @CsvSource({
        "0, 5, 1, 5", // Conflict in the same row
        "0, 5, 9, 5", // Conflict in the same column
        "0, 5, 10, 5"  // Conflict in the same block
    })
    public void testSetValue_ConflictingValue(int id1, int value1, int id2, int value2) {
        // Create buttons and synchronize with buttonMap
        JButton button1 = new JButton();
        JButton button2 = new JButton();
        buttonMapMock.put(id1, button1);
        buttonMapMock.put(id2, button2);
        buttonModel.setButtonMap(buttonMapMock);

        // Set values and test for conflicts
        buttonModel.setValue(id1, value1); // Set initial value
        buttonModel.setValue(id2, value2); // Attempt to set conflicting value

        int row = id2 / 9;
        int col = id2 % 9;
        assertEquals(0, buttonModel.getCurrentGrid().get(row).get(col), "Conflicting value should not be set.");
    }
    
    /**
     * Test method to verify that setting a value for a non-existent button
     * still updates the grid correctly without affecting buttonMap.
     * 
     * @param id    ID of the cell being updated
     * @param value Value to be set in the grid
     */
    @ParameterizedTest
    @CsvSource({
        "0, 5",
        "1, 8",
        "2, 9"
    })
    public void testSetValue_NonExistentButton(int id, int value) {
        // Create an empty buttonMap and synchronize it with buttonModel
        Map<Integer, JButton> emptyButtonMap = new HashMap<>();
        buttonModel.setButtonMap(emptyButtonMap);

        // Attempt to set value
        buttonModel.setValue(id, value);

        int row = id / 9;
        int col = id % 9;

        // Check that the value is set in the grid
        assertEquals(value, buttonModel.getCurrentGrid().get(row).get(col), "Value should still be set in the model.");
        // Verify no button exists in the map
        assertNull(emptyButtonMap.get(id), "No button should exist in the button map.");
    }

    /**
     * Test method to verify the correctness of value validation logic.
     * 
     * @param id       ID of the cell where the initial value is set
     * @param targetId ID of the target cell to validate
     * @param value    Value to validate
     * @param expected Expected result of the validation
     */
    @ParameterizedTest
    @CsvSource({
        // Correct scenarios
        "0, 0, 5, true",  // Same value in the cell
        "8, 8, 9, true",  // Valid placement at the edge

        // Incorrect scenarios (force exceptions or invalid states)
        "0, 1, 5, false", // Conflict in the row
        "0, 9, 5, false", // Conflict in the column
        "0, 10, 5, false", // Conflict in the block

        // Boundary cases
        "0, 0, 10, false",  // Invalid value (out of range)
        "0, 0, 10, false", // Invalid value (out of range)
        "80, 80, 5, true", // Valid placement at the last cell
        "80, 80, 10, false" // Invalid placement with out-of-range value
    })
    public void testIsValueValid(int id, int targetId, int value, boolean expected) {
        // Create a button for the given id and synchronize with buttonModel
        JButton button = new JButton();
        buttonMapMock.put(id, button);
        buttonModel.setButtonMap(buttonMapMock);

        // Set initial value and validate the placement of the next value
        buttonModel.setValue(id, value);

        int row = targetId / 9;
        int col = targetId % 9;
        boolean isValid = buttonModel.isValueValid(row, col, value);

        // Verify the validity of the placement
        assertEquals(expected, isValid, "Check if value placement validity is as expected.");
    }   

}
