import Model.*;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;



/**
 * @author Karol Pitera
 */

@TestMethodOrder(OrderAnnotation.class)
public class ButtonModelTest {

    static private ButtonModel buttonModel;
    static private Map<Integer, JButton> buttonMapMock;
    static private Map<Integer, Integer> valueMapMock;

    public ButtonModelTest() {
    }

    @BeforeAll
    static public void setUp() {
        buttonModel = new ButtonModel();
        buttonModel.setCurrentGrid(); // Ustawienie initialGrid w Sudoku

        // Mocking the buttonMap and valueMap
        buttonMapMock = new HashMap<>();
        valueMapMock = new HashMap<>();
        buttonModel.setButtonMap(buttonMapMock);  // Assuming setter exists
        buttonModel.setValueMap(valueMapMock);   // Assuming setter exists

    }
    
//    @BeforeEach
//    public void setUp() {   
//        buttonModel = new ButtonModel();
//        buttonModel.setCurrentGrid(); // Ustawienie initialGrid w Sudoku
//
//        // Mocking the buttonMap and valueMap
//        buttonMapMock = new HashMap<>();
//        valueMapMock = new HashMap<>();
//        buttonModel.setButtonMap(buttonMapMock);  // Assuming setter exists
//        buttonModel.setValueMap(valueMapMock);   // Assuming setter exists
//    }

    @Test
    void testSetCurrentGrid() {
        // Wykonanie metody initCurrentGrid
        buttonModel.setCurrentGrid();

        // comparison of initial Grid with current Grid
        assertEquals(buttonModel.getInitialGrid(), buttonModel.getCurrentGrid(), "currentGrid should be equal to initialGrid.");

        // check if changes in currentGrid do not affect initialGrid
        buttonModel.getCurrentGrid().get(0).set(1, 0); // change in currentGrid
        buttonModel.getCurrentGrid().get(0).set(5, 0);
        buttonModel.getCurrentGrid().get(0).set(7, 0);
        assertNotEquals(buttonModel.getInitialGrid(), buttonModel.getCurrentGrid(), "currentGrid change shouldn't impact on the initialGrid.");
    }

    @ParameterizedTest
    @CsvSource({
        "0, 0", 
        "1, 2",
        "8, 0"
    })
    public void testSetValue_ValidValue(int id, int value) {
        // Create the button and add it to the buttonMap
        JButton button = new JButton();
        buttonMapMock.put(id, button);

        // Call the method to set the value
        buttonModel.setValue(id, value);

        // Check if the valueMap and currentGrid were updated correctly
        int row = id / 9;
        int col = id % 9;
        assertEquals(value, buttonModel.getCurrentGrid().get(row).get(col), "Check if the value is set correctly in the grid.");

        // Check if the button text is updated correctly
        assertEquals(value == 0 ? "" : String.valueOf(value), button.getText(), "Button text should reflect the value.");
    }

    @ParameterizedTest
    @CsvSource({
        "0, 5, 1, 5", // Conflict in the same row
        "0, 5, 9, 5", // Conflict in the same column
        "0, 5, 10, 5"  // Conflict in the same block
    })
    public void testSetValue_ConflictingValue(int id1, int value1, int id2, int value2) {
        buttonModel.setValue(id1, value1); // Set initial value
        buttonModel.setValue(id2, value2); // Attempt to set conflicting value

        int row = id2 / 9;
        int col = id2 % 9;
        assertEquals(0, buttonModel.getCurrentGrid().get(row).get(col), "Conflicting value should not be set.");
    }

    @ParameterizedTest
    @CsvSource({
        "0, 5",
        "1, 8",
        "2, 9"
    })
    public void testSetValue_NonExistentButton(int id, int value) {
        Map<Integer, JButton> emptyButtonMap = new HashMap<>();
        buttonModel.setButtonMap(emptyButtonMap);

        buttonModel.setValue(id, value);

        int row = id / 9;
        int col = id % 9;
        assertEquals(value, buttonModel.getCurrentGrid().get(row).get(col), "Value should still be set in the model.");
        assertNull(emptyButtonMap.get(id), "No button should exist in the button map.");
    }

    @ParameterizedTest
    @CsvSource({
        "0, 0, 5, true",  // Same value in the cell
        "0, 1, 5, false", // Conflict in the row
        "0, 9, 5, false", // Conflict in the column
        "0, 10, 5, false", // Conflict in the block
        "8, 8, 5, true"   // Valid placement
    })
    public void testIsValueValid(int id, int targetId, int value, boolean expected) {
        buttonModel.setValue(id, value); // Set initial value

        int row = targetId / 9;
        int col = targetId % 9;
        boolean isValid = buttonModel.isValueValid(row, col, value);

        assertEquals(expected, isValid, "Check if value placement validity is as expected.");
    }

    @ParameterizedTest
    @CsvSource({
        "Easy, EASY",
        "Medium, MEDIUM",
        "Hard, HARD",
        "choose_level, CHOOSE_LEVEL"
    })
    public void testFromString_ValidLevels(String input, DifficultyLevel expected) {
        try {
            assertEquals(expected, DifficultyLevel.fromString(input), "Should return correct DifficultyLevel for input " + input);
        } catch (InvalidDifficultyLevelException e) {
            fail("Exception should not be thrown for valid levels.");
        }
    }

    @ParameterizedTest
    @CsvSource({
        "Impossible",
        "Invalid",
        "Unknown"
    })
    public void testFromString_InvalidLevels(String input) {
        assertThrows(InvalidDifficultyLevelException.class, () -> DifficultyLevel.fromString(input),
                "Should throw InvalidDifficultyLevelException for input " + input);
    }
}
