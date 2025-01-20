package pl.polsl.Model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
/**
 * Enum representing the difficulty levels for Sudoku.
 * Provides methods for display and validation.
 * 
 * @author Karol Pitera
 */

@Getter // Lombok: generates getter for the `displayName` field
@RequiredArgsConstructor // Lombok: generates a private final field constructor
public enum DifficultyLevel {
    
    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard"),
    CHOOSE_LEVEL("choose_level");
    private final String displayName;

    /**
     * Converts a string to the corresponding DifficultyLevel.
     *
     * @param level the difficulty level as a string
     * @return the matching DifficultyLevel
     * @throws InvalidDifficultyLevelException if the input does not match any valid level
     */
    public static DifficultyLevel fromString(String level) throws InvalidDifficultyLevelException {
        for (DifficultyLevel difficulty : DifficultyLevel.values()) {
            if (difficulty.displayName.equalsIgnoreCase(level)) {
                return difficulty;
            }
        }
        throw new InvalidDifficultyLevelException(
                "Invalid difficulty level: " + level + ". Valid levels are Easy, Medium, or Hard.");
        
    }
    
}
