package View;

/**
 * The ViewListener interface defines the methods that are used for handling
 * events related to the user interface in the game.
 * This interface allows for actions such as starting the game and navigating back
 * to previous screens.
 * 
 * @author Karol Pitera
 */
public interface ViewListener {
    
    /**
     * This method is called when the user starts the game.
     * It passes the user's name and the selected difficulty level to initiate the game.
     * 
     * @param name the name entered by the user
     * @param difficultyLevel the selected difficulty level for the game
     */
    void onGameStart(String name, String difficultyLevel);
    
    /**
     * This method is called when the user clicks the "Back" button.
     * It typically triggers a navigation action to go back to the previous screen.
     */
    void onBackButtonClicked();
    
}
