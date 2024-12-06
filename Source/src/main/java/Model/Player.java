package Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a player in the game, containing a name and difficulty level.
 * <p>
 * This class provides methods to access and modify the player's name and difficulty level.
 * </p>
 * 
 * @author Karol Pitera
 * @version 1.0
 */

@Getter
@Setter
@ToString(exclude = "playerData") // Możesz wykluczyć playerData z toString


public class Player {
    
    /** The player's data stored in a PlayerRecord. */
    private PlayerRecord playerData;
    
    /**
     * Constructs a new Player with the specified name and difficulty level.
     *
     * @param name the name of the player
     * @param difficultyLevel the difficulty level chosen by the player
     * @throws InvalidDifficultyLevelException if the difficulty level is invalid
     */
    public Player(String name, DifficultyLevel difficultyLevel)  throws InvalidDifficultyLevelException {   
        validateDifficultyLevel(difficultyLevel);
        this.playerData = new PlayerRecord(name, difficultyLevel);
    }
    
    /**
     * Gets the player's name.
     *
     * @return the name of the player
     */
    public String getName() {
        return playerData.name();
    }
    
    public DifficultyLevel getLevel() {
        return playerData.level();
    }

    /**
     * Sets the player's name.
     *
     * @param name the new name of the player
     */
    public void setName(String name) {
        this.playerData = new PlayerRecord(name, playerData.level());
    }

    /**
     * Gets the player's chosen difficulty level.
     *
     * @return the difficulty level of the player as a string
     */
    public String getDifficultyLevel() {
        return playerData.level().getDisplayName();  // Zwrócenie nazwy poziomu trudności
    }

  /**
     * Sets the difficulty level for the player.
     * Throws InvalidDifficultyLevelException if the level is invalid.
     *
     * @param difficultyLevel the difficulty level as a string
     * @throws InvalidDifficultyLevelException if the difficulty level is invalid
     */
    public void setDifficultyLevel(String difficultyLevel) throws InvalidDifficultyLevelException {
        DifficultyLevel level = DifficultyLevel.fromString(difficultyLevel);  // Zamiana na enum
        this.playerData = new PlayerRecord(playerData.name(), level);
    }
    
    /**
     * Validates the difficulty level.
     *
     * @param difficultyLevel the difficulty level to validate
     * @throws InvalidDifficultyLevelException if the difficulty level is invalid
     */
   private void validateDifficultyLevel(DifficultyLevel difficultyLevel) throws InvalidDifficultyLevelException {
    if (difficultyLevel == null) {
        throw new InvalidDifficultyLevelException("Difficulty level cannot be null. Valid levels are EASY, MEDIUM, HARD, or CHOOSE_LEVEL.");
    }
    
    // Validating that the difficulty level is one of the valid enum values
    if (difficultyLevel != DifficultyLevel.EASY && 
        difficultyLevel != DifficultyLevel.MEDIUM && 
        difficultyLevel != DifficultyLevel.HARD && 
        difficultyLevel != DifficultyLevel.CHOOSE_LEVEL) {
        throw new InvalidDifficultyLevelException("Invalid difficulty level: " + difficultyLevel + ". Valid levels are EASY, MEDIUM, HARD, or CHOOSE_LEVEL.");
    }
}
}

