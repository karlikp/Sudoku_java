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
@AllArgsConstructor
@ToString

public class Player {
    
     /** The name of the player. */
    private String name;
    
    /** The difficulty level chosen by the player. */
    private String difficultyLevel;

    private PlayerRecord playerData;
    /**
     * Constructs a new Player with the specified name and difficulty level.
     *
     * @param name the name of the player
     * @param difficultyLevel the difficulty level chosen by the player
     */
    public Player(String name, String difficultyLevel) {
        this.name = name;
        this.difficultyLevel = difficultyLevel;
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
        this.name = name;
    }

    /**
     * Gets the player's chosen difficulty level.
     *
     * @return the difficulty level of the player
     */
    public String getDifficultyLevel() {
        return difficultyLevel;
    }

     /**
     * Sets the difficulty level for the player.
     * Throws InvalidDifficultyLevelException if the level is invalid.
     *
     * @param difficultyLevel the difficulty level as a string
     * @throws InvalidDifficultyLevelException if the difficulty level is invalid
     */
    public void setDifficultyLevel(String difficultyLevel) throws InvalidDifficultyLevelException {
        if (!difficultyLevel.equals("Easy") && !difficultyLevel.equals("Medium") && !difficultyLevel.equals("Hard")) {
            throw new InvalidDifficultyLevelException("Invalid difficulty level: " + difficultyLevel + ". Valid levels are Easy, Medium, or Hard.");
        }
        this.difficultyLevel = difficultyLevel;
    }
}

