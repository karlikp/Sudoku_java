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
     */
    public static void main(String[] args) {
        // Creating model (Player), view (GameView) and controller (GameController)
        Player model = new Player("", "");  
        GameView view = new GameView();     
        GameController controller = new GameController(model, view);  

        // Starting game
        controller.GameStart(args);  // Controller manage interaction between model and view
    }
}
