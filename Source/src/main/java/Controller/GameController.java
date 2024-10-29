package Controller;

import Model.*;
import View.*;



/**
 * The GameController class is responsible for managing the game logic and
 * coordinating interactions between the Model (`Player`) and the View (`GameView`).
 * <p>
 * This class initializes the game, retrieves input from the user or command line, 
 * and sets the player's name and difficulty level.
 * </p>
 * 
 * @author Karol Pitera
 * @version 1.0
 */
public class GameController {
    
    /** The model representing the player data. */
    final private Player model;
    
    /** The view for interacting with the user. */
    final private SudokuFrame view;

    /**
     * Constructs a new GameController with the specified model and view.
     *
     * @param model the Player model containing player data
     * @param view the GameView instance for user interaction
     */
    public GameController(Player model, SudokuFrame view) {
        this.model = model;
        this.view = view;
    }
    
    /**
     * Checks if the provided difficulty level argument can be converted to an integer.
     * If the input is invalid (not an integer), sets the level to 1 (Easy) by default.
     *
     * @param arg the difficulty level provided as a string input
     * @return the parsed difficulty level as an integer, or 1 if input is invalid
     */
    public int checkExceptionForLevel(String arg){
        int level;
        try {
                level = Integer.parseInt(arg); // Second argument is difficulty level
            } catch (NumberFormatException e) {    //Exception 
                System.out.println("Difficulty level must be an integer. Setting to default level: 1 (Easy).");
                level = 1; // Set default difficulty level
            }
        return level;
    }

    /**
     * Starts the game, handling input for the player's name and difficulty level.
     * <p>
     * If the name and difficulty level are provided as command-line arguments, 
     * they are used; otherwise, the view prompts the user for input. The method 
     * then sets the model data and displays a greeting.
     * </p>
     *
     * @param args command-line arguments (expected: player's name and difficulty 
     * level as an integer)
     */
    public void GameStart(String[] args) {
        
        String name;
        String tempLevel;
        int level;
        
        // Checking if arguments are passed from the command line
        
        if (args.length >= 2) {
            name = args[0]; // First argument is name
            level = checkExceptionForLevel(args[1]);
        }
        else{
        // Retrieve input from the view if command-line arguments are missing    
        name = view.downloadName();
        tempLevel = view.downloadDifficultyLevel();
        level = checkExceptionForLevel(tempLevel);
        }
        // Determine difficulty level as a string based on the integer input
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
        
        try {
            model.setDifficultyLevel(difficultyLevel); // May throw InvalidDifficultyLevelException
        } catch (InvalidDifficultyLevelException e) {
            System.out.println("Error: " + e.getMessage());
            try {
                model.setDifficultyLevel("Easy"); // Set default level in case of error
            } catch (InvalidDifficultyLevelException ex) {
                 System.out.println("Unexpected error: " + ex.getMessage());
            }
        }

        // Show gretting
        //view.showGreeting(model.getName(), model.getDifficultyLevel());
    }
}