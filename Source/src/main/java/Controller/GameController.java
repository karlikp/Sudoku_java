package Controller;

import Model.Player;
import Model.*;
import View.*;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import lombok.RequiredArgsConstructor;



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

@RequiredArgsConstructor
public class GameController {
    
    /** The model representing the player data. */
    final private Player model;
    
    /** The view for interacting with the user. */
    final private InitFrame view;
    
    /**
     * Checks if the provided difficulty level argument can be converted to an integer.
     * If the input is invalid (not an integer), sets the level to 1 (Easy) by default.
     *
     * @param arg the difficulty level provided as a string input
     * @return the parsed difficulty level as an integer, or 1 if input is invalid
     */
    public int checkExceptionForLevel(String arg){
    int level;
    if (arg == null || arg.trim().isEmpty()) {
        level = 1; // Domyślny poziom trudności
    } else {
        try {
            level = Integer.parseInt(arg); // Spróbuj przekonwertować na liczbę
        } catch (NumberFormatException e) { // Obsługa błędu, gdy konwersja się nie powiedzie
            JOptionPane.showMessageDialog(null, "Difficulty level must be an integer. Setting to default level: 1 (Easy).");
            level = 1; // Domyślny poziom trudności
        }
    }
    return level;
    }

    /**
     * Initialization the game, handling input for the player's name and difficulty level.
     * <p>
     * If the name and difficulty level are provided as command-line arguments, 
     * they are used; otherwise, the view prompts the user for input. The method 
     * then sets the model data and displays a greeting.
     * </p>
     *
     * @param args command-line arguments (expected: player's name and difficulty 
     * level as an integer)
     */
    public void GameInit(String[] args) {
        
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