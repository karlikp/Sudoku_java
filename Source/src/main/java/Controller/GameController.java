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
    final private Player playerModel;
    private SudokuModel sudokuModel;
    private MessageFrame messageView;
    
    /** The view for interacting with the user. */
    final private InitFrame initView;
    private GameplayFrame gameplayView;

    /**
     * Constructs a new GameController with the specified model and view.
     *
     * @param playerModel the Player model containing player data
     * @param view the GameView instance for user interaction
     */
    public GameController(Player playerModel, InitFrame initView, 
            SudokuModel sudokuModel, GameplayFrame gameplayView) {
        
        this.playerModel = playerModel;
        this.sudokuModel = sudokuModel;
        this.initView = initView;
        this.gameplayView = gameplayView;
        
    }
    
    public void handleUserMove(int row, int col, int num) {
        
        System.out.println("Attempting to make a move: ");
        System.out.println("Row: " + row + ", Column: " + col + ", Number: " + num);
        try {
            boolean validMove = sudokuModel.makeMove(row, col, num);
            if (validMove) {
                gameplayView.updateBoard(sudokuModel.getBoard());
            } else {
                new MessageFrame("Invalid move.");
            }
        } catch (SudokuModel.InvalidMoveException ex) {
            new MessageFrame(ex.getMessage());
        }
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
                new MessageFrame("Difficulty level must be an integer. Setting to default level: 1 (Easy).");
                level = 1; // Set default difficulty level
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
        name = initView.downloadName();
        tempLevel = initView.downloadDifficultyLevel();
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
                new MessageFrame("Wrong choice. Set default difficulty level: Easy.");
        }

        // Set model data
        playerModel.setName(name);
        
        try {
            playerModel.setDifficultyLevel(difficultyLevel); // May throw InvalidDifficultyLevelException
        } catch (InvalidDifficultyLevelException e) {
            new MessageFrame("Error: " + e.getMessage());
            try {
                playerModel.setDifficultyLevel("Easy"); // Set default level in case of error
            } catch (InvalidDifficultyLevelException ex) {
                 new MessageFrame("Unexpected error: " + ex.getMessage());
            }
        }
    }
    
     // This method is called by InitFrame to start the game
    public void startGame(String playerName, String difficulty) throws InvalidDifficultyLevelException {
        playerModel.setName(playerName);
        playerModel.setDifficultyLevel(difficulty);
               
        
        // Initialize gameplay frame and pass the controller
        gameplayView = new GameplayFrame();
        gameplayView.setVisible(true); // Show gameplay view
        
        // Optionally, dispose of InitFrame if it was passed from the main application.
        // initView.dispose();
    }
    public String getName(){
        return playerModel.getName();
    }
    
    public String getLevel(){
        return playerModel.getDifficultyLevel();
    }
    /**
     *
     * @param row
     * @param col
     * @param num
     */
    
    
}