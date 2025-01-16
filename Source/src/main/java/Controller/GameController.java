package Controller;

import Model.*;
import View.*;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;




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

@Slf4j // Logger for debugging purposes
@RequiredArgsConstructor // Generates a constructor with required fields (final and @NonNull)

public class GameController implements ViewListener{
     
    /** The view representing the gameplay UI. */
    private GameplayFrame gameplayView;
    
     /** The view representing the initialization frame. */
    private InitFrame initFrame;
    
    /** The model representing the player data. */
    private final Player playerModel;
  
    /** The model representing button data and Sudoku grid. */
    @Getter
    private ButtonModel buttonModel;
    
    /**
    * Constructs a GameController with the specified Player and ButtonModel instances.
    * <p>
    * This constructor initializes the controller with the provided models, 
    * which represent the player's data and the state of the Sudoku buttons, respectively.
    * </p>
    *
    * @param playerModel  the model representing player data, including name and difficulty level
    * @param buttonModel  the model managing the state and interactions of the Sudoku grid buttons
    */
    
    public GameController   
        (Player playerModel,ButtonModel buttonModel){
       this.playerModel = playerModel;
       this.buttonModel = buttonModel;
       
    }
    
     /**
     * Handles the event of starting the game.
     *
     * @param name            the name of the player
     * @param difficultyLevel the chosen difficulty level
     */
    @Override
    public void onGameStart(String name, String difficultyLevel) {
        
        this.gameplayView = new GameplayFrame(name, difficultyLevel);
        this.gameplayView.setGameplayActionListener(this);
        gameplayView.setVisible(true);

        setupSudokuGrid();
    }
    
     /**
     * Handles the event when the back button is clicked.
     */
    @Override
    public void onBackButtonClicked() {
        this.initFrame = new InitFrame(this);
        initFrame.setVisible(true);
        gameplayView.dispose(); 
    }

    /**
     * Sets up the 9x9 Sudoku grid with interactive buttons.
     */
    private void setupSudokuGrid() {
    buttonModel = new Model.ButtonModel();
    JButton[][] buttons = new JButton[9][9];

    buttonModel.setCurrentGrid();
    
    for (int row = 0; row < 9; row++) {
        for (int col = 0; col < 9; col++) {
            JButton button = new JButton("");
            button.setPreferredSize(new Dimension(67, 67));
            button.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 20));

            // visual settings
            if ((row >= 3 && row <= 5) || (col >= 3 && col <= 5)) {
                button.setBackground(new Color(211, 211, 211));
            } else {
                button.setBackground(Color.WHITE);
            }
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            // Generation unique ID
            int id = row * 9 + col;
            
            button.setName("cell_" + id);
            button.setToolTipText("Click to enter number");

            // Set init value from model
            int value = buttonModel.getCurrentGrid().get(row).get(col);
            buttonModel.setButton(id, button); 
            buttonModel.setValue(id, value); 

            // Block buttons with init value
            if (value != 0) {
                button.setEnabled(false);
                button.setForeground(Color.BLUE); // Underline init value
            } else {
                button.setToolTipText("Click to enter number");

                // Button action for empty cells
                button.addActionListener(e -> {
                    String input = JOptionPane.showInputDialog("Enter a number (1-9):");
                    if (input != null && input.matches("[1-9]")) {
                        buttonModel.setValue(id, Integer.parseInt(input)); 
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid input! Please enter a number between 1 and 9.");
                    }
                });
            }

            buttons[row][col] = button;
            gameplayView.addToGridPanel(button);
        }
    }
    gameplayView.setViewportView();
    gameplayView.revalidate();
    gameplayView.repaint();
 
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
    if (arg == null || arg.trim().isEmpty()) {
        level = 1; // Default level is easy
    } else {
        try {
            level = Integer.parseInt(arg); 
        } catch (NumberFormatException e) { 
            JOptionPane.showMessageDialog(null, "Difficulty level must be an integer. Setting to default level: 1 (Easy).");
            level = 1; 
        }
    }
    return level;
    }     
}