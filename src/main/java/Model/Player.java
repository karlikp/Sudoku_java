package Model;

/**
 *
 * @author piter
 */
public class Player {
    private String name;
    private String difficultyLevel;

    // Constructor
    public Player(String name, String difficultyLevel) {
        this.name = name;
        this.difficultyLevel = difficultyLevel;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}

