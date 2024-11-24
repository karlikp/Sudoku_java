package Controller;

import Model.*;
import View.*;


/**
 * The main entry point for the game application, responsible for initializing
 * the MVC components and starting the game.
 * <p>
 * This class creates an instance of the Model (`Player`), the View (`GameView`), 
 * and the Controller (`GameController`). The controller coordinates interactions 
 * between the model and the view.
 * </p>
 * 
 * @author Karol Pitera
 * @version 1.0
 */
public class Main {
    
    /**
     * Main method to launch the game application.
     * <p>
     * Initializes the MVC components and starts the game by calling the 
     * controller's `GameStart` method.
     * </p>
     *
     * @param args command-line arguments passed during the application launch
     *  - first argument is used to set user name
     *  - second argument is used to set difficulty level
     */
    public static void main(String[] args) {
        // Create the model instances
        String placeholderName = "Default Name";  // Placeholder name
        String placeholderDifficulty = "Easy";  // Default difficulty (can be changed later)
        
        Player playerModel = new Player(placeholderName, placeholderDifficulty);  // Create Player with placeholder values
        SudokuModel sudokuModel = new SudokuModel();  // Pass the playerModel to SudokuModel

        // Create the view instances
        InitFrame initView = new InitFrame();  // Create the InitFrame view
        GameplayFrame gameplayView = new GameplayFrame();  // Create the GameplayFrame view

        // Create the controller instance
        GameController controller = new GameController(playerModel, initView, sudokuModel, gameplayView);
        
        // Pass the controller to the InitFrame using the setter
        System.out.println("Kontroler przed przypisaniem: " + controller);
        initView.setController(controller);  // Set the controller after the InitFrame is created
        System.out.println("Kontroler po przypisaniu: " + initView.getController());
        gameplayView.setController(controller);
        // Make the InitFrame visible
        initView.setVisible(true);  // Show the InitFrame to start the process
    }

}
