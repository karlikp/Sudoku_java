package Model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
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
    public Player(String name, String difficultyLevel)  throws InvalidDifficultyLevelException {   
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
     * @return the difficulty level of the player
     */
    public String getDifficultyLevel() {
        return playerData.level();
    }

     /**
     * Sets the difficulty level for the player.
     * Throws InvalidDifficultyLevelException if the level is invalid.
     *
     * @param difficultyLevel the difficulty level as a string
     * @throws InvalidDifficultyLevelException if the difficulty level is invalid
     */
    public void setDifficultyLevel(String difficultyLevel) throws InvalidDifficultyLevelException {
        validateDifficultyLevel(difficultyLevel);
        this.playerData = new PlayerRecord(playerData.name(), difficultyLevel);
    }
    
    /**
     * Validates the difficulty level.
     *
     * @param difficultyLevel the difficulty level to validate
     * @throws InvalidDifficultyLevelException if the difficulty level is invalid
     */
    private void validateDifficultyLevel(String difficultyLevel) throws InvalidDifficultyLevelException {
        if (difficultyLevel == null || difficultyLevel.trim().isEmpty()) {
        throw new InvalidDifficultyLevelException("Difficulty level cannot be null or empty. Valid levels are Easy, Medium, or Hard.");
    }
        if (!difficultyLevel.equals("Easy") && !difficultyLevel.equals("Medium") && !difficultyLevel.equals("Hard") && !difficultyLevel.equals("choose_level")) {
            throw new InvalidDifficultyLevelException("Invalid difficulty level: " + difficultyLevel + ". Valid levels are Easy, Medium, or Hard.");
        }
    }
}

