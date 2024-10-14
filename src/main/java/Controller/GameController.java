package Controller;

import Model.*;
import View.*;



public class GameController {
    final private Player model;
    final private GameView view;

    // Constructor
    public GameController(Player model, GameView view) {
        this.model = model;
        this.view = view;
    }

    // Game logic
    public void GameStart(String[] args) {
        
        String name;
        int level;
        
        // Checking if arguments are passed from the command line
        
        if (args.length >= 2) {
            name = args[0]; // First argument is name
            try {
                level = Integer.parseInt(args[1]); // Second argument is difficulty level
            } catch (NumberFormatException e) {
                System.out.println("Difficulty level must be an integer. Setting to default level: 1 (Easy).");
                level = 1; // Set default difficulty level
            }
        }
        else{
        name = view.downloadName();
        level = view.downloadDifficultyLevel();
        }

        String difficultyLevel;
        switch (level) {
            case 1:
                difficultyLevel = "Easy";
                break;
            case 2:
                difficultyLevel = "Medium";
                break;
            case 3:
                difficultyLevel = "Hard";
                break;
            default:
                difficultyLevel = "Easy";
                System.out.println("Wrong choice. Set default difficulty level: Easy.");
        }

        // Set model data
        model.setName(name);
        model.setDifficultyLevel(difficultyLevel);

        // Show gretting
        view.showGreeting(model.getName(), model.getDifficultyLevel());
    }
}