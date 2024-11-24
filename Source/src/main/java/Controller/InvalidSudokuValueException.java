package Controller;

/**
 * Exception thrown when an invalid value is entered in the Sudoku grid.
 */
public class InvalidSudokuValueException extends RuntimeException {
    public InvalidSudokuValueException(String message) {
        super(message);
    }
}
