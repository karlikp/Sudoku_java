package pl.polsl.Model;

/**
 * A record representing a player in the Sudoku game.
 * <p>
 * This record stores the player's name and their chosen difficulty level.
 * The {@link DifficultyLevel} defines the difficulty of the Sudoku puzzle 
 * assigned to the player.
 * </p>
 * 
 * @param name the name of the player
 * @param level the difficulty level chosen by the player
 * 
 * @author Karol Pitera
 */
public record PlayerRecord(String name, DifficultyLevel level) {}