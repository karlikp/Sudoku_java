import Model.*;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Test;

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
        //assertEquals(buttonModel.getInitialGrid(), buttonModel.getCurrentGrid(), "currentGrid should be equal to initialGrid.");
        
    }
    
@Test
public void testSetValue_ValidValue() {
    int id = 0;
    int value = 0;

    // Create the button and add it to the buttonMap
    JButton button = new JButton();
    buttonMapMock.put(id, button);

    // Call the method to set the value
    buttonModel.setValue(id, value);

    // Check if the valueMap and currentGrid were updated correctly
    assertEquals(value, buttonModel.getCurrentGrid().get(0).get(0));  // Check currentGrid update

    // Check if the button text is empty when value is 0
    assertEquals("", button.getText());  // Button text should be empty when value is 0
}

@Test
public void testSetValue_ZeroValue() {
    int id = 0;
    int value = 2;

    // Create the button and add it to the buttonMap
    JButton button = new JButton();
    buttonMapMock.put(id, button);

    // Call the method to set the value
    buttonModel.setValue(id, value);

    // Check if the valueMap and currentGrid were updated correctly
    assertEquals(value, buttonModel.getCurrentGrid().get(0).get(0));  // Check currentGrid update

    // Check if the button text is empty when value is 0
    assertEquals("", button.getText());  // Button text should still be empty when value is 0
}
}
    

