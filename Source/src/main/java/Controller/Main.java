package Controller;

//import Model.Player;
import Model.*;
import View.*;
import lombok.extern.slf4j.Slf4j;

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

@Slf4j  //annotation generate logger which keep information about errors
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
     * 
     * @throws Model.InvalidDifficultyLevelException
     */
    public static void main(String[] args) throws InvalidDifficultyLevelException {
        // Creating model (Player), view (GameView) and controller (GameController)
        Player model = new Player("your_name", DifficultyLevel.CHOOSE_LEVEL);  
        InitFrame view = new InitFrame();     
        GameController controller = new GameController(model, view);  

        // Starting game
        controller.GameInit(args);  // Controller manage interaction between model and view
        
        java.awt.EventQueue.invokeLater(new Runnable() { //java.awt - package: Abstract Window Toolkit
            public void run() {                          //EventQueue - class, invokeLaer - method
                new InitFrame().setVisible(true);
                
            }
        });
    }
}
