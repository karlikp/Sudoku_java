/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Karol
 */
public enum DifficultyLevel {
    
    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard"),
    CHOOSE_LEVEL("choose_level");
    private String displayName;

    DifficultyLevel(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static DifficultyLevel fromString(String level) throws InvalidDifficultyLevelException {
        for (DifficultyLevel difficulty : DifficultyLevel.values()) {
            if (difficulty.displayName.equalsIgnoreCase(level)) {
                return difficulty;
            }
        }
        throw new InvalidDifficultyLevelException("Invalid difficulty level: " + level + ". Valid levels are Easy, Medium, or Hard.");
    }
    
}
