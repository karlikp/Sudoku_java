package View;

import java.util.Scanner;

/**
 * The GameView class is responsible for interacting with the user, providing methods to
 * capture the player's name and difficulty level, and displaying game messages.
 * <p>
 * This class uses a Scanner to capture user input from the console.
 * </p>
 * 
 * @author Karol Pitera
 * @version 1.0
 */
public class GameView {
    
    /** Scanner to capture user input from the console. */
    private Scanner scanner = new Scanner(System.in);

   /**
     * Prompts the user to enter their name and returns the input.
     *
     * @return the name entered by the user
     */
    public String downloadName() {
        System.out.print("Type in your name: ");
        return scanner.nextLine();
    }

    /**
     * Displays difficulty level options and prompts the user to select one.
     *
     * @return the difficulty level chosen by the user as an integer (1, 2, or 3)
     */
    public String downloadDifficultyLevel() {
        System.out.println("Choose difficulty level:");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        System.out.print("Type in difficulty lavel number (1, 2 lub 3): ");
        return scanner.nextLine();
    }

     /**
     * Displays a greeting message to the player, showing their name and chosen difficulty level.
     *
     * @param name the player's name
     * @param difficultyLevel the chosen difficulty level as a string
     */
    public void showGreeting(String name, String difficultyLevel) {
        System.out.println("\nHello, " + name + "! You chose difficulty level: " + difficultyLevel + ".");
    }
}
