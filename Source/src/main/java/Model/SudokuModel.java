package Model;



public class SudokuModel {
    private int[][] board;  // Stores the Sudoku grid values
    private boolean[][] modifiable;  // Indicates which cells can be modified
    private final int SIZE = 9;  // Size of the Sudoku board (9x9)
    private Player player;  // The player object holding information like difficulty

    // Default constructor
    public SudokuModel() {
        board = new int[SIZE][SIZE];
        modifiable = new boolean[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                modifiable[i][j] = true;  // All cells are modifiable by default
            }
        }
    }

    // Constructor with player information
    public SudokuModel(Player player) {
        this();
        this.player = player;
    }

    // Returns the current value at a specific cell
    public int getValue(int row, int col) {
        return board[row][col];
    }

    // Returns the full board
    public int[][] getBoard() {
        return board;
    }

    // Sets the player object
    public void setPlayer(Player player) {
        this.player = player;
    }

    // Returns the current player
    public Player getPlayer() {
        return player;
    }

    // Checks if placing a number at the specified position is valid according to Sudoku rules
    public boolean isValidMove(int row, int col, int value) {
        // Check row
        for (int c = 0; c < SIZE; c++) {
            if (board[row][c] == value) {
                return false;
            }
        }

        // Check column
        for (int r = 0; r < SIZE; r++) {
            if (board[r][col] == value) {
                return false;
            }
        }

        // Check 3x3 sub-grid
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (board[r][c] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    // Places a number at the specified position if valid
    public boolean makeMove(int row, int col, int num) throws InvalidMoveException {
        if (!modifiable[row][col]) {
            throw new InvalidMoveException("This cell is not modifiable.");
        }

        if (isValidMove(row, col, num)) {
            board[row][col] = num;
            return true;
        }
        throw new InvalidMoveException("Invalid move.");
    }

    // Clears the value at a specified cell if the cell is modifiable
    public void clearCell(int row, int col) throws InvalidMoveException {
        if (!modifiable[row][col]) {
            throw new InvalidMoveException("This cell is not modifiable.");
        }
        board[row][col] = 0;
    }

    // Generates a puzzle based on difficulty
    public void generatePuzzle() {
        String difficulty = player.getDifficultyLevel();
        switch (difficulty) {
            case "Easy":
                generateEasyPuzzle();
                break;
            case "Medium":
                generateMediumPuzzle();
                break;
            case "Hard":
                generateHardPuzzle();
                break;
            default:
                generateEasyPuzzle();
                break;
        }
    }

    // Logic for generating a puzzle based on difficulty
    private void generateEasyPuzzle() {
        // Implement the logic for easy puzzle generation (e.g., fewer filled cells)
    }

    private void generateMediumPuzzle() {
        // Implement medium puzzle generation logic
    }

    private void generateHardPuzzle() {
        // Implement hard puzzle generation logic
    }
    
    // Utility method to mark a cell as non-modifiable (for filled cells in generated puzzles)
    public void setCellModifiable(int row, int col, boolean modifiable) {
        this.modifiable[row][col] = modifiable;
    }

    // Exception class for invalid moves
    public static class InvalidMoveException extends Exception {
        public InvalidMoveException(String message) {
            super(message);
        }
    }
}