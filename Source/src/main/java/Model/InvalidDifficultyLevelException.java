
package Model;

/**
 * Exception thrown when an invalid difficulty level is set for a Player.
 * <p>
 * Valid difficulty levels are Easy, Medium, or Hard.
 * </p>
 * 
 * @author Karol Pitera
 * @version 1.0
 */

public class InvalidDifficultyLevelException extends Exception {
    
    /**
     * Constructs a new InvalidDifficultyLevelException with the specified detail message.
     *
     * @param message the detail message explaining the cause of the exception
     */
    public InvalidDifficultyLevelException(String message) {
        super(message);
    }
}
